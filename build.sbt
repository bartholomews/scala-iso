name := "scala-iso"

// https://github.com/scala/scala3
val `scala-3` = "3.7.1"
// https://github.com/scala/scala
val `scala-2.13` = "2.13.16"

ThisBuild / scalaVersion       := `scala-3`
ThisBuild / crossScalaVersions := Seq(`scala-2.13`, `scala-3`)

inThisBuild(
  List(
    organization := "io.bartholomews",
    homepage := Some(url("https://github.com/bartholomews/scala-iso")),
    licenses += ("MIT", url("https://opensource.org/licenses/MIT")),
    developers := List(
      Developer(
        "bartholomews",
        "Federico Bartolomei",
        "scala-iso@bartholomews.io",
        url("https://bartholomews.io")
      )
    )
  )
)

scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((3, _)) => Seq("-Yretain-trees")
    case Some((2, _)) => Seq.empty
    case _ => Seq.empty
  }
}

libraryDependencies ++= Seq(
  // https://github.com/lloydmeta/enumeratum/releases
  "com.beachape" %% "enumeratum" % "1.9.0",
  // https://github.com/circe/circe/releases
  "io.circe" %% "circe-parser" % "0.14.14" % Test,
  // HTML parsing library (optional, as we're using simple string operations in the code)
  "org.jsoup" % "jsoup" % "1.17.2" % Test,
  // ScalaTest for unit testing
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)
