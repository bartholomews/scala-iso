name := "scala-iso"
organization := "io.bartholomews"
scalaVersion := "2.13.2"
crossScalaVersions := Seq("2.12.11", "2.13.2")

inThisBuild(List(
  organization := "io.bartholomews",
  homepage := Some(url("https://github.com/bartholomews/scala-iso")),
  licenses += ("GPL", url("http://opensource.org/licenses/GPL-3.0")),
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
  "com.beachape" %% "enumeratum-circe" % "1.5.23",
  "io.circe" %% "circe-parser" % "0.13.0" % Test
)