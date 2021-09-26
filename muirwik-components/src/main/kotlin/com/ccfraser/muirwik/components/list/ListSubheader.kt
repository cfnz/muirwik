package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.lineHeight
import kotlinx.css.padding
import kotlinx.css.properties.LineHeight
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.css


@JsModule("@mui/material/ListSubheader")
private external val listSubheaderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listSubheaderComponentType: ComponentType<MListSubheaderProps> = listSubheaderModule.default

@Suppress("EnumEntryName")
enum class MListSubheaderColor {
    default, primary, inherit
}

external interface MListSubheaderProps : MButtonBaseProps {
    var disableGutters: Boolean
    var disableSticky: Boolean
    var inset: Boolean
}
var MListSubheaderProps.color by EnumPropToString(MListSubheaderColor.values())

/**
 * A list sub-header which allows you to pass a string to use for the heading.
 */
fun RBuilder.mListSubheader(
    heading: String,
    color: MListSubheaderColor = MListSubheaderColor.default,
    component: String? = null,
    disableGutters: Boolean = false,
    disableSticky: Boolean = false,
    inset: Boolean = false,
    compact: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MListSubheaderProps>? = null
) {
    createStyled(listSubheaderComponentType, className, handler) {
        attrs.color = color
        component?.let { attrs.component = component }
        attrs.disableGutters = disableGutters
        attrs.disableSticky = disableSticky
        attrs.inset = inset

        childList.add(ReactNode(heading))

        if (compact) {
            css {
                lineHeight = LineHeight("1em")
                padding(vertical = 1.spacingUnits)
            }
        }
    }
}

/**
 * The 'standard' list sub-header... you need to create a child item for the heading.
 */
fun RBuilder.mListSubheader(
    color: MListSubheaderColor = MListSubheaderColor.default,
    component: String? = null,
    disableGutters: Boolean = false,
    disableSticky: Boolean = false,
    inset: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MListSubheaderProps>? = null
){
    createStyled(listSubheaderComponentType, className, handler) {
        attrs.color = color
        component?.let { attrs.component = component }
        attrs.disableGutters = disableGutters
        attrs.disableSticky = disableSticky
        attrs.inset = inset
    }
}
