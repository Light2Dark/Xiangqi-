package model.ChessPieces

import utils.Teams.Team

class Horse(_x: Double, _y: Double, _team: Team, text: String = "Horse") extends ChessPiece(_x, _y, _team, text) {
  def move(moveX: Double, moveY: Double) = println("horse moving")
//  def die = println("died")
//  def capture: Unit = println("captured")

}
