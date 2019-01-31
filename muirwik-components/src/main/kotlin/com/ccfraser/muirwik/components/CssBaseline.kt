package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState


@JsModule("@material-ui/core/CssBaseline")
private external val cssBaselineModule: dynamic

private val cssBaselineComponent: RComponent<RProps, RState> = cssBaselineModule.default

fun RBuilder.mCssBaseline() = child(cssBaselineComponent) {}

