package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.styles.Theme
import com.ccfraser.muirwik.wrapper.styles.ThemeOptions
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/styles/MuiThemeProvider")
private external val muiThemeProviderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val muiThemeProviderComponent: RComponent<MuiThemeProviderProps, RState> = muiThemeProviderModule.default

@JsModule("@material-ui/core/styles/createMuiTheme")
private external val createMuiThemeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val createMuiThemeFunction: dynamic = createMuiThemeModule.default
//@Suppress("UnsafeCastFromDynamic")
//fun createMuiThemeFunction(options: dynamic): Theme = createMuiThemeModule.default

var currentTheme: Theme = createMuiThemeFunction()

interface MuiThemeProviderProps : RProps {
    var disableStylesGeneration: Boolean
    var sheetsManager: Any
    var theme: Any
}

fun RBuilder.mMuiThemeProvider(
        theme: Any,
        disableStylesGeneration: Boolean? = null,
        sheetsManager: Any? = null,

        handler: RHandler<MuiThemeProviderProps>? = null) = child(muiThemeProviderComponent) {
    disableStylesGeneration?.let { attrs.disableStylesGeneration = disableStylesGeneration }
    sheetsManager?.let { attrs.sheetsManager = sheetsManager }
    attrs.theme = theme

    if (handler != null) handler()
}

