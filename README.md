[![CircleCI](https://circleci.com/gh/bartholomews/scalatestudo/tree/master.svg?style=svg)](https://circleci.com/gh/bartholomews/scala-iso/tree/master)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/bartholomews/spotify4s/blob/master/LICENSE)

# scala-iso
Scala library for ISO enums

```
libraryDependencies += "io.bartholomews" %% "scala-iso" % "0.2.0"
```

### Scala 3

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_3/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_3)


### Scala 2.13

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bartholomews/scala-iso_2.13)

### [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)

`CountryCodeAlpha2` is a *StringEnum* 
(using [enumeratum](https://github.com/lloydmeta/enumeratum) on the two-digits ISO string)

```scala
import io.bartholomews.iso.CountryCodeAlpha2
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

### [ISO 639-1](https://en.wikipedia.org/wiki/ISO_639-1)

`LanguageCode` is a *StringEnum*
(using [enumeratum](https://github.com/lloydmeta/enumeratum) on the two-digits ISO string)

```scala
import io.bartholomews.iso.LanguageCode
import io.circe._
import io.circe.syntax._

val italy: LanguageCode = LanguageCode.ITALIAN

implicit val encoder: Encoder[LanguageCode] = c => Json.fromString(c.value)
implicit val decoder: Decoder[LanguageCode] = Decoder.decodeString.emap(
  str =>
    LanguageCode.values
      .find(_.value == str)
      .toRight(s"Invalid ISO_639 code: [$str]")
)

assert(italy.asJson == Json.fromString("it"))
assert("it".asJson.as[LanguageCode].map(_.name) == Right("Italian"))
```
