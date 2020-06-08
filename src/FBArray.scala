import scala.collection.immutable.HashMap
import scala.collection.mutable.ArrayBuffer

/**
  * Created by neetikasrivastava on 7/06/20.
  */
object FBArray {

  def main(args: Array[String]) = {
    //testLengthOfLongestSubstring
    // testStockMarket
    testSumEqualsK

  }
  def testLengthOfLongestSubstring = {
    println(lengthOfLongestSubstring("abcabcbb") == 3)
    println(lengthOfLongestSubstring("bbbbb") == 1)
    println(lengthOfLongestSubstring("pwwkew") == 3)
    println(lengthOfLongestSubstring("kewpww") == 4)
    println(lengthOfLongestSubstring("abba") == 2)
  }

  def testStockMarket = {
    val in = List(8,3,7,6,4,9,2)
    println(stockMarketMany(in).sameElements(Array((2,3),(5,6))))
    println(stockMarketOnce(in).equals(5,6))
  }

  def testSumEqualsK = {
    println(sumEqualsK(List(5,9,2,14,2,7,9), 20) == false)
    println(sumEqualsK(List(5,9,2,14,2,7,9,2), 20) == true)
    println(sumEqualsK(List(20,5,9,2,14,2,7,9), 20) == true)
    println(sumEqualsK(List(5,9,2,14,4,7,9), 20) == true)
  }

  def sumEqualsK(in: List[Int], k: Int) : Boolean = {
    var start = 0;
    var sum = 0;
     for(i <- 0 to in.length - 1) {
       sum = sum + in(i)
         while(i < in.length && start < i && sum > k ){
           sum = sum - in(start)
           start = start + 1
         }
       if(sum == k)
         return true
     }
    false
  }

  def stockMarketMany(in: List[Int]): Array[(Int, Int)]  = {
    if(in.length < 1) return Array((0,0))
    val ans = new ArrayBuffer[(Int,Int)]()
    var arrIndex = -1
    var hasStock = false
    for(i <- 0 to in.length - 2){

      if(in(i) > in(i+1) &&  hasStock ){
        hasStock = false
        val buyVal = ans(arrIndex)._1
        ans(arrIndex) = (buyVal, i+1)
      }
      else if(in(i) < in(i+1)  && !hasStock) {
      hasStock = true
      arrIndex = arrIndex + 1
      ans += ((i+1, 0))
      }
    }
    if(hasStock) {
      val buyVal = ans(arrIndex)._1
      ans(arrIndex) = (buyVal, in.length)
    }
    ans.toArray
  }

  def stockMarketOnce(in: Seq[Int]): (Int, Int) = {
    if(in.length < 1) return (0,0)
    var cmin = (0,0)
    var cmax = (0,0)
    var gdiff = 0
    var ans = (0,0)
    var hasStock = false
    for(i <- 0 to in.length - 2){
      if(in(i) > in(i+1) && hasStock){
        hasStock = false
        cmax = (i, in(i))
        val diff = cmax._2 - cmin._2
        if(diff > gdiff){
          gdiff = diff
          ans = (cmin._1+1, cmax._1+1)
        }
      }
      else if(in(i) < in(i+1) && !hasStock){
        hasStock = true
        cmin = (i, in(i))
        cmax = (0,0)
      }
    }
    if(hasStock && in(in.length - 1) - cmin._2 > gdiff){
      ans = (cmin._1+1, in.length - 1+1)
    }
    ans
  }


  def lengthOfLongestSubstring(s: String): Int = {
    if(s.length < 0) return 0
    var currMaxLength = 0
    var globalMaxLength = 0
    var start = 0
    var hm = new HashMap[Char, Int]()
    for((c, index) <- s.toCharArray.zipWithIndex){
      if(hm.get(c) != None){
        start = Math.max(hm(c), start)
        currMaxLength = index - start

      }else {
        currMaxLength = currMaxLength + 1
      }
      globalMaxLength = Math.max(currMaxLength, globalMaxLength)
      hm += (c -> index)
    }
    globalMaxLength
  }

}
