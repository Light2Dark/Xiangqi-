package model

class Timer(private var _timeLimit: Double) {
  private var _running: Boolean = false
  private var currentTime: Double = _timeLimit

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

  // setter for time limit
  def timeLimit(timeLimit: Double) = {
    _timeLimit = timeLimit
    currentTime = timeLimit
  }

  def getTimeLimit() = {
    _timeLimit
  }

//  def timeTaken(): Long = {
//    if (running) {
//      System.nanoTime() - startTime / 1000000
//    } else {
//      (stopTime - startTime) / 1000000
//    }
//  }

  def timeToString(time: Double = currentTime) = {
    // https://www.learnjavacoding.com/convert-seconds-to-hours/
    val timeString = time.toString.split("\\.")
    var totalSeconds: Int = timeString(0).toInt * 60 - 40 + timeString(1).substring(0,2).toInt //(0.5 + currentTime * 60).asInstanceOf[Int]

    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    minutes + ":" + seconds
  }

  def time: String = {
    currentTime = currentTime - 0.01
    timesUp(currentTime)

    // https://www.learnjavacoding.com/convert-seconds-to-hours/
    val timeString = currentTime.toString.split("\\.")
    var totalSeconds: Int = timeString(0).toInt * 60 - 40 + timeString(1).substring(0,2).toInt //(0.5 + currentTime * 60).asInstanceOf[Int]

    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    minutes + ":" + seconds
  }
}