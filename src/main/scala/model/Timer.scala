package model


// code inspired by https://github.com/andyjiang3/chinese-chess/blob/master/GameLogic/Timer.java
class Timer {
  var startTime: Long = 0
  var stopTime: Long = 0
  private var _running: Boolean = false

  def running: Boolean = _running // getter

  def startTimer(): Unit = {
    startTime = System.nanoTime()
    _running = true
  }

  def stopTimer(): Unit = {
    stopTime = System.nanoTime()
    _running = false
  }

  def timeTaken(): Long = {
    if (running == true) {
      return System.nanoTime() - startTime / 1000000
    } else {
      return (stopTime - startTime) / 1000000
    }
  }
}