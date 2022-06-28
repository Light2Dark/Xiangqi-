package model

import java.util.concurrent.atomic.AtomicReferenceArray

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{ Duration, FiniteDuration }

//import Unsafe.{ instance ⇒ unsafe }

class Timer(ec: ExecutionContext, wheelShift: Int, _tickDuration: FiniteDuration) {
  import Timer._

  if (wheelShift < 1 || wheelShift > 31)
    throw new IllegalArgumentException("wheelShift must be in the interval [1, 31]")

  private val start = System.nanoTime
  private val tickDuration = _tickDuration.toNanos

  private def wheelSize = 1 << wheelShift
  private val wheelMask = wheelSize - 1
  private val wheel = new AtomicReferenceArray[TaskHolder](wheelSize)

  @volatile private var currentBucket = 0

  def schedule(r: Runnable, delay: FiniteDuration)(implicit ec: ExecutionContext): TimerTask = {
    if (delay <= Duration.Zero) ec.execute(r)
    else {
      // the check above ensures ticks >= 1
      val ticks = ((delay.toNanos + tickDuration - 1) / tickDuration).toInt
      val rounds = (ticks >> wheelShift).toInt

      /*
       * works as follows:
       * - ticks are calculated to be never “too early”
       * - base off of currentBucket, even after that was moved in the meantime
       * - timer thread will swap in Pause, increment currentBucket, swap in null
       * - hence spin on Pause, else normal CAS
       */
      @tailrec
      def rec(t: TaskHolder): TimerTask = {
        val bucket = (currentBucket + ticks) & wheelMask
        val tail = wheel.get(bucket)
        if (tail eq Pause) rec(t)
        else {
          t.next = tail
          if (wheel.compareAndSet(bucket, tail, t)) t
          else rec(t)
        }
      }

      rec(new TaskHolder(r, null, rounds, ec))
    }
    null
  }

  // take one thread out of the proffered execution context
  ec.execute(new Runnable {
    var tick = 0
    @tailrec final def run = {
      val now = System.nanoTime
      val sleepTime = start + tick * tickDuration - now

      if (sleepTime > 0) {

        val sleepMs =
        // see http://www.javamex.com/tutorials/threads/sleep_issues.shtml
          if (Helpers.isWindows) (sleepTime + 4999999) / 10000000 * 10
          else (sleepTime + 999999) / 1000000

        Thread.sleep(sleepMs)

      } else {

        // first get the list of tasks out and turn the wheel
        val bucket = currentBucket
        val tasks = wheel.getAndSet(bucket, Pause)
        currentBucket = (bucket + 1) & wheelMask
        wheel.set(bucket, null)

        // then process the tasks and keep the non-ripe ones in a list
        var last: TaskHolder = null // the last element of the putBack list
        @tailrec def rec1(task: TaskHolder, nonRipe: TaskHolder): TaskHolder = {
          if (task == null) nonRipe
          else if (task.task == null) rec1(task.next, nonRipe)
          else if (task.rounds > 0) {
            task.rounds -= 1
            val next = task.next
            task.next = nonRipe
            if (last == null) last = task
            rec1(next, task)
          } else {
            val t = task.extractTask()
            if (t != null) task.ec.execute(t)
            rec1(task.next, nonRipe)
          }
        }
        val putBack = rec1(tasks, null)

        // finally put back the non-ripe ones, who had their rounds decremented
        @tailrec def rec2() {
          val tail = wheel.get(bucket)
          last.next = tail
          if (!wheel.compareAndSet(bucket, tail, putBack)) rec2()
        }
        if (last != null) rec2()

        // and off to the next tick
        tick += 1
      }
      run
    }
  })

}

object Timer {
  private val taskOffset = unsafe.objectFieldOffset(classOf[TaskHolder].getDeclaredField("task"))
  private val nextOffset = unsafe.objectFieldOffset(classOf[TaskHolder].getDeclaredField("next"))

  private class TaskHolder(@volatile var task: Runnable,
                           @volatile var next: TaskHolder,
                           @volatile var rounds: Int,
                           val ec: ExecutionContext) extends TimerTask {
    @tailrec
    private[akka] final def extractTask(): Runnable = {
      val t = task
      if (unsafe.compareAndSwapObject(this, taskOffset, task, null)) t
      else extractTask()
    }

    def cancel() = extractTask() != null
  }
  // marker object during wheel movement
  private val Pause = new TaskHolder(null, null, 0, null)
}

trait TimerTask {
  def cancel(): Boolean
}
