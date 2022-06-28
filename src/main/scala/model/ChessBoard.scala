package model

//import scalafx.scene.layout.AnchorPane
//
//class ChessBoard extends AnchorPane{
//  children = Seq(
//
//  )
//}

/*
ChessBoard is an internal representation of the chessboard
 */
class ChessBoard {
  val board = Array.fill(10,9)("O") // create board with 10 rows, 9 cols and fill it with O to indicate empty
  board foreach { row => row foreach print; println }

  // checks if there is an existing piece on this location of the array
  def checkPieceInSpot(row: Int, col: Int): Boolean = {
    false
  }

  def movePiece = {

  }

  def checkRiver() = {}


}
