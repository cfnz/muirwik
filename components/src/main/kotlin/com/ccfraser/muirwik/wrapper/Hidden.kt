package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.styles.Breakpoint
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Hidden")
private external val hiddenModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val hiddenComponent: RComponent<MHiddenProps, RState> = hiddenModule.default

@Suppress("EnumEntryName")
enum class MHiddenImplementation {
    js, css;
}

enum class MHiddenVariant {
    Permanent, Persistent, Temporary;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MHiddenProps : StyledProps {
    var implementation: String
    var initialWidth: String
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

        handler: StyledHandler<MHiddenProps>) = createStyled(hiddenComponent) {
    attrs.implementation = implementation.toString()
    initialWidth?.let {  attrs.initialWidth = it.toString() }
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

    setStyledPropsAndRunHandler(className, handler)
}



