/**
  * Created by neetikasrivastava on 16/03/20.
  */
object ProductOfArray {

 def main(args: Array[String]):Unit = {

   println("code started");
   val ipArr: Array[Int] = Array(1,2,3,4);
   val result = productExceptSelf(ipArr);
   result.foreach((r) => {
     println(r);
   })

 }

  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    var temp:Int  = 1;
    var outputArr : Array[Int] = new Array[Int](nums.length);

    for (i <- 0 until nums.length) {

      if(i == 0) {
        outputArr(i) = 1;
      }
      else {
        outputArr(i) = outputArr(i-1) * temp;
      }
      temp = nums(i);
    }

    for (i <- 0 until nums.length) {

      if(i != 0) {

        outputArr(nums.length - i -1) = outputArr(nums.length - i -1) * temp;
        temp = nums(nums.length - i -1) * temp;

      }

    }

    return outputArr
  }

}
