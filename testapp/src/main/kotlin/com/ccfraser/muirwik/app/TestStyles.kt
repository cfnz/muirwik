package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestStyles.ComponentStyles.aStyle
import com.ccfraser.muirwik.wrapper.mButton
import com.ccfraser.muirwik.wrapper.spacingUnits
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

class TestStyles : RComponent<RProps, RState>() {
    var checked1: Boolean = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
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
    }

    private val anotherStyle = js {
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
            css { padding(2.spacingUnits)}

            mButton(caption = "Normal Button")
            mButton("Styled With Object") {
                css(aStyle)
            }
            mButton("Styled asDynamic") {
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
                attrs.asDynamic().style = anotherStyle
            }
            mButton("Styled with mods... should be black text") {
                css {
                    +aStyle
                    color = Color.black
                }
            }
        }
    }
}

fun RBuilder.testStyles() = child(TestStyles::class) {}
