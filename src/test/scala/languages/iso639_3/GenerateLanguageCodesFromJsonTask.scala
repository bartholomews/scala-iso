package languages.iso639_3

import io.circe.parser.parse
import java.text.Normalizer
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

import scala.io.Source

object GenerateLanguageCodesFromJsonTask {
  def main(args: Array[String]): Unit = {
    val targetFile = "/home/viperey/data/dev/vcs/scala-iso/src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala"

    parse(Source.fromResource("iso-639-3.json").getLines().mkString) match {
      case Left(error) =>
        println(s"Error parsing JSON: ${error.message}")
      case Right(json) =>
        // Collect ISO 639-1 codes for later use
        val iso639_1Codes = json.asArray.get.toList.flatMap { obj =>
          val jsonObj = obj.asObject.get
          jsonObj("iso639_1").map { iso639_1Json =>
            val code = iso639_1Json.asString.get
            val iso639_3 = jsonObj("iso639_3").get.asString.get
            (code, iso639_3)
          }
        }.toMap

        val caseObjects = new StringBuilder()

        json.asArray.get.toList.foreach { obj =>
          val jsonObj = obj.asObject.get
          val iso639_3 = jsonObj("iso639_3").get.asString.get
          val name = jsonObj("name").get.asString.get
          val native = jsonObj("native").get
            .as[List[String]]
            .getOrElse(throw new Exception("native"))
          val iso639_1 = jsonObj("iso639_1").map(_.asString.get)
          val caseObjectName = s"${iso639_3.toUpperCase}_${normalizeToLatinAlphabet(name.toUpperCase)}"

          // Add case object definition
          caseObjects.append(s"""
    case object $caseObjectName extends LanguageCode(value = "$iso639_3") {
      override val name = "$name"
      override val native: List[String] = ${formatNativeList(native)}
      override val iso639_1: Option[String] = ${formatIso639_1(iso639_1)}
    }
""")
        }
        // Group case objects by first letter
        val caseObjectsByLetter = scala.collection.mutable.Map[Char, StringBuilder]()
        val caseObjectNamesByLetter = scala.collection.mutable.Map[Char, List[String]]()

        json.asArray.get.toList.foreach { obj =>
          val jsonObj = obj.asObject.get
          val iso639_3 = jsonObj("iso639_3").get.asString.get
          val firstLetter = iso639_3.charAt(0).toUpper

          if (!caseObjectsByLetter.contains(firstLetter)) {
            caseObjectsByLetter(firstLetter) = new StringBuilder()
            caseObjectNamesByLetter(firstLetter) = List.empty[String]
          }

          val name = jsonObj("name").get.asString.get
          val native = jsonObj("native").get
            .as[List[String]]
            .getOrElse(throw new Exception("native"))
          val iso639_1 = jsonObj("iso639_1").map(_.asString.get)
          val caseObjectName = s"${iso639_3.toUpperCase}_${normalizeToLatinAlphabet(name.toUpperCase)}"

          // Add case object name to the list for this letter
          caseObjectNamesByLetter(firstLetter) = caseObjectNamesByLetter(firstLetter) :+ caseObjectName

          // Add case object definition to the appropriate letter group
          caseObjectsByLetter(firstLetter).append(s"""
    case object $caseObjectName extends ${firstLetter}Language("$iso639_3") {
      override val name = "$name"
      override val native: List[String] = ${formatNativeList(native)}
      override val iso639_1: Option[String] = ${formatIso639_1(iso639_1)}
    }
""")
        }

        // Create the letter traits and objects with their case objects
        val letterObjects = new StringBuilder()
        val letterTraits = new StringBuilder()
        val allLanguagesMethod = new StringBuilder("  // Method to get all language codes\n  def allLanguages: IndexedSeq[LanguageCode] = ")

        val sortedLetters = caseObjectsByLetter.keys.toList.sorted
        sortedLetters.foreach { letter =>
          // Create trait for this letter group
          letterTraits.append(s"""
  sealed abstract class ${letter}Language(override val value: String) extends LanguageCode(value)""")

          // Create explicit list of case objects instead of using findValues
          val valuesListStr = if (caseObjectNamesByLetter(letter).isEmpty) {
            "IndexedSeq.empty"
          } else {
            caseObjectNamesByLetter(letter).mkString("IndexedSeq(", ", ", ")")
          }

          // Case objects already extend their letter trait
          val updatedCaseObjects = caseObjectsByLetter(letter).toString()

          letterObjects.append(s"""
  object ${letter} extends StringEnum[${letter}Language] {
    override val values: IndexedSeq[${letter}Language] = findValues
$updatedCaseObjects
  }
""")

          // Add this letter's values to the allLanguages method
          if (letter == sortedLetters.head) {
            allLanguagesMethod.append(s"${letter}.values")
          } else {
            allLanguagesMethod.append(s" ++ ${letter}.values")
          }
        }

        // Complete the allLanguages method
        allLanguagesMethod.append("\n")

        // Generate methods for ISO 639-1 code lookup
        val iso639_1Methods = new StringBuilder()
        iso639_1Methods.append("""
  // Create a map from ISO 639-1 codes to language codes
  private val iso639_1ToValuesMap: Map[String, LanguageCode] = 
    allLanguages.flatMap(v => v.iso639_1.map(code => code -> v)).toMap
    
  // Collect all language codes from all nested objects
  private val allValues: IndexedSeq[LanguageCode] = allLanguages
    
  // Create a map from names (values) to language codes
  private val namesToValuesMap: Map[String, LanguageCode] = 
    allValues.map(v => v.value -> v).toMap
    
  /** Returns a [[LanguageCode]] for a given name, or throws if the name does not match any of the values.
    */
  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def withName(name: String): LanguageCode = {
    if (name.length == 2) {
      iso639_1ToValuesMap.getOrElse(name, 
        throw new NoSuchElementException(s"No value found for ISO 639-1 code '$name'"))
    } else {
      withNameOption(name).getOrElse(
        throw new NoSuchElementException(s"No value found for ISO 639-3 code '$name'"))
    }
  }

  /** Optionally returns a [[LanguageCode]] for a given name.
    */
  def withNameOption(name: String): Option[LanguageCode] = {
    if (name.length == 2) {
      iso639_1ToValuesMap.get(name)
    } else {
      namesToValuesMap.get(name)
    }
  }

  /** Returns a [[Right[LanguageCode]] ] for a given name, or a [[Left[NoSuchMember]] ] if the name does not
    * match any of the values' .value values.
    */
  def withNameEither(name: String): Either[NoSuchMember[String, LanguageCode], LanguageCode] = {
    if (name.length == 2) {
      iso639_1ToValuesMap.get(name).toRight(NoSuchMember(name, allValues))
    } else {
      namesToValuesMap.get(name).toRight(NoSuchMember(name, allValues))
    }
  }
""")

        // Generate the complete file content
        val fileContent = s"""package io.bartholomews.iso.iso639_3

import enumeratum.values.{NoSuchMember, StringEnum, StringEnumEntry}

// https://en.wikipedia.org/wiki/ISO_639-3
sealed abstract class LanguageCode(val value: String) extends StringEnumEntry {
  def name: String
  def native: List[String]
  def iso639_1: Option[String]
}

object LanguageCode {
${letterTraits}
${letterObjects}
${allLanguagesMethod}${iso639_1Methods}}
"""

        // Write to file
        Files.write(Paths.get(targetFile), fileContent.getBytes(StandardCharsets.UTF_8))
        println(s"Successfully wrote language codes to $targetFile")
    }
  }

  private def formatNativeList(native: List[String]): String =
    if (native.isEmpty) "List.empty[String]"
    else native.map(str => s""""$str"""").mkString("List(", ", ", ")")

  private def formatIso639_1(iso639_1: Option[String]): String =
    iso639_1 match {
      case Some(code) => s"""Some("$code")"""
      case None => "None"
    }

  def normalizeToLatinAlphabet(text: String): String = {
    val normalized = Normalizer
      .normalize(text, Normalizer.Form.NFD)
      .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
    val replacements = List(
      ("\\(", ""),
      ("\\)", ""),
      ("\\.", ""),
      (",", ""),
      ("'", ""),
      ("´", ""),
      ("'", ""),
      ("-", "_"),
      ("–", "_"),
      (";", "_"),
      ("!", "_"),
      (":", "_"),
      ("/", "_"),
      (" ", "_"),
      // Additional character mappings for special cases not handled by normalization
      ("Ø", "O"),
      ("ø", "o"),
      ("Þ", "Th"),
      ("þ", "th"),
      ("Ð", "D"),
      ("ð", "d"),
      ("ß", "ss"),
      ("Æ", "AE"),
      ("æ", "ae"),
      ("Œ", "OE"),
      ("œ", "oe")
    )
    replacements
      .foldLeft(normalized) {
        case (acc, (from, to)) =>
          acc.replaceAll(from, to)
      }
      .replaceAll("[^a-zA-Z0-9_]", "")
  }
}
