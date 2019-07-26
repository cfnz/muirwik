package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.mClickAwayListener
import com.ccfraser.muirwik.components.mPaper
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.TestClickAwayListener.ComponentStyles.fake
import kotlinx.css.*
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestClickAwayListener : RComponent<RProps, RState>() {
    private var open: Boolean = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val fake by css {
            backgroundColor = Colors.Grey.shade200
            height = 1.spacingUnits
            margin(2.spacingUnits)
            nthChild("2n") {
                marginRight = 3.spacingUnits
            }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.relative
                width = 200.px
            }
            mClickAwayListener({ setState { open = false }}) {
                div {
                    mButton("Open Fake Menu", onClick = { setState { open = true }})
                    if (open) {
                        mPaper {
                            css {
                                position = Position.absolute
                                top = 36.px
                                left = 0.px
                                right = 0.px
                            }
                            styledDiv { css(fake) }
                            styledDiv { css(fake) }
                            styledDiv { css(fake) }
                            styledDiv { css(fake) }
                            styledDiv { css(fake) }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testClickAwayListener() = child(TestClickAwayListener::class) {}
