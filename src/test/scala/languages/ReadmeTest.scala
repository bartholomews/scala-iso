package languages

object ReadmeTest extends App {

  import io.bartholomews.iso.LanguageCode
  import io.circe._
  import io.circe.syntax._

  val italy: LanguageCode = LanguageCode.ITALIAN

  implicit val encoder: Encoder[LanguageCode] = c => Json.fromString(c.value)
  implicit val decoder: Decoder[LanguageCode] = Decoder.decodeString.emap(
    str =>
      LanguageCode.values
        .find(_.value == str)
        .toRight(s"Invalid ISO_639-1 code: [$str]")
  )

  assert(italy.asJson == Json.fromString("it"))
  assert("it".asJson.as[LanguageCode].map(_.name) == Right("Italian"))
}
