package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
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
val listSubheaderComponentType: ComponentType<ListSubheaderProps> = listSubheaderModule.default

@Suppress("EnumEntryName")
enum class ListSubheaderColor {
    default, primary, inherit
}

external interface ListSubheaderProps : ButtonBaseProps {
    var disableGutters: Boolean
    var disableSticky: Boolean
    var inset: Boolean
}
var ListSubheaderProps.color by EnumPropToString(ListSubheaderColor.values())


fun RBuilder.listSubheader(heading: String? = null, handler: StyledHandler<ListSubheaderProps>? = null) {
    createStyled(listSubheaderComponentType, handler) {
        heading?.let { childList.add(ReactNode(it)) }
    }
}


@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
/**
 * A list sub-header which allows you to pass a string to use for the heading.
 */
fun RBuilder.mListSubheader(
    heading: String,
    color: ListSubheaderColor = ListSubheaderColor.default,
    component: String? = null,
    disableGutters: Boolean = false,
    disableSticky: Boolean = false,
    inset: Boolean = false,
    compact: Boolean = false,
    className: String? = null,
    handler: StyledHandler<ListSubheaderProps>? = null
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

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
/**
 * The 'standard' list sub-header... you need to create a child item for the heading.
 */
fun RBuilder.mListSubheader(
    color: ListSubheaderColor = ListSubheaderColor.default,
    component: String? = null,
    disableGutters: Boolean = false,
    disableSticky: Boolean = false,
    inset: Boolean = false,
    className: String? = null,
    handler: StyledHandler<ListSubheaderProps>? = null
){
    createStyled(listSubheaderComponentType, className, handler) {
        attrs.color = color
        component?.let { attrs.component = component }
        attrs.disableGutters = disableGutters
        attrs.disableSticky = disableSticky
        attrs.inset = inset
    }
}
