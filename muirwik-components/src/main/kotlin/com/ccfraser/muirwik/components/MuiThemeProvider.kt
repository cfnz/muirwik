package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Theme
import react.*


@JsModule("@material-ui/core/styles")
private external val muiThemeProviderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val muiThemeProviderComponent: RComponent<MuiThemeProviderProps, RState> = muiThemeProviderModule.ThemeProvider

interface MuiThemeProviderProps : RProps {
    var disableStylesGeneration: Boolean
    var sheetsManager: Any
    var theme: Theme
}

@Deprecated("Using mMuiThemeProvider directly does not provide usable contexts to access the theme property. " +
        "Consider using themeProvider which wraps mMuiThemeProvider instead.", ReplaceWith("themeProvider"))
fun RBuilder.mMuiThemeProvider(theme: Theme, disableStylesGeneration: Boolean? = null, sheetsManager: Any? = null,
        handler: RHandler<MuiThemeProviderProps>? = null) = child(muiThemeProviderComponent) {
    disableStylesGeneration?.let { attrs.disableStylesGeneration = disableStylesGeneration }
    sheetsManager?.let { attrs.sheetsManager = sheetsManager }
    attrs.theme = theme

    if (handler != null) handler()
}

