import scala.io.Source
import java.io.PrintWriter

//Some(new PrintWriter("filename")).foreach{p => p.write("hello world"); p.close}

// GoogleCodeJamAfrica2010Qual

object gcj2 extends App {
	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/B-small-practice.in"

	val lines = Source.fromFile(inputFile).getLines

	val out = new PrintWriter("data/B-large-practice.out" , "UTF-8")

	val numCases = lines.next.toInt
	println(s"Tratando $numCases casos")

	var cnt = 1
	lines.foreach { (s: String) =>
		out.println(s"Case #$cnt: " + (s.split(" ").reverse.mkString(" ")))
		cnt = cnt + 1
	}

	out.close

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

}