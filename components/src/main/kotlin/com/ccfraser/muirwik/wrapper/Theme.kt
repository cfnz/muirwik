package com.ccfraser.muirwik.wrapper
//
//interface CommonColors {
//    val black: String
//    val white: String
//}
//
//interface PaletteColor {
//    val light: String
//    val main: String
//    val dark: String
//    val contrastText: String
//}
//interface TypeText {
//    val primary: String
//    val secondary: String
//    val disabled: String
//    val hint: String
//}
//
//interface Color {
//    var _50: String
//    var _100: String
//    var _200: String
//    var _300: String
//    var _400: String
//    var _500: String
//    var _600: String
//    var _700: String
//    var _800: String
//    var _900: String
//    var A100: String
//    var A200: String
//    var A400: String
//    var A700: String
//}
//
//interface ThemePalette {
//    val common: CommonColors
//    val type: String
//    val contrastThreshold: Number
//    val tonalOffset: Number
//    val primary: PaletteColor
//    val secondary: PaletteColor
//    val error: PaletteColor
//    val grey: Color
//    val text: TypeText
//    val divider: String
//    val action: TypeAction
//    val background: TypeBackground
//    val getContrastText: (color: string) => string
//}
//
//interface ThemeSpacing {
//    val unit: Int
//}
//
//interface ThemeZIndex {
//    val mobileStepper: Int
//    val appBar: Int
//    val drawer: Int
//    val modal: Int
//    val snackbar: Int
//    val tooltip: Int
//}
//
//interface Theme {
//    val direction: String
//    val palette: ThemePalette
//    val typography: Typography
//    val mixins: Mixins
//    val breakpoints: Breakpoints
//    val shadows: Shadows
//    val transitions: Transitions
//    val spacing: Spacing
//    val zIndex: ZIndex
//    val overrides: Overrides
//}