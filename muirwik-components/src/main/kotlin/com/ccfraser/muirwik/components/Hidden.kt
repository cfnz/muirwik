package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Hidden")
private external val hiddenModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val hiddenComponentType: ComponentType<MHiddenProps> = hiddenModule.default

@Suppress("EnumEntryName")
enum class MHiddenImplementation {
    js, css
}

external interface MHiddenProps : StyledProps {
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
var MHiddenProps.initialWidth by EnumPropToStringNullable(Breakpoint.values())
var MHiddenProps.implementation by EnumPropToString(MHiddenImplementation.values())

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
    implementation: MHiddenImplementation = MHiddenImplementation.js,
    initialWidth: Breakpoint? = null,
    handler: StyledHandler<MHiddenProps>
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



