import scala.io.Source
import java.io.PrintWriter

// GoogleCodeJamAfrica2010Qual

object gcj3 extends App {
	val trfMap = Map(
		' ' -> ('0',1),
		'a' -> ('2',1),
		'b' -> ('2',2),
		'c' -> ('2',3),
		'd' -> ('3',1),
		'e' -> ('3',2),
		'f' -> ('3',3),
		'g' -> ('4',1),
		'h' -> ('4',2),
		'i' -> ('4',3),
		'j' -> ('5',1),
		'k' -> ('5',2),
		'l' -> ('5',3),
		'm' -> ('6',1),
		'n' -> ('6',2),
		'o' -> ('6',3),
		'p' -> ('7',1),
		'q' -> ('7',2),
		'r' -> ('7',3),
		's' -> ('7',4),
		't' -> ('8',1),
		'u' -> ('8',2),
		'v' -> ('8',3),
		'w' -> ('9',1),
		'x' -> ('9',2),
		'y' -> ('9',3),
		'z' -> ('9',4)
		)

	def transform(s: String): String = {
		s.map(trfMap).map { p =>
			p._1.toString * p._2
		}
		.reduce { (total,cur) =>
			if (total.last == cur.head)
				total + " " + cur
			else
				total + cur
		}	

	}

	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/C-small-practice.in"

	val lines = Source.fromFile(inputFile).getLines

	val out = new PrintWriter("data/C-large-practice.out" , "UTF-8")

	val numCases = lines.next.toInt
	println(s"Tratando $numCases casos")

	var cnt = 1
	lines.foreach { (s: String) =>
		out.println(s"Case #$cnt: " + transform(s))
		cnt = cnt + 1
	}

	out.close

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

}