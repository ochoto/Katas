import scala.io.Source
import java.io.PrintWriter

// GoogleCodeJam 2012 Qual

object gcj_2012_a extends App {
	val learningBase = List( ("ejp mysljylc kd kxveddknmc re jsicpdrysi",
							  "our language is impossible to understand"),
							 ("rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
							  "there are twenty six factorial possibilities"),
							 ("de kr kd eoya kw aej tysr re ujdr lkgc jv",
							  "so it is okay if you want to just give up"),
							 ("y qee", 
							  "a zoo")
	)

	private def learnToTranslate: Map[Char,Char] = 
		(learningBase.flatMap { case (t, p) => t zip p }).toMap.withDefaultValue('q')
	
	val start = System.nanoTime 

	val inputFile = if (args.size > 0)
						args(0)
					else
						"data/2012-A-small-practice.in"

	val lines = Source.fromFile(inputFile).getLines
	val out = new PrintWriter("data/2012-A-small.out" , "UTF-8")

	val numCases = lines.next.toInt
	println(s"There are $numCases cases")

	val translateMap = learnToTranslate
	
	println(translateMap + " " + translateMap.size)
	println(translateMap.keys.toList.sorted)
	println(translateMap.values.toList.sorted)

	val check = learningBase.map { t => (t._2 == t._1.map(translateMap)) }
	println(check)

	var cnt = 1
	lines.foreach { (s: String) =>
		out.println(s"Case #$cnt: " + s.map(translateMap))
		cnt = cnt + 1
	}

	out.close

	val elapsed_ms = (System.nanoTime - start ) / 1000000
	println("Elapsed time: " + elapsed_ms + "ms")

}