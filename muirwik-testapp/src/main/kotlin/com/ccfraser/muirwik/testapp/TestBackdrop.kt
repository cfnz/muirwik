package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mButton
import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.css.zIndex
import react.*
import react.dom.div
import styled.css


class TestBackdrop : RComponent<RProps, RState>() {
    var open = false

    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            div {
                mButton("Show Backdrop", MColor.primary, onClick = { setState { open = !open } })
                mBackdrop(open, onClick = { setState { open = !open } }) {
                    css {
                        zIndex = theme.zIndex.drawer + 1
                        color = Color("#fff")
                    }
                    mCircularProgress(color = MCircularProgressColor.inherit)
                }
            }
        }
    }
}

fun RBuilder.testBackdrop() = child(TestBackdrop::class) {}
