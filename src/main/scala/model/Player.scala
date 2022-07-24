package model

class Player(var name: String, private var timeLimit: Double) {
  def this() = {
    this("Player", 15.0) // defaults to 15 minutes
  }

  def this(name: String) = {
    this(name, 15.0)
  }

  val timer: Timer = new Timer(timeLimit)

//  var checkmateStatus: Boolean = false
}
