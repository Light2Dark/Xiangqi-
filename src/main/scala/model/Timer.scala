package model

// code inspired by https://github.com/andyjiang3/chinese-chess/blob/master/GameLogic/Timer.java
class Timer(val timeLimit: Double) {
  private var _running: Boolean = false
  private var currentTime: Double = timeLimit

  def running: Boolean = _running // getter

  def startTimer(): Unit = {
    // startTime = System.nanoTime()
    _running = true
  }

  def stopTimer(): Unit = {
    // stopTime = System.nanoTime()
    _running = false
  }

  def timesUp(time: Double) = {
    if (time <= 0) {
      println("TIMES UPP! You have lost")
    }
  }

//  def timeTaken(): Long = {
//    if (running) {
//      System.nanoTime() - startTime / 1000000
//    } else {
//      (stopTime - startTime) / 1000000
//    }
//  }

  def time: String = {
    currentTime = currentTime - 0.01
    timesUp(currentTime)

    // https://www.learnjavacoding.com/convert-seconds-to-hours/
    val timeString = currentTime.toString.split("\\.")
    var totalSeconds: Int = timeString(0).toInt * 60 + timeString(1).substring(0,2).toInt //(0.5 + currentTime * 60).asInstanceOf[Int]

    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    minutes + ":" + seconds
  }
}