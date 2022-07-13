package model

import scalafx.beans.property.BooleanProperty
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
  var redTimerText: Text = new Text()
  var blackTimerText: Text = new Text()
  // ChessBoard.player1Turn

  val change = BooleanProperty(playerOneTimer.running)

  // update the label time
  def updateTime(team: Team) = {
    val timer = if (team == Teams.redTeam) playerOneTimer else playerTwoTimer
    val text = if (team == Teams.redTeam) redTimerText else blackTimerText

    thread = new Thread(() => {
      while(timer.running) {
        text.text = timer.time
        Thread.sleep(1000) // artificially creating a time interval of 1 second in system time, not most accurate method
      }
    })

    thread.start()
  }

}
