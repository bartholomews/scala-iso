package languages.iso639_3

import io.bartholomews.iso.iso639_3.{LanguageCode => ISO639_3}
import io.circe.parser.parse
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class LanguageCodeTest extends AnyFunSuite with Matchers {

  test("Non-happy path - invalid codes") {
    val exception = intercept[NoSuchElementException] {
      ISO639_3.withName("invalid")
    }
    exception.getMessage should include("No value found for ISO 639-3 code 'invalid'")

    val invalidOption = ISO639_3.withNameOption("invalid")
    invalidOption shouldBe empty

    val invalidEither = ISO639_3.withNameEither("invalid")
    invalidEither.isLeft shouldBe true
    invalidEither.left.toOption.get.notFoundValue shouldBe "invalid"
  }

  test("ISO 639-1 code lookup") {
    // Parse the JSON file to get all languages with ISO 639-1 codes
    parse(Source.fromResource("iso-639-3.json").getLines().mkString) match {
      case Left(error) =>
        fail(s"Failed to parse JSON: ${error.message}")

      case Right(json) =>
        var totalIso639_1Codes = 0
        var successfulTests = 0

        json.asArray.get.toList.foreach { obj =>
          val jsonObj = obj.asObject.get
          
          // Check if this language has an ISO 639-1 code
          jsonObj("iso639_1").flatMap(_.asString).foreach { iso639_1 =>
            // Only process valid ISO 639-1 codes (exactly 2 characters, no special characters)
            if (iso639_1.length == 2 && iso639_1.matches("[a-z]{2}")) {
              val iso639_3 = jsonObj("iso639_3").get.asString.get
              val name = jsonObj("name").get.asString.get
              
              totalIso639_1Codes += 1
              
              try {
                // Test withName
                val language = ISO639_3.withName(iso639_1)
                language.value shouldBe iso639_3
                language.name shouldBe name
                language.iso639_1.get shouldBe iso639_1

                // Test withNameOption
                val languageOption = ISO639_3.withNameOption(iso639_1)
                languageOption shouldBe defined
                languageOption.get.value shouldBe iso639_3
                languageOption.get.name shouldBe name
                languageOption.get.iso639_1.get shouldBe iso639_1

                // Test withNameEither
                val languageEither = ISO639_3.withNameEither(iso639_1)
                languageEither.isRight shouldBe true
                languageEither.toOption.get.value shouldBe iso639_3
                languageEither.toOption.get.name shouldBe name
                languageEither.toOption.get.iso639_1.get shouldBe iso639_1
                
                successfulTests += 1
              } catch {
                case e: Throwable =>
                  fail(s"Error testing ISO 639-1 code '$iso639_1': ${e.getMessage}")
              }
            }
          }
        }
        
        // Ensure we have tested all ISO 639-1 codes
        successfulTests shouldBe totalIso639_1Codes
        
        // Make sure we actually tested some codes
        totalIso639_1Codes should be > 0
        
        // Print the number of ISO 639-1 codes tested for verification
        println(s"Successfully tested $successfulTests ISO 639-1 codes")
    }
  }

  test("Happy path for all language codes from JSON file") {
    // Parse the JSON file
    parse(Source.fromResource("iso-639-3.json").getLines().mkString) match {
      case Left(error) =>
        fail(s"Failed to parse JSON: ${error.message}")

      case Right(json) =>
        var totalCodes = 0
        var successfulTests = 0

        json.asArray.get.toList.foreach { obj =>
          val jsonObj = obj.asObject.get
          val iso639_3 = jsonObj("iso639_3").get.asString.get
          val name = jsonObj("name").get.asString.get

          totalCodes += 1

          try {
            val language = ISO639_3.withName(iso639_3)
            language.name shouldBe name
            language.value shouldBe iso639_3

            val languageOption = ISO639_3.withNameOption(iso639_3)
            languageOption shouldBe defined
            languageOption.get.name shouldBe name
            languageOption.get.value shouldBe iso639_3

            val languageEither = ISO639_3.withNameEither(iso639_3)
            languageEither.isRight shouldBe true
            languageEither.toOption.get.name shouldBe name
            languageEither.toOption.get.value shouldBe iso639_3

            successfulTests += 1
          } catch {
            case e: Throwable =>
              fail(s"Error testing code '$iso639_3': ${e.getMessage}")
          }
        }

        successfulTests shouldBe totalCodes
    }
  }
}
