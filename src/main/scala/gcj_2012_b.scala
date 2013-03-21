import scala.io.Source
import java.io.PrintWriter

// GoogleCodeJam 2012 Qual B

case class Case(N: Int, S: Int, P: Int, Ti: List[Int]) {
	private def min(a: Int, b: Int)  = if (a<=b) a else b
	def solve = {
			assert(N == Ti.size)

			val (unSurprisingHighPass,lowPass) = Ti.partition { t =>
				(t/3 >= P) || (t/3 == P-1 && t%3 > 0)
			}

			val surprisable =	if (S>0)
									lowPass.filter { t =>
										val div = t / 3
										val rem = t % 3
										(t>1 && t<29) &&( 
											(rem == 0 && div+1 >= P) ||
											(rem == 2 && div+2 >= P)
										) 
									}.size
							  	else 0

			val res = unSurprisingHighPass.size + min(surprisable,S)

			//s"N:$N, S:$S, P:$P, Ti:${Ti} = ${res}, un: ${unSurprisingHighPass.size}, sur: ${min(surprisable,S)}, low: ${lowPass}, sur: ${surprisable}"

			res
	}
}

object Case {
	def create(line: String) = 
		line.split(" ").toList match { case n :: s :: p :: ti => Case(n.toInt, s.toInt, p.toInt, ti.map(_.toInt)) }
}

object gcj_2012_b extends App {
	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/2012-B-small-practice.in"

	val lines = Source.fromFile(inputFile).getLines
	val out = new PrintWriter("data/2012-B-small-practice.out" , "UTF-8")

	val numCases = lines.next.toInt
	println(s"There are $numCases cases")

	var cnt = 1
	lines.foreach { (l: String) => 
		out.println(s"Case #$cnt: " + Case.create(l).solve)
		cnt = cnt + 1
	}

	out.close

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

}