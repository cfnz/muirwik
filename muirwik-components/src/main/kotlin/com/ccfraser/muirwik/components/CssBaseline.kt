package com.ccfraser.muirwik.components

import kotlinext.js.jsObject
import react.ComponentType
import react.RBuilder
import react.Props


@JsModule("@mui/material/CssBaseline")
private external val cssBaselineModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cssBaselineComponentType: ComponentType<Props> = cssBaselineModule.default

fun RBuilder.mCssBaseline() {
    child(cssBaselineComponentType, jsObject()) {}
}

