package countries

object ReadmeTest extends App {

  import io.circe._
  import io.circe.syntax._
  import io.bartholomews.iso.CountryCodeAlpha2

  val italy: CountryCodeAlpha2 = CountryCodeAlpha2.ITALY

  implicit val encoder: Encoder[CountryCodeAlpha2] = c =>
    Json.fromString(c.value)
  implicit val decoder: Decoder[CountryCodeAlpha2] = Decoder.decodeString.emap(
    str =>
      CountryCodeAlpha2.values
        .find(_.value == str)
        .toRight(s"Invalid ISO_3166-1 code: [$str]")
  )

  assert(italy.asJson == Json.fromString("IT"))
  assert("IT".asJson.as[CountryCodeAlpha2].map(_.name) == Right("Italy"))
}
