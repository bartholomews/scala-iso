package languages.scripting

import java.io.File
import scala.io.Source

/**
  * Test to verify language codes across different sources.
  * Gathers information per letter:
  * - Number of entries in *.tab file
  * - Number of entries in the corresponding HTML file
  * - Number of entries in the corresponding pseudo-scala file
  * - Number of entries in the scala code
  */
object VerifyLanguageCodesTask extends App {
  val letters = ('a' to 'z').toList
  val tabFilePath = "src/test/resources/iso-639-3.tab"
  val scalaCodePath = "src/main/scala/io/bartholomews/iso/iso639_3/LanguageCode.scala"

  println("| Letter | Tab File | HTML File | Pseudo-Scala | Scala Code |")
  println("|--------|----------|-----------|--------------|------------|")

  letters.foreach { letter =>
    val letterStr = letter.toString
    val tabCount = countTabEntries(tabFilePath, letterStr)
    val htmlCount = countHtmlEntries(s"src/test/resources/wikipedia/$letterStr-table.html", letterStr)
    val pseudoScalaCount = countPseudoScalaEntries(s"src/test/resources/pseudo-scala/$letterStr-language-codes.txt")
    val scalaCodeCount = countScalaCodeEntries(scalaCodePath, letterStr)
    println(f"| $letterStr      | $tabCount%8d | $htmlCount%9d | $pseudoScalaCount%12d | $scalaCodeCount%10d |")
  }

  val totalTabCount = letters.map(l => countTabEntries(tabFilePath, l.toString)).sum
  val totalHtmlCount = letters.map(l => countHtmlEntries(s"src/test/resources/wikipedia/$l-table.html", l.toString)).sum
  val totalPseudoScalaCount =
    letters.map(l => countPseudoScalaEntries(s"src/test/resources/pseudo-scala/$l-language-codes.txt")).sum
  val totalScalaCodeCount = letters.map(l => countScalaCodeEntries(scalaCodePath, l.toString)).sum

  println("|--------|----------|-----------|--------------|------------|")
  println(
    f"| Total  | $totalTabCount%8d | $totalHtmlCount%9d | $totalPseudoScalaCount%12d | $totalScalaCodeCount%10d |"
  )
  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN HTML BUT MISSING FROM PSEUDO-SCALA")
  println("=" * 80 + "\n")

  var totalMissingCodes = 0

  letters.foreach { letter =>
    val letterStr = letter.toString
    val missingCodes = findMissingCodes(letterStr)
    totalMissingCodes += missingCodes.size

    if (missingCodes.nonEmpty) {
      println(s"Letter '$letterStr': ${missingCodes.size} missing codes")
      val sortedCodes = missingCodes.toList.sorted
      sortedCodes.grouped(5).foreach { group =>
        println("  " + group.mkString(", "))
      }
      println() // Add a blank line between letters
    }
  }

  println(s"Total missing codes: $totalMissingCodes")
  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN PSEUDO-SCALA BUT MISSING FROM HTML")
  println("=" * 80 + "\n")

  var totalReverseMissingCodes = 0
  letters.foreach { letter =>
    val letterStr = letter.toString
    val reverseMissingCodes = findReverseMissingCodes(letterStr)
    totalReverseMissingCodes += reverseMissingCodes.size

    if (reverseMissingCodes.nonEmpty) {
      println(s"Letter '$letterStr': ${reverseMissingCodes.size} missing codes")
      val sortedCodes = reverseMissingCodes.toList.sorted
      sortedCodes.grouped(5).foreach { group =>
        println("  " + group.mkString(", "))
      }
      println() // Add a blank line between letters
    }
  }

  println(s"Total missing codes: $totalReverseMissingCodes")
  
  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN TAB FILE BUT MISSING FROM SCALA FILE")
  println("=" * 80 + "\n")
  
  val tabNotInScala = findTabNotInScala()
  println(s"Total codes in tab file but not in Scala file: ${tabNotInScala.size}")
  
  if (tabNotInScala.nonEmpty) {
    val sortedCodes = tabNotInScala.toList.sorted
    sortedCodes.grouped(5).foreach { group =>
      println("  " + group.mkString(", "))
    }
  }
  
  println("\n" + "=" * 80)
  println("LANGUAGE CODES PRESENT IN SCALA FILE BUT MISSING FROM TAB FILE")
  println("=" * 80 + "\n")
  
  val scalaNotInTab = findScalaNotInTab()
  println(s"Total codes in Scala file but not in tab file: ${scalaNotInTab.size}")
  
  if (scalaNotInTab.nonEmpty) {
    val sortedCodes = scalaNotInTab.toList.sorted
    sortedCodes.grouped(5).foreach { group =>
      println("  " + group.mkString(", "))
    }
  }

  /**
    * Count entries in the tab file that start with the given letter.
    */
  def countTabEntries(filePath: String, letter: String): Int = {
    try {
      val lines = Source.fromFile(filePath).getLines().toList
      val dataLines = lines.tail
      dataLines.count { line =>
        val fields = line.split("\t")
        fields(0).startsWith(letter)
      }
    } catch {
      case e: Exception =>
        println(s"Error counting tab entries for letter '$letter': ${e.getMessage}")
        0
    }
  }
  
  /**
    * Extract language codes from the tab file.
    * @return A set of language codes found in the tab file
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
      dataLines.map { line =>
        val fields = line.split("\t")
        fields(0).trim // The first field is the language code
      }.toSet
    } catch {
      case e: Exception =>
        println(s"Error extracting tab entries: ${e.getMessage}")
        Set.empty
    }
  }

  /**
    * Count entries in the HTML file that start with the given letter.
    */
  def countHtmlEntries(filePath: String, letter: String): Int =
    extractHtmlCodes(filePath, letter).size

  /**
    * Extract language codes from the HTML file that start with the given letter.
    * Uses regex to find language codes in the HTML content.
    * @return A set of language codes found in the HTML file
    */
  def extractHtmlCodes(filePath: String, letter: String): Set[String] = {
    try {
      val htmlFile = new File(filePath)
      if (!htmlFile.exists()) {
        println(s"HTML file for letter '$letter' not found at $filePath")
        return Set.empty
      }
      val content = Source.fromFile(htmlFile, "UTF-8").getLines().mkString("\n")
      val codePattern = s"""<code><a href=".*"[^>]*>($letter[^<]+)</a></code>""".r
      codePattern.findAllMatchIn(content).map(_.group(1)).toSet
    } catch {
      case e: Exception =>
        println(s"Error extracting HTML entries for letter '$letter': ${e.getMessage}")
        Set.empty
    }
  }

  /**
    * Count entries in the pseudo-scala file.
    */
  def countPseudoScalaEntries(filePath: String): Int =
    extractPseudoScalaCodes(filePath).size

  /**
    * Extract language codes from the pseudo-scala file.
    * @return A set of language codes found in the pseudo-scala file
    */
  def extractPseudoScalaCodes(filePath: String): Set[String] = {
    try {
      val file = new File(filePath)
      if (!file.exists()) {
        println(s"Pseudo-scala file not found at $filePath")
        return Set.empty
      }

      val content = Source.fromFile(filePath).getLines().mkString("\n")
      val caseObjectPattern = """case object [A-Z0-9_]+ extends LanguageCode\(value = "([^"]+)"\)""".r
      caseObjectPattern.findAllMatchIn(content).map(_.group(1)).toSet
    } catch {
      case e: Exception =>
        println(s"Error extracting pseudo-scala entries: ${e.getMessage}")
        Set.empty
    }
  }

  /**
    * Count entries in the scala code file that start with the given letter.
    */
  def countScalaCodeEntries(filePath: String, letter: String): Int = {
    try {
      val file = new File(filePath)
      if (!file.exists()) {
        println(s"Scala code file not found at $filePath")
        return 0
      }

      val content = Source.fromFile(filePath).getLines().mkString("\n")
      val caseObjectPattern = s"""case object [A-Z0-9_]+ extends LanguageCode\\(value = "$letter[^"]*"\\)""".r
      caseObjectPattern.findAllMatchIn(content).size
    } catch {
      case e: Exception =>
        println(s"Error counting scala code entries for letter '$letter': ${e.getMessage}")
        0
    }
  }
  
  /**
    * Extract language codes from the scala code file.
    * @return A set of language codes found in the scala code file
    */
  def extractScalaCodes(filePath: String): Set[String] = {
    try {
      val file = new File(filePath)
      if (!file.exists()) {
        println(s"Scala code file not found at $filePath")
        return Set.empty
      }

      val content = Source.fromFile(filePath).getLines().mkString("\n")
      val caseObjectPattern = """case object [A-Z0-9_]+ extends LanguageCode\(value = "([^"]+)"\)""".r
      caseObjectPattern.findAllMatchIn(content).map(_.group(1)).toSet
    } catch {
      case e: Exception =>
        println(s"Error extracting scala code entries: ${e.getMessage}")
        Set.empty
    }
  }

  /**
    * Find language codes that are present in the HTML file but missing from the pseudo-scala file.
    * @return A set of language codes that are in HTML but not in pseudo-scala
    */
  def findMissingCodes(letter: String): Set[String] = {
    val htmlFilePath = s"src/test/resources/wikipedia/$letter-table.html"
    val pseudoScalaFilePath = s"src/test/resources/pseudo-scala/$letter-language-codes.txt"
    val htmlCodes = extractHtmlCodes(htmlFilePath, letter)
    val pseudoScalaCodes = extractPseudoScalaCodes(pseudoScalaFilePath)
    htmlCodes.diff(pseudoScalaCodes)
  }

  /**
    * Find language codes that are present in the pseudo-scala file but missing from the HTML file.
    * @return A set of language codes that are in pseudo-scala but not in HTML
    */
  def findReverseMissingCodes(letter: String): Set[String] = {
    val htmlFilePath = s"src/test/resources/wikipedia/$letter-table.html"
    val pseudoScalaFilePath = s"src/test/resources/pseudo-scala/$letter-language-codes.txt"
    val htmlCodes = extractHtmlCodes(htmlFilePath, letter)
    val pseudoScalaCodes = extractPseudoScalaCodes(pseudoScalaFilePath)
    pseudoScalaCodes.diff(htmlCodes)
  }
  
  /**
    * Find language codes that are present in the tab file but missing from the Scala file.
    * @return A set of language codes that are in tab file but not in Scala file
    */
  def findTabNotInScala(): Set[String] = {
    val tabCodes = extractTabCodes(tabFilePath)
    val scalaCodes = extractScalaCodes(scalaCodePath)
    tabCodes.diff(scalaCodes)
  }
  
  /**
    * Find language codes that are present in the Scala file but missing from the tab file.
    * @return A set of language codes that are in Scala file but not in tab file
    */
  def findScalaNotInTab(): Set[String] = {
    val tabCodes = extractTabCodes(tabFilePath)
    val scalaCodes = extractScalaCodes(scalaCodePath)
    scalaCodes.diff(tabCodes)
  }
}
