package model.ChessPieces

import utils.Teams.Team

class Horse(_x: Double, _y: Double, _team: Team, text: String = "Horse") extends ChessPiece(_x, _y, _team, text) {
  def movePiece(moveX: Double, moveY: Double): Boolean = {
    println("horse has moved")
    true
  }
//  def die = println("died")
//  def capture: Unit = println("captured")

}
