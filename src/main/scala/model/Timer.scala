package model

import scalafx.beans.property.{BooleanProperty, LongProperty, StringProperty}


// code inspired by https://github.com/andyjiang3/chinese-chess/blob/master/GameLogic/Timer.java
class Timer {
  private var startTime: Long = 0
  private var stopTime: Long = 0
  private var _running: Boolean = false
  val timeLimit: Double = 15.00
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