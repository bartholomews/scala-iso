name := "scala-iso"
crossScalaVersions := Seq("3.7.0", "2.13.16")

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
  "io.circe" %% "circe-parser" % "0.14.14" % Test
)
