package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.lab.mAutoComplete
import com.ccfraser.muirwik.testapp.AutoCompleteStyles.margin
import kotlinx.css.*
import react.*
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledSpan

data class Film(val title: String, val year: Int)
data class Country(val code: String, val label: String, val phone: String)


private object AutoCompleteStyles : StyleSheet("AutoCompleteStyles", isStatic = true) {
    val margin by css {
        marginRight = 10.px
        fontSize = 18.px
    }
}

private val testAutoComplete = functionalComponent<RProps> { props ->
    mTypography("This demo shows usage of the Lab AutoComplete component (and how to use flag emojis)")
    mAutoComplete(top100Films, { params -> mTextField("Combo Box", variant = MFormControlVariant.outlined) {
        spreadProps(params)
    } }) {
        attrs.getOptionLabel = {option -> option.title }
        css {
          width = 350.px
        }
    }

    var selectedCountry: Country? by useState(null)

    mAutoComplete(countries, { params -> mTextField("Choose a country", variant = MFormControlVariant.outlined) {
            spreadProps(params)
        }
    }) {
        attrs.apply {
            id = "country-select-demo"
            autoHighlight = true
            getOptionLabel = { option -> option.label }
            renderOption = { option, _ ->
                Fragment {
                    styledSpan {
                        +isoCountryCodeToFlagEmoji(option.code)
                        css(margin)
                    }
                    +"${option.label} (${option.code}) +${option.phone} "
                }
            }
            onChange = { event, value, reason ->
                println("onChange event: $event, value: $value, reason: $reason")
                selectedCountry = value
            }
        }
        css {
            width = 350.px
        }
    }

    selectedCountry?.let {
        mTypography("Selected country's flag: ${isoCountryCodeToFlagEmoji(it.code)}")
    }

    mAutoComplete(top100Films.sortedBy { film -> film.title }.toTypedArray(), { params -> mTextField("Grouped", variant = MFormControlVariant.outlined) {
        spreadProps(params)
    } }) {
        attrs.id = "grouped-auto-complete"
        attrs.groupBy = { option -> option.title.first().toString() }
        attrs.getOptionLabel = {option -> option.title }
        css {
            width = 350.px
        }
    }

    mAutoComplete(top100Films.sortedBy { film -> film.title }.toTypedArray(), { params -> mTextField("Multiple Values", variant = MFormControlVariant.outlined) {
        spreadProps(params)
    } }) {
        attrs.id = "multiple-values"
        attrs.multiple = true
        attrs.filterSelectedOptions = true
        attrs.getOptionLabel = {option -> option.title }
        css {
            width = 700.px
        }
    }
}

fun isoCountryCodeToFlagEmoji(code: String): String {
    return if (code.length != 2) {
        code
    } else {
        val sb = StringBuilder()
        code.forEach {
            val s: String = js("String.fromCodePoint(it + 127397)") as String
            sb.append(s)
        }
        sb.toString()
    }
}

val top100Films = arrayOf(
    Film("The Shawshank Redemption", 1994),
    Film("The Godfather", 1972),
    Film("The Godfather: Part II", 1974),
    Film("The Dark Knight", 2008),
    Film("12 Angry Men", 1957),
    Film("Schindler's List", 1993),
    Film("Pulp Fiction", 1994),
    Film("The Lord of the Rings: The Return of the King", 2003),
    Film("The Good, the Bad and the Ugly", 1966),
    Film("Fight Club", 1999),
    Film("The Lord of the Rings: The Fellowship of the Ring", 2001),
    Film("Star Wars: Episode V - The Empire Strikes Back", 1980),
    Film("Forrest Gump", 1994),
    Film("Inception", 2010),
    Film("The Lord of the Rings: The Two Towers", 2002),
    Film("One Flew Over the Cuckoo's Nest", 1975),
    Film("Goodfellas", 1990),
    Film("The Matrix", 1999),
    Film("Seven Samurai", 1954),
    Film("Star Wars: Episode IV - A New Hope", 1977),
    Film("City of God", 2002),
    Film("Se7en", 1995),
    Film("The Silence of the Lambs", 1991),
    Film("It's a Wonderful Life", 1946),
    Film("Life Is Beautiful", 1997),
    Film("The Usual Suspects", 1995),
    Film("Léon: The Professional", 1994),
    Film("Spirited Away", 2001),
    Film("Saving Private Ryan", 1998),
    Film("Once Upon a Time in the West", 1968),
    Film("American History X", 1998),
    Film("Interstellar", 2014),
    Film("Casablanca", 1942),
    Film("City Lights", 1931),
    Film("Psycho", 1960),
    Film("The Green Mile", 1999),
    Film("The Intouchables", 2011),
    Film("Modern Times", 1936),
    Film("Raiders of the Lost Ark", 1981),
    Film("Rear Window", 1954),
    Film("The Pianist", 2002),
    Film("The Departed", 2006),
    Film("Terminator 2: Judgment Day", 1991),
    Film("Back to the Future", 1985),
    Film("Whiplash", 2014),
    Film("Gladiator", 2000),
    Film("Memento", 2000),
    Film("The Prestige", 2006),
    Film("The Lion King", 1994),
    Film("Apocalypse Now", 1979),
    Film("Alien", 1979),
    Film("Sunset Boulevard", 1950),
    Film("Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb", 1964),
    Film("The Great Dictator", 1940),
    Film("Cinema Paradiso", 1988),
    Film("The Lives of Others", 2006),
    Film("Grave of the Fireflies", 1988),
    Film("Paths of Glory", 1957),
    Film("Django Unchained", 2012),
    Film("The Shining", 1980),
    Film("WALL·E", 2008),
    Film("American Beauty", 1999),
    Film("The Dark Knight Rises", 2012),
    Film("Princess Mononoke", 1997),
    Film("Aliens", 1986),
    Film("Oldboy", 2003),
    Film("Once Upon a Time in America", 1984),
    Film("Witness for the Prosecution", 1957),
    Film("Das Boot", 1981),
    Film("Citizen Kane", 1941),
    Film("North by Northwest", 1959),
    Film("Vertigo", 1958),
    Film("Star Wars: Episode VI - Return of the Jedi", 1983),
    Film("Reservoir Dogs", 1992),
    Film("Braveheart", 1995),
    Film("M", 1931),
    Film("Requiem for a Dream", 2000),
    Film("Amélie", 2001),
    Film("A Clockwork Orange", 1971),
    Film("Like Stars on Earth", 2007),
    Film("Taxi Driver", 1976),
    Film("Lawrence of Arabia", 1962),
    Film("Double Indemnity", 1944),
    Film("Eternal Sunshine of the Spotless Mind", 2004),
    Film("Amadeus", 1984),
    Film("To Kill a Mockingbird", 1962),
    Film("Toy Story 3", 2010),
    Film("Logan", 2017),
    Film("Full Metal Jacket", 1987),
    Film("Dangal", 2016),
    Film("The Sting", 1973),
    Film("2001: A Space Odyssey", 1968),
    Film("Singin' in the Rain", 1952),
    Film("Toy Story", 1995),
    Film("Bicycle Thieves", 1948),
    Film("The Kid", 1921),
    Film("Inglourious Basterds", 2009),
    Film("Snatch", 2000),
    Film("3 Idiots", 2009),
    Film("Monty Python and the Holy Grail", 1975),
)

val countries = arrayOf(
    Country("AD", "Andorra", "376"),
    Country("AE", "United Arab Emirates", "971"),
    Country("AF", "Afghanistan", "93"),
    Country("AG", "Antigua and Barbuda", "268"),
    Country("AI", "Anguilla", "264"),
    Country("AL", "Albania", "355"),
    Country("AM", "Armenia", "374"),
    Country("AO", "Angola", "244"),
    Country("AQ", "Antarctica", "672"),
    Country("AR", "Argentina", "54"),
    Country("AS", "American Samoa", "684"),
    Country("AT", "Austria", "43"),
    Country("AU", "Australia", "61"),
    Country("AW", "Aruba", "297"),
    Country("AX", "Alland Islands", "358"),
    Country("AZ", "Azerbaijan", "994"),
    Country("BA", "Bosnia and Herzegovina", "387"),
    Country("BB", "Barbados", "246"),
    Country("BD", "Bangladesh", "880"),
    Country("BE", "Belgium", "32"),
    Country("BF", "Burkina Faso", "226"),
    Country("BG", "Bulgaria", "359"),
    Country("BH", "Bahrain", "973"),
    Country("BI", "Burundi", "257"),
    Country("BJ", "Benin", "229"),
    Country("BL", "Saint Barthelemy", "590"),
    Country("BM", "Bermuda", "441"),
    Country("BN", "Brunei Darussalam", "673"),
    Country("BO", "Bolivia", "591"),
    Country("BR", "Brazil", "55"),
    Country("BS", "Bahamas", "242"),
    Country("BT", "Bhutan", "975"),
    Country("BV", "Bouvet Island", "47"),
    Country("BW", "Botswana", "267"),
    Country("BY", "Belarus", "375"),
    Country("BZ", "Belize", "501"),
    Country("CA", "Canada", "1"),
    Country("CC", "Cocos (Keeling) Islands", "61"),
    Country("CD", "Congo, Democratic Republic of the", "243"),
    Country("CF", "Central African Republic", "236"),
    Country("CG", "Congo, Republic of the", "242"),
    Country("CH", "Switzerland", "41"),
    Country("CI", "Cote d'Ivoire", "225"),
    Country("CK", "Cook Islands", "682"),
    Country("CL", "Chile", "56"),
    Country("CM", "Cameroon", "237"),
    Country("CN", "China", "86"),
    Country("CO", "Colombia", "57"),
    Country("CR", "Costa Rica", "506"),
    Country("CU", "Cuba", "53"),
    Country("CV", "Cape Verde", "238"),
    Country("CW", "Curacao", "599"),
    Country("CX", "Christmas Island", "61"),
    Country("CY", "Cyprus", "357"),
    Country("CZ", "Czech Republic", "420"),
    Country("DE", "Germany", "49"),
    Country("DJ", "Djibouti", "253"),
    Country("DK", "Denmark", "45"),
    Country("DM", "Dominica", "767"),
    Country("DO", "Dominican Republic", "809"),
    Country("DZ", "Algeria", "213"),
    Country("EC", "Ecuador", "593"),
    Country("EE", "Estonia", "372"),
    Country("EG", "Egypt", "20"),
    Country("EH", "Western Sahara", "212"),
    Country("ER", "Eritrea", "291"),
    Country("ES", "Spain", "34"),
    Country("ET", "Ethiopia", "251"),
    Country("FI", "Finland", "358"),
    Country("FJ", "Fiji", "679"),
    Country("FK", "Falkland Islands (Malvinas", "500"),
    Country("FM", "Micronesia, Federated States of", "691"),
    Country("FO", "Faroe Islands", "298"),
    Country("FR", "France","33"),
    Country("GA", "Gabon", "241"),
    Country("GB", "United Kingdom", "44"),
    Country("GD", "Grenada", "473"),
    Country("GE", "Georgia", "995"),
    Country("GF", "French Guiana", "594"),
    Country("GG", "Guernsey", "44"),
    Country("GH", "Ghana", "233"),
    Country("GI", "Gibraltar", "350"),
    Country("GL", "Greenland", "299"),
    Country("GM", "Gambia", "220"),
    Country("GN", "Guinea", "224"),
    Country("GP", "Guadeloupe", "590"),
    Country("GQ", "Equatorial Guinea", "240"),
    Country("GR", "Greece", "30"),
    Country("GS", "South Georgia and the South Sandwich Islands", "500"),
    Country("GT", "Guatemala", "502"),
    Country("GU", "Guam", "671"),
    Country("GW", "Guinea-Bissau", "245"),
    Country("GY", "Guyana", "592"),
    Country("HK", "Hong Kong", "852"),
    Country("HM", "Heard Island and McDonald Islands", "672"),
    Country("HN", "Honduras", "504"),
    Country("HR", "Croatia", "385"),
    Country("HT", "Haiti", "509"),
    Country("HU", "Hungary", "36"),
    Country("ID", "Indonesia", "62"),
    Country("IE", "Ireland", "353"),
    Country("IL", "Israel", "972"),
    Country("IM", "Isle of Man", "44"),
    Country("IN", "India", "91"),
    Country("IO", "British Indian Ocean Territory", "246"),
    Country("IQ", "Iraq", "964"),
    Country("IR", "Iran, Islamic Republic of", "98"),
    Country("IS", "Iceland", "354"),
    Country("IT", "Italy", "39"),
    Country("JE", "Jersey", "44"),
    Country("JM", "Jamaica", "876"),
    Country("JO", "Jordan", "962"),
    Country("JP", "Japan", "81"),
    Country("KE", "Kenya", "254"),
    Country("KG", "Kyrgyzstan", "996"),
    Country("KH", "Cambodia", "855"),
    Country("KI", "Kiribati", "686"),
    Country("KM", "Comoros", "269"),
    Country("KN", "Saint Kitts and Nevis", "869"),
    Country("KP", "Korea, Democratic People's Republic of", "850"),
    Country("KR", "Korea, Republic of", "82"),
    Country("KW", "Kuwait", "965"),
    Country("KY", "Cayman Islands", "345"),
    Country("KZ", "Kazakhstan", "7"),
    Country("LA", "Lao People's Democratic Republic", "856"),
    Country("LB", "Lebanon", "961"),
    Country("LC", "Saint Lucia", "758"),
    Country("LI", "Liechtenstein", "423"),
    Country("LK", "Sri Lanka", "94"),
    Country("LR", "Liberia", "231"),
    Country("LS", "Lesotho", "266"),
    Country("LT", "Lithuania", "370"),
    Country("LU", "Luxembourg", "352"),
    Country("LV", "Latvia", "371"),
    Country("LY", "Libya", "218"),
    Country("MA", "Morocco", "212"),
    Country("MC", "Monaco", "377"),
    Country("MD", "Moldova, Republic of", "373"),
    Country("ME", "Montenegro", "382"),
    Country("MF", "Saint Martin (French part", "590"),
    Country("MG", "Madagascar", "261"),
    Country("MH", "Marshall Islands", "692"),
    Country("MK", "Macedonia, the Former Yugoslav Republic of", "389"),
    Country("ML", "Mali", "223"),
    Country("MM", "Myanmar", "95"),
    Country("MN", "Mongolia", "976"),
    Country("MO", "Macao", "853"),
    Country("MP", "Northern Mariana Islands", "670"),
    Country("MQ", "Martinique", "596"),
    Country("MR", "Mauritania", "222"),
    Country("MS", "Montserrat", "664"),
    Country("MT", "Malta", "356"),
    Country("MU", "Mauritius", "230"),
    Country("MV", "Maldives", "960"),
    Country("MW", "Malawi", "265"),
    Country("MX", "Mexico", "52"),
    Country("MY", "Malaysia", "60"),
    Country("MZ", "Mozambique", "258"),
    Country("NA", "Namibia", "264"),
    Country("NC", "New Caledonia", "687"),
    Country("NE", "Niger", "227"),
    Country("NF", "Norfolk Island", "672"),
    Country("NG", "Nigeria", "234"),
    Country("NI", "Nicaragua", "505"),
    Country("NL", "Netherlands", "31"),
    Country("NO", "Norway", "47"),
    Country("NP", "Nepal", "977"),
    Country("NR", "Nauru", "674"),
    Country("NU", "Niue", "683"),
    Country("NZ", "New Zealand", "64"),
    Country("OM", "Oman", "968"),
    Country("PA", "Panama", "507"),
    Country("PE", "Peru", "51"),
    Country("PF", "French Polynesia", "689"),
    Country("PG", "Papua New Guinea", "675"),
    Country("PH", "Philippines", "63"),
    Country("PK", "Pakistan", "92"),
    Country("PL", "Poland", "48"),
    Country("PM", "Saint Pierre and Miquelon", "508"),
    Country("PN", "Pitcairn", "870"),
    Country("PR", "Puerto Rico", "1"),
    Country("PS", "Palestine, State of", "970"),
    Country("PT", "Portugal", "351"),
    Country("PW", "Palau", "680"),
    Country("PY", "Paraguay", "595"),
    Country("QA", "Qatar", "974"),
    Country("RE", "Reunion", "262"),
    Country("RO", "Romania", "40"),
    Country("RS", "Serbia", "381"),
    Country("RU", "Russian Federation", "7"),
    Country("RW", "Rwanda", "250"),
    Country("SA", "Saudi Arabia", "966"),
    Country("SB", "Solomon Islands", "677"),
    Country("SC", "Seychelles", "248"),
    Country("SD", "Sudan", "249"),
    Country("SE", "Sweden", "46"),
    Country("SG", "Singapore", "65"),
    Country("SH", "Saint Helena", "290"),
    Country("SI", "Slovenia", "386"),
    Country("SJ", "Svalbard and Jan Mayen", "47"),
    Country("SK", "Slovakia", "421"),
    Country("SL", "Sierra Leone", "232"),
    Country("SM", "San Marino", "378"),
    Country("SN", "Senegal", "221"),
    Country("SO", "Somalia", "252"),
    Country("SR", "Suriname", "597"),
    Country("SS", "South Sudan", "211"),
    Country("ST", "Sao Tome and Principe", "239"),
    Country("SV", "El Salvador", "503"),
    Country("SX", "Sint Maarten (Dutch part)", "721"),
    Country("SY", "Syrian Arab Republic", "963"),
    Country("SZ", "Swaziland", "268"),
    Country("TC", "Turks and Caicos Islands", "649"),
    Country("TD", "Chad", "235"),
    Country("TF", "French Southern Territories", "262"),
    Country("TG", "Togo", "228"),
    Country("TH", "Thailand", "66"),
    Country("TJ", "Tajikistan", "992"),
    Country("TK", "Tokelau", "690"),
    Country("TL", "Timor-Leste", "670"),
    Country("TM", "Turkmenistan", "993"),
    Country("TN", "Tunisia", "216"),
    Country("TO", "Tonga", "676"),
    Country("TR", "Turkey", "90"),
    Country("TT", "Trinidad and Tobago", "868"),
    Country("TV", "Tuvalu", "688"),
    Country("TW", "Taiwan, Province of China", "886"),
    Country("TZ", "United Republic of Tanzania", "255"),
    Country("UA", "Ukraine", "380"),
    Country("UG", "Uganda", "256"),
    Country("US", "United States", "1"),
    Country("UY", "Uruguay", "598"),
    Country("UZ", "Uzbekistan", "998"),
    Country("VA", "Holy See (Vatican City State", "379"),
    Country("VC", "Saint Vincent and the Grenadines", "784"),
    Country("VE", "Venezuela", "58"),
    Country("VG", "British Virgin Islands", "284"),
    Country("VI", "US Virgin Islands", "340"),
    Country("VN", "Vietnam", "84"),
    Country("VU", "Vanuatu", "678"),
    Country("WF", "Wallis and Futuna", "681"),
    Country("WS", "Samoa", "685"),
    Country("XK", "Kosovo", "383"),
    Country("YE", "Yemen", "967"),
    Country("YT", "Mayotte", "262"),
    Country("ZA", "South Africa", "27"),
    Country("ZM", "Zambia", "260"),
    Country("ZW", "Zimbabwe", "263"),
)

fun RBuilder.testAutoComplete() = child(testAutoComplete)
