package model.ChessPieces

import utils.Teams.Team

class Horse(_x: Double, _y: Double, _team: Team, text: String = "傌", var _rowIndex: Int, var _colIndex: Int) extends ChessPiece(_x, _y, _team, text, _rowIndex, _colIndex) {
  def movePiece(moveX: Double, moveY: Double): Boolean = {
    println("horse has moved")
    true
  }
//  def die = println("died")
//  def capture: Unit = println("captured")

}
