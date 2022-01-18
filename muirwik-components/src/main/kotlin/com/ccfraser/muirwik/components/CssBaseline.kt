package com.ccfraser.muirwik.components

import kotlinext.js.jso
import react.ComponentType
import react.Props
import react.RBuilder


@JsModule("@mui/material/CssBaseline")
private external val cssBaselineModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cssBaselineComponentType: ComponentType<Props> = cssBaselineModule.default

fun RBuilder.mCssBaseline() {
    child(cssBaselineComponentType, jso()) {}
}

