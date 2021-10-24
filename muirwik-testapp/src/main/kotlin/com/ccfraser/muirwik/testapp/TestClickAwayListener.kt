package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.testapp.TestClickAwayListener.ComponentStyles.fake
import kotlinx.css.*
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestClickAwayListener : RComponent<Props, State>() {
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
            clickAwayListener {
                attrs.onClickAway = { setState { open = false }}

                div {
                    button("Open Fake Menu") { attrs.onClick = { setState { open = true } } }
                    if (open) {
                        paper {
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
