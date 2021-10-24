package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Hidden")
private external val hiddenModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val hiddenComponentType: ComponentType<HiddenProps> = hiddenModule.default

@Suppress("EnumEntryName")
enum class HiddenImplementation {
    js, css
}

external interface HiddenProps : StyledProps {
    var lgDown: Boolean
    var lgUp: Boolean
    var mdDown: Boolean
    var mdUp: Boolean
    var only: Array<Breakpoint>
    var smDown: Boolean
    var smUp: Boolean
    var xlDown: Boolean
    var xlUp: Boolean
    var xsDown: Boolean
    var xsUp: Boolean
}
var HiddenProps.initialWidth by EnumPropToStringNullable(Breakpoint.values())
var HiddenProps.implementation by EnumPropToString(HiddenImplementation.values())

fun RBuilder.hidden(handler: StyledHandler<HiddenProps>) {
    createStyled(hiddenComponentType, handler)
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mHidden(
    only: Array<Breakpoint> = emptyArray(),
    xsUp: Boolean = false,
    smUp: Boolean = false,
    mdUp: Boolean = false,
    lgUp: Boolean = false,
    xlUp: Boolean = false,
    xsDown: Boolean = false,
    smDown: Boolean = false,
    mdDown: Boolean = false,
    lgDown: Boolean = false,
    xlDown: Boolean = false,
    className: String? = null,
    implementation: HiddenImplementation = HiddenImplementation.js,
    initialWidth: Breakpoint? = null,
    handler: StyledHandler<HiddenProps>
) {
    createStyled(hiddenComponentType, className, handler) {
        attrs.implementation = implementation
        initialWidth?.let { attrs.initialWidth = it }
        attrs.lgDown = lgDown
        attrs.lgUp = lgUp
        attrs.mdDown = mdDown
        attrs.mdUp = mdUp
        attrs.only = only
        attrs.smDown = smDown
        attrs.smUp = smUp
        attrs.xlDown = xlDown
        attrs.xlUp = xlUp
        attrs.xsDown = xsDown
        attrs.xsUp = xsUp
    }
}



