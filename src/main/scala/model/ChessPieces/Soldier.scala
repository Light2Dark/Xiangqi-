package model.ChessPieces

import model.ChessBoard
import utils.Teams.{Team, blackTeam, redTeam}
import utils.Maths._

class Soldier(var _x: Double, var _y: Double, team: Team, val text: String = "兵", _rowIndex: Int, _colIndex: Int) extends ChessPiece(_x, _y, team, text, _rowIndex, _colIndex) {

  def validMovement(deltaX: Double, deltaY: Double): Boolean = {
    if (team == redTeam) { // move upwards
      if (deltaY > 0 || deltaY < -100) {
        println("soldier cannot move down")
        return false
      } else if (deltaY < -100) {
        println("not dragging up enough")
        return false
      }

      return true // satisfies the movement condition

    } else if (team == blackTeam) {
      if (deltaY < 0) { // dragging upwards or not dragging downwards enough
        println("soldier cannot move up")
        return false
      } else if (deltaY < 40) {
        println("not dragging down enough")
        return false
      } else if (deltaY > 80) {
        println("dragging down too much")
        return false
      }

      return true // satisfies movement condition

    } else {
      println("ERROR, supposed to have team")
      false
    }
  }

  // check whether there is obstruction or not
  // if there is, it should return (row, col) of obstructed piece


  def movePiece(deltaX: Double, deltaY: Double): Boolean = {

    if (!validMovement(deltaX, deltaY)) return false // if not valid dragging of piece

    if (team == redTeam) { // move upwards
      if (ChessBoard.obstruction(rowIndex - 1, colIndex) == (0,0)) { // no obstruction
        println("red moving up " + rowIndex, colIndex)
        this.layoutY = y + -40
        y = this.layoutY.value
        _y = y

      } else { // there is obstruction
        // get black team piece to delete
        println("got obstructionss")
        val piece = ChessBoard.pieces.filter(chessPiece => {
          if (chessPiece.colIndex == colIndex && chessPiece.rowIndex == rowIndex - 1) true else false
        })

        ChessBoard.pieces = ChessBoard.pieces.filterNot(chessPiece => { // delete piece
          if (chessPiece.colIndex == colIndex && chessPiece.rowIndex == rowIndex - 1) true else false
        })
      }

      rowIndex = rowIndex - 1
      true

    } else if (team == blackTeam) { // black team goes down
      println("black moving down " + "row: " + rowIndex + " col: " + colIndex)
      this.layoutY = y + 40
      y = this.layoutY.value
      _y = y

      rowIndex = rowIndex + 1 // moving down
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
