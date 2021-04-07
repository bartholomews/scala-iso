package languages

import io.circe.parser.parse

import scala.io.Source

object ParseJsonTask {
  def main(args: Array[String]): Unit = {
    parse(Source.fromResource("languages.json").getLines().mkString) match {
      case Left(value) =>
        println(value.message)
      case Right(json) =>
        json.asArray.get.toList.foreach(obj => {
          val jsonObj = obj.asObject.get
          val name = jsonObj("name").get.asString.get
          val code = jsonObj("code").get.asString.get
          val native = jsonObj("native").get
            .as[List[String]]
            .getOrElse(throw new Exception("native"))
          print(
            s"""
               |case object ${name.toUpperCase
                 .replaceAll("\\(", "")
                 .replaceAll("\\)", "")
                 .replaceAll("\\.", "")
                 .replaceAll(",", "")
                 .replaceAll("'", "")
                 .replaceAll("Å", "A")
                 .replaceAll("Ç", "C")
                 .replaceAll("É", "E")
                 .replaceAll("Ô", "O")
                 .replaceAll("Ü", "U")
                 .replaceAll("-", "_")
                 .replaceAll(" ", "_")} extends LanguageAlpha2(value = "$code") {
               |  override val name = "$name"
               |  override val native = ${native.map(str => s""""$str"""")}
               |}
               |""".stripMargin)
        })
    }
  }
}
