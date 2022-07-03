package utils

object Maths {
  // https://stackoverflow.com/questions/13146511/clip-number-within-range-in-scala - Inspired by code above
  def clamp(x: Double, min: Double, max: Double) = math.max(min, math.min(max, x))

  def xJumpValue: Double = {
    42.8
  }

  def yJumpValue: Double = {
    41.5
  }

  def riverJump: Double = {
    62
  }
}
