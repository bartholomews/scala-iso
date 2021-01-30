name := "scala-iso"
scalaVersion := "2.13.3"
crossScalaVersions := Seq("2.12.11", "2.13.2")

inThisBuild(List(
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
))

libraryDependencies ++= Seq(
  // https://github.com/lloydmeta/enumeratum/releases
  "com.beachape" %% "enumeratum" % "1.6.1",
  // https://github.com/circe/circe/releases
  "io.circe" %% "circe-parser" % "0.13.0" % Test
)