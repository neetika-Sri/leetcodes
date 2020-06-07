import scala.collection.immutable.HashMap

/**
  * Created by neetikasrivastava on 7/06/20.
  */
object FBArray {

  def main(args: Array[String]) = {
    testLengthOfLongestSubstring

  }
  def testLengthOfLongestSubstring = {
    println(lengthOfLongestSubstring("abcabcbb") == 3)
    println(lengthOfLongestSubstring("bbbbb") == 1)
    println(lengthOfLongestSubstring("pwwkew") == 3)
    println(lengthOfLongestSubstring("kewpww") == 4)
    println(lengthOfLongestSubstring("abba") == 2)
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
