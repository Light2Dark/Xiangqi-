package model.ChessPieces

import utils.Teams.Team

class General(_x: Double, _y: Double, team: Team, private val text: String = "帥", _rowIndex: Int, _colIndex: Int) extends ChessPiece(x = _x, y = _y, team = team, text=text, rowIndex = _rowIndex, colIndex = _colIndex) {
  def movePiece(deltaX: Double, deltaY: Double): Boolean = {
    false
  }
}
