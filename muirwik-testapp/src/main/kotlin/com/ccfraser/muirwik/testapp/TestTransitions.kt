package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.form.mFormControlLabel
import com.ccfraser.muirwik.components.mPaper
import com.ccfraser.muirwik.components.mSwitch
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.transitions.*
import kotlinext.js.js
import kotlinx.css.*
import react.*
import react.dom.div
import react.dom.svg
import styled.StyleSheet
import styled.css
import styled.styledDiv

private open class ShapeProps (
    val points: String,
    val stroke: String,
    val fill: String,
    val strokeWidth: Int
): RProps

private fun RBuilder.upsideDownV(w: Int, h: Int) {
    svg {
        attrs["width"] = "$w"
        attrs["height"] = "$h"
//        attrs["viewBox"] = "0 0 $w $h"

        child(createElement("polygon", ShapeProps("0,$h ${w/2},0 $w,$h", "silver", "white", 1)))
    }
}

private fun RBuilder.demoComponent() {
    mPaper(elevation = 4) {
        css {
            width = 100.px
            height = 100.px
            margin(1.spacingUnits)
        }
        upsideDownV(100, 100)
    }
}

class TestTransitions : RComponent<RProps, RState>() {
    var collapseShown: Boolean = false
    var fadeShown: Boolean = false
    var growShown: Boolean = false
    var slideShown: Boolean = false
    var zoomShown: Boolean = false

    object ComponentStyles : StyleSheet("TransitionStyles", isStatic = true) {
        val area by css {
            height = 180.px
            padding(2.spacingUnits)
        }
    }

    override fun RBuilder.render() {
        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        val altBuilder = RBuilder()
        div {
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Collapse", altBuilder.mSwitch(checked = collapseShown, onChange = {_, _ ->  setState {collapseShown = ! collapseShown}}))

                styledDiv {
                    css { display = Display.flex }
                    mCollapse(show = collapseShown) {
                        attrs.timeout = AutoTransitionDuration()
                        demoComponent()
                    }
                    mCollapse(show = collapseShown, collapsedHeight = 40.px) {
                        demoComponent()
                    }
                    mTypography("(Shows use of collapseHeight)")
                }
            }

            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Fade (with a slow duration set)", altBuilder.mSwitch(checked = fadeShown, onChange = {_, _ ->  setState {fadeShown = ! fadeShown}}))

                styledDiv {
                    css {
                        display = Display.flex
                    }
                    mFade(show = fadeShown) {
                        demoComponent()
                    }
                    mFade(show = fadeShown) {
                        attrs.timeout = SimpleTransitionDuration(1000)
                        demoComponent()
                    }
                    mFade(show = fadeShown, timeout = SimpleTransitionDuration(2000)) {
                        demoComponent()
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Grow", altBuilder.mSwitch(checked = growShown, onChange = {_, _ ->  setState {growShown = ! growShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mGrow(show = growShown) {
                        demoComponent()
                    }
                    mGrow(show = growShown, timeout = EnterExitTransitionDuration(500, 1500)) {
                        attrs.asDynamic().style = js {transformOrigin = "0 0 0"}
                        attrs.timeout = AutoTransitionDuration()
                        demoComponent()
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Slide", altBuilder.mSwitch(checked = slideShown, onChange = {_, _ ->  setState {slideShown = ! slideShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mSlide(show = slideShown, direction = SlideTransitionDirection.up) {
                        demoComponent()
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Zoom", altBuilder.mSwitch(checked = zoomShown, onChange = {_, _ ->  setState {zoomShown = ! zoomShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mZoom(show = zoomShown) {
                        demoComponent()
                    }
                    mZoom(show = zoomShown) {
                        attrs.asDynamic().style = js {transitionDelay = if (zoomShown) 500 else 0}
                        demoComponent()
                    }
                }
            }
        }
    }
}

fun RBuilder.testTransitions() = child(TestTransitions::class) {}
