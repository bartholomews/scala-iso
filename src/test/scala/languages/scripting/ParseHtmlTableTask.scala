package languages.scripting

import io.circe.{Json, JsonObject}
import org.jsoup.Jsoup

import java.io.{File, PrintWriter}
import java.text.Normalizer
import scala.io.Source

case class LanguageCodeDto(iso639_1: Option[String], iso639_3: String, english: String, native: List[String])

object ParseHtmlTableTask extends App {
  val allLetters = ('a' to 'z').map(_.toString).toArray
  val lettersToProcess =
    if (args.isEmpty) allLetters
    else args.filter(arg => arg.length == 1 && arg.head.isLetter).map(_.toLowerCase)
  val officialLanguageCodes = loadOfficialLanguageCodes()
  val retiredLanguageCodes = loadRetiredLanguageCodes()
  val languageCodes = processAllLetters()
  val filteredLanguageCodes = languageCodes.filterNot(dto => retiredLanguageCodes.contains(dto.iso639_3))
  generateOutputFiles(filteredLanguageCodes)

  def processAllLetters(): List[LanguageCodeDto] = {
    val allLanguageCodesFile = new File("src/test/resources/pseudo-scala/all-language-codes.txt")
    val allLanguageCodesWriter = new PrintWriter(allLanguageCodesFile)

    val languageCodeAutoFile = new File("src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala")
    languageCodeAutoFile.getParentFile.mkdirs()
    val languageCodeAutoWriter = new PrintWriter(languageCodeAutoFile)

    try {
      writeScalaFileHeader(languageCodeAutoWriter)
      val allLanguageCodeDtos = lettersToProcess.flatMap { letter =>
        processLetter(letter, allLanguageCodesWriter, languageCodeAutoWriter)
      }.toList
      allLanguageCodeDtos
    } finally closeWriters(allLanguageCodesWriter, languageCodeAutoWriter)
  }

  def generateOutputFiles(languageCodeDtos: List[LanguageCodeDto]): Unit = {
    val scalaString = generateLanguageCodeScalaString(languageCodeDtos)
    val scalaFile = new File("src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala")
    writeToFile(scalaFile, scalaString)
    println(s"Complete LanguageCode.scala written to src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala")
    val jsonString = generateLanguageCodeJsonString(languageCodeDtos)
    val jsonFile = new File("src/test/resources/iso-639-3.json")
    writeToFile(jsonFile, jsonString)
    println(s"Complete languages.json written to src/test/resources/iso-639-3.json")
  }

  def writeToFile(file: File, content: String): Unit = {
    val writer = new PrintWriter(file)
    try writer.print(content)
    finally writer.close()
  }

  def writeScalaFileHeader(writer: PrintWriter): Unit = {
    writer.println("package io.bartholomews.iso.iso639_3")
    writer.println()
    writer.println("import enumeratum.values.{StringEnum, StringEnumEntry}")
    writer.println("import io.bartholomews.iso.LanguageCode")
    writer.println()
    writer.println("// https://en.wikipedia.org/wiki/ISO_639-3")
    writer.println("sealed abstract class LanguageCode(val value: String) extends StringEnumEntry {")
    writer.println("  def name: String")
    writer.println("  def native: List[String]")
    writer.println("  def iso639_1: Option[String]")
    writer.println("}")
    writer.println()
    writer.println("object LanguageCode extends StringEnum[LanguageCode] {")
    writer.println()
    writer.println("    override val values: IndexedSeq[LanguageCode] = IndexedSeq.empty[LanguageCode]")
    writer.println()
    writer.println("  // All language codes")
  }

  def closeWriters(allLanguageCodesWriter: PrintWriter, languageCodeAutoWriter: PrintWriter): Unit = {
    languageCodeAutoWriter.println("}")
    languageCodeAutoWriter.close()
    println(s"LanguageCode.scala written to src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala")

    allLanguageCodesWriter.close()
    println(s"All language codes written to src/test/resources/pseudo-scala/all-language-codes.txt")
  }

  def processLetter(
    letter: String,
    allLanguageCodesWriter: PrintWriter,
    languageCodeAutoWriter: PrintWriter
  ): List[LanguageCodeDto] = {
    val htmlFilePath = s"src/test/resources/wikipedia/$letter-table.html"
    val outputFilePath = s"src/test/resources/pseudo-scala/$letter-language-codes.txt"
    val htmlFile = new File(htmlFilePath)
    if (!htmlFile.exists()) {
      println(s"HTML file for letter '$letter' not found at $htmlFilePath")
      return List.empty
    }

    println(s"Processing language codes starting with '$letter'...")
    try {
      val letterLanguageCodeDtos = parseHtmlFile(htmlFile, letter)
      val formattedEntries = formatLanguageCodeEntries(letterLanguageCodeDtos)
      writeFormattedEntries(outputFilePath, formattedEntries)
      writeToGlobalFiles(formattedEntries, allLanguageCodesWriter, languageCodeAutoWriter)

      println(s"Extracted ${formattedEntries.size} language codes starting with '${letter.toUpperCase}'")
      println(s"Output written to $outputFilePath")
      println(s"Also added to all-language-codes.txt")

      letterLanguageCodeDtos
    } catch {
      case e: Exception =>
        println(s"Error processing letter '$letter': ${e.getMessage}")
        e.printStackTrace()
        List.empty
    }
  }

  def parseHtmlFile(htmlFile: File, letter: String): List[LanguageCodeDto] = {
    val doc = Jsoup.parse(htmlFile, "UTF-8")
    val rows = doc.select("table.wikitable tr")
    (2 until rows.size()).flatMap { rowIndex =>
      val row = rows.get(rowIndex)
      val cells = row.select("th, td")

      if (cells.size() >= 8) {
        Option(cells.get(0).select("code a").first()).flatMap { codeElement =>
          val code = codeElement.text().trim()
          if (code.startsWith(letter))
            extractLanguageCodeDto(cells, code)
          else
            None
        }
      } else {
        println(s"Row $rowIndex has ${cells.size()} cells, expected at least 8")
        None
      }
    }.toList
  }

  def extractLanguageCodeDto(cells: org.jsoup.select.Elements, code: String): Option[LanguageCodeDto] = {
    val iso639_1Text = if (cells.size() > 1) cells.get(1).text().trim() else ""
    val nativeText = if (cells.size() > 5) cells.get(5).text().trim() else ""
    val englishText = if (cells.size() > 6) cells.get(6).text().trim() else ""

    if (officialLanguageCodes.contains(code)) {
      val officialName = officialLanguageCodes(code)
      val english = officialName
      val iso639_1 = if (iso639_1Text.isEmpty) None else Some(iso639_1Text)
      val nativeList = if (nativeText.isEmpty) List.empty else List(nativeText)
      Some(
        LanguageCodeDto(
          iso639_1 = iso639_1,
          iso639_3 = code,
          english = english,
          native = nativeList
        )
      )
    } else {
      println(s"Warning: Language code '$code' not found in the official ISO 639-3 tab file")
      val english = if (englishText.isEmpty && nativeText.nonEmpty) nativeText else englishText
      val native = if (nativeText.isEmpty && englishText.nonEmpty) englishText else nativeText
      val iso639_1 = if (iso639_1Text.isEmpty) None else Some(iso639_1Text)
      val nativeList = if (native.isEmpty) List.empty[String] else List(native)
      Some(
        LanguageCodeDto(
          iso639_1 = iso639_1,
          iso639_3 = code,
          english = english,
          native = nativeList
        )
      )
    }
  }

  def formatLanguageCodeEntries(languageCodeDtos: List[LanguageCodeDto]): List[String] = {
    languageCodeDtos.map { langDto =>
      val objectName = createObjectName(langDto)
      val iso639_1Value = formatIso639_1Value(langDto.iso639_1)
      val nativeValue = formatNativeValue(langDto.native)
      formatLanguageCodeEntry(objectName, langDto, iso639_1Value, nativeValue)
    }
  }

  def createObjectName(langDto: LanguageCodeDto): String =
    langDto.iso639_3.toUpperCase + "_" + normalizeToLatinAlphabet(langDto.english.toUpperCase)

  def formatIso639_1Value(iso639_1: Option[String]): String =
    iso639_1.fold("None")(iso => s"""Some("$iso")""")

  def formatNativeValue(native: List[String]): String =
    if (native.isEmpty) "List.empty[String]"
    else native.map(n => s""""$n"""").mkString("List(", ", ", ")")

  def formatLanguageCodeEntry(
    objectName: String,
    langDto: LanguageCodeDto,
    iso639_1Value: String,
    nativeValue: String
  ): String = {
    s"""  case object $objectName extends LanguageCode(value = "${langDto.iso639_3}") {
       |    override val name = "${langDto.english}"
       |    override val native: List[String] = $nativeValue
       |    override val iso639_1: Option[String] = $iso639_1Value
       |  }""".stripMargin
  }

  def writeFormattedEntries(outputFilePath: String, formattedEntries: List[String]): Unit = {
    val writer = new PrintWriter(new File(outputFilePath))
    try {
      writer.println()

      formattedEntries.foreach { entry =>
        writer.println(entry)
        writer.println()
      }
    } finally writer.close()
  }

  def writeToGlobalFiles(
    formattedEntries: List[String],
    allLanguageCodesWriter: PrintWriter,
    languageCodeAutoWriter: PrintWriter
  ): Unit = {
    formattedEntries.foreach { entry =>
      allLanguageCodesWriter.println(entry)
      allLanguageCodesWriter.println()
      languageCodeAutoWriter.println(entry)
      languageCodeAutoWriter.println()
    }
  }

  def generateLanguageCodeScalaString(languageCodeDtos: List[LanguageCodeDto]): String = {
    val sb = new StringBuilder()

    writeScalaFileStringHeader(sb)
    appendLanguageCodeEntries(sb, languageCodeDtos)
    sb.append("}")

    sb.toString()
  }

  def writeScalaFileStringHeader(sb: StringBuilder): Unit = {
    sb.append("package io.bartholomews.iso.iso639_3\n\n")
    sb.append("import enumeratum.values.{StringEnum, StringEnumEntry}\n")
    sb.append("import io.bartholomews.iso.LanguageCode\n\n")
    sb.append("// https://en.wikipedia.org/wiki/ISO_639-3\n")
    sb.append("sealed abstract class LanguageCode(val value: String) extends StringEnumEntry {\n")
    sb.append("  def name: String\n")
    sb.append("  def native: List[String]\n")
    sb.append("  def iso639_1: Option[String]\n")
    sb.append("}\n\n")
    sb.append("object LanguageCode extends StringEnum[LanguageCode] {\n\n")
    sb.append("    override val values: IndexedSeq[LanguageCode] = IndexedSeq.empty[LanguageCode]\n\n")
    sb.append("  // All language codes\n\n")
  }

  def appendLanguageCodeEntries(sb: StringBuilder, languageCodeDtos: List[LanguageCodeDto]): Unit = {
    languageCodeDtos.foreach { langDto =>
      val objectName = createObjectName(langDto)
      val iso639_1Value = formatIso639_1Value(langDto.iso639_1)
      val nativeValue = formatNativeValue(langDto.native)

      val entry = formatLanguageCodeEntry(objectName, langDto, iso639_1Value, nativeValue)
      sb.append(entry).append("\n\n")
    }
  }

  def generateLanguageCodeJsonString(languageCodeDtos: List[LanguageCodeDto]): String = {
    val jsonEntries = languageCodeDtos.map(convertDtoToJson)
    val jsonArray = Json.arr(jsonEntries: _*)
    jsonArray.spaces2
  }

  def convertDtoToJson(langDto: LanguageCodeDto): Json = {
    val baseFields = Map(
      "iso639_3" -> Json.fromString(langDto.iso639_3),
      "name" -> Json.fromString(langDto.english),
      "native" -> (if (langDto.native.nonEmpty)
                     Json.fromValues(langDto.native.map(Json.fromString))
                   else
                     Json.arr())
    )
    val fields = langDto.iso639_1.fold(baseFields) { iso1 =>
      baseFields + ("iso639_1" -> Json.fromString(iso1))
    }

    Json.fromJsonObject(JsonObject.fromMap(fields))
  }

  def loadOfficialLanguageCodes(): Map[String, String] = {
    val tabFilePath = "src/test/resources/iso-639-3.tab"
    val tabFile = new File(tabFilePath)

    if (!tabFile.exists()) {
      println(s"Warning: Official ISO 639-3 tab file not found at $tabFilePath")
      return Map.empty[String, String]
    }

    println("Loading official ISO 639-3 language codes from tab file...")

    try {
      val source = Source.fromFile(tabFile, "UTF-8")
      val result = source
        .getLines()
        .drop(1) // Skip header line
        .map { line =>
          val fields = line.split("\t")
          if (fields.length >= 7) {
            val code = fields(0).trim
            val name = fields(6).trim
            (code, name)
          } else {
            println(s"Warning: Invalid line format in tab file: $line")
            ("", "")
          }
        }
        .filter(_._1.nonEmpty)
        .toMap

      source.close()
      println(s"Loaded ${result.size} official language codes")
      result
    } catch {
      case e: Exception =>
        println(s"Error loading official language codes: ${e.getMessage}")
        e.printStackTrace()
        Map.empty[String, String]
    }
  }
  
  def loadRetiredLanguageCodes(): Set[String] = {
    val tabFilePath = "src/test/resources/iso-639-3_Retirements.tab"
    val tabFile = new File(tabFilePath)

    if (!tabFile.exists()) {
      println(s"Warning: ISO 639-3 Retirements tab file not found at $tabFilePath")
      return Set.empty[String]
    }

    println("Loading retired ISO 639-3 language codes from tab file...")

    try {
      val source = Source.fromFile(tabFile, "UTF-8")
      val result = source
        .getLines()
        .drop(1) // Skip header line
        .map { line =>
          val fields = line.split("\t")
          if (fields.length >= 1) {
            fields(0).trim // The first field is the retired language code
          } else {
            println(s"Warning: Invalid line format in retirements tab file: $line")
            ""
          }
        }
        .filter(_.nonEmpty)
        .toSet

      source.close()
      println(s"Loaded ${result.size} retired language codes")
      result
    } catch {
      case e: Exception =>
        println(s"Error loading retired language codes: ${e.getMessage}")
        e.printStackTrace()
        Set.empty[String]
    }
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
