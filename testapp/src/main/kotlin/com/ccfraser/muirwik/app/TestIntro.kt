package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestIntro.ComponentStyles.typographyStyle
import com.ccfraser.muirwik.wrapper.MTypographyVariant
import com.ccfraser.muirwik.wrapper.mTypography
import com.ccfraser.muirwik.wrapper.spacingUnits
import kotlinx.css.TextAlign
import kotlinx.css.em
import kotlinx.css.padding
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestIntro : RComponent<RProps, RState>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val typographyStyle by css {
             fontSize = 1.em
             paddingBottom = 2.spacingUnits
        }
    }

    override fun RBuilder.render() {

        styledDiv {
            css {
                padding(3.spacingUnits)
                textAlign = TextAlign.center
            }

            mTypography("Welcome to Muirwik", MTypographyVariant.display2) { css { paddingBottom = 3.spacingUnits }}
            mTypography{
                css(typographyStyle)
                +"Muirwik gets it name from being a "
                a("https://material-ui.com/", "_black") { +"Material UI" }
                +" "
                a("https://reactjs.org/", "_black") { +"React" }
                +" wrapper (written) in "
                a("https://kotlinlang.org/", "_black") { +"Kotlin" }
                +"."
            }

            mTypography ("This project is a test/playground area for the Muirwik components. It is modeled loosely " +
                    "off the Material UI demo pages and uses their source as a reference.") {
                css(typographyStyle)
            }

            mTypography {
                css(typographyStyle)
                +"For more information, see the above links (particularly the Material UI one) and this project's "
                a(href = "#") { +"Github page"}
                +"."
            }
        }
    }
}

fun RBuilder.testIntro() = child(TestIntro::class) {}
