package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Theme
import kotlinext.js.jsObject
import react.*


@JsModule("@mui/material/styles")
private external val muiThemeProviderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val muiThemeProviderComponentType: ComponentType<MuiThemeProviderProps> = muiThemeProviderModule.ThemeProvider

external interface MuiThemeProviderProps : PropsWithChildren {
    var disableStylesGeneration: Boolean
    var sheetsManager: Any
    var theme: Theme
}

@Deprecated("Using mMuiThemeProvider directly does not provide usable contexts to access the theme property. " +
        "Consider using themeProvider which wraps mMuiThemeProvider instead.", ReplaceWith("themeProvider"))
fun RBuilder.mMuiThemeProvider(
    theme: Theme,
    disableStylesGeneration: Boolean? = null,
    sheetsManager: Any? = null,
    handler: RHandler<MuiThemeProviderProps>? = null
) {
    child(muiThemeProviderComponentType, jsObject()) {
        disableStylesGeneration?.let { attrs.disableStylesGeneration = disableStylesGeneration }
        sheetsManager?.let { attrs.sheetsManager = sheetsManager }
        attrs.theme = theme

        if (handler != null) handler()
    }
}

