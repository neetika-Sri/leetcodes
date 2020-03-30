/**
  * Created by neetikasrivastava on 30/03/20.
  */
object Celebrity {

  def main(args: Array[String]): Unit = {

    val inp: Array[Array[Int]] = Array(
      Array(1, 0, 1, 1, 0, 1),
      Array(0, 1, 0, 1, 1, 1),
      Array(1, 0, 1, 1, 0, 1),
      Array(0, 0, 0, 1, 0, 0),
      Array(0, 1, 1, 1, 1, 0),
      Array(0, 1, 1, 1, 1, 1)
    )
    val noCelebFoundInput: Array[Array[Int]] = Array(
      Array(1, 0, 1, 1, 0, 1),
      Array(0, 1, 0, 1, 1, 1),
      Array(1, 0, 1, 0, 0, 1),
      Array(0, 0, 0, 1, 0, 0),
      Array(0, 1, 1, 1, 1, 0),
      Array(0, 1, 1, 1, 1, 1)
    )

    if (!validateMetrix(inp)) {
      printf("Not a valid Metrix. check Input")
      return
    }

    val c: Int = findCelebrity(inp)

    if (c == 0) {
      println("No Celebrity found")
    } else {
      println("Celebrity is No. " + c + " person")
    }

  }

  def findCelebrity(metrix: Array[Array[Int]]): Int = {
    var canBeCeleb = 0
    var i = 1
    println("A:" + canBeCeleb);
    println("B: " + i);
    while (i < metrix.length) {
      if ((metrix(canBeCeleb)(i) == 0 && metrix(i)(canBeCeleb) == 0)
        || (metrix(canBeCeleb)(i) == 1 && metrix(i)(canBeCeleb) == 1)) {
        // A does not knows B and B does not knows A or knows each other
        // both cant be celeb
        println("Both cant be Celeb")
        canBeCeleb = i + 1
        i = i + 2
      } else if (metrix(canBeCeleb)(i) == 1) {
        // B does not know A
        // B can be celebrity
        println("B can be Celeb")
        canBeCeleb = i
        i = i + 1
      } else {
        // A can be celebrity already assigned
        println("A can be Celeb")
        i = i + 1
      }
      println("A:" + canBeCeleb)
      println("B: " + i)
    }
    if (isCeleb(metrix, canBeCeleb)) {
      return canBeCeleb + 1
    }

    return 0
  }

  def isCeleb(metrix: Array[Array[Int]], celeb: Int): Boolean = {
    for (i <- 0 until metrix.length) {
      if (metrix(i)(celeb) == 0) {
        return false
      }
      if (i != celeb && metrix(celeb)(i) == 1) {
        return false
      }
    }
    return true
  }

  def validateMetrix(metrix: Array[Array[Int]]): Boolean = {
    val l = metrix.length
    metrix.foreach(m => {
      if (m.length != l) {
        return false
      }
    })
    return true
  }

}
