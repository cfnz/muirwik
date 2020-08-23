package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.mLink
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.Intro.ComponentStyles.typographyStyle
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.img
import styled.StyleSheet
import styled.css
import styled.styledDiv


class Intro : RComponent<RProps, RState>() {

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
                textAlign = TextAlign.left
            }
            img("Muirwik Box", "/images/muirwik-logo.png") {}
            mTypography("Welcome to Muirwik", MTypographyVariant.h3) { css { paddingBottom = 3.spacingUnits }}
            mTypography{
                css(typographyStyle)
                +"Muirwik gets it name from being a "
                mLink("Material UI", "https://material-ui.com/")
                +" "
                mLink("React", "https://reactjs.org/")
                +" wrapper (written) in "
                mLink("Kotlin", "https://kotlinlang.org/")
                +"."
            }

            mTypography ("This project is a test/playground area for the Muirwik components. It is modeled loosely " +
                    "off the Material UI demo pages and uses their source as a reference, however some pages have a bit " +
                    "more experimentation going on at the expense of a highly tuned visual experience (i.e. some bits " +
                    "might be a bit ugly).") {
                css(typographyStyle)
            }

            mTypography {
                css(typographyStyle)
                +"For more information, see the above links (particularly the Material UI one) and this project's "
                mLink("Github page", "https://github.com/cfnz/muirwik")
                +"."
            }
        }
    }
}

fun RBuilder.intro() = child(Intro::class) {}
