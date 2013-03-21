organization := "com.github.ochoto"

name := "Katas"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.1"

licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

scalacOptions ++= Seq("-deprecation","-feature")

//initialCommands in console := "import java.math.BigInteger"

//set mainClass in (Compile, run) := Some("CurrentMain")

mainClass in (Compile, run) := Some("CurrentMain")

//fork in run := true

//javaOptions in run += "-server -Xms512M -Xmx2G"





