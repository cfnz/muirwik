package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Divider")
private external val dividerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dividerComponentType: ComponentType<MDividerProps> = dividerModule.default

@Suppress("EnumEntryName")
enum class MDividerOrientation {
    horizontal, vertical
}

@Suppress("EnumEntryName")
enum class MDividerVariant {
    fullWidth, inset, middle
}

external interface MDividerProps : StyledProps {
    var absolute: Boolean
    var component: String
    var light: Boolean
}
var MDividerProps.orientation by EnumPropToString(MDividerOrientation.values())
var MDividerProps.variant by EnumPropToString(MDividerVariant.values())

fun RBuilder.mDivider(
        variant: MDividerVariant = MDividerVariant.fullWidth,
        light: Boolean = false,
        absolute: Boolean = false,
        orientation: MDividerOrientation = MDividerOrientation.horizontal,
        component: String = "hr",

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MDividerProps>? = null) = createStyled(dividerComponentType, addAsChild) {
    attrs.absolute = absolute
    attrs.component = component
    attrs.light = light
    attrs.orientation = orientation
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}

