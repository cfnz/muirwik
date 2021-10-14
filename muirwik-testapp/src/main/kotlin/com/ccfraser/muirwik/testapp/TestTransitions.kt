package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.mFormControlLabel
import com.ccfraser.muirwik.components.styles.PaletteMode
import com.ccfraser.muirwik.components.styles.mode
import com.ccfraser.muirwik.components.transitions.*
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import org.w3c.dom.HTMLElement
import react.*
import react.dom.div
import react.dom.svg
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// This works in Legacy, but not IR
//private open class ShapeProps (
//    val points: String,
//    val stroke: String,
//    val fill: String,
//    val strokeWidth: Int
//): Props

external interface ShapeProps : Props {
    var points: String
    var stroke: String
    var fill: String
    var strokeWidth: Int
}


private fun RBuilder.upsideDownV(w: Int, h: Int) {
    val shapeProps = jsObject<ShapeProps> {
        points = "0,$h ${w/2},0 $w,$h"
        stroke = "silver"
        fill = "white"
        strokeWidth = 1
    }

    svg {
        attrs["width"] = "$w"
        attrs["height"] = "$h"
//        attrs["viewBox"] = "0 0 $w $h"

//        Works in Legacy, but not IR... need to use the longer method of creating a jsObject as above
//        child(createElement("polygon", ShapeProps("0,$h ${w/2},0 $w,$h", "silver", "white", 1)))
        child(createElement("polygon", shapeProps))
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

class TestTransitions : RComponent<Props, State>() {
    var collapseShown: Boolean = false
    var fadeShown: Boolean = false
    var growShown: Boolean = false
    var slideShown: Boolean = false
    var zoomShown: Boolean = false
    val containerRef = createRef<HTMLElement>()

    object ComponentStyles : StyleSheet("TransitionStyles", isStatic = true) {
        val area by css {
            height = 180.px
            padding(2.spacingUnits)
        }
    }

    override fun RBuilder.render() {

        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        div {
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Collapse", buildElement { mSwitch(checked = collapseShown, onChange = {_, _ ->  setState {collapseShown = ! collapseShown}}) })

                styledDiv {
                    css { display = Display.flex }
                    mCollapse(show = collapseShown) {
                        attrs.timeout = AutoTransitionDuration()
                        demoComponent()
                    }
                    mCollapse(show = collapseShown, collapsedSize = 40.px) {
                        demoComponent()
                    }
                    mTypography("(Shows use of collapseHeight)")
                }
            }

            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Fade (with a slow duration set)", buildElement { mSwitch(checked = fadeShown, onChange = {_, _ ->  setState {fadeShown = ! fadeShown}}) })

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
                mFormControlLabel("Grow", buildElement { mSwitch(checked = growShown, onChange = {_, _ ->  setState {growShown = ! growShown}}) })
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
                css {
                    +ComponentStyles.area
                    display = Display.flex
                }
                styledDiv {
                    css { width = 140.px}
                    mFormControlLabel("Slide", buildElement { mSwitch(checked = slideShown, onChange = {_, _ ->  setState {slideShown = ! slideShown}}) })
                    mSlide(show = slideShown, direction = SlideTransitionDirection.up) {
                        demoComponent()
                    }
                }
                themeContext.Consumer { theme ->
                    mBox {
                        css {
                            height = 150.px
                            width = 150.px
                            display = Display.flex
                            padding(2.spacingUnits)
                            borderRadius = 1.spacingUnits
                            backgroundColor = if (theme.palette.mode == PaletteMode.light) Colors.Grey.shade100 else Colors.Grey.shade900
                            overflow = Overflow.hidden
                        }
                        ref = containerRef
                        mSlide(show = slideShown, direction = SlideTransitionDirection.up, container = containerRef.current) {
                            demoComponent()
                        }
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                mFormControlLabel("Zoom", buildElement { mSwitch(checked = zoomShown, onChange = {_, _ ->  setState {zoomShown = ! zoomShown}}) })
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
