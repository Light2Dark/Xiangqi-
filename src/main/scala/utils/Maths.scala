package utils

object Maths {
  // https://stackoverflow.com/questions/13146511/clip-number-within-range-in-scala - Inspired by code above
  def clamp(x: Double, min: Double, max: Double) = math.max(min, math.min(max, x))

  val xTopLeft: Double = 10.0 // coordinates at top left of the board
  val yTopLeft: Double = 5.0

  val xJumpValue: Double = 42.8
  def yJumpValue: Double = 41.5
  def riverJump: Double = 62
}
