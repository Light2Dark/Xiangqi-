package model.ChessPieces

import utils.Maths._
import utils.Teams.Team

import scala.collection.mutable

class Horse(_x: Double, _y: Double, _team: Team, text: String = "傌", var _rowIndex: Int, var _colIndex: Int) extends ChessPiece(_x, _y, _team, text, _rowIndex, _colIndex) {

  xDragBoundary = mutable.HashMap("min" -> 30, "max" -> 60)
  yDragBoundary = mutable.HashMap("min" -> 70, "max" -> 100)

  def validDragging(deltaX: Double, deltaY: Double): Boolean = {
    if ((Math.abs(deltaY) < yDragBoundary("min")) && (Math.abs(deltaX) < xDragBoundary("min"))) { // assuming player does not drag out of bounds
        println("not dragging enough vertically or horizontally")
        return false
      } else if (Math.abs(deltaX) > xDragBoundary("max") || Math.abs(deltaY) > yDragBoundary("max")) {
      println("dragging too much")
    }

      println("valid movement")
      true // satisfies movement condition
  }

  def movePiece(deltaX: Double, deltaY: Double): Boolean = {
    if (!validDragging(deltaX, deltaY)) return false

    // if no obstruction

    if (deltaY < 0 && deltaX > 0) { // moving upper right
      if (captureAndMove(rowIndex = this.rowIndex - 2, colIndex = this.colIndex + 1)) {
        println(rowIndex - 2, colIndex + 1)
        this.layoutY = y - yMove * 2
        this.layoutX = x + xMove
        this.rowIndex = rowIndex - 2
        this.colIndex = colIndex + 1
      } else {
        return false
      }

    } else if (deltaY < 0 && deltaX < 0) { // moving upper left
      if (captureAndMove(rowIndex = this.rowIndex - 2, colIndex = this.colIndex - 1)) {
        this.layoutY = y - yMove * 2
        this.layoutX = x - xMove
        this.rowIndex = rowIndex - 2
        this.colIndex = colIndex - 1
      } else {
        return false
      }

    } else if (deltaY > 0 && deltaX > 0) { // moving down right
      if (captureAndMove(rowIndex = this.rowIndex + 2, colIndex = this.colIndex + 1)) {
        this.layoutY = y + yMove * 2
        this.layoutX = x + xMove
        this.rowIndex = rowIndex + 2
        this.colIndex = colIndex + 1
      } else return false

    } else if (deltaY > 0 && deltaX < 0) { // moving down left
      if (captureAndMove(rowIndex = this.rowIndex + 2, colIndex = this.colIndex - 1)) {
        this.layoutY = y + yMove * 2
        this.layoutX = x - xMove
        this.rowIndex = rowIndex + 2
        this.colIndex = colIndex - 1
      } else return false

    } else {
      println("incorrect movement")
      return false
    }

    // setting the values up
    y = this.layoutY.value
    x = this.layoutX.value

    true
  }

//  def die = println("died")
//  def capture: Unit = println("captured")

}
