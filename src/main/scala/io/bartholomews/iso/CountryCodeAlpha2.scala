package io.bartholomews.iso

import enumeratum.values.{StringEnum, StringEnumEntry}
import scala.collection.immutable.IndexedSeq

// https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
sealed abstract class CountryCodeAlpha2(val value: String) extends StringEnumEntry {
  def name: String
}

object CountryCodeAlpha2 extends StringEnum[CountryCodeAlpha2] {

  override val values: IndexedSeq[CountryCodeAlpha2] = findValues

  case object AFGHANISTAN extends CountryCodeAlpha2(value = "AF") {
    override val name = "Afghanistan"
  }

  case object ALAND_ISLANDS extends CountryCodeAlpha2(value = "AX") {
    override val name = "Åland Islands"
  }

  case object ALBANIA extends CountryCodeAlpha2(value = "AL") {
    override val name = "Albania"
  }

  case object ALGERIA extends CountryCodeAlpha2(value = "DZ") {
    override val name = "Algeria"
  }

  case object AMERICAN_SAMOA extends CountryCodeAlpha2(value = "AS") {
    override val name = "American Samoa"
  }

  case object ANDORRA extends CountryCodeAlpha2(value = "AD") {
    override val name = "Andorra"
  }

  case object ANGOLA extends CountryCodeAlpha2(value = "AO") {
    override val name = "Angola"
  }

  case object ANGUILLA extends CountryCodeAlpha2(value = "AI") {
    override val name = "Anguilla"
  }

  case object ANTARCTICA extends CountryCodeAlpha2(value = "AQ") {
    override val name = "Antarctica"
  }

  case object ANTIGUA_AND_BARBUDA extends CountryCodeAlpha2(value = "AG") {
    override val name = "Antigua and Barbuda"
  }

  case object ARGENTINA extends CountryCodeAlpha2(value = "AR") {
    override val name = "Argentina"
  }

  case object ARMENIA extends CountryCodeAlpha2(value = "AM") {
    override val name = "Armenia"
  }

  case object ARUBA extends CountryCodeAlpha2(value = "AW") {
    override val name = "Aruba"
  }

  case object AUSTRALIA extends CountryCodeAlpha2(value = "AU") {
    override val name = "Australia"
  }

  case object AUSTRIA extends CountryCodeAlpha2(value = "AT") {
    override val name = "Austria"
  }

  case object AZERBAIJAN extends CountryCodeAlpha2(value = "AZ") {
    override val name = "Azerbaijan"
  }

  case object BAHAMAS extends CountryCodeAlpha2(value = "BS") {
    override val name = "Bahamas"
  }

  case object BAHRAIN extends CountryCodeAlpha2(value = "BH") {
    override val name = "Bahrain"
  }

  case object BANGLADESH extends CountryCodeAlpha2(value = "BD") {
    override val name = "Bangladesh"
  }

  case object BARBADOS extends CountryCodeAlpha2(value = "BB") {
    override val name = "Barbados"
  }

  case object BELARUS extends CountryCodeAlpha2(value = "BY") {
    override val name = "Belarus"
  }

  case object BELGIUM extends CountryCodeAlpha2(value = "BE") {
    override val name = "Belgium"
  }

  case object BELIZE extends CountryCodeAlpha2(value = "BZ") {
    override val name = "Belize"
  }

  case object BENIN extends CountryCodeAlpha2(value = "BJ") {
    override val name = "Benin"
  }

  case object BERMUDA extends CountryCodeAlpha2(value = "BM") {
    override val name = "Bermuda"
  }

  case object BHUTAN extends CountryCodeAlpha2(value = "BT") {
    override val name = "Bhutan"
  }

  case object BOLIVIA_PLURINATIONAL_STATE_OF extends CountryCodeAlpha2(value = "BO") {
    override val name = "Bolivia (Plurinational State of)"
  }

  case object BONAIRE_SINT_EUSTATIUS_AND_SABA extends CountryCodeAlpha2(value = "BQ") {
    override val name = "Bonaire, Sint Eustatius and Saba"
  }

  case object BOSNIA_AND_HERZEGOVINA extends CountryCodeAlpha2(value = "BA") {
    override val name = "Bosnia and Herzegovina"
  }

  case object BOTSWANA extends CountryCodeAlpha2(value = "BW") {
    override val name = "Botswana"
  }

  case object BOUVET_ISLAND extends CountryCodeAlpha2(value = "BV") {
    override val name = "Bouvet Island"
  }

  case object BRAZIL extends CountryCodeAlpha2(value = "BR") {
    override val name = "Brazil"
  }

  case object BRITISH_INDIAN_OCEAN_TERRITORY extends CountryCodeAlpha2(value = "IO") {
    override val name = "British Indian Ocean Territory"
  }

  case object BRUNEI_DARUSSALAM extends CountryCodeAlpha2(value = "BN") {
    override val name = "Brunei Darussalam"
  }

  case object BULGARIA extends CountryCodeAlpha2(value = "BG") {
    override val name = "Bulgaria"
  }

  case object BURKINA_FASO extends CountryCodeAlpha2(value = "BF") {
    override val name = "Burkina Faso"
  }

  case object BURUNDI extends CountryCodeAlpha2(value = "BI") {
    override val name = "Burundi"
  }

  case object CABO_VERDE extends CountryCodeAlpha2(value = "CV") {
    override val name = "Cabo Verde"
  }

  case object CAMBODIA extends CountryCodeAlpha2(value = "KH") {
    override val name = "Cambodia"
  }

  case object CAMEROON extends CountryCodeAlpha2(value = "CM") {
    override val name = "Cameroon"
  }

  case object CANADA extends CountryCodeAlpha2(value = "CA") {
    override val name = "Canada"
  }

  case object CAYMAN_ISLANDS extends CountryCodeAlpha2(value = "KY") {
    override val name = "Cayman Islands"
  }

  case object CENTRAL_AFRICAN_REPUBLIC extends CountryCodeAlpha2(value = "CF") {
    override val name = "Central African Republic"
  }

  case object CHAD extends CountryCodeAlpha2(value = "TD") {
    override val name = "Chad"
  }

  case object CHILE extends CountryCodeAlpha2(value = "CL") {
    override val name = "Chile"
  }

  case object CHINA extends CountryCodeAlpha2(value = "CN") {
    override val name = "China"
  }

  case object CHRISTMAS_ISLAND extends CountryCodeAlpha2(value = "CX") {
    override val name = "Christmas Island"
  }

  case object COCOS_KEELING_ISLANDS extends CountryCodeAlpha2(value = "CC") {
    override val name = "Cocos (Keeling) Islands"
  }

  case object COLOMBIA extends CountryCodeAlpha2(value = "CO") {
    override val name = "Colombia"
  }

  case object COMOROS extends CountryCodeAlpha2(value = "KM") {
    override val name = "Comoros"
  }

  case object CONGO extends CountryCodeAlpha2(value = "CG") {
    override val name = "Congo"
  }

  case object CONGO_DEMOCRATIC_REPUBLIC_OF_THE extends CountryCodeAlpha2(value = "CD") {
    override val name = "Congo, Democratic Republic of the"
  }

  case object COOK_ISLANDS extends CountryCodeAlpha2(value = "CK") {
    override val name = "Cook Islands"
  }

  case object COSTA_RICA extends CountryCodeAlpha2(value = "CR") {
    override val name = "Costa Rica"
  }

  case object COTE_DIVOIRE extends CountryCodeAlpha2(value = "CI") {
    override val name = "Côte d'Ivoire"
  }

  case object CROATIA extends CountryCodeAlpha2(value = "HR") {
    override val name = "Croatia"
  }

  case object CUBA extends CountryCodeAlpha2(value = "CU") {
    override val name = "Cuba"
  }

  case object CURACAO extends CountryCodeAlpha2(value = "CW") {
    override val name = "Curaçao"
  }

  case object CYPRUS extends CountryCodeAlpha2(value = "CY") {
    override val name = "Cyprus"
  }

  case object CZECHIA extends CountryCodeAlpha2(value = "CZ") {
    override val name = "Czechia"
  }

  case object DENMARK extends CountryCodeAlpha2(value = "DK") {
    override val name = "Denmark"
  }

  case object DJIBOUTI extends CountryCodeAlpha2(value = "DJ") {
    override val name = "Djibouti"
  }

  case object DOMINICA extends CountryCodeAlpha2(value = "DM") {
    override val name = "Dominica"
  }

  case object DOMINICAN_REPUBLIC extends CountryCodeAlpha2(value = "DO") {
    override val name = "Dominican Republic"
  }

  case object ECUADOR extends CountryCodeAlpha2(value = "EC") {
    override val name = "Ecuador"
  }

  case object EGYPT extends CountryCodeAlpha2(value = "EG") {
    override val name = "Egypt"
  }

  case object EL_SALVADOR extends CountryCodeAlpha2(value = "SV") {
    override val name = "El Salvador"
  }

  case object EQUATORIAL_GUINEA extends CountryCodeAlpha2(value = "GQ") {
    override val name = "Equatorial Guinea"
  }

  case object ERITREA extends CountryCodeAlpha2(value = "ER") {
    override val name = "Eritrea"
  }

  case object ESTONIA extends CountryCodeAlpha2(value = "EE") {
    override val name = "Estonia"
  }

  case object ESWATINI extends CountryCodeAlpha2(value = "SZ") {
    override val name = "Eswatini"
  }

  case object ETHIOPIA extends CountryCodeAlpha2(value = "ET") {
    override val name = "Ethiopia"
  }

  case object FALKLAND_ISLANDS_MALVINAS extends CountryCodeAlpha2(value = "FK") {
    override val name = "Falkland Islands (Malvinas)"
  }

  case object FAROE_ISLANDS extends CountryCodeAlpha2(value = "FO") {
    override val name = "Faroe Islands"
  }

  case object FIJI extends CountryCodeAlpha2(value = "FJ") {
    override val name = "Fiji"
  }

  case object FINLAND extends CountryCodeAlpha2(value = "FI") {
    override val name = "Finland"
  }

  case object FRANCE extends CountryCodeAlpha2(value = "FR") {
    override val name = "France"
  }

  case object FRENCH_GUIANA extends CountryCodeAlpha2(value = "GF") {
    override val name = "French Guiana"
  }

  case object FRENCH_POLYNESIA extends CountryCodeAlpha2(value = "PF") {
    override val name = "French Polynesia"
  }

  case object FRENCH_SOUTHERN_TERRITORIES extends CountryCodeAlpha2(value = "TF") {
    override val name = "French Southern Territories"
  }

  case object GABON extends CountryCodeAlpha2(value = "GA") {
    override val name = "Gabon"
  }

  case object GAMBIA extends CountryCodeAlpha2(value = "GM") {
    override val name = "Gambia"
  }

  case object GEORGIA extends CountryCodeAlpha2(value = "GE") {
    override val name = "Georgia"
  }

  case object GERMANY extends CountryCodeAlpha2(value = "DE") {
    override val name = "Germany"
  }

  case object GHANA extends CountryCodeAlpha2(value = "GH") {
    override val name = "Ghana"
  }

  case object GIBRALTAR extends CountryCodeAlpha2(value = "GI") {
    override val name = "Gibraltar"
  }

  case object GREECE extends CountryCodeAlpha2(value = "GR") {
    override val name = "Greece"
  }

  case object GREENLAND extends CountryCodeAlpha2(value = "GL") {
    override val name = "Greenland"
  }

  case object GRENADA extends CountryCodeAlpha2(value = "GD") {
    override val name = "Grenada"
  }

  case object GUADELOUPE extends CountryCodeAlpha2(value = "GP") {
    override val name = "Guadeloupe"
  }

  case object GUAM extends CountryCodeAlpha2(value = "GU") {
    override val name = "Guam"
  }

  case object GUATEMALA extends CountryCodeAlpha2(value = "GT") {
    override val name = "Guatemala"
  }

  case object GUERNSEY extends CountryCodeAlpha2(value = "GG") {
    override val name = "Guernsey"
  }

  case object GUINEA extends CountryCodeAlpha2(value = "GN") {
    override val name = "Guinea"
  }

  case object GUINEA_BISSAU extends CountryCodeAlpha2(value = "GW") {
    override val name = "Guinea-Bissau"
  }

  case object GUYANA extends CountryCodeAlpha2(value = "GY") {
    override val name = "Guyana"
  }

  case object HAITI extends CountryCodeAlpha2(value = "HT") {
    override val name = "Haiti"
  }

  case object HEARD_ISLAND_AND_MCDONALD_ISLANDS extends CountryCodeAlpha2(value = "HM") {
    override val name = "Heard Island and McDonald Islands"
  }

  case object HOLY_SEE extends CountryCodeAlpha2(value = "VA") {
    override val name = "Holy See"
  }

  case object HONDURAS extends CountryCodeAlpha2(value = "HN") {
    override val name = "Honduras"
  }

  case object HONG_KONG extends CountryCodeAlpha2(value = "HK") {
    override val name = "Hong Kong"
  }

  case object HUNGARY extends CountryCodeAlpha2(value = "HU") {
    override val name = "Hungary"
  }

  case object ICELAND extends CountryCodeAlpha2(value = "IS") {
    override val name = "Iceland"
  }

  case object INDIA extends CountryCodeAlpha2(value = "IN") {
    override val name = "India"
  }

  case object INDONESIA extends CountryCodeAlpha2(value = "ID") {
    override val name = "Indonesia"
  }

  case object IRAN_ISLAMIC_REPUBLIC_OF extends CountryCodeAlpha2(value = "IR") {
    override val name = "Iran (Islamic Republic of)"
  }

  case object IRAQ extends CountryCodeAlpha2(value = "IQ") {
    override val name = "Iraq"
  }

  case object IRELAND extends CountryCodeAlpha2(value = "IE") {
    override val name = "Ireland"
  }

  case object ISLE_OF_MAN extends CountryCodeAlpha2(value = "IM") {
    override val name = "Isle of Man"
  }

  case object ISRAEL extends CountryCodeAlpha2(value = "IL") {
    override val name = "Israel"
  }

  case object ITALY extends CountryCodeAlpha2(value = "IT") {
    override val name = "Italy"
  }

  case object JAMAICA extends CountryCodeAlpha2(value = "JM") {
    override val name = "Jamaica"
  }

  case object JAPAN extends CountryCodeAlpha2(value = "JP") {
    override val name = "Japan"
  }

  case object JERSEY extends CountryCodeAlpha2(value = "JE") {
    override val name = "Jersey"
  }

  case object JORDAN extends CountryCodeAlpha2(value = "JO") {
    override val name = "Jordan"
  }

  case object KAZAKHSTAN extends CountryCodeAlpha2(value = "KZ") {
    override val name = "Kazakhstan"
  }

  case object KENYA extends CountryCodeAlpha2(value = "KE") {
    override val name = "Kenya"
  }

  case object KIRIBATI extends CountryCodeAlpha2(value = "KI") {
    override val name = "Kiribati"
  }

  case object KOSOVO extends CountryCodeAlpha2(value = "XK") {
    override val name = "Kosovo"
  }

  case object KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF extends CountryCodeAlpha2(value = "KP") {
    override val name = "Korea (Democratic People's Republic of)"
  }

  case object KOREA_REPUBLIC_OF extends CountryCodeAlpha2(value = "KR") {
    override val name = "Korea, Republic of"
  }

  case object KUWAIT extends CountryCodeAlpha2(value = "KW") {
    override val name = "Kuwait"
  }

  case object KYRGYZSTAN extends CountryCodeAlpha2(value = "KG") {
    override val name = "Kyrgyzstan"
  }

  case object LAO_PEOPLES_DEMOCRATIC_REPUBLIC extends CountryCodeAlpha2(value = "LA") {
    override val name = "Lao People's Democratic Republic"
  }

  case object LATVIA extends CountryCodeAlpha2(value = "LV") {
    override val name = "Latvia"
  }

  case object LEBANON extends CountryCodeAlpha2(value = "LB") {
    override val name = "Lebanon"
  }

  case object LESOTHO extends CountryCodeAlpha2(value = "LS") {
    override val name = "Lesotho"
  }

  case object LIBERIA extends CountryCodeAlpha2(value = "LR") {
    override val name = "Liberia"
  }

  case object LIBYA extends CountryCodeAlpha2(value = "LY") {
    override val name = "Libya"
  }

  case object LIECHTENSTEIN extends CountryCodeAlpha2(value = "LI") {
    override val name = "Liechtenstein"
  }

  case object LITHUANIA extends CountryCodeAlpha2(value = "LT") {
    override val name = "Lithuania"
  }

  case object LUXEMBOURG extends CountryCodeAlpha2(value = "LU") {
    override val name = "Luxembourg"
  }

  case object MACAO extends CountryCodeAlpha2(value = "MO") {
    override val name = "Macao"
  }

  case object MADAGASCAR extends CountryCodeAlpha2(value = "MG") {
    override val name = "Madagascar"
  }

  case object MALAWI extends CountryCodeAlpha2(value = "MW") {
    override val name = "Malawi"
  }

  case object MALAYSIA extends CountryCodeAlpha2(value = "MY") {
    override val name = "Malaysia"
  }

  case object MALDIVES extends CountryCodeAlpha2(value = "MV") {
    override val name = "Maldives"
  }

  case object MALI extends CountryCodeAlpha2(value = "ML") {
    override val name = "Mali"
  }

  case object MALTA extends CountryCodeAlpha2(value = "MT") {
    override val name = "Malta"
  }

  case object MARSHALL_ISLANDS extends CountryCodeAlpha2(value = "MH") {
    override val name = "Marshall Islands"
  }

  case object MARTINIQUE extends CountryCodeAlpha2(value = "MQ") {
    override val name = "Martinique"
  }

  case object MAURITANIA extends CountryCodeAlpha2(value = "MR") {
    override val name = "Mauritania"
  }

  case object MAURITIUS extends CountryCodeAlpha2(value = "MU") {
    override val name = "Mauritius"
  }

  case object MAYOTTE extends CountryCodeAlpha2(value = "YT") {
    override val name = "Mayotte"
  }

  case object MEXICO extends CountryCodeAlpha2(value = "MX") {
    override val name = "Mexico"
  }

  case object MICRONESIA_FEDERATED_STATES_OF extends CountryCodeAlpha2(value = "FM") {
    override val name = "Micronesia (Federated States of)"
  }

  case object MOLDOVA_REPUBLIC_OF extends CountryCodeAlpha2(value = "MD") {
    override val name = "Moldova, Republic of"
  }

  case object MONACO extends CountryCodeAlpha2(value = "MC") {
    override val name = "Monaco"
  }

  case object MONGOLIA extends CountryCodeAlpha2(value = "MN") {
    override val name = "Mongolia"
  }

  case object MONTENEGRO extends CountryCodeAlpha2(value = "ME") {
    override val name = "Montenegro"
  }

  case object MONTSERRAT extends CountryCodeAlpha2(value = "MS") {
    override val name = "Montserrat"
  }

  case object MOROCCO extends CountryCodeAlpha2(value = "MA") {
    override val name = "Morocco"
  }

  case object MOZAMBIQUE extends CountryCodeAlpha2(value = "MZ") {
    override val name = "Mozambique"
  }

  case object MYANMAR extends CountryCodeAlpha2(value = "MM") {
    override val name = "Myanmar"
  }

  case object NAMIBIA extends CountryCodeAlpha2(value = "NA") {
    override val name = "Namibia"
  }

  case object NAURU extends CountryCodeAlpha2(value = "NR") {
    override val name = "Nauru"
  }

  case object NEPAL extends CountryCodeAlpha2(value = "NP") {
    override val name = "Nepal"
  }

  case object NETHERLANDS extends CountryCodeAlpha2(value = "NL") {
    override val name = "Netherlands"
  }

  case object NEW_CALEDONIA extends CountryCodeAlpha2(value = "NC") {
    override val name = "New Caledonia"
  }

  case object NEW_ZEALAND extends CountryCodeAlpha2(value = "NZ") {
    override val name = "New Zealand"
  }

  case object NICARAGUA extends CountryCodeAlpha2(value = "NI") {
    override val name = "Nicaragua"
  }

  case object NIGER extends CountryCodeAlpha2(value = "NE") {
    override val name = "Niger"
  }

  case object NIGERIA extends CountryCodeAlpha2(value = "NG") {
    override val name = "Nigeria"
  }

  case object NIUE extends CountryCodeAlpha2(value = "NU") {
    override val name = "Niue"
  }

  case object NORFOLK_ISLAND extends CountryCodeAlpha2(value = "NF") {
    override val name = "Norfolk Island"
  }

  case object NORTH_MACEDONIA extends CountryCodeAlpha2(value = "MK") {
    override val name = "North Macedonia"
  }

  case object NORTHERN_MARIANA_ISLANDS extends CountryCodeAlpha2(value = "MP") {
    override val name = "Northern Mariana Islands"
  }

  case object NORWAY extends CountryCodeAlpha2(value = "NO") {
    override val name = "Norway"
  }

  case object OMAN extends CountryCodeAlpha2(value = "OM") {
    override val name = "Oman"
  }

  case object PAKISTAN extends CountryCodeAlpha2(value = "PK") {
    override val name = "Pakistan"
  }

  case object PALAU extends CountryCodeAlpha2(value = "PW") {
    override val name = "Palau"
  }

  case object PALESTINE_STATE_OF extends CountryCodeAlpha2(value = "PS") {
    override val name = "Palestine, State of"
  }

  case object PANAMA extends CountryCodeAlpha2(value = "PA") {
    override val name = "Panama"
  }

  case object PAPUA_NEW_GUINEA extends CountryCodeAlpha2(value = "PG") {
    override val name = "Papua New Guinea"
  }

  case object PARAGUAY extends CountryCodeAlpha2(value = "PY") {
    override val name = "Paraguay"
  }

  case object PERU extends CountryCodeAlpha2(value = "PE") {
    override val name = "Peru"
  }

  case object PHILIPPINES extends CountryCodeAlpha2(value = "PH") {
    override val name = "Philippines"
  }

  case object PITCAIRN extends CountryCodeAlpha2(value = "PN") {
    override val name = "Pitcairn"
  }

  case object POLAND extends CountryCodeAlpha2(value = "PL") {
    override val name = "Poland"
  }

  case object PORTUGAL extends CountryCodeAlpha2(value = "PT") {
    override val name = "Portugal"
  }

  case object PUERTO_RICO extends CountryCodeAlpha2(value = "PR") {
    override val name = "Puerto Rico"
  }

  case object QATAR extends CountryCodeAlpha2(value = "QA") {
    override val name = "Qatar"
  }

  case object REUNION extends CountryCodeAlpha2(value = "RE") {
    override val name = "Réunion"
  }

  case object ROMANIA extends CountryCodeAlpha2(value = "RO") {
    override val name = "Romania"
  }

  case object RUSSIAN_FEDERATION extends CountryCodeAlpha2(value = "RU") {
    override val name = "Russian Federation"
  }

  case object RWANDA extends CountryCodeAlpha2(value = "RW") {
    override val name = "Rwanda"
  }

  case object SAINT_BARTHELEMY extends CountryCodeAlpha2(value = "BL") {
    override val name = "Saint Barthélemy"
  }

  case object SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA extends CountryCodeAlpha2(value = "SH") {
    override val name = "Saint Helena, Ascension and Tristan da Cunha"
  }

  case object SAINT_KITTS_AND_NEVIS extends CountryCodeAlpha2(value = "KN") {
    override val name = "Saint Kitts and Nevis"
  }

  case object SAINT_LUCIA extends CountryCodeAlpha2(value = "LC") {
    override val name = "Saint Lucia"
  }

  case object SAINT_MARTIN_FRENCH_PART extends CountryCodeAlpha2(value = "MF") {
    override val name = "Saint Martin (French part)"
  }

  case object SAINT_PIERRE_AND_MIQUELON extends CountryCodeAlpha2(value = "PM") {
    override val name = "Saint Pierre and Miquelon"
  }

  case object SAINT_VINCENT_AND_THE_GRENADINES extends CountryCodeAlpha2(value = "VC") {
    override val name = "Saint Vincent and the Grenadines"
  }

  case object SAMOA extends CountryCodeAlpha2(value = "WS") {
    override val name = "Samoa"
  }

  case object SAN_MARINO extends CountryCodeAlpha2(value = "SM") {
    override val name = "San Marino"
  }

  case object SAO_TOME_AND_PRINCIPE extends CountryCodeAlpha2(value = "ST") {
    override val name = "Sao Tome and Principe"
  }

  case object SAUDI_ARABIA extends CountryCodeAlpha2(value = "SA") {
    override val name = "Saudi Arabia"
  }

  case object SENEGAL extends CountryCodeAlpha2(value = "SN") {
    override val name = "Senegal"
  }

  case object SERBIA extends CountryCodeAlpha2(value = "RS") {
    override val name = "Serbia"
  }

  case object SEYCHELLES extends CountryCodeAlpha2(value = "SC") {
    override val name = "Seychelles"
  }

  case object SIERRA_LEONE extends CountryCodeAlpha2(value = "SL") {
    override val name = "Sierra Leone"
  }

  case object SINGAPORE extends CountryCodeAlpha2(value = "SG") {
    override val name = "Singapore"
  }

  case object SINT_MAARTEN_DUTCH_PART extends CountryCodeAlpha2(value = "SX") {
    override val name = "Sint Maarten (Dutch part)"
  }

  case object SLOVAKIA extends CountryCodeAlpha2(value = "SK") {
    override val name = "Slovakia"
  }

  case object SLOVENIA extends CountryCodeAlpha2(value = "SI") {
    override val name = "Slovenia"
  }

  case object SOLOMON_ISLANDS extends CountryCodeAlpha2(value = "SB") {
    override val name = "Solomon Islands"
  }

  case object SOMALIA extends CountryCodeAlpha2(value = "SO") {
    override val name = "Somalia"
  }

  case object SOUTH_AFRICA extends CountryCodeAlpha2(value = "ZA") {
    override val name = "South Africa"
  }

  case object SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS extends CountryCodeAlpha2(value = "GS") {
    override val name = "South Georgia and the South Sandwich Islands"
  }

  case object SOUTH_SUDAN extends CountryCodeAlpha2(value = "SS") {
    override val name = "South Sudan"
  }

  case object SPAIN extends CountryCodeAlpha2(value = "ES") {
    override val name = "Spain"
  }

  case object SRI_LANKA extends CountryCodeAlpha2(value = "LK") {
    override val name = "Sri Lanka"
  }

  case object SUDAN extends CountryCodeAlpha2(value = "SD") {
    override val name = "Sudan"
  }

  case object SURINAME extends CountryCodeAlpha2(value = "SR") {
    override val name = "Suriname"
  }

  case object SVALBARD_AND_JAN_MAYEN extends CountryCodeAlpha2(value = "SJ") {
    override val name = "Svalbard and Jan Mayen"
  }

  case object SWEDEN extends CountryCodeAlpha2(value = "SE") {
    override val name = "Sweden"
  }

  case object SWITZERLAND extends CountryCodeAlpha2(value = "CH") {
    override val name = "Switzerland"
  }

  case object SYRIAN_ARAB_REPUBLIC extends CountryCodeAlpha2(value = "SY") {
    override val name = "Syrian Arab Republic"
  }

  case object TAIWAN_PROVINCE_OF_CHINA extends CountryCodeAlpha2(value = "TW") {
    override val name = "Taiwan, Province of China"
  }

  case object TAJIKISTAN extends CountryCodeAlpha2(value = "TJ") {
    override val name = "Tajikistan"
  }

  case object TANZANIA_UNITED_REPUBLIC_OF extends CountryCodeAlpha2(value = "TZ") {
    override val name = "Tanzania, United Republic of"
  }

  case object THAILAND extends CountryCodeAlpha2(value = "TH") {
    override val name = "Thailand"
  }

  case object TIMOR_LESTE extends CountryCodeAlpha2(value = "TL") {
    override val name = "Timor-Leste"
  }

  case object TOGO extends CountryCodeAlpha2(value = "TG") {
    override val name = "Togo"
  }

  case object TOKELAU extends CountryCodeAlpha2(value = "TK") {
    override val name = "Tokelau"
  }

  case object TONGA extends CountryCodeAlpha2(value = "TO") {
    override val name = "Tonga"
  }

  case object TRINIDAD_AND_TOBAGO extends CountryCodeAlpha2(value = "TT") {
    override val name = "Trinidad and Tobago"
  }

  case object TUNISIA extends CountryCodeAlpha2(value = "TN") {
    override val name = "Tunisia"
  }

  case object TURKEY extends CountryCodeAlpha2(value = "TR") {
    override val name = "Turkey"
  }

  case object TURKMENISTAN extends CountryCodeAlpha2(value = "TM") {
    override val name = "Turkmenistan"
  }

  case object TURKS_AND_CAICOS_ISLANDS extends CountryCodeAlpha2(value = "TC") {
    override val name = "Turks and Caicos Islands"
  }

  case object TUVALU extends CountryCodeAlpha2(value = "TV") {
    override val name = "Tuvalu"
  }

  case object UGANDA extends CountryCodeAlpha2(value = "UG") {
    override val name = "Uganda"
  }

  case object UKRAINE extends CountryCodeAlpha2(value = "UA") {
    override val name = "Ukraine"
  }

  case object UNITED_ARAB_EMIRATES extends CountryCodeAlpha2(value = "AE") {
    override val name = "United Arab Emirates"
  }

  case object UNITED_KINGDOM_OF_GREAT_BRITAIN_AND_NORTHERN_IRELAND extends CountryCodeAlpha2(value = "GB") {
    override val name = "United Kingdom of Great Britain and Northern Ireland"
  }

  case object UNITED_STATES_OF_AMERICA extends CountryCodeAlpha2(value = "US") {
    override val name = "United States of America"
  }

  case object UNITED_STATES_MINOR_OUTLYING_ISLANDS extends CountryCodeAlpha2(value = "UM") {
    override val name = "United States Minor Outlying Islands"
  }

  case object URUGUAY extends CountryCodeAlpha2(value = "UY") {
    override val name = "Uruguay"
  }

  case object UZBEKISTAN extends CountryCodeAlpha2(value = "UZ") {
    override val name = "Uzbekistan"
  }

  case object VANUATU extends CountryCodeAlpha2(value = "VU") {
    override val name = "Vanuatu"
  }

  case object VENEZUELA_BOLIVARIAN_REPUBLIC_OF extends CountryCodeAlpha2(value = "VE") {
    override val name = "Venezuela (Bolivarian Republic of)"
  }

  case object VIET_NAM extends CountryCodeAlpha2(value = "VN") {
    override val name = "Viet Nam"
  }

  case object VIRGIN_ISLANDS_BRITISH extends CountryCodeAlpha2(value = "VG") {
    override val name = "Virgin Islands (British)"
  }

  case object VIRGIN_ISLANDS_US extends CountryCodeAlpha2(value = "VI") {
    override val name = "Virgin Islands (U.S.)"
  }

  case object WALLIS_AND_FUTUNA extends CountryCodeAlpha2(value = "WF") {
    override val name = "Wallis and Futuna"
  }

  case object WESTERN_SAHARA extends CountryCodeAlpha2(value = "EH") {
    override val name = "Western Sahara"
  }

  case object YEMEN extends CountryCodeAlpha2(value = "YE") {
    override val name = "Yemen"
  }

  case object ZAMBIA extends CountryCodeAlpha2(value = "ZM") {
    override val name = "Zambia"
  }

  case object ZIMBABWE extends CountryCodeAlpha2(value = "ZW") {
    override val name = "Zimbabwe"
  }

}
