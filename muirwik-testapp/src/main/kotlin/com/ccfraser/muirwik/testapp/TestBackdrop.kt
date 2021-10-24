package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.css.zIndex
import react.*
import react.dom.div
import styled.css


class TestBackdrop : RComponent<Props, State>() {
    var open = false

    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            div {
                button("Show Backdrop", ButtonColor.primary) { attrs.onClick = { setState { open = !open } } }
                backdrop(open) {
                    attrs.onClick = { setState { open = !open } }
                    css {
                        zIndex = theme.zIndex.drawer + 1
                        color = Color("#fff")
                    }
                    circularProgress(color = CircularProgressColor.inherit)
                }
            }
        }
    }
}
