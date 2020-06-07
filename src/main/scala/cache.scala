import scala.collection.mutable

class CacheMap[K,V](cacheSize: Int)  {
	var cacheMap = new mutable.LinkedHashMap[K,V]()

	def addElement(key: K, value:V): Unit = {
		cacheMap.synchronized {
			if (cacheMap.size >= cacheSize) {
				removeFirst()
			}
			cacheMap.put(key, value)
		}
	}

	def removeFirst(): Unit = {
		if(cacheMap.nonEmpty){
			cacheMap.synchronized{
				cacheMap = cacheMap.drop(1)
			}
		}
	}

	def get(key:K): Option[V] = {
		cacheMap.synchronized {
			val value = cacheMap.remove(key)
			if (value.isDefined) {
				cacheMap.put(key, value.get)
			}
			value
		}
	}



	def show():Unit = {
		for (a <- cacheMap.keys){
			printf("key: %s, value: %s ",a.toString, cacheMap(a).toString)
		}
	}
}

class DBMap[K,V] {
	var dbMap = new mutable.HashMap[K,V]()

	def add(key: K, value: V): Option[V] ={
		dbMap.synchronized{
			dbMap.put(key, value)
		}
	}

	def get(key:K) : Option[V] = {
		dbMap.get(key)
	}

	def show():Unit = {
		for (a <- dbMap.keys){
			printf("key: %s , value: %s",a.toString, dbMap(a).toString)
		}
	}
}

object Cache {
	val db = new DBMap[Int, Int]()
	val cache = new CacheMap[Int, Int](10)

	def main(args: Array[String]): Unit = {

		fillDb(db,100)
		db.show()
		getElement(3)
		println("______________________")
		cache.show()
		getElement(5)
		println("______________________")
		cache.show()
		getElement(6)
		println("______________________")
		cache.show()
		getElement(8)
		println("______________________")
		cache.show()
		getElement(77)
		println("______________________")
		cache.show()
		getElement(21)
		println("______________________")
		cache.show()
		getElement(3)
		println("______________________")
		cache.show()
		getElement(3)
		println("______________________")
		cache.show()
		getElement(31)
		println("______________________")
		cache.show()
		getElement(32)
		println("______________________")
		cache.show()
		getElement(34)
		println("______________________")
		cache.show()
		getElement(35)
		println("______________________")
		cache.show()
		getElement(23)
		println("______________________")
		cache.show()
		getElement(43)
		println("______________________")
		cache.show()
		getElement(109)
		println("______________________")
		cache.show()


	}
	def fillDb(db:DBMap[Int, Int], size: Int): Unit = {

		for(i <- 1 to size){
			val r = scala.util.Random
			db.add(i,r.nextInt(1000))
		}
	}

	def getElement(key: Int): Option[Int] ={
		var value = cache.get(key)
		if(value.isEmpty){
			value = db.get(key)
			if(value.isDefined){
				cache.addElement(key, value.get)
			}
		}
		value
	}
}