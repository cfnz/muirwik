package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.form.mFormControlLabel
import com.ccfraser.muirwik.components.mPaper
import com.ccfraser.muirwik.components.mSwitch
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.transitions.*
import com.ccfraser.muirwik.testapp.TestTransitions.ComponentStyles.paper
import kotlinext.js.js
import kotlinx.css.*
import react.*
import react.dom.div
import react.dom.jsStyle
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestTransitions : RComponent<RProps, RState>() {
    var collapseShown: Boolean = false
    var fadeShown: Boolean = false
    var growShown: Boolean = false
    var slideShown: Boolean = false
    var zoomShown: Boolean = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val area by css {
            height = 180.px
            padding(2.spacingUnits)
        }
        val paper by css {
            width = 100.px
            height = 100.px
            margin(1.spacingUnits)
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
                        mPaper(elevation = 4) { css(paper) }
                    }
                    mCollapse(show = collapseShown, collapsedHeight = 40.px) {
                        mPaper(elevation = 4) { css(paper) }
                    }
                    mTypography("(Shows use of collapseHeight)")
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Fade", altBuilder.mSwitch(checked = fadeShown, onChange = {_, _ ->  setState {fadeShown = ! fadeShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mFade(show = fadeShown) {
                        attrs.timeout = SimpleTransitionDuration(2000)
                        mPaper(elevation = 4) { css(paper) }
                    }
                    mFade(show = fadeShown, timeout = SimpleTransitionDuration(2000)) {
                        mPaper(elevation = 4) { css(paper) }
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Grow", altBuilder.mSwitch(checked = growShown, onChange = {_, _ ->  setState {growShown = ! growShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mGrow(show = growShown) {
                        mPaper(elevation = 4) { css(paper) }
                    }
                    mGrow(show = growShown, timeout = EnterExitTransitionDuration(500, 1500)) {
                        attrs.asDynamic().style = js {transformOrigin = "0 0 0"}
                        attrs.timeout = AutoTransitionDuration()
                        mPaper(elevation = 4) { css(paper) }
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Slide", altBuilder.mSwitch(checked = slideShown, onChange = {_, _ ->  setState {slideShown = ! slideShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mSlide(show = slideShown, direction = SlideTransitionDirection.up) {
                        mPaper(elevation = 4) { css(paper) }
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Zoom", altBuilder.mSwitch(checked = zoomShown, onChange = {_, _ ->  setState {zoomShown = ! zoomShown}}))
                styledDiv {
                    css { display = Display.flex }
                    mZoom(show = zoomShown) {
                        mPaper(elevation = 4) { css(paper) }
                    }
                    mZoom(show = zoomShown) {
                        attrs.asDynamic().style = js {transitionDelay = if (zoomShown) 500 else 0}
                        mPaper(elevation = 4) { css(paper) }
                    }
                }
            }
        }
    }
}

fun RBuilder.testTransitions() = child(TestTransitions::class) {}

