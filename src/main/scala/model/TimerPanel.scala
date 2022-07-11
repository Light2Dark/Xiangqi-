package model

import scalafx.beans.property.StringProperty
import scalafx.scene.text.Text
import utils.Teams
import utils.Teams.Team

// handles time conversion to string
// return string property for game controller to bind to
// use threads to prevent blocking / allow async operations

object TimerPanel extends Thread {
  var thread = new Thread()
  val playerOneTimer: Timer = ChessBoard.timerPlayerOne
  val playerTwoTimer: Timer = ChessBoard.timerPlayerTwo
  // ChessBoard.player1Turn

  // update the label time
  def updateTime(text: Text, team: Team) = {
    val timer = if (team == Teams.redTeam) playerOneTimer else playerTwoTimer

    thread = new Thread(() => {
      while(timer.running) {
        text.text = timer.time
        Thread.sleep(1000) // artificially creating a time interval of 1 second in system time, not most accurate method
      }
    })

    thread.start()
  }

}
