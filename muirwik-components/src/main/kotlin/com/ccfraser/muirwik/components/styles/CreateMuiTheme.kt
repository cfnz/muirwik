package com.ccfraser.muirwik.components.styles

import react.RProps

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
    var props: RProps
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
    var props: RProps
    var shadows: dynamic
    var spacing: Spacing
    var transitions: Transitions
    var typography: Typography
    var zIndex: ZIndex
}
