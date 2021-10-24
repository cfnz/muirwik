package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.targetValue
import com.ccfraser.muirwik.testapp.TestSpeedDial.ComponentStyles.panel

import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestSpeedDial : RComponent<Props, State>() {
    private var direction: String = "down"

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val panel by css {
            width = 400.px
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.flexEnd
        }
    }

    data class Action(val icon: ReactNode, val name: String)
    val actions = arrayOf(
        Action(buildElement { icon("content_copy") }, "Copy"),
        Action(buildElement { icon("save") }, "Save"),
        Action(buildElement { icon("print") }, "Print"),
        Action(buildElement { icon("share") }, "Share"),
    )

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Basic Speed Dial") {
                css(panel)
                speedDial("SpeedDial basic example") {
                    actions.forEach {
                        speedDialAction(it.name, it.icon, it.name)
                    }
                }
            }
            demoPanel("Playground") {
                css {
                    width = 400.px
//                    +panel
                }
                formControl {
                    attrs.component = FormControlComponent.fieldSet
                    formLabel("Direction")
                    radioGroup(direction, "Direction") {
                        css {
                            flexDirection = FlexDirection.row
                            alignItems = Align.flexStart
                        }
                        attrs.onChange = { event, _ -> setState { direction = event.targetValue.toString() }}
                        formControlLabel("up", "up", buildElement { radio {  } })
                        formControlLabel("right", "right", buildElement { radio {  } })
                        formControlLabel("down", "down", buildElement { radio {  } })
                        formControlLabel("left", "left", buildElement { radio {  } })
                    }
                }
                styledDiv {
                    css {
                        display = Display.flex
                        alignItems = Align.flexEnd
                    }
                    speedDial("SpeedDial basic example") {
                        attrs.direction = SpeedDialDirection.valueOf(direction)
                        actions.forEach {
                            speedDialAction(it.name, it.icon, it.name)
                        }
                    }
                }
            }
            demoPanel("Custom Close Icon") {
                css(panel)
                speedDial("SpeedDial basic example", buildElement { speedDialIcon(openIcon = buildElement { icon("edit") }) }) {
                    actions.forEach {
                        speedDialAction(it.name, it.icon, it.name)
                    }
                }
            }
        }
    }
}
