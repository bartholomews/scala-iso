[![CircleCI](https://circleci.com/gh/bartholomews/scalatestudo/tree/master.svg?style=svg)](https://circleci.com/gh/bartholomews/scala-iso/tree/master)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# scala-iso
Scala library for ISO enums

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13)

```
libraryDependencies += "io.bartholomews" %% "scala-iso" % "<VERSION>"
```

### ISO 3166-1 alpha-2

`CountryCodeAlpha2` are *StringCirceEnums* 
(using [enumeratum](https://github.com/lloydmeta/enumeratum) with circe codecs on the two-digits ISO string)

```scala
  import io.bartholomews.iso_country.CountryCodeAlpha2
  import io.circe._
  import io.circe.syntax._

  val italy: CountryCodeAlpha2 = CountryCodeAlpha2.ITALY
  
  assert(italy.asJson == Json.fromString("IT"))
  assert("IT".asJson.as[CountryCodeAlpha2].map(_.name) == Right("Italy"))
```