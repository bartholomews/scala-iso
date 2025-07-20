package languages.iso639_3

import java.io.File
import scala.io.Source
import io.circe.parser.parse
import io.bartholomews.iso.iso639_3.{LanguageCode => ISO639_3}

/**
  * Test to verify language codes across different sources.
  * Compares language codes between:
  * - The .tab file (excluding retired codes)
  * - The JSON file
  */
object VerifyLanguageCodesTask extends App {
  val tabFilePath = "src/test/resources/iso-639-3.tab"
  val jsonFilePath = "src/test/resources/iso-639-3.json"
  val tabCodes = extractTabCodes(tabFilePath)
  val jsonCodes = extractJsonCodes(jsonFilePath)

  println(s"Total codes in tab file (excluding retired): ${tabCodes.size}")
  println(s"Total codes in JSON file: ${jsonCodes.size}")
  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN TAB FILE BUT MISSING FROM JSON FILE")
  println("=" * 80 + "\n")

  val tabNotInJson = tabCodes.diff(jsonCodes)
  println(s"Total codes in tab file but not in JSON file: ${tabNotInJson.size}")

  if (tabNotInJson.nonEmpty) {
    val sortedCodes = tabNotInJson.toList.sorted
    sortedCodes.grouped(5).foreach { group =>
      println("  " + group.mkString(", "))
    }
  }

  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN JSON FILE BUT MISSING FROM TAB FILE")
  println("=" * 80 + "\n")

  val jsonNotInTab = jsonCodes.diff(tabCodes)
  println(s"Total codes in JSON file but not in tab file: ${jsonNotInTab.size}")

  if (jsonNotInTab.nonEmpty) {
    val sortedCodes = jsonNotInTab.toList.sorted
    sortedCodes.grouped(5).foreach { group =>
      println("  " + group.mkString(", "))
    }

    // For codes in JSON but not in tab, try to get additional information using LanguageCode
    println("\nDetails for codes in JSON but not in tab:")
    sortedCodes.foreach { code =>
      try {
        val language = ISO639_3.withName(code)
        println(s"  $code: ${language.name} (ISO 639-1: ${language.iso639_1.getOrElse("N/A")})")
      } catch {
        case _: NoSuchElementException =>
          println(s"  $code: Not found in LanguageCode")
      }
    }
  }

  /**
    * Extract language codes from the tab file, excluding retired codes.
    * @return A set of language codes found in the tab file, excluding retired codes
    */
  def extractTabCodes(filePath: String): Set[String] = {
    try {
      val file = new File(filePath)
      if (!file.exists()) {
        println(s"Tab file not found at $filePath")
        return Set.empty
      }

      val lines = Source.fromFile(filePath).getLines().toList
      val dataLines = lines.tail // Skip header line
      dataLines
        .filterNot { line =>
          // Exclude retired/deprecated codes
          line.toLowerCase.contains("retired") ||
          line.toLowerCase.contains("deprecated")
        }
        .map { line =>
          val fields = line.split("\t")
          fields(0).trim // The first field is the language code
        }
        .toSet
    } catch {
      case e: Exception =>
        println(s"Error extracting tab entries: ${e.getMessage}")
        Set.empty
    }
  }

  /**
    * Extract language codes from the JSON file.
    * @return A set of language codes found in the JSON file
    */
  def extractJsonCodes(filePath: String): Set[String] = {
    try {
      val file = new File(filePath)
      if (!file.exists()) {
        println(s"JSON file not found at $filePath")
        return Set.empty
      }

      val jsonString = Source.fromFile(filePath).getLines().mkString
      parse(jsonString) match {
        case Left(error) =>
          println(s"Error parsing JSON: ${error.message}")
          Set.empty
        case Right(json) =>
          json.asArray.get.toList.flatMap { obj =>
            val jsonObj = obj.asObject.get
            jsonObj("iso639_3").flatMap(_.asString)
          }.toSet
      }
    } catch {
      case e: Exception =>
        println(s"Error extracting JSON entries: ${e.getMessage}")
        Set.empty
    }
  }
}
