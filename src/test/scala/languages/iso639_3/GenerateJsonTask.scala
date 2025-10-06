package languages.iso639_3

import io.circe.{Json, JsonObject}
import org.jsoup.Jsoup

import java.io.{File, PrintWriter}
import java.net.{HttpURLConnection, URL}
import java.text.Normalizer
import scala.io.Source
import scala.util.{Failure, Success, Try}

case class LanguageCodeDto(iso639_1: Option[String], iso639_3: String, english: String, native: List[String])

object GenerateJsonTask extends App {
  val allLetters = ('a' to 'z').map(_.toString).toArray
  val lettersToProcess =
    if (args == null || args.isEmpty) allLetters
    else args.filter(arg => arg.length == 1 && arg.head.isLetter).map(_.toLowerCase)
  val wikipediaDir = new File("src/test/resources/wikipedia")
  if (!wikipediaDir.exists()) wikipediaDir.mkdirs()
  println("Starting to fetch and process language codes...")
  val officialLanguageCodes = loadOfficialLanguageCodes()
  val retiredLanguageCodes = loadRetiredLanguageCodes()
  val languageCodes = lettersToProcess.flatMap { letter =>
    fetchAndProcessLetter(letter.charAt(0))
  }.toList
  val filteredLanguageCodes = languageCodes.filterNot(dto => retiredLanguageCodes.contains(dto.iso639_3))
  generateJsonOutput(filteredLanguageCodes)
  cleanupTemporaryDirectories()

  println("Process completed successfully!")

  def fetchAndProcessLetter(letter: Char): List[LanguageCodeDto] = {
    println(s"Processing letter: $letter")

    val url = s"https://en.wikipedia.org/wiki/ISO_639:$letter"
    println(s"Fetching URL: $url")

    fetchHtml(url) match {
      case Success(html) =>
        println(s"Successfully fetched HTML for letter '$letter'")
        extractTable(html) match {
          case Success(tableHtml) =>
            println(s"Successfully extracted table for letter '$letter'")
            val htmlFile = new File(wikipediaDir, s"$letter-table.html")
            saveTable(letter, tableHtml, htmlFile) match {
              case Success(_) =>
                println(s"Successfully saved table for letter '$letter'")
                try {
                  val letterLanguageCodeDtos = parseHtmlFile(htmlFile, letter.toString)
                  println(
                    s"Extracted ${letterLanguageCodeDtos.size} language codes starting with '${letter.toString.toUpperCase}'"
                  )
                  letterLanguageCodeDtos
                } catch {
                  case e: Exception =>
                    println(s"Error processing letter '$letter': ${e.getMessage}")
                    e.printStackTrace()
                    List.empty
                }
              case Failure(e) =>
                println(s"Failed to save table for letter '$letter': ${e.getMessage}")
                List.empty
            }
          case Failure(e) =>
            println(s"Failed to extract table for letter '$letter': ${e.getMessage}")
            List.empty
        }
      case Failure(e) =>
        println(s"Failed to fetch HTML for letter '$letter': ${e.getMessage}")
        List.empty
    }
  }

  def fetchHtml(urlString: String): Try[String] = {
    Try {
      val url = new URL(urlString)
      val connection = url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("GET")
      connection.setRequestProperty("User-Agent", "Mozilla/5.0")

      val inputStream = connection.getInputStream
      val content = Source.fromInputStream(inputStream).mkString
      inputStream.close()
      connection.disconnect()

      content
    }
  }

  def extractTable(html: String): Try[String] = {
    Try {
      val tableStartTag = """<table class="wikitable sortable">"""
      val tableEndTag = """</table>"""

      val tableStartIndex = html.indexOf(tableStartTag)
      if (tableStartIndex == -1)
        throw new Exception("Table not found in HTML content")

      val tableEndIndex = html.indexOf(tableEndTag, tableStartIndex) + tableEndTag.length
      html.substring(tableStartIndex, tableEndIndex)
    }
  }

  def saveTable(letter: Char, tableHtml: String, outputFile: File): Try[Unit] = {
    Try {
      val writer = new PrintWriter(outputFile)
      try writer.println(tableHtml)
      finally writer.close()
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

  def generateJsonOutput(languageCodeDtos: List[LanguageCodeDto]): Unit = {
    val jsonString = generateLanguageCodeJsonString(languageCodeDtos)
    val jsonFile = new File("src/test/resources/iso-639-3.json")
    writeToFile(jsonFile, jsonString)
    println(s"Complete languages.json written to src/test/resources/iso-639-3.json")
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

  def writeToFile(file: File, content: String): Unit = {
    val writer = new PrintWriter(file)
    try writer.print(content)
    finally writer.close()
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
          if (fields.length >= 1)
            fields(0).trim // The first field is the retired language code
          else {
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

  def cleanupTemporaryDirectories(): Unit = {
    // Delete wikipedia directory
    deleteDirectory(wikipediaDir)
    println("Deleted wikipedia directory")

    // Delete pseudo-scala directory if it exists
    val pseudoScalaDir = new File("src/test/resources/pseudo-scala")
    if (pseudoScalaDir.exists()) {
      deleteDirectory(pseudoScalaDir)
      println("Deleted pseudo-scala directory")
    }
  }

  def deleteDirectory(directory: File): Boolean = {
    if (directory.exists()) {
      val files = directory.listFiles()
      if (files != null) {
        for (file <- files) {
          if (file.isDirectory)
            deleteDirectory(file)
          else
            file.delete()
        }
      }
      directory.delete()
    } else
      false
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
