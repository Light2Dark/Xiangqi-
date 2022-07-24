package model.ChessPieces

import utils.Teams.{Team, blackTeam, redTeam}
import utils.Maths._

import scala.collection.mutable

//import scala.collection.immutable.HashMap
import scala.collection.mutable.HashMap

class Soldier(_x: Double, _y: Double, team: Team, val text: String = "兵", _rowIndex: Int, _colIndex: Int) extends ChessPiece(_x, _y, team, text, _rowIndex, _colIndex) {

    var passedRiver: Boolean = false
    xDragBoundary = mutable.HashMap("min" -> 10.0, "max" -> 100.0)
    yDragBoundary = mutable.HashMap("min" -> 10 , "max" -> 60)

    def validDragging(deltaX: Double, deltaY: Double): Boolean = {
    if (team == redTeam) { // move upwards
      if (deltaY > 10) {
        println("soldier cannot move backwards")
        return false
      }

      if (!passedRiver) {
        if (deltaY > - yDragBoundary("max")) return true else return false
      } else {
        if (deltaY > - yDragBoundary("max") || Math.abs(deltaX) < xDragBoundary("max")) return true else return false
      }

      true // satisfies the movement condition

    } else if (team == blackTeam) {
      if (deltaY < -10) { // dragging upwards or not dragging downwards enough
        println("soldier cannot move up")
        return false
      }

      if (!passedRiver) {
        if (deltaY < 40) {
          println("not dragging down enough")
          return false
        } else if (deltaY > 80) {
          println("dragging down too much")
          return false
        }
      } else {
        if (deltaY < 80 && deltaY > 40 || Math.abs(deltaX) < xDragBoundary("max")) return true else return false
      }

      true // satisfies movement condition

    } else {
      println("ERROR, supposed to have team")
      false
    }
  }

  // check whether there is obstruction or not
  // if there is, it should return (row, col) of obstructed piece

  def moveLeftRight(deltaX: Double): Boolean = {
    if (deltaX > 40) { // moving right
      if (captureAndMove(colIndex + 1, rowIndex)) {
        colIndex = colIndex + 1
        this.layoutX = x + xMove
        true
      } else false

    } else if (deltaX < -40) { // moving left
      if (captureAndMove(colIndex - 1, rowIndex)) {
        colIndex = colIndex - 1
        this.layoutX = x - xMove
        true
      } else false
    } else false
  }


  def movePiece(deltaX: Double, deltaY: Double): Boolean = {

    if (!validDragging(deltaX, deltaY)) return false // if not valid dragging of piece

    if (team == redTeam) {
      if (passedRiver) {
        if (!moveLeftRight(deltaX)) { // not moving left or right
          if (deltaY < 20) { // moving upwards
            if (captureAndMove(colIndex, rowIndex - 1)) {
              rowIndex -= 1
              this.layoutY = y - yMove
            } else return false
          } else return false
        }

      } else if (!passedRiver) {
        if (deltaY < 0) { // moving upwards
          if (captureAndMove(colIndex, rowIndex - 1)) {
            rowIndex -= 1
            if (rowIndex == 4) passedRiver = true
            this.layoutY = y - yMove
          } else return false
        } else return false
      }

      y = this.layoutY.value
      x = this.layoutX.value
      true

    } else if (team == blackTeam) { // black team goes down
      if (passedRiver) {
        if (!moveLeftRight(deltaX)) {
          if (deltaY > 0) { // moving downwards
            if (captureAndMove(colIndex, rowIndex + 1)) {
              rowIndex += 1
              this.layoutY = y + yMove
            } else return false
          } else return false
        }

      } else if (!passedRiver) {
        if (deltaY > 0) { // moving downwards
          if (captureAndMove(colIndex, rowIndex + 1)) {
            println("moving")
            rowIndex += 1
            if (rowIndex == 5) passedRiver = true
            this.layoutY = y + yMove
          } else return false
        } else return false
      }

      y = this.layoutY.value
      x = this.layoutX.value
      true

    } else {
      println("ERROR, supposed to have team")
      false
    }
  }

//  override def move(moveX: Double, moveY: Double): Unit = {
//    if (team == redTeam) { // move upwards
//      val deltaX = moveX - _x
//      val deltaY = moveY - _y
//      println(deltaX, deltaY)
//
//      if (deltaY > 0 || deltaY < -100) { // dragging downwards or not dragging upwards enough
//        println("should not move up")
//        return None: Unit
//      }
//
//      println("moving up")
//      this.layoutY = y + -40
//      // println("before y",y)
//      y = this.layoutY.value
//      _y = y
//      // println("after , y")
//
////      if (moveX > 9.0 || moveX < -9.0) {
////        println("soldier cannot move sideways before the river")
////        this.layoutX = _x
////        this.layoutY = _y
////        return
////      }
////
////      // > 0 means cannot move backwards
////      // red teams move up which is -ve y
////      if (moveY > 0 || moveY < -50) {
////        println("soldier can only move 1 box upwards at a time")
////        this.layoutX = _x
////        this.layoutY = _y
////        return
////      }
////
////      // move in a straight line, clamp it to next box
////      println("moving up")
////      this.translateY = -yJumpValue
//    }
//  }

}
