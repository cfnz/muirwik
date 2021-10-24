package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.components.utils.ControlColor
import com.ccfraser.muirwik.components.utils.targetInputValue
import com.ccfraser.muirwik.testapp.TestPopover.ComponentStyles.dot
import com.ccfraser.muirwik.testapp.TestPopover.ComponentStyles.greenRadio
import kotlinx.css.*
import org.w3c.dom.Node
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestPopover : RComponent<Props, State>() {
    private var open: Boolean = false
    private var anchorRef: PopoverAnchorRef = PopoverAnchorRef.anchorEl
    private var anchorOriginVertical: PopoverVerticalPosition = PopoverVerticalPosition.top
    private var anchorOriginHorizontal: PopoverHorizontalPosition = PopoverHorizontalPosition.left
    private var transformOriginVertical: PopoverVerticalPosition = PopoverVerticalPosition.top
    private var transformOriginHorizontal: PopoverHorizontalPosition = PopoverHorizontalPosition.left
    private var buttonRef = createRef<Node>()
    private var anchorPosTop: Int = 50
    private var anchorPosLeft: Int = 70

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val dot by css {
            position = Position.absolute
            width = 10.px
            height = 10.px
            borderRadius = 50.pct
            backgroundColor = Colors.Green.shade400
        }

        val greenRadio by css {
            color = Colors.Green.shade400
        }
    }

    private fun greenRadio() = buildElement {
        radio(color = ControlColor.default) {
            css(greenRadio)
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                minWidth = 600.px
            }
            div {
                gridContainer {
                    attrs.justify = GridJustify.center
                    gridItem {
                        css {
                            position = Position.relative
                            marginBottom = 32.px
                        }
                        button("Open Popover", variant = ButtonVariant.contained) {
                            attrs.onClick = { setState { open = true }}
                            ref = buttonRef
                        }
                        styledDiv {
                            css {
                                +dot
                                if (anchorRef == PopoverAnchorRef.anchorEl) {
                                    when(anchorOriginVertical) {
                                        PopoverVerticalPosition.top -> top = -(5.px)
                                        PopoverVerticalPosition.center -> top = 50.pct - 5.px
                                        PopoverVerticalPosition.bottom -> bottom = -(5.px)
                                    }
                                    when(anchorOriginHorizontal) {
                                        PopoverHorizontalPosition.left -> left = -(5.px)
                                        PopoverHorizontalPosition.center -> left = 50.pct - 5.px
                                        PopoverHorizontalPosition.right -> right = -(5.px)
                                    }
                                } else {
                                    display = Display.none
                                    top = anchorPosTop.px
                                    left = anchorPosLeft.px
                                }
                            }
                        }
                    }
                    popover(open) {
                        attrs.onClose = { _, _ -> setState { open = false}}
                        attrs.anchorReference = anchorRef
                        attrs.anchorEl = buttonRef.current
                        if (anchorRef == PopoverAnchorRef.anchorEl) {
                            attrs.anchorOriginVertical = anchorOriginVertical
                            attrs.anchorOriginHorizontal = anchorOriginHorizontal
                        } else {
                            attrs.anchorPositionTop = anchorPosTop
                            attrs.anchorPositionLeft = anchorPosLeft
                        }
                        attrs.transformOriginHorizontal = transformOriginHorizontal
                        attrs.transformOriginVertical = transformOriginVertical
                        styledDiv {
                            css {
                                padding(2.spacingUnits)
                            }
                            +"The content of the popover"
                        }
                    }
                }

                gridContainer(GridSpacing.spacing2) {
                    attrs.alignContent = GridAlignContent.flexStart
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        formControl {
                            formLabel("anchorRef")
                            radioGroup(value = anchorRef.toString()) {
                                attrs.row = true
                                attrs.onChange = { _, value -> setState { anchorRef = PopoverAnchorRef.valueOf(value) } }
                                radioWithLabel("anchorEl", value = PopoverAnchorRef.anchorEl.toString())
                                radioWithLabel("anchorPosition", value = PopoverAnchorRef.anchorPosition.toString())
                            }
                        }
                    }
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        textField("anchorPosition.top", anchorPosTop.toString()) {
                            attrs.onChange = { setState { anchorPosTop = it.targetInputValue.toIntOrNull() ?: 0 } }
                            css { paddingRight = 1.spacingUnits }
                        }
                        textField("anchorPosition.left", anchorPosLeft.toString()) {
                            attrs.onChange = { setState { anchorPosLeft = it.targetInputValue.toIntOrNull() ?: 0 } }
                        }
                    }
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        formControl {
                            formLabel("anchorOrigin.vertical")
                            radioGroup(anchorOriginVertical.toString()) {
                                attrs.onChange = { _, value -> setState { anchorOriginVertical = PopoverVerticalPosition.valueOf(value) } }
                                formControlLabel("Top", value = PopoverVerticalPosition.top.toString(), control = greenRadio())
                                formControlLabel("Center", value = PopoverVerticalPosition.center.toString(), control = greenRadio())
                                formControlLabel("Bottom", value = PopoverVerticalPosition.bottom.toString(), control = greenRadio())
                            }
                        }
                    }
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        formControl {
                            formLabel("transformOrigin.vertical")
                            radioGroup(transformOriginVertical.toString()) {
                                attrs.onChange = { _, value -> setState { transformOriginVertical = PopoverVerticalPosition.valueOf(value) } }
                                radioWithLabel("Top", value = PopoverVerticalPosition.top.toString())
                                radioWithLabel("Center", value = PopoverVerticalPosition.center.toString())
                                radioWithLabel("Bottom", value = PopoverVerticalPosition.bottom.toString())
                            }
                        }
                    }
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        formControl {
                            formLabel("anchorOrigin.horizontal")
                            radioGroup(value = anchorOriginHorizontal.toString()) {
                                attrs.row = true
                                attrs.onChange = { _, value -> setState { anchorOriginHorizontal = PopoverHorizontalPosition.valueOf(value) } }
                                formControlLabel("Left", value = PopoverHorizontalPosition.left.toString(), control = greenRadio())
                                formControlLabel("Center", value = PopoverHorizontalPosition.center.toString(), control = greenRadio())
                                formControlLabel("Right", value = PopoverHorizontalPosition.right.toString(), control = greenRadio())
                            }
                        }
                    }
                    gridItem {
                        attrs.xs = GridSize.cells12
                        attrs.sm = GridSize.cells6
                        formControl {
                            formLabel("transformOrigin.horizontal")
                            radioGroup(value = transformOriginHorizontal.toString()) {
                                attrs.row = true
                                attrs.onChange = { _, value -> setState { transformOriginHorizontal = PopoverHorizontalPosition.valueOf(value) } }
                                radioWithLabel("Left", value = PopoverHorizontalPosition.left.toString())
                                radioWithLabel("Center", value = PopoverHorizontalPosition.center.toString())
                                radioWithLabel("Right", value = PopoverHorizontalPosition.right.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}
