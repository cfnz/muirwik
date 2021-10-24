package com.ccfraser.muirwik.components.styles

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import react.Props

/**
 * ts2kt types with tweaks from material-ui/styles/createPalette and tweaked
 */
external interface TypeText {
    var primary: String
    var secondary: String
    var disabled: String
}
external interface TypeAction {
    var active: String
    var hover: String
    var hoverOpacity: Number
    var selected: String
    var selectedOpacity: Number
    var disabled: String
    var disabledOpacity: Number
    var disabledBackground: String
    var focus: String
    var focusOpacity: Number
    var activatedOpacity: Number
}
external interface TypeBackground {
    var default: String
    var paper: String
}

typealias TypeDivider = String

typealias PaletteColorOptions = SimplePaletteColorOptions //SimplePaletteColorOptions | ColorPartial;

external interface SimplePaletteColorOptions {
    var light: String?
    var main: String
    var dark: String?
    var contrastText: String?
}
external interface PaletteColor {
    var light: String
    var main: String
    var dark: String
    var contrastText: String
}
external interface TypeObject {
    var text: TypeText
    var action: TypeAction
    var divider: TypeDivider
    var background: TypeBackground
}

typealias PaletteTonalOffset = Any

external interface PaletteAugmentColorOptions {
    var color: dynamic
    var mainShade: dynamic
    var lightShade: dynamic
    var darkShade: dynamic
    var name: dynamic
}
enum class PaletteMode {
    light, dark
}
external interface Palette : Props {
    var common: CommonColors
    var contrastThreshold: Int
    var tonalOffset: Float
    var primary: PaletteColor
    var secondary: PaletteColor
    var error: PaletteColor
    var warning: PaletteColor
    var info: PaletteColor
    var success: PaletteColor
    var grey: Color
    var text: TypeText
    var divider: String
    var action: TypeAction
    var background: TypeBackground
    var getContrastText: (color: String) -> String
    var augmentColor: (options: PaletteAugmentColorOptions) -> PaletteColor
}
var Palette.mode by EnumPropToString(PaletteMode.values())

external interface PaletteOptions : Props {
    var primary: PaletteColorOptions?
    var secondary: PaletteColorOptions?
    var error: PaletteColorOptions?
    var warning: PaletteColorOptions?
    var info: PaletteColorOptions?
    var success: PaletteColorOptions?
    var tonalOffset: PaletteTonalOffset?
    var contrastThreshold: Number?
    var common: CommonColors? // Partial<CommonColors>?
    var grey: Color? // ColorPartial?
    var text: TypeText? // Partial<TypeText>?
    var divider: String?
    var action: TypeAction? // Partial<TypeAction>?
    var background: TypeBackground? // Partial<TypeBackground>?
    var getContrastText: (background: String) -> String?
}
var PaletteOptions.mode by EnumPropToStringNullable(PaletteMode.values())
