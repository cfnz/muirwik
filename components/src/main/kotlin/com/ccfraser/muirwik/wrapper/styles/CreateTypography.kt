package com.ccfraser.muirwik.wrapper.styles


external interface FontStyle {
    var fontFamily: String?
    var fontSize: Int
    var fontWeightLight: Int
    var fontWeightRegular: Int
    var fontWeightMedium: Int
    var htmlFontSize: Int?
}

external interface TypographyStyle {
    var color: String?
    var fontFamily: String?
    var fontSize: Int
    var fontWeight: Int
    var letterSpacing: String?
    var lineHeight: String?
    var textTransform: String?
}

external interface Typography : FontStyle, TypographyStyle {
    var display1: TypographyStyle
    var display2: TypographyStyle
    var display3: TypographyStyle
    var display4: TypographyStyle
    var headline: TypographyStyle
    var title: TypographyStyle
    var subheading: TypographyStyle
    var body1: TypographyStyle
    var body2: TypographyStyle
    var caption: TypographyStyle
}
