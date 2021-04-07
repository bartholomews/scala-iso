package io.bartholomews.iso

import enumeratum.values.{StringEnum, StringEnumEntry}
import scala.collection.immutable.IndexedSeq

// https://en.wikipedia.org/wiki/ISO_639-1
// https://www.loc.gov/standards/iso639-2/php/English_list.php
sealed abstract class LanguageCode(val value: String) extends StringEnumEntry {
  def name: String
  def native: List[String]
}

object LanguageCode extends StringEnum[LanguageCode] {

  override val values: IndexedSeq[LanguageCode] = findValues

  case object AFAR extends LanguageCode(value = "aa") {
    override val name = "Afar"
    override val native = List("Afar")
  }

  case object ABKHAZIAN extends LanguageCode(value = "ab") {
    override val name = "Abkhazian"
    override val native = List("Аҧсуа")
  }

  case object AFRIKAANS extends LanguageCode(value = "af") {
    override val name = "Afrikaans"
    override val native = List("Afrikaans")
  }

  case object AKAN extends LanguageCode(value = "ak") {
    override val name = "Akan"
    override val native = List("Akana")
  }

  case object AMHARIC extends LanguageCode(value = "am") {
    override val name = "Amharic"
    override val native = List("አማርኛ")
  }

  case object ARAGONESE extends LanguageCode(value = "an") {
    override val name = "Aragonese"
    override val native = List("Aragonés")
  }

  case object ARABIC extends LanguageCode(value = "ar") {
    override val name = "Arabic"
    override val native = List("العربية")
  }

  case object ASSAMESE extends LanguageCode(value = "as") {
    override val name = "Assamese"
    override val native = List("অসমীয়া")
  }

  case object AVAR extends LanguageCode(value = "av") {
    override val name = "Avar"
    override val native = List("Авар")
  }

  case object AYMARA extends LanguageCode(value = "ay") {
    override val name = "Aymara"
    override val native = List("Aymar")
  }

  case object AZERBAIJANI extends LanguageCode(value = "az") {
    override val name = "Azerbaijani"
    override val native = List("Azərbaycanca", "آذربايجان")
  }

  case object BASHKIR extends LanguageCode(value = "ba") {
    override val name = "Bashkir"
    override val native = List("Башҡорт")
  }

  case object BELARUSIAN extends LanguageCode(value = "be") {
    override val name = "Belarusian"
    override val native = List("Беларуская")
  }

  case object BULGARIAN extends LanguageCode(value = "bg") {
    override val name = "Bulgarian"
    override val native = List("Български")
  }

  case object BIHARI extends LanguageCode(value = "bh") {
    override val name = "Bihari"
    override val native = List("भोजपुरी")
  }

  case object BISLAMA extends LanguageCode(value = "bi") {
    override val name = "Bislama"
    override val native = List("Bislama")
  }

  case object BAMBARA extends LanguageCode(value = "bm") {
    override val name = "Bambara"
    override val native = List("Bamanankan")
  }

  case object BENGALI extends LanguageCode(value = "bn") {
    override val name = "Bengali"
    override val native = List("বাংলা")
  }

  case object TIBETAN extends LanguageCode(value = "bo") {
    override val name = "Tibetan"
    override val native = List("བོད་ཡིག", "Bod skad")
  }

  case object BRETON extends LanguageCode(value = "br") {
    override val name = "Breton"
    override val native = List("Brezhoneg")
  }

  case object BOSNIAN extends LanguageCode(value = "bs") {
    override val name = "Bosnian"
    override val native = List("Bosanski")
  }

  case object CATALAN extends LanguageCode(value = "ca") {
    override val name = "Catalan"
    override val native = List("Català")
  }

  case object CHECHEN extends LanguageCode(value = "ce") {
    override val name = "Chechen"
    override val native = List("Нохчийн")
  }

  case object CHAMORRO extends LanguageCode(value = "ch") {
    override val name = "Chamorro"
    override val native = List("Chamoru")
  }

  case object CORSICAN extends LanguageCode(value = "co") {
    override val name = "Corsican"
    override val native = List("Corsu")
  }

  case object CREE extends LanguageCode(value = "cr") {
    override val name = "Cree"
    override val native = List("Nehiyaw")
  }

  case object CZECH extends LanguageCode(value = "cs") {
    override val name = "Czech"
    override val native = List("Česky")
  }

  case object OLD_CHURCH_SLAVONIC extends LanguageCode(value = "cu") {
    override val name = "Old Church Slavonic"
    override val native = List("словѣньскъ", "slověnĭskŭ")
  }

  case object CHUVASH extends LanguageCode(value = "cv") {
    override val name = "Chuvash"
    override val native = List("Чăваш")
  }

  case object WELSH extends LanguageCode(value = "cy") {
    override val name = "Welsh"
    override val native = List("Cymraeg")
  }

  case object DANISH extends LanguageCode(value = "da") {
    override val name = "Danish"
    override val native = List("Dansk")
  }

  case object GERMAN extends LanguageCode(value = "de") {
    override val name = "German"
    override val native = List("Deutsch")
  }

  case object DIVEHI extends LanguageCode(value = "dv") {
    override val name = "Divehi"
    override val native = List("ދިވެހިބަސް")
  }

  case object DZONGKHA extends LanguageCode(value = "dz") {
    override val name = "Dzongkha"
    override val native = List("ཇོང་ཁ")
  }

  case object EWE extends LanguageCode(value = "ee") {
    override val name = "Ewe"
    override val native = List("Ɛʋɛ")
  }

  case object GREEK extends LanguageCode(value = "el") {
    override val name = "Greek"
    override val native = List("Ελληνικά")
  }

  case object ENGLISH extends LanguageCode(value = "en") {
    override val name = "English"
    override val native = List("English")
  }

  case object ESPERANTO extends LanguageCode(value = "eo") {
    override val name = "Esperanto"
    override val native = List("Esperanto")
  }

  case object SPANISH extends LanguageCode(value = "es") {
    override val name = "Spanish"
    override val native = List("Español")
  }

  case object ESTONIAN extends LanguageCode(value = "et") {
    override val name = "Estonian"
    override val native = List("Eesti")
  }

  case object BASQUE extends LanguageCode(value = "eu") {
    override val name = "Basque"
    override val native = List("Euskara")
  }

  case object PERSIAN extends LanguageCode(value = "fa") {
    override val name = "Persian"
    override val native = List("فارسی")
  }

  case object PEUL extends LanguageCode(value = "ff") {
    override val name = "Peul"
    override val native = List("Fulfulde")
  }

  case object FINNISH extends LanguageCode(value = "fi") {
    override val name = "Finnish"
    override val native = List("Suomi")
  }

  case object FIJIAN extends LanguageCode(value = "fj") {
    override val name = "Fijian"
    override val native = List("Na Vosa Vakaviti")
  }

  case object FAROESE extends LanguageCode(value = "fo") {
    override val name = "Faroese"
    override val native = List("Føroyskt")
  }

  case object FRENCH extends LanguageCode(value = "fr") {
    override val name = "French"
    override val native = List("Français")
  }

  case object WEST_FRISIAN extends LanguageCode(value = "fy") {
    override val name = "West Frisian"
    override val native = List("Frysk")
  }

  case object IRISH extends LanguageCode(value = "ga") {
    override val name = "Irish"
    override val native = List("Gaeilge")
  }

  case object SCOTTISH_GAELIC extends LanguageCode(value = "gd") {
    override val name = "Scottish Gaelic"
    override val native = List("Gàidhlig")
  }

  case object GALICIAN extends LanguageCode(value = "gl") {
    override val name = "Galician"
    override val native = List("Galego")
  }

  case object GUARANI extends LanguageCode(value = "gn") {
    override val name = "Guarani"
    override val native = List("Avañe'ẽ")
  }

  case object GUJARATI extends LanguageCode(value = "gu") {
    override val name = "Gujarati"
    override val native = List("ગુજરાતી")
  }

  case object MANX extends LanguageCode(value = "gv") {
    override val name = "Manx"
    override val native = List("Gaelg")
  }

  case object HAUSA extends LanguageCode(value = "ha") {
    override val name = "Hausa"
    override val native = List("هَوُسَ")
  }

  case object HEBREW extends LanguageCode(value = "he") {
    override val name = "Hebrew"
    override val native = List("עברית")
  }

  case object HINDI extends LanguageCode(value = "hi") {
    override val name = "Hindi"
    override val native = List("हिन्दी")
  }

  case object HIRI_MOTU extends LanguageCode(value = "ho") {
    override val name = "Hiri Motu"
    override val native = List("Hiri Motu")
  }

  case object CROATIAN extends LanguageCode(value = "hr") {
    override val name = "Croatian"
    override val native = List("Hrvatski")
  }

  case object HAITIAN extends LanguageCode(value = "ht") {
    override val name = "Haitian"
    override val native = List("Krèyol ayisyen")
  }

  case object HUNGARIAN extends LanguageCode(value = "hu") {
    override val name = "Hungarian"
    override val native = List("Magyar")
  }

  case object ARMENIAN extends LanguageCode(value = "hy") {
    override val name = "Armenian"
    override val native = List("Հայերեն")
  }

  case object HERERO extends LanguageCode(value = "hz") {
    override val name = "Herero"
    override val native = List("Otsiherero")
  }

  case object INTERLINGUA extends LanguageCode(value = "ia") {
    override val name = "Interlingua"
    override val native = List("Interlingua")
  }

  case object INDONESIAN extends LanguageCode(value = "id") {
    override val name = "Indonesian"
    override val native = List("Bahasa Indonesia")
  }

  case object INTERLINGUE extends LanguageCode(value = "ie") {
    override val name = "Interlingue"
    override val native = List("Interlingue")
  }

  case object IGBO extends LanguageCode(value = "ig") {
    override val name = "Igbo"
    override val native = List("Igbo")
  }

  case object SICHUAN_YI extends LanguageCode(value = "ii") {
    override val name = "Sichuan Yi"
    override val native = List("ꆇꉙ", "四川彝语")
  }

  case object INUPIAK extends LanguageCode(value = "ik") {
    override val name = "Inupiak"
    override val native = List("Iñupiak")
  }

  case object IDO extends LanguageCode(value = "io") {
    override val name = "Ido"
    override val native = List("Ido")
  }

  case object ICELANDIC extends LanguageCode(value = "is") {
    override val name = "Icelandic"
    override val native = List("Íslenska")
  }

  case object ITALIAN extends LanguageCode(value = "it") {
    override val name = "Italian"
    override val native = List("Italiano")
  }

  case object INUKTITUT extends LanguageCode(value = "iu") {
    override val name = "Inuktitut"
    override val native = List("ᐃᓄᒃᑎᑐᑦ")
  }

  case object JAPANESE extends LanguageCode(value = "ja") {
    override val name = "Japanese"
    override val native = List("日本語")
  }

  case object JAVANESE extends LanguageCode(value = "jv") {
    override val name = "Javanese"
    override val native = List("Basa Jawa")
  }

  case object GEORGIAN extends LanguageCode(value = "ka") {
    override val name = "Georgian"
    override val native = List("ქართული")
  }

  case object KONGO extends LanguageCode(value = "kg") {
    override val name = "Kongo"
    override val native = List("KiKongo")
  }

  case object KIKUYU extends LanguageCode(value = "ki") {
    override val name = "Kikuyu"
    override val native = List("Gĩkũyũ")
  }

  case object KUANYAMA extends LanguageCode(value = "kj") {
    override val name = "Kuanyama"
    override val native = List("Kuanyama")
  }

  case object KAZAKH extends LanguageCode(value = "kk") {
    override val name = "Kazakh"
    override val native = List("Қазақша")
  }

  case object GREENLANDIC extends LanguageCode(value = "kl") {
    override val name = "Greenlandic"
    override val native = List("Kalaallisut")
  }

  case object CAMBODIAN extends LanguageCode(value = "km") {
    override val name = "Cambodian"
    override val native = List("ភាសាខ្មែរ")
  }

  case object KANNADA extends LanguageCode(value = "kn") {
    override val name = "Kannada"
    override val native = List("ಕನ್ನಡ")
  }

  case object KOREAN extends LanguageCode(value = "ko") {
    override val name = "Korean"
    override val native = List("한국어")
  }

  case object KANURI extends LanguageCode(value = "kr") {
    override val name = "Kanuri"
    override val native = List("Kanuri")
  }

  case object KASHMIRI extends LanguageCode(value = "ks") {
    override val name = "Kashmiri"
    override val native = List("कश्मीरी", "كشميري")
  }

  case object KURDISH extends LanguageCode(value = "ku") {
    override val name = "Kurdish"
    override val native = List("Kurdî", "كوردی")
  }

  case object KOMI extends LanguageCode(value = "kv") {
    override val name = "Komi"
    override val native = List("Коми")
  }

  case object CORNISH extends LanguageCode(value = "kw") {
    override val name = "Cornish"
    override val native = List("Kernewek")
  }

  case object KIRGHIZ extends LanguageCode(value = "ky") {
    override val name = "Kirghiz"
    override val native = List("Kırgızca", "Кыргызча")
  }

  case object LATIN extends LanguageCode(value = "la") {
    override val name = "Latin"
    override val native = List("Latina")
  }

  case object LUXEMBOURGISH extends LanguageCode(value = "lb") {
    override val name = "Luxembourgish"
    override val native = List("Lëtzebuergesch")
  }

  case object GANDA extends LanguageCode(value = "lg") {
    override val name = "Ganda"
    override val native = List("Luganda")
  }

  case object LIMBURGIAN extends LanguageCode(value = "li") {
    override val name = "Limburgian"
    override val native = List("Limburgs")
  }

  case object LINGALA extends LanguageCode(value = "ln") {
    override val name = "Lingala"
    override val native = List("Lingála")
  }

  case object LAOTIAN extends LanguageCode(value = "lo") {
    override val name = "Laotian"
    override val native = List("ລາວ", "Pha xa lao")
  }

  case object LITHUANIAN extends LanguageCode(value = "lt") {
    override val name = "Lithuanian"
    override val native = List("Lietuvių")
  }

  case object LUBA_KATANGA extends LanguageCode(value = "lu") {
    override val name = "Luba-Katanga"
    override val native = List("Tshiluba")
  }

  case object LATVIAN extends LanguageCode(value = "lv") {
    override val name = "Latvian"
    override val native = List("Latviešu")
  }

  case object MALAGASY extends LanguageCode(value = "mg") {
    override val name = "Malagasy"
    override val native = List("Malagasy")
  }

  case object MARSHALLESE extends LanguageCode(value = "mh") {
    override val name = "Marshallese"
    override val native = List("Kajin Majel", "Ebon")
  }

  case object MAORI extends LanguageCode(value = "mi") {
    override val name = "Maori"
    override val native = List("Māori")
  }

  case object MACEDONIAN extends LanguageCode(value = "mk") {
    override val name = "Macedonian"
    override val native = List("Македонски")
  }

  case object MALAYALAM extends LanguageCode(value = "ml") {
    override val name = "Malayalam"
    override val native = List("മലയാളം")
  }

  case object MONGOLIAN extends LanguageCode(value = "mn") {
    override val name = "Mongolian"
    override val native = List("Монгол")
  }

  case object MOLDOVAN extends LanguageCode(value = "mo") {
    override val name = "Moldovan"
    override val native = List("Moldovenească")
  }

  case object MARATHI extends LanguageCode(value = "mr") {
    override val name = "Marathi"
    override val native = List("मराठी")
  }

  case object MALAY extends LanguageCode(value = "ms") {
    override val name = "Malay"
    override val native = List("Bahasa Melayu")
  }

  case object MALTESE extends LanguageCode(value = "mt") {
    override val name = "Maltese"
    override val native = List("bil-Malti")
  }

  case object BURMESE extends LanguageCode(value = "my") {
    override val name = "Burmese"
    override val native = List("မြန်မာစာ")
  }

  case object NAURUAN extends LanguageCode(value = "na") {
    override val name = "Nauruan"
    override val native = List("Dorerin Naoero")
  }

  case object NORWEGIAN_BOKMAL extends LanguageCode(value = "nb") {
    override val name = "Norwegian Bokmål"
    override val native = List("Norsk bokmål")
  }

  case object NORTH_NDEBELE extends LanguageCode(value = "nd") {
    override val name = "North Ndebele"
    override val native = List("Sindebele")
  }

  case object NEPALI extends LanguageCode(value = "ne") {
    override val name = "Nepali"
    override val native = List("नेपाली")
  }

  case object NDONGA extends LanguageCode(value = "ng") {
    override val name = "Ndonga"
    override val native = List("Oshiwambo")
  }

  case object DUTCH extends LanguageCode(value = "nl") {
    override val name = "Dutch"
    override val native = List("Nederlands")
  }

  case object NORWEGIAN_NYNORSK extends LanguageCode(value = "nn") {
    override val name = "Norwegian Nynorsk"
    override val native = List("Norsk nynorsk")
  }

  case object NORWEGIAN extends LanguageCode(value = "no") {
    override val name = "Norwegian"
    override val native = List("Norsk")
  }

  case object SOUTH_NDEBELE extends LanguageCode(value = "nr") {
    override val name = "South Ndebele"
    override val native = List("isiNdebele")
  }

  case object NAVAJO extends LanguageCode(value = "nv") {
    override val name = "Navajo"
    override val native = List("Diné bizaad")
  }

  case object CHICHEWA extends LanguageCode(value = "ny") {
    override val name = "Chichewa"
    override val native = List("Chi-Chewa")
  }

  case object OCCITAN extends LanguageCode(value = "oc") {
    override val name = "Occitan"
    override val native = List("Occitan")
  }

  case object OJIBWA extends LanguageCode(value = "oj") {
    override val name = "Ojibwa"
    override val native = List("ᐊᓂᔑᓈᐯᒧᐎᓐ", "Anishinaabemowin")
  }

  case object OROMO extends LanguageCode(value = "om") {
    override val name = "Oromo"
    override val native = List("Oromoo")
  }

  case object ORIYA extends LanguageCode(value = "or") {
    override val name = "Oriya"
    override val native = List("ଓଡ଼ିଆ")
  }

  case object OSSETIAN extends LanguageCode(value = "os") {
    override val name = "Ossetian / Ossetic"
    override val native = List("Иронау")
  }

  case object PUNJABI extends LanguageCode(value = "pa") {
    override val name = "Panjabi / Punjabi"
    override val native = List("ਪੰਜਾਬੀ", "पंजाबी", "پنجابي")
  }

  case object PALI extends LanguageCode(value = "pi") {
    override val name = "Pali"
    override val native = List("Pāli", "पाऴि")
  }

  case object POLISH extends LanguageCode(value = "pl") {
    override val name = "Polish"
    override val native = List("Polski")
  }

  case object PASHTO extends LanguageCode(value = "ps") {
    override val name = "Pashto"
    override val native = List("پښتو")
  }

  case object PORTUGUESE extends LanguageCode(value = "pt") {
    override val name = "Portuguese"
    override val native = List("Português")
  }

  case object QUECHUA extends LanguageCode(value = "qu") {
    override val name = "Quechua"
    override val native = List("Runa Simi")
  }

  case object RAETO_ROMANCE extends LanguageCode(value = "rm") {
    override val name = "Raeto Romance"
    override val native = List("Rumantsch")
  }

  case object KIRUNDI extends LanguageCode(value = "rn") {
    override val name = "Kirundi"
    override val native = List("Kirundi")
  }

  case object ROMANIAN extends LanguageCode(value = "ro") {
    override val name = "Romanian"
    override val native = List("Română")
  }

  case object RUSSIAN extends LanguageCode(value = "ru") {
    override val name = "Russian"
    override val native = List("Русский")
  }

  case object RWANDI extends LanguageCode(value = "rw") {
    override val name = "Rwandi"
    override val native = List("Kinyarwandi")
  }

  case object SANSKRIT extends LanguageCode(value = "sa") {
    override val name = "Sanskrit"
    override val native = List("संस्कृतम्")
  }

  case object SARDINIAN extends LanguageCode(value = "sc") {
    override val name = "Sardinian"
    override val native = List("Sardu")
  }

  case object SINDHI extends LanguageCode(value = "sd") {
    override val name = "Sindhi"
    override val native = List("सिनधि")
  }

  case object NORTHERN_SAMI extends LanguageCode(value = "se") {
    override val name = "Northern Sami"
    override val native = List("Sámegiella")
  }

  case object SANGO extends LanguageCode(value = "sg") {
    override val name = "Sango"
    override val native = List("Sängö")
  }

  case object SERBO_CROATIAN extends LanguageCode(value = "sh") {
    override val name = "Serbo-Croatian"
    override val native = List("Srpskohrvatski", "Српскохрватски")
  }

  case object SINHALESE extends LanguageCode(value = "si") {
    override val name = "Sinhalese"
    override val native = List("සිංහල")
  }

  case object SLOVAK extends LanguageCode(value = "sk") {
    override val name = "Slovak"
    override val native = List("Slovenčina")
  }

  case object SLOVENIAN extends LanguageCode(value = "sl") {
    override val name = "Slovenian"
    override val native = List("Slovenščina")
  }

  case object SAMOAN extends LanguageCode(value = "sm") {
    override val name = "Samoan"
    override val native = List("Gagana Samoa")
  }

  case object SHONA extends LanguageCode(value = "sn") {
    override val name = "Shona"
    override val native = List("chiShona")
  }

  case object SOMALIA extends LanguageCode(value = "so") {
    override val name = "Somalia"
    override val native = List("Soomaaliga")
  }

  case object ALBANIAN extends LanguageCode(value = "sq") {
    override val name = "Albanian"
    override val native = List("Shqip")
  }

  case object SERBIAN extends LanguageCode(value = "sr") {
    override val name = "Serbian"
    override val native = List("Српски")
  }

  case object SWATI extends LanguageCode(value = "ss") {
    override val name = "Swati"
    override val native = List("SiSwati")
  }

  case object SOUTHERN_SOTHO extends LanguageCode(value = "st") {
    override val name = "Southern Sotho"
    override val native = List("Sesotho")
  }

  case object SUNDANESE extends LanguageCode(value = "su") {
    override val name = "Sundanese"
    override val native = List("Basa Sunda")
  }

  case object SWEDISH extends LanguageCode(value = "sv") {
    override val name = "Swedish"
    override val native = List("Svenska")
  }

  case object SWAHILI extends LanguageCode(value = "sw") {
    override val name = "Swahili"
    override val native = List("Kiswahili")
  }

  case object TAMIL extends LanguageCode(value = "ta") {
    override val name = "Tamil"
    override val native = List("தமிழ்")
  }

  case object TELUGU extends LanguageCode(value = "te") {
    override val name = "Telugu"
    override val native = List("తెలుగు")
  }

  case object TAJIK extends LanguageCode(value = "tg") {
    override val name = "Tajik"
    override val native = List("Тоҷикӣ")
  }

  case object THAI extends LanguageCode(value = "th") {
    override val name = "Thai"
    override val native = List("ไทย", "Phasa Thai")
  }

  case object TIGRINYA extends LanguageCode(value = "ti") {
    override val name = "Tigrinya"
    override val native = List("ትግርኛ")
  }

  case object TURKMEN extends LanguageCode(value = "tk") {
    override val name = "Turkmen"
    override val native = List("Туркмен", "تركمن")
  }

  case object FILIPINO extends LanguageCode(value = "tl") {
    override val name = "Tagalog / Filipino"
    override val native = List("Tagalog")
  }

  case object TSWANA extends LanguageCode(value = "tn") {
    override val name = "Tswana"
    override val native = List("Setswana")
  }

  case object TONGA extends LanguageCode(value = "to") {
    override val name = "Tonga"
    override val native = List("Lea Faka-Tonga")
  }

  case object TURKISH extends LanguageCode(value = "tr") {
    override val name = "Turkish"
    override val native = List("Türkçe")
  }

  case object TSONGA extends LanguageCode(value = "ts") {
    override val name = "Tsonga"
    override val native = List("Xitsonga")
  }

  case object TATAR extends LanguageCode(value = "tt") {
    override val name = "Tatar"
    override val native = List("Tatarça")
  }

  case object TWI extends LanguageCode(value = "tw") {
    override val name = "Twi"
    override val native = List("Twi")
  }

  case object TAHITIAN extends LanguageCode(value = "ty") {
    override val name = "Tahitian"
    override val native = List("Reo Mā`ohi")
  }

  case object UYGHUR extends LanguageCode(value = "ug") {
    override val name = "Uyghur"
    override val native = List("Uyƣurqə", "ئۇيغۇرچە")
  }

  case object UKRAINIAN extends LanguageCode(value = "uk") {
    override val name = "Ukrainian"
    override val native = List("Українська")
  }

  case object URDU extends LanguageCode(value = "ur") {
    override val name = "Urdu"
    override val native = List("اردو")
  }

  case object UZBEK extends LanguageCode(value = "uz") {
    override val name = "Uzbek"
    override val native = List("Ўзбек")
  }

  case object VENDA extends LanguageCode(value = "ve") {
    override val name = "Venda"
    override val native = List("Tshivenḓa")
  }

  case object VIETNAMESE extends LanguageCode(value = "vi") {
    override val name = "Vietnamese"
    override val native = List("Tiếng Việt")
  }

  case object VOLAPUK extends LanguageCode(value = "vo") {
    override val name = "Volapük"
    override val native = List("Volapük")
  }

  case object WALLOON extends LanguageCode(value = "wa") {
    override val name = "Walloon"
    override val native = List("Walon")
  }

  case object WOLOF extends LanguageCode(value = "wo") {
    override val name = "Wolof"
    override val native = List("Wollof")
  }

  case object XHOSA extends LanguageCode(value = "xh") {
    override val name = "Xhosa"
    override val native = List("isiXhosa")
  }

  case object YIDDISH extends LanguageCode(value = "yi") {
    override val name = "Yiddish"
    override val native = List("ייִדיש")
  }

  case object YORUBA extends LanguageCode(value = "yo") {
    override val name = "Yoruba"
    override val native = List("Yorùbá")
  }

  case object ZHUANG extends LanguageCode(value = "za") {
    override val name = "Zhuang"
    override val native = List("Cuengh", "Tôô", "壮语")
  }

  case object CHINESE extends LanguageCode(value = "zh") {
    override val name = "Chinese"
    override val native = List("中文")
  }

  case object ZULU extends LanguageCode(value = "zu") {
    override val name = "Zulu"
    override val native = List("isiZulu")
  }
}
