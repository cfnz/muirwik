package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.form.mFormControlLabel
import com.ccfraser.muirwik.components.form.mFormLabel
import com.ccfraser.muirwik.testapp.TestPopover.ComponentStyles.dot
import com.ccfraser.muirwik.testapp.TestPopover.ComponentStyles.greenRadio
import kotlinx.css.*
import org.w3c.dom.Node
import react.*
import react.dom.div
import react.dom.findDOMNode
import styled.StyleSheet
import styled.css
import styled.styledDiv


@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestPopover : RComponent<RProps, RState>() {
    private var open: Boolean = false
    private var anchorRef: MPopoverAnchorRef = MPopoverAnchorRef.anchorEl
    private var anchorOriginVertical: MPopoverVerticalPosition = MPopoverVerticalPosition.top
    private var anchorOriginHorizontal: MPopoverHorizontalPosition = MPopoverHorizontalPosition.left
    private var transformOriginVertical: MPopoverVerticalPosition = MPopoverVerticalPosition.top
    private var transformOriginHorizontal: MPopoverHorizontalPosition = MPopoverHorizontalPosition.left
    private var buttonRef: Node? = null
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

    private fun RBuilder.greenRadio() = mRadio(color = MOptionColor.default, addAsChild = false) {
        css(greenRadio)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                minWidth = 600.px
            }
            div {
                mGridContainer(justify = MGridJustify.center) {
                    mGridItem {
                        css {
                            position = Position.relative
                            marginBottom = 32.px
                        }
                        mButton("Open Popover", variant = MButtonVariant.contained, onClick = { setState { open = true }} ) {
                            ref {
                                buttonRef = findDOMNode(it)
                            }
                        }
                        styledDiv {
                            css {
                                +dot
                                if (anchorRef == MPopoverAnchorRef.anchorEl) {
                                    when(anchorOriginVertical) {
                                        MPopoverVerticalPosition.top -> top = -(5.px)
                                        MPopoverVerticalPosition.center -> top = 50.pct - 5.px
                                        MPopoverVerticalPosition.bottom -> bottom = -(5.px)
                                    }
                                    when(anchorOriginHorizontal) {
                                        MPopoverHorizontalPosition.left -> left = -(5.px)
                                        MPopoverHorizontalPosition.center -> left = 50.pct - 5.px
                                        MPopoverHorizontalPosition.right -> right = -(5.px)
                                    }
                                } else {
                                    display = Display.none
                                    top = anchorPosTop.px
                                    left = anchorPosLeft.px
                                }
                            }
                        }
                    }
                    mPopover(open, onClose = { _, _ -> setState { open = false}} ) {
                        attrs.anchorReference = anchorRef
                        attrs.anchorEl = buttonRef
                        if (anchorRef == MPopoverAnchorRef.anchorEl) {
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

                mGridContainer(MGridSpacing.spacing2, alignContent = MGridAlignContent.flexStart) {
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mFormControl {
                            mFormLabel("anchorRef")
                            mRadioGroup(value = anchorRef.toString(), row = true, onChange = { _, value -> setState { anchorRef = MPopoverAnchorRef.valueOf(value) }}) {
                                mRadioWithLabel("anchorEl", value = MPopoverAnchorRef.anchorEl.toString())
                                mRadioWithLabel("anchorPosition", value = MPopoverAnchorRef.anchorPosition.toString())
                            }
                        }
                    }
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mTextField("anchorPosition.top", anchorPosTop.toString(), onChange = {
                            val v = it.targetInputValue
                            setState { anchorPosTop = v.toIntOrNull() ?: 0 }
                        }) {
                            css { paddingRight = 1.spacingUnits }
                        }
                        mTextField("anchorPosition.left", anchorPosLeft.toString(), onChange = {
                            val v = it.targetInputValue
                            setState { anchorPosLeft = v.toIntOrNull() ?: 0 }
                        })
                    }
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mFormControl {
                            mFormLabel("anchorOrigin.vertical")
                            mRadioGroup(value = anchorOriginVertical.toString(), onChange = { _, value -> setState {
                                anchorOriginVertical = MPopoverVerticalPosition.valueOf(value)
                            }}) {
                                mFormControlLabel("Top", value = MPopoverVerticalPosition.top.toString(), control = greenRadio())
                                mFormControlLabel("Center", value = MPopoverVerticalPosition.center.toString(), control = greenRadio())
                                mFormControlLabel("Bottom", value = MPopoverVerticalPosition.bottom.toString(), control = greenRadio())
                            }
                        }
                    }
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mFormControl {
                            mFormLabel("transformOrigin.vertical")
                            mRadioGroup(value = transformOriginVertical.toString(), onChange = { _, value -> setState { transformOriginVertical = MPopoverVerticalPosition.valueOf(value) }}) {
                                mRadioWithLabel("Top", value = MPopoverVerticalPosition.top.toString())
                                mRadioWithLabel("Center", value = MPopoverVerticalPosition.center.toString())
                                mRadioWithLabel("Bottom", value = MPopoverVerticalPosition.bottom.toString())
                            }
                        }
                    }
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mFormControl {
                            mFormLabel("anchorOrigin.horizontal")
                            mRadioGroup(value = anchorOriginHorizontal.toString(), row = true, onChange = { _, value -> setState { anchorOriginHorizontal = MPopoverHorizontalPosition.valueOf(value) }}) {
                                mFormControlLabel("Left", value = MPopoverHorizontalPosition.left.toString(), control = greenRadio())
                                mFormControlLabel("Center", value = MPopoverHorizontalPosition.center.toString(), control = greenRadio())
                                mFormControlLabel("Right", value = MPopoverHorizontalPosition.right.toString(), control = greenRadio())
                            }
                        }
                    }
                    mGridItem(xs = MGridSize.cells12, sm = MGridSize.cells6) {
                        mFormControl {
                            mFormLabel("transformOrigin.horizontal")
                            mRadioGroup(value = transformOriginHorizontal.toString(), row = true, onChange = { _, value -> setState { transformOriginHorizontal = MPopoverHorizontalPosition.valueOf(value) }}) {
                                mRadioWithLabel("Left", value = MPopoverHorizontalPosition.left.toString())
                                mRadioWithLabel("Center", value = MPopoverHorizontalPosition.center.toString())
                                mRadioWithLabel("Right", value = MPopoverHorizontalPosition.right.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testPopover() = child(TestPopover::class) {}
