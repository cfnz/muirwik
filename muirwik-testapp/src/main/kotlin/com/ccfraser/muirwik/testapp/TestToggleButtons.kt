package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestToggleButtons.ComponentStyles.customButton

import kotlinx.css.*
import kotlinx.css.properties.border
import react.*
import styled.StyleSheet
import styled.css


class TestToggleButtons : RComponent<Props, State>() {
    private var alignment: String? = "left"
    private var alignment2: String? = "left"
    private var formats = arrayOf("bold", "italic")
    private var platform: String? = "web"
    private var view: String? = "list"

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val customButton by css {
            margin(4.px)
            put("border-radius", "8px !important")
            put("border", "0 !important")
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Exclusive selection (ensure one selected)") {
                toggleButtonGroup(alignment, true) {
                    attrs.onChange = { _, value -> setState { if (value != null) alignment = value } }
                    toggleButton("left") {
                        icon("format_align_left")
                    }
                    toggleButton("center") {
                        icon("format_align_center")
                    }
                    toggleButton("right") {
                        icon("format_align_right")
                    }
                    toggleButton("justify") {
                        attrs.disabled = true
                        icon("format_align_justify")
                    }
                }
            }
            demoPanel("Multiple selection") {
                toggleButtonGroup(formats) {
                    attrs.onChange = { _, value -> setState { formats = value!! } }
                    toggleButton("bold") {
                        icon("format_bold")
                    }
                    toggleButton("italic") {
                        icon("format_italic")
                    }
                    toggleButton("underlined") {
                        icon("format_underlined")
                    }
                    toggleButton("color") {
                        icon("format_color_fill")
                    }
                }
            }
            demoPanel("Size") {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    gap = 2.spacingUnits
                }
                addButtonGroup(ToggleButtonSize.small)
                addButtonGroup(ToggleButtonSize.medium)
                addButtonGroup(ToggleButtonSize.large)
            }
            demoPanel("Color") {
                toggleButtonGroup(platform, true, ToggleButtonColor.primary) {
                    attrs.onChange = { _, value -> setState { platform = value } }
                    toggleButton("web") { +"Web" }
                    toggleButton("android") { +"Android" }
                    toggleButton("ios") { +"iOS" }
                }
            }
            demoPanel("Vertical buttons") {
                toggleButtonGroup(view, true) {
                    attrs.orientation = ToggleButtonGroupOrientation.vertical
                    attrs.onChange = { _, value -> setState { view = value }}
                    toggleButton("list") {
                        icon("view_list")
                    }
                    toggleButton("module") {
                        icon("view_module")
                    }
                    toggleButton("quilt") {
                        icon("view_quilt")
                    }
                }

            }
            demoPanel("Customization") {
                paper(0) {
                    css {
                        display = Display.flex
                        flexWrap = FlexWrap.wrap
                        border(1.px, BorderStyle.solid, Color.silver)
                    }
                    toggleButtonGroup(alignment2, true, size = ToggleButtonSize.small) {
                        attrs.onChange = { _, value -> setState { alignment2 = value }}
                        toggleButton("left") {
                            css(customButton)
                            icon("format_align_left")
                        }
                        toggleButton("center") {
                            css(customButton)
                            icon("format_align_center")
                        }
                        toggleButton("right") {
                            css(customButton)
                            icon("format_align_right")
                        }
                        toggleButton("justify") {
                            css(customButton)
                            icon("format_align_justify")
                        }
                    }
                    divider(DividerOrientation.vertical) {
                        css {
                            margin(vertical = 8.px)
                        }
                        attrs.flexItem = true
                    }
                    toggleButtonGroup(formats, true, size = ToggleButtonSize.small) {
                        attrs.onChange = { _, value -> setState { formats = value as Array<String> }}

                        toggleButton("bold") {
                            css(customButton)
                            icon("format_bold")
                        }
                        toggleButton("italic") {
                            css(customButton)
                            icon("format_italic")
                        }
                        toggleButton("underlined") {
                            css(customButton)
                            icon("format_underlined")
                        }
                        toggleButton("color") {
                            css(customButton)
                            attrs.disabled = true
                            icon("format_color_fill")
                            icon("arrow_drop_down")
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.addButtonGroup(size: ToggleButtonSize) {
        toggleButtonGroup(alignment2, size = size) {
            attrs.exclusive = true
            attrs.onChange = { _, value -> setState { alignment2 = value }}
            toggleButton("left") {
                icon("format_align_left")
            }
            toggleButton("center") {
                icon("format_align_center")
            }
            toggleButton("right") {
                icon("format_align_right")
            }
            toggleButton("justify") {
                icon("format_align_justify")
            }
        }
    }

}
