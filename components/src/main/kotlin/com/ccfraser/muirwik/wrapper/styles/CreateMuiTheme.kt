package com.ccfraser.muirwik.wrapper.styles

/**
 * ts2kt types with tweaks from material-ui/styles/createMuiTheme
 */
external interface ThemeOptions {
    var direction: dynamic /* String /* "ltr" */ | String /* "rtl" */ */ get() = definedExternally; set(value) = definedExternally
    var palette: PaletteOptions? get() = definedExternally; set(value) = definedExternally
    var typography: dynamic /* TypographyOptions | (palette: Palette) -> TypographyOptions */ get() = definedExternally; set(value) = definedExternally
    var mixins: dynamic
    var breakpoints: Breakpoints
    var shadows: dynamic
    var transitions: TransitionsOptions? get() = definedExternally; set(value) = definedExternally
    var spacing: dynamic
    var zIndex: dynamic
    var overrides: dynamic
}

external interface Theme {
    var direction: dynamic /* String /* "ltr" */ | String /* "rtl" */ */
    var palette: Palette
    var typography: Typography
    var mixins: Mixins
    var breakpoints: Breakpoints
    var shadows: dynamic
    var transitions: Transitions
    var spacing: Spacing
    var zIndex: ZIndex
    var overrides: dynamic
}
