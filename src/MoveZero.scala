/**
  * Created by neetikasrivastava on 1/04/20.
  */
object MoveZero {

  def main(args: Array[String]): Unit = {
    val inp: Array[Int] = Array(0, 0, 1, 0, 3, 12)
    val op: Array[Int] = Array(1, 3, 12, 0, 0, 0)
    moveZeroes(inp)
    println()
    inp.foreach(print(_))
  }

  def moveZeroes(nums: Array[Int]): Unit = {
    var zc = 0

    for ((n, i) <- nums.zipWithIndex) {
      if (n == 0) {
        zc = zc + 1
      }
      else if (zc != 0) {
        nums(i - zc) = nums(i)
        nums(i) = 0
      }
    }
  }

}
