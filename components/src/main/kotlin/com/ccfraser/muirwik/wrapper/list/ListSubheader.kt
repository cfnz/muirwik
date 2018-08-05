package com.ccfraser.muirwik.wrapper.list

import com.ccfraser.muirwik.wrapper.MButtonBaseProps
import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.mDivider
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/ListSubheader")
private external val listSubheaderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listSubheaderComponent: RComponent<MListSubheaderProps, RState> = listSubheaderModule.default

enum class MListSubheaderColor {
    Default, Primary, Inherit;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MListSubheaderProps : MButtonBaseProps {
    var color: String
    var disableSticky: Boolean
    var inset: Boolean
}

fun RBuilder.mListSubheader(
        heading: String,
        color: MListSubheaderColor = MListSubheaderColor.Default,
        component: String? = null,
        disableSticky: Boolean = false,
        inset: Boolean = false,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MListSubheaderProps>? = null) = createStyled(listSubheaderComponent, addAsChild) {
    attrs.color = color.toString()
    component?.let { attrs.component = component }
    attrs.disableSticky = disableSticky
    attrs.inset = inset

    childList.add(heading)
    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mListSubheader(
        color: MListSubheaderColor = MListSubheaderColor.Default,
        component: String? = null,
        disableSticky: Boolean = false,
        inset: Boolean = false,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MListSubheaderProps>? = null) = createStyled(listSubheaderComponent, addAsChild) {
    attrs.color = color.toString()
    component?.let { attrs.component = component }
    attrs.disableSticky = disableSticky
    attrs.inset = inset

    setStyledPropsAndRunHandler(className, handler)
}
