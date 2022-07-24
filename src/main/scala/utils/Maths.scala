package utils

object Maths {
  // https://stackoverflow.com/questions/13146511/clip-number-within-range-in-scala - Inspired by code above
  def clamp(x: Double, min: Double, max: Double) = math.max(min, math.min(max, x))

  val xTopLeft: Double = 2.0 // coordinates at top left of the board
  val yTopLeft: Double = 3.0
  val yDrag: Double = 100.0
  val xDrag: Double = 100.0

  val xMove: Double = 45
  val yMove: Double = 44 // 40 40 originally

  val xJumpValue: Double = 45
  def yJumpValue: Double = 44
  def riverJump: Double = 62
}
