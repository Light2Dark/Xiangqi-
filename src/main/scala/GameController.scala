import model.ChessPieces.{ChessPiece, Horse, Soldier}
import scalafx.scene.layout.AnchorPane
import scalafxml.core.macros.sfxml
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.Includes._
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import utils.Teams.{blackTeam, redTeam}
import utils.Maths._
import scalafx.scene.Group
import model.{ChessBoard, Player, Timer, TimerPanel}
import scalafx.scene.text.Text

@sfxml
class GameController(private val chessBoard: AnchorPane, private val playerOneName: Text, private val playerTwoName: Text, val redTimer: Text, val blackTimer: Text) {

  val playerOneTimer: Timer = ChessBoard.timerPlayerOne
  val playerTwoTimer: Timer = ChessBoard.timerPlayerTwo

  playerOneName.text.value = ChessBoard.playerOne.name
  playerTwoName.text.value = ChessBoard.playerTwo.name

  // redTimer.text <== playerOneTimer.timeString()
  playerOneTimer.startTimer()

  if (ChessBoard.player1Turn) {
    TimerPanel.updateTime(redTimer, redTeam)
  }


  // placing all chess pieces
  chessBoard.children = ChessBoard.pieces


  // audio
//  val audio = new Media(getClass.getResource("../musicResource/battlechess.mp3").toURI.toString)
//  val musicPlayer = new MediaPlayer(media = audio)
//  musicPlayer.setCycleCount(MediaPlayer.Indefinite)
//  musicPlayer.play()
//  for (i <- 0 to 8) {
//    val piece = new Horse(xTopLeft + (i * xJump) + i, yTopLeft, redTeam)
//    pieces = pieces :+ piece
//  }
//
//  for (i <- 0 to 9) {
//    val piece = new Soldier(xTopLeft, yTopLeft + (i * yJump) + (i * 3.5), redTeam)
//    pieces = pieces :+ piece
//  }
}
