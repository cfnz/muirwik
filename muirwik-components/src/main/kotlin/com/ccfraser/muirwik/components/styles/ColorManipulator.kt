@file:JsModule("@material-ui/core/styles/colorManipulator")
package com.ccfraser.muirwik.components.styles

//external fun recomposeColor(color: ColorObject): string
external fun convertHexToRGB(hex: String): String
//external fun decomposeColor(color: String): ColorObject
external fun getContrastRatio(foreground: String, background: String): Double
external fun getLuminance(color: String): Double
external fun emphasize(color: String, coefficient: Double?): String
external fun fade(color: String, value: Double): String
external fun darken(color: String, coefficient: Double?): String
external fun lighten(color: String, coefficient: Double?): String

