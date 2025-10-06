package languages.iso639_3

import org.jsoup.Jsoup

import java.io.{File, FileOutputStream, InputStream}
import java.net.{HttpURLConnection, URL}
import java.nio.channels.Channels
import java.nio.file.{Files, Paths, StandardCopyOption}
import scala.io.Source
import scala.util.{Failure, Success, Try}

object ExtractTabTask extends App {
  val downloadUrl = "https://iso639-3.sil.org/code_tables/download_tables"
  val resourcesDir = "src/test/resources"
  val mainTabFileName = "iso-639-3.tab"
  val retirementsTabFileName = "iso-639-3_Retirements.tab"

  println("Starting to fetch ISO 639-3 tab files...")

  fetchHtml(downloadUrl) match {
    case Success(html) =>
      println(s"Successfully fetched HTML from $downloadUrl")
      val tabLinks = extractTabLinks(html)
      println(s"Found ${tabLinks.size} tab file links")
      downloadAndSaveTabFiles(tabLinks)

    case Failure(e) =>
      println(s"Failed to fetch HTML from $downloadUrl: ${e.getMessage}")
      e.printStackTrace()
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

  def extractTabLinks(html: String): List[String] = {
    val doc = Jsoup.parse(html)
    val links = doc.select("a[href]")

    links
      .eachAttr("href")
      .toArray
      .toList
      .filter(_.toString.endsWith(".tab"))
      .filter(link =>
        link.toString.contains("iso-639-3.tab") ||
          link.toString.contains("iso-639-3_Retirements.tab")
      )
      .map(_.toString)
      .distinct
  }

  def downloadAndSaveTabFiles(links: List[String]): Unit = {
    links.foreach { link =>
      val fileName = if (link.contains("Retirements")) retirementsTabFileName else mainTabFileName
      val outputFile = new File(resourcesDir, fileName)

      println(s"Downloading $link to ${outputFile.getPath}")

      downloadFile(link, outputFile) match {
        case Success(_) =>
          println(s"Successfully downloaded and saved $fileName")
        case Failure(e) =>
          println(s"Failed to download $link: ${e.getMessage}")
          e.printStackTrace()
      }
    }
  }

  def downloadFile(urlString: String, outputFile: File): Try[Unit] = {
    Try {
      val url = new URL(urlString)
      val connection = url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("GET")
      connection.setRequestProperty("User-Agent", "Mozilla/5.0")

      val resourcesDirectory = new File(resourcesDir)
      if (!resourcesDirectory.exists())
        resourcesDirectory.mkdirs()

      val inputStream = connection.getInputStream
      Files.copy(inputStream, outputFile.toPath, StandardCopyOption.REPLACE_EXISTING)
      inputStream.close()
      connection.disconnect()
    }
  }
}
