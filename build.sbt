name := "scala-iso"
organization := "io.bartholomews"
version := "0.1.0-SNAPSHOT"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
scalaVersion := "2.13.1"
crossScalaVersions := Seq("2.12.10")
libraryDependencies ++= Seq(
  "com.beachape" %% "enumeratum-circe" % "1.5.23",
  "io.circe" %% "circe-parser" % "0.13.0" % Test
)