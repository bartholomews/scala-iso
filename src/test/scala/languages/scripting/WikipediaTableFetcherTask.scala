package languages.scripting

import java.io.{File, PrintWriter}
import java.net.{HttpURLConnection, URL}
import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
  * Test for fetching ISO 639 language code tables from Wikipedia.
  *
  * This test iterates over all letters in the Latin alphabet and for each letter:
  * 1. Constructs a URL to the Wikipedia page for ISO 639 codes starting with that letter
  * 2. Fetches the HTML content from the URL
  * 3. Extracts the wikitable containing language codes
  * 4. Saves the table to a file in the resources/wikipedia directory
  *
  * Note: This test does not execute the actual fetching and parsing.
  * It only provides the code to do so, which can be manually executed for specific letters.
  */
object WikipediaTableFetcherTask extends App {

  // Define the list of letters to iterate over (a-z)
  val letters = ('c' to 'z').toList

  // Print the list of letters for manual testing
  println("Letters to iterate over:")
  println(letters.mkString(", "))
  println("To test a single letter, modify the code to use only that letter.")

  /**
    * Fetches the HTML content from a URL.
    *
    * @param urlString The URL to fetch
    * @return A Try containing the HTML content as a String if successful
    */
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

  /**
    * Extracts the wikitable from the HTML content.
    *
    * @param html The HTML content
    * @return A Try containing the table HTML as a String if successful
    */
  def extractTable(html: String): Try[String] = {
    Try {
      // Look for the specific table class mentioned in the requirements
      val tableStartTag = """<table class="wikitable sortable">"""
      val tableEndTag = """</table>"""

      val tableStartIndex = html.indexOf(tableStartTag)
      if (tableStartIndex == -1)
        throw new Exception("Table not found in HTML content")

      val tableEndIndex = html.indexOf(tableEndTag, tableStartIndex) + tableEndTag.length
      html.substring(tableStartIndex, tableEndIndex)
    }
  }

  /**
    * Saves the table HTML to a file.
    *
    * @param letter The letter corresponding to the table
    * @param tableHtml The table HTML content
    * @return A Try indicating success or failure
    */
  def saveTable(letter: Char, tableHtml: String): Try[Unit] = {
    Try {
      val outputDir = new File("src/test/resources/wikipedia")
      if (!outputDir.exists())
        outputDir.mkdirs()

      val outputFile = new File(outputDir, s"$letter-table.html")
      val writer = new PrintWriter(outputFile)
      try {
        writer.println(tableHtml)
        println(s"Saved table for letter '$letter' to ${outputFile.getPath}")
      } finally writer.close()
    }
  }

  /**
    * Processes a single letter by fetching the Wikipedia page, extracting the table,
    * and saving it to a file.
    *
    * @param letter The letter to process
    */
  def processLetter(letter: Char): Unit = {
    Thread.sleep(5000)
    println(s"Processing letter: $letter")

    // Construct the URL for the letter
    val url = s"https://en.wikipedia.org/wiki/ISO_639:$letter"
    println(s"Fetching URL: $url")

    // Fetch the HTML content
    fetchHtml(url) match {
      case Success(html) =>
        println(s"Successfully fetched HTML for letter '$letter'")

        // Extract the table
        extractTable(html) match {
          case Success(tableHtml) =>
            println(s"Successfully extracted table for letter '$letter'")

            // Save the table to a file
            saveTable(letter, tableHtml) match {
              case Success(_) =>
                println(s"Successfully saved table for letter '$letter'")
              case Failure(e) =>
                println(s"Failed to save table for letter '$letter': ${e.getMessage}")
            }
          case Failure(e) =>
            println(s"Failed to extract table for letter '$letter': ${e.getMessage}")
        }
      case Failure(e) =>
        println(s"Failed to fetch HTML for letter '$letter': ${e.getMessage}")
    }
  }

  // Note: This code is not executed automatically
  // To process all letters, uncomment the following code:
  /*
  // Process each letter
   */
  letters.foreach { letter =>
    processLetter(letter)
  }

  // To process a single letter for manual testing, uncomment and modify the following code:
  /*
  // Process a single letter (e.g., 'a')
   */
//  processLetter('a')

  println("To execute this test, uncomment the appropriate code section.")
  println("Make sure to add the necessary dependencies to build.sbt:")
  println("  - A library for HTTP requests (e.g., \"org.scalaj\" %% \"scalaj-http\" % \"2.4.2\")")
  println("  - A library for HTML parsing (optional, as we're using simple string operations)")
}
