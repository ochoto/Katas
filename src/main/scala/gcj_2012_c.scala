import scala.io.Source
import java.io.PrintWriter

// GoogleCodeJam 2012 Qual C

package gcj_2012_c {
	case class Case(A: Int, B: Int) {
		val length = A.toString.size
		assert(length == B.toString.size)

		def arrEqualWithOffset(offset: Int, a: String, b: String) =
			(0 until length).forall( i => a((i+offset) % a.size) == b(i))

		def isRecycled(a: String, b: String) = {
				(1 until length).exists( arrEqualWithOffset(_, a, b) )
		}

		def calcNumOfRecycledMs(n: Int): Int = {
			val strN = n.toString
			(for {
				i <- 1 until length
				shifted = strN.drop(i) + strN.take(i)
				shiftedInt = shifted.toInt
				if shiftedInt >= n && shiftedInt <= B
			} yield (shiftedInt)).takeWhile(_ != n).size
		}

		def solve = {
			(for {
				n <- A to B
			} yield calcNumOfRecycledMs(n)).sum
				//s"N:$N, S:$S, P:$P, Ti:${Ti} = ${res}, un: ${unSurprisingHighPass.size}, sur: ${min(surprisable,S)}, low: ${lowPass}, sur: ${surprisable}"
		}
	}

	object Case {
		def create(line: String) = 
			line.split(" ").toList match { case a :: b :: Nil => Case(a.toInt, b.toInt) }
	}

}

object CurrentMain extends App {
	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/2012-C-small-practice.in"

	val lines = Source.fromFile(inputFile).getLines
	val out = new PrintWriter("data/2012-C-small-practice.out" , "UTF-8")

	val numCases = lines.next.toInt
	println(s"There are $numCases cases")

	var cnt = 1
	lines.foreach { (l: String) => 
		out.println(s"Case #$cnt: " + gcj_2012_c.Case.create(l).solve)
		cnt = cnt + 1
	}

	out.close

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

}