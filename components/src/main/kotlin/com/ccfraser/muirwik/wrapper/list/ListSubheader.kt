package com.ccfraser.muirwik.wrapper.list

import com.ccfraser.muirwik.wrapper.MButtonBaseProps
import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/ListSubheader")
private external val listSubheaderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listSubheaderComponent: RComponent<MListSubheaderProps, RState> = listSubheaderModule.default

@Suppress("EnumEntryName")
enum class MListSubheaderColor {
    default, primary, inherit
}

interface MListSubheaderProps : MButtonBaseProps {
    var color: String
    var disableGutters: Boolean
    var disableSticky: Boolean
    var inset: Boolean
}

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

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MListSubheaderProps>? = null) = createStyled(listSubheaderComponent, addAsChild) {
    attrs.color = color.toString()
    component?.let { attrs.component = component }
    attrs.disableGutters = disableGutters
    attrs.disableSticky = disableSticky
    attrs.inset = inset

    childList.add(heading)
    setStyledPropsAndRunHandler(className, handler)
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

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MListSubheaderProps>? = null) = createStyled(listSubheaderComponent, addAsChild) {
    attrs.color = color.toString()
    component?.let { attrs.component = component }
    attrs.disableGutters = disableGutters
    attrs.disableSticky = disableSticky
    attrs.inset = inset

    setStyledPropsAndRunHandler(className, handler)
}
