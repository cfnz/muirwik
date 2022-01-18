package com.ccfraser.muirwik.components.styles

import kotlinext.js.jso
import react.Props

/**
 * ts2kt types with tweaks from material-ui/styles/createMuiTheme
 */
external interface ThemeOptions {
    var shape: ShapeOptions
    var breakpoints: Breakpoints
    var direction: dynamic /* String /* "ltr" */ | String /* "rtl" */ */ get() = definedExternally; set(value) = definedExternally
    var mixins: dynamic
    var overrides: dynamic
    var palette: PaletteOptions? get() = definedExternally; set(value) = definedExternally
    var props: Props
    var shadows: dynamic
    var spacing: dynamic
    var transitions: TransitionsOptions? get() = definedExternally; set(value) = definedExternally
//    var typography: dynamic /* TypographyOptions | (palette: Palette) -> TypographyOptions */ get() = definedExternally; set(value) = definedExternally
    var typography: TypographyOptions? get() = definedExternally; set(value) = definedExternally
    var zIndex: dynamic
}

external interface Theme {
    var shape: ShapeOptions
    var breakpoints: Breakpoints
    var direction: dynamic /* String /* "ltr" */ | String /* "rtl" */ */
    var mixins: Mixins
    var overrides: dynamic
    var palette: Palette
    var props: Props
    var shadows: dynamic
    var spacing: Spacing
    var transitions: Transitions
    var typography: Typography
    var zIndex: ZIndex
}

@JsModule("@mui/material/styles/createTheme")
private external val createThemeModule: dynamic

/**
 * @param themeOptions Options for changing the theme (see Material-UI documentation)
 * @param args Further options, specifically you can change the locale of the theme. (See location
 *             guide of Material-UI documentation and the test Localization example.)
 */
@Suppress("UnsafeCastFromDynamic")
fun createTheme(themeOptions: ThemeOptions? = null, args: dynamic = null): Theme {

    // We shall just use default (i.e. blank) options if none are provided
    val ourThemeOptions = themeOptions ?: jso {  }

    return createThemeModule.default(ourThemeOptions, args)
}
