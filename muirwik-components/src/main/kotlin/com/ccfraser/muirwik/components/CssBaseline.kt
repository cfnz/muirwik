package com.ccfraser.muirwik.components

import kotlinext.js.jsObject
import react.ComponentType
import react.RBuilder
import react.RProps


@JsModule("@material-ui/core/CssBaseline")
private external val cssBaselineModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cssBaselineComponentType: ComponentType<RProps> = cssBaselineModule.default

fun RBuilder.mCssBaseline() = child(cssBaselineComponentType, jsObject()) {}

