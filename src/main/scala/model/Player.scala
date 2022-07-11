package model

class Player(var name: String) {
  def this() = {
    this("Player")
  }

  var checkmateStatus: Boolean = false
  var timeElapsed: Long = 0

  // inspired from https://github.com/andyjiang3/chinese-chess/blob/master/GameLogic/Player.java
  val timer = new Timer()


}
