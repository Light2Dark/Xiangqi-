package model.ChessPieces

import utils.Maths._
import utils.Teams.{Team, redTeam}

import scala.collection.immutable.HashMap
import scala.collection.mutable

class Elephant(_x: Double, _y: Double, team: Team, private val text: String = "相", _rowIndex: Int, _colIndex: Int) extends ChessPiece(x = _x, y = _y, team = team, text=text, rowIndex = _rowIndex, colIndex = _colIndex) {
  xDragBoundary = mutable.HashMap("min" -> 75.0, "max" -> 105.0)
  yDragBoundary = mutable.HashMap("min" -> 75.0, "max" -> 105.0)

  def movePiece(deltaX: Double, deltaY: Double): Boolean = {
    if (!validDragging(deltaX, deltaY)) return false

    if (deltaX < 0) { // moving left
      if (colIndex - 2 < 0) return false // out of bounds (left) check

      if (deltaY > 0) { // moving down
        if (rowIndex + 2 > 9) return false // out of bounds (bottom) check
        if (captureAndMove(colIndex - 2, rowIndex + 2)) {
          this.layoutY = y + yMove * 2
          this.rowIndex = rowIndex + 2
        } else return false

      } else if (deltaY < 0) { // moving up
        if (rowIndex - 2 < 0) return false // out of bounds (top) check
        if (captureAndMove(colIndex -2, rowIndex -2)) {
          this.layoutY = y - yMove * 2
          this.rowIndex = rowIndex - 2
        } else return false
      }
      this.layoutX = x - xMove * 2
      this.colIndex = colIndex - 2 // since moving left
      true

    } else if (deltaX > 0) { // moving right
      if (deltaY > 0) { // moving down
        if (rowIndex + 2 > 9) return false // out of bounds (bottom) check
        if (captureAndMove(colIndex + 2, rowIndex + 2)) {
          this.layoutY = y + yMove * 2
          this.rowIndex = rowIndex + 2
        } else return false

      }  else if (deltaY < 0) { // moving up
        if (rowIndex - 2 < 0) return false
        if (captureAndMove(colIndex + 2, rowIndex - 2)) {
          this.layoutY = y - yMove * 2
          this.rowIndex = rowIndex - 2
        } else return false
      }

      this.layoutX = x + xMove * 2
      this.colIndex = colIndex + 2
      true

    } else {
      println("not moving")
      false
    }
  }

  def validDragging(deltaX: Double, deltaY: Double): Boolean = {
    println(deltaX, deltaY)
    if ((Math.abs(deltaX) < xDragBoundary("max") && Math.abs(deltaX) > xDragBoundary("min")) && (Math.abs(deltaY) < xDragBoundary("max") && Math.abs(deltaY) > yDragBoundary("min"))) {
      return true
    }
    false
  }


}