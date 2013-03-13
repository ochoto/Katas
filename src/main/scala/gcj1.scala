import scala.io.Source

// GoogleCodeJamAfrica2010Qual

case class Store(credit: Int, numItemsInStore: Int, prices: List[Int]) {
	println(s"New store with credit [$credit], Items: [$numItemsInStore], Prices: ${prices}")
}

object gcj1 extends App {
	// println("args.size = " + args.size)
	// args.foreach(println)

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/A-small-practice.in"

	val listOfStores = readStores(inputFile)

	def readStores(fromFile: String): List[Store] = {
		def readStoreList(n: Int, lines: Iterator[String], list: List[Store]): List[Store] = {
			if (n==0) {
				list
			}
			else {
				val credit = lines.next.toInt
				val items  = lines.next.toInt
				val prices = lines.next.toString.split(" ").toList.map(_.toInt)
				val store = Store(credit,items, prices)
				readStoreList(n-1,lines, list ++ List(store))
			}
		}

		val lines = Source.fromFile(inputFile).getLines
		val numOfCases = lines.next.toInt
		println("There are " + numOfCases + " cases in input file " + inputFile)
		readStoreList(numOfCases,lines,Nil)
	}

}