package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.HRefOptions
import com.ccfraser.muirwik.testapp.TestIntro.ComponentStyles.typographyStyle
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.img
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestIntro : RComponent<Props, State>() {

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
            typography("Welcome to Muirwik", TypographyVariant.h3) { css { paddingBottom = 3.spacingUnits }}
            typography {
                css(typographyStyle)
                +"Muirwik gets it name from being a "
                link("Material UI", HRefOptions("https://material-ui.com/"))
                +" "
                link("React", HRefOptions("https://reactjs.org/"))
                +" wrapper (written) in "
                link("Kotlin", HRefOptions("https://kotlinlang.org/"))
                +"."
            }

            typography("This project is a test/playground area for the Muirwik components. It is modeled loosely " +
                    "off the Material UI demo pages and uses their source as a reference, however some pages have a bit " +
                    "more experimentation going on at the expense of a highly tuned visual experience (i.e. some bits " +
                    "might be a bit ugly).") {
                css(typographyStyle)
            }

            typography {
                css(typographyStyle)
                +"For more information, see the above links (particularly the Material UI one) and this project's "
                link("Github page", HRefOptions("https://github.com/cfnz/muirwik"))
                +"."
            }
        }
    }
}

