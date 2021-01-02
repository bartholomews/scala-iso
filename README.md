[![CircleCI](https://circleci.com/gh/bartholomews/scalatestudo/tree/master.svg?style=svg)](https://circleci.com/gh/bartholomews/scala-iso/tree/master)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# scala-iso
Scala library for ISO enums

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13)

```
libraryDependencies += "io.bartholomews" %% "scala-iso" % "0.1.1"
```

### ISO 3166-1 alpha-2

`CountryCodeAlpha2` are *StringEnum* 
(using [enumeratum](https://github.com/lloydmeta/enumeratum) on the two-digits ISO string)

```scala
  import io.bartholomews.iso_country.CountryCodeAlpha2
  import io.circe._
  import io.circe.syntax._

  val italy: CountryCodeAlpha2 = CountryCodeAlpha2.ITALY

  implicit val encoder: Encoder[CountryCodeAlpha2] = c => Json.fromString(c.value)
  implicit val decoder: Decoder[CountryCodeAlpha2] = Decoder.decodeString.emap(
    str => CountryCodeAlpha2.values.find(_.value == str).toRight(s"Invalid ISO_3166-1 code: [$str]")
  )

  assert(italy.asJson == Json.fromString("IT"))
  assert("IT".asJson.as[CountryCodeAlpha2].map(_.name) == Right("Italy"))
```