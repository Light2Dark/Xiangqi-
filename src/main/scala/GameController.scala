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

@sfxml
class GameController(private val chessBoard: AnchorPane) {

  val xTopLeft: Double = 10.0 // coordinates at top left of the board
  val yTopLeft: Double = 5.0

  private var pieces: Array[ChessPiece] = Array() // use array because random access is faster
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

  // red pieces, bottom side
  // ROW INDEX and COL INDEX so that we can track the chess piece position and delete, move it etc.
  // CHess board can iterate through pieces instead of having internal representation of pieces
  pieces = pieces ++ Array(
    new Soldier(xTopLeft + 0 * xJumpValue, yTopLeft + riverJump + 5 * yJumpValue, redTeam, _rowIndex = 3, _colIndex = 0),
    new Soldier(xTopLeft + 2 * xJumpValue, yTopLeft + riverJump + 5 * yJumpValue, redTeam),
    new Soldier(xTopLeft + 4 * xJumpValue, yTopLeft + riverJump + 5 * yJumpValue, redTeam),
    new Soldier(xTopLeft + 6 * xJumpValue, yTopLeft + riverJump + 5 * yJumpValue, redTeam),
    new Soldier(xTopLeft + 8 * xJumpValue, yTopLeft + riverJump + 5 * yJumpValue, redTeam)
  )

  // black pieces, top side
  pieces = pieces ++ Array(
    new Soldier(xTopLeft + 0 * xJumpValue, yTopLeft + 2 * yJumpValue, blackTeam),
    new Soldier(xTopLeft + 2 * xJumpValue, yTopLeft + 2 * yJumpValue, blackTeam),
    new Soldier(xTopLeft + 4 * xJumpValue, yTopLeft + 2 * yJumpValue, blackTeam),
    new Soldier(xTopLeft + 6 * xJumpValue, yTopLeft + 2 * yJumpValue, blackTeam),
    new Soldier(xTopLeft + 8 * xJumpValue, yTopLeft + 2 * yJumpValue, blackTeam)
  )

  for (row <- pieces) {

  }
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
