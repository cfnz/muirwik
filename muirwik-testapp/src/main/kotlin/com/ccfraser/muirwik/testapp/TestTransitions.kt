package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.PaletteMode
import com.ccfraser.muirwik.components.styles.mode
import com.ccfraser.muirwik.components.utils.Colors
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
    paper(elevation = 4) {
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
    private val containerRef = createRef<HTMLElement>()

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
                formControlLabel("Collapse", buildElement {
                    switch(collapseShown) { attrs.onChange = { _, _ ->  setState { collapseShown = ! collapseShown } } }
                })

                styledDiv {
                    css { display = Display.flex }
                    collapse(collapseShown) {
                        attrs.timeout = AutoTransitionDuration()
                        demoComponent()
                    }
                    collapse(show = collapseShown) {
                        attrs.collapsedSize = 40.px
                        demoComponent()
                    }
                    typography("(Shows use of collapseHeight)")
                }
            }

            styledDiv {
                css(ComponentStyles.area)
                formControlLabel("Fade (with a slow duration set)", buildElement {
                    switch(fadeShown) { attrs.onChange = {_, _ ->  setState { fadeShown = ! fadeShown } } }
                })

                styledDiv {
                    css {
                        display = Display.flex
                    }
                    fade(fadeShown) {
                        demoComponent()
                    }
                    fade(fadeShown) {
                        attrs.timeout = SimpleTransitionDuration(1000)
                        demoComponent()
                    }
                    fade(fadeShown) {
                        attrs.timeout = SimpleTransitionDuration(2000)
                        demoComponent()
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                formControlLabel("Grow", buildElement {
                    switch(growShown) { attrs.onChange = {_, _ ->  setState { growShown = ! growShown } } }
                })
                styledDiv {
                    css { display = Display.flex }
                    grow(growShown) {
                        demoComponent()
                    }
                    grow(growShown) {
                        attrs.timeout = EnterExitTransitionDuration(500, 1500)
                        attrs.asDynamic().style = js {transformOrigin = "0 0 0"}
//                        attrs.timeout = AutoTransitionDuration()
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
                    formControlLabel("Slide", buildElement {
                        switch(slideShown) { attrs.onChange = {_, _ ->  setState { slideShown = ! slideShown } } }
                    })
                    slide(slideShown, SlideTransitionDirection.up) {
                        demoComponent()
                    }
                }
                themeContext.Consumer { theme ->
                    box {
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
                        slide(slideShown, SlideTransitionDirection.up) {
                            containerRef.current?.let { attrs.container = it }
                            demoComponent()
                        }
                    }
                }
            }
            styledDiv {
                css(ComponentStyles.area)
                formControlLabel("Zoom", buildElement {
                    switch(zoomShown) { attrs.onChange = { _, _ -> setState { zoomShown = !zoomShown } } }
                })
                styledDiv {
                    css { display = Display.flex }
                    zoom(zoomShown) {
                        demoComponent()
                    }
                    zoom(zoomShown) {
                        attrs.asDynamic().style = js {transitionDelay = if (zoomShown) 500 else 0}
                        demoComponent()
                    }
                }
            }
        }
    }
}
