package model.ChessPieces

import javafx.scene.input.MouseEvent
import scalafx.Includes._
import scalafx.scene.input.DragEvent
import scalafx.scene.layout.StackPane
import scalafx.scene.text.Font

import scala.collection.mutable
//import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Circle
import utils.Maths._
import utils.Teams.{Team, blackTeam, redTeam}
import scalafx.scene.text.Text
import model.ChessBoard

abstract class ChessPiece(var x: Double, var y: Double, val team: Team, text: String, var rowIndex: Int, var colIndex: Int) extends StackPane {
  this.layoutY = y
  this.layoutX = x

  private val circle = new Circle()
  circle.radius = 20
  circle.fill = BlanchedAlmond
  circle.strokeWidth = 2.0
  if (team == redTeam) {
    circle.stroke <== when(hover) choose DarkBlue otherwise Red
  } else if (team == blackTeam) {
    circle.stroke <== when(hover) choose DarkGray otherwise Black
  }

  val pieceText = new Text(0, 0, text)
  pieceText.style = "-fx-font: normal bold 20pt cursive"

  this.children.addAll(circle, pieceText)

  def resetPos = {
    this.layoutX = x
    this.layoutY = y
  }

  this.onMouseClicked = (event: Any )=> {
    // to-do: Add points/squares where the piece can move to
  }

  // def cursor_=(v: Cursor): Unit

  this.onMouseReleased = (event: MouseEvent) => {
    // before moving, check if valid

    // reset
    resetPos

    if (correctSide) {
      move(event.getSceneX - 190, event.getSceneY - 110)
    }

  }

  this.onMouseDragged = (event: MouseEvent) => {
    val xPos = event.getSceneX - 190   // need to set offset based on screen size but we will disable resizing for now
    val yPos = event.getSceneY - 110

    val xMove = clamp(xPos, -5, 355)
    val yMove = clamp(yPos, 10, 400)

    if (correctSide) {
      this.relocate(xMove, yMove)
    }
  }

  // check which player is playing and able to move
  def correctSide: Boolean = {
    if ((ChessBoard.player1Turn && team == blackTeam) || (!ChessBoard.player1Turn && team == redTeam)) false else true
  }



  def move(moveX: Double, moveY: Double): Unit = {
    val deltaX = moveX - x
    val deltaY = moveY - y
    if (movePiece(deltaX, deltaY)) {
      endTurn
    }
  }

  // if there is an enemy piece, delete it and return true. If same team's piece, return false
  def captureAndMove(colIndex: Int, rowIndex: Int): Boolean = {
    val piece = ChessBoard.getPiece(colIndex, rowIndex)
    if (piece.isEmpty) return true// no piece on that square

    // must be chess piece
    if (piece.get.team != this.team) {
      ChessBoard.deletePiece(colIndex, rowIndex) // captures the enemy piece
      println("Piece is captured!")
      true
    } else {
      println("Already a piece there")
      false
    }
  }

  def movePiece(deltaX: Double, deltaY: Double): Boolean // return whether piece has been moved successfully
  def validDragging(deltaX: Double, deltaY: Double): Boolean // another abstract method
  var xDragBoundary: mutable.HashMap[String, Double] = mutable.HashMap("min" -> 10 , "max" -> 60) // represents boundary of x-axis this piece can move, min and max
  var yDragBoundary: mutable.HashMap[String, Double] = mutable.HashMap("min" -> 30 , "max" -> 100) // represents boundary of y-axis this piece can move, min and max

  def endTurn: Unit = {
    ChessBoard.switchTurn
  }
}