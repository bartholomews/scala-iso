import io.circe.parser._

import scala.io.Source

object ParseJsonTask {
  def main(args: Array[String]): Unit = {
    parse(Source.fromResource("slim-2.json").getLines().mkString) match {
      case Left(value) =>
        println(value.message)
      case Right(json) =>
        json.asArray.get.toList.foreach(obj => {
          val jsonObj = obj.asObject.get
          val name = jsonObj("name").get.asString.get
          val alpha2 = jsonObj("alpha-2").get.asString.get
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
                 .replaceAll("-", "_")
                 .replaceAll(" ", "_")} extends CountryCodeAlpha2(value = "$alpha2") {
               |  override val name = "$name"
               |}
               |""".stripMargin)
        })
    }
  }
}
