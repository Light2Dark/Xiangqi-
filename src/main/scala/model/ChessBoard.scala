package model

/*
ChessBoard is an internal representation of the chessboard
Handles which player's turn
Validates a correct move
Creates jump amount etc.
Handles captures, attacks, checkmates
 */
object ChessBoard {
  val board = Array.fill(10,9)("O") // create board with 10 rows, 9 cols and fill it with O to indicate empty
  board foreach { row => row foreach print; println }

  private var _player1Turn: Boolean = true // starts with player 1, if false, means player 2's turn
  def player1Turn: Boolean = _player1Turn

  // checks if there is an existing piece on this location of the array
  def checkPieceInSpot(row: Int, col: Int): Boolean = {
    false
  }

  def movePiece = {

  }

  def checkRiver() = {}

  def switchTurn = {
    _player1Turn = !_player1Turn
  }


}
