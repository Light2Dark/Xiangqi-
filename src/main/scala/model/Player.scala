package model

class Player(var name: String) {
  def this() = {
    this("Player")
  }

  var checkmateStatus: Boolean = false
  var timeElapsed: Long = 0
}
