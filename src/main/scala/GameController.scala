import model.ChessPieces.{ChessPiece, Horse, Soldier}
import scalafx.scene.layout.AnchorPane
import scalafxml.core.macros.sfxml
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.Includes._
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import utils.Teams.{blackTeam, redTeam}
import scalafx.scene.Group

@sfxml
class GameController(private val chessBoard: AnchorPane) {

  val xJump: Double = 44.0 // translation amount when a piece moves across one box
  val yJump: Double = 36.0

  val xTopLeft: Double = 17.0 // coordinates at top left of the board
  val yTopLeft: Double = 32.0

  var pieces: Array[ChessPiece] = Array()
  var player1Turn: Boolean = true // starts with player 1, if false, means player 2's turn

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
  pieces = pieces :+ new Soldier(xTopLeft, yTopLeft + 7 * yJump, redTeam)
  pieces = pieces :+ new Soldier(xTopLeft, yTopLeft + 1 * yJump, blackTeam)

  //  chessBoard.children = Seq(
  //    new Circle() {
  //      layoutX=331.0
  //      layoutY=187.0
  //      radius=30.0
  //      stroke <== when(hover) choose Brown otherwise Red
  //      fill = White
  //    }
  //    new Horse(17.0, 32.0, redTeam),
  //    new Horse(61, 68, redTeam), // x + 44, y + 36
  //    new Horse(149, 140, blackTeam),
  //
  //  )
  chessBoard.children = pieces
}
