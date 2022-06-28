package model.ChessPieces

import utils.Teams.{Team, blackTeam, redTeam}
import utils.Maths._

class Soldier(var _x: Double, var _y: Double, team: Team, private val text: String = "兵") extends ChessPiece(_x, _y, team, text){

  def move(moveX: Double, moveY: Double): Unit = {
    if (team == redTeam) { // move upwards
      val deltaX = moveX - _x
      val deltaY = moveY - _y
      println(deltaX, deltaY)

      if (deltaY > 0 || deltaY < -100) { // dragging downwards or not dragging upwards enough
        println("should not move up")
        return None: Unit
      }

      println("moving up")
      this.layoutY = y + -40
      // println("before y",y)
      y = this.layoutY.value
      _y = y
      // println("after , y")

//      if (moveX > 9.0 || moveX < -9.0) {
//        println("soldier cannot move sideways before the river")
//        this.layoutX = _x
//        this.layoutY = _y
//        return
//      }
//
//      // > 0 means cannot move backwards
//      // red teams move up which is -ve y
//      if (moveY > 0 || moveY < -50) {
//        println("soldier can only move 1 box upwards at a time")
//        this.layoutX = _x
//        this.layoutY = _y
//        return
//      }
//
//      // move in a straight line, clamp it to next box
//      println("moving up")
//      this.translateY = -yJumpValue
    }
  }

}