import scala.io.Source

// GoogleCodeJamAfrica2010Qual

case class Store(credit: Int, numItemsInStore: Int, prices: List[Int]) {
	//println(s"New store with credit [$credit], Items: [$numItemsInStore], Prices: ${prices}")

	def solve = {
		val indexedPrices = (1 to prices.size+1) zip prices
		(
			for {
				i1 <- indexedPrices
				i2 <- indexedPrices
				if i1._1 < i2._1
				if i1._2 + i2._2 == credit
			} yield (i1._1, i2._1)
		).head
	}
}

object gcj1 extends App {
	// println("args.size = " + args.size)
	// args.foreach(println)

	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/A-small-practice.in"

	val listOfStores = readStores(inputFile)
	val solutions = listOfStores.map(_.solve)
	val indexedSolutions = (1 to solutions.size+1) zip solutions
	indexedSolutions.foreach { _ match {
			case (cnt,(idx1,idx2)) => println(s"Case #$cnt: $idx1 $idx2")
		}
	}

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

	def readStores(fromFile: String): List[Store] = {
		def readStoreList(n: Int, lines: Iterator[String], list: List[Store]): List[Store] = {
			if (n==0) {
				list
			}
			else {
				val credit = lines.next.toInt
				val items  = lines.next.toInt
				val prices = lines.next.toString.split(" ").toList.map(_.toInt)
				val store = Store(credit, items, prices)
				readStoreList(n-1, lines, list :+ store)
			}
		}

		val lines = Source.fromFile(inputFile).getLines
		val numOfCases = lines.next.toInt
		println("There are " + numOfCases + " cases in input file " + inputFile)
		readStoreList(numOfCases,lines,Nil)
	}

}