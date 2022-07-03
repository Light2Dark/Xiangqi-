package model

import utils.Teams
import utils.Teams.{Team, redTeam}

/*
ChessBoard is an internal representation of the chessboard
Handles which player's turn
Validates a correct move
Creates jump amount etc.
Handles captures, attacks, checkmates
 */

/*
  Each piece is represented by a string. The first part of the string is the piece type, the second part is the team.
  Eg: Hr means Horse in Red team.

  O = Empty
  S = Soldier
  H = Horse
  G = General
  E = Elephant
  C = Cannon
  A = Advisor
  R = Rook

  b = Black team
  r = Red team
 */

object ChessBoard {

  // val boardx = Array.fill(10,9)("O") // create board with 10 rows, 9 cols and fill it with O to indicate empty
  val board: Array[Array[String]] = Array(
    Array("Rb", "Hb", "Eb", "Ab", "Gb", "Ab", "Eb", "Hb", "Rb"),
    Array.fill(10)("O"),
    Array("O", "Cb", "O", "O", "O", "O", "O", "O", "Cb", "O"),
    Array("Sb", "O", "Sb", "O", "Sb", "O", "Sb", "O", "Sb", "O"),
    Array.fill(10)("O"),
    Array.fill(10)("O"),
    Array("Rr", "Hr", "Er", "Ar", "Gr", "Ar", "Er", "Hr", "Rr"),
    Array.fill(10)("O"),
    Array("O", "Cr", "O", "O", "O", "O", "O", "O", "Cr", "O"),
    Array("Sr", "O", "Sr", "O", "Sr", "O", "Sr", "O", "Sr", "O"),
  )
  // board foreach { row => row foreach print; println }

  private var _player1Turn: Boolean = true // starts with player 1, if false, means player 2's turn
  def player1Turn: Boolean = _player1Turn

  // checks if a move to a row and col is valid (no piece of same team)
  def validMove(row: Int, col: Int, currentTeam: Team): Boolean = {
    val pieceOnSquare = board(row)(col)
    var pieceTeam: Team = redTeam // temporarily setting

    if (pieceOnSquare != "O") { // there is a piece on this square
      pieceOnSquare.charAt(1) match {
        case 'r' => pieceTeam = Teams.redTeam
        case 'b' => pieceTeam = Teams.blackTeam
        case _ => println("Error, piece does not have team!")
      }
      if (pieceTeam == currentTeam) false else true // if piece is same team, this is not a valid move

    } else {
      return false // out of bounds or board movement
    }
  }

  def movePiece = {

  }

  def checkRiver() = {}

  def switchTurn = {
    _player1Turn = !_player1Turn
  }


}
