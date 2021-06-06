package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.TestStyles.ComponentStyles.aStyle
import com.ccfraser.muirwik.testapp.TestStyles.ComponentStyles.divPadding
import kotlinext.js.js
import kotlinx.css.*
import kotlinx.css.properties.BoxShadow
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.toStyle

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestStyles : RComponent<RProps, RState>() {
    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val divPadding by css {
            padding(2.spacingUnits)
        }

        val aStyle by css {
            background = "linear-gradient(45deg, #9d6bfe 30%, #FF8E53 90%)"
            borderRadius = 8.px
            borderStyle = BorderStyle.none
            color = Color.white
            height = 48.px
            padding(0.px, 30.px)
            margin(1.spacingUnits)
            boxShadow += BoxShadow(false, 0.px, 3.px, 5.px, 2.px, rgba(255, 105, 135, 0.3))
        }
        val style2 by css {
            color = Color.aqua
            fontSize = 2.em
        }
    }

    private val styleByJs = js {
        background = "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)"
        borderRadius = 8
        border = 0
        color = "white"
        height = "48px"
        padding = "0 30px"
        margin = "8px"
        boxShadow = "0 3px 5px 2px rgba(255, 105, 135, .30)"
    }

    override fun RBuilder.render() {
        styledDiv {
            css(divPadding)

            mButton(caption = "Normal Button")

            mButton(caption = "Styled with inline CSS Builder") {
                css {
                    color = Color.white
                    background = Color.silver.toString()
                }
            }
        }

        styledDiv {
            css(divPadding)

            mButton("Styled With CSS Builder") {
                css(aStyle)
            }

            mButton("Styled with CSS Builder + mods... should be black text") {
                // Sometimes this does not get applied as Black... a refresh of the page fixes, but not sure exactly what is going on.
                css {
                    +aStyle
                    color = Color.black
                }
            }
        }

        styledDiv {
            css(divPadding)

            mTypography("The following uses js Style methods. You would not style mButton this way since it is " +
                    "a styled component (i.e. uses CSSBulder), but sometimes you have to style a component that is not " +
                    "using CSSBuilder... for example, when you have to pass the css as a prop. The following is some ways to do it.")
            mButton("Styled asDynamic js Style") {
                attrs.asDynamic().style = js {
                    background = "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)"
                    borderRadius = 3
                    border = 0
                    color = "white"
                    height = "48px"
                    padding = "0 30px"
                    boxShadow = "0 3px 5px 2px rgba(255, 105, 135, .30)"
                }
            }

            mButton("Styled with asDynamic var") {
                attrs.asDynamic().style = styleByJs
            }

            // If you need to pass a style to some object (usually as a prop), you can also use a typesafe CSSBuilder
            // and then convert it to a js style object.
            val myStyle = CSSBuilder().apply { borderRadius = 6.px; background = Color.silver.toString() }.toStyle()
            mButton("Styled with CSSBuilder converted to js") {
                attrs.asDynamic().style = myStyle
            }

            // Sometimes you need the class name of the CSS Builder...
            mButton("Sometimes you need to use class name") {
                attrs.className = "${ComponentStyles.name}-${::aStyle.name}"
            }
        }
    }
}

fun RBuilder.testStyles() = child(TestStyles::class) {}
