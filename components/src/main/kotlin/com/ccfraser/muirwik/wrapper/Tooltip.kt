package com.ccfraser.muirwik.wrapper

import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Tooltip")
private external val tooltipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tooltipComponent: RComponent<MTooltipProps, RState> = tooltipModule.default

enum class TooltipPlacement {
    BottomEnd, BottomStart, Bottom, LeftEnd, LeftStart, Left, RightEnd, RightStart, Right, TopEnd, TopStart, Top;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

interface MTooltipProps : StyledProps {
    var disableFocusListener: Boolean
    var disableHoverListener: Boolean
    var disableTouchListener: Boolean
    var enterDelay: Int
    var enterTouchDelay: Int
    var id: String
    var leaveDelay: Int
    var leaveTouchDelay: Int
    var onClose: Event
    var onOpen: Event
    var open: Boolean
    var placement: String

    @JsName("PopperProps")
    var popperProps: RProps
    var title: String
}

fun RBuilder.mTooltip(
        title: String,
        placement: TooltipPlacement = TooltipPlacement.Bottom,

        enterDelay: Int? = null,
        enterTouchDelay: Int? = null,
        leaveDelay: Int? = null,
        leaveTouchDelay: Int? = null,

        id: String? = null,

        className: String? = null,
        handler: StyledHandler<MTooltipProps>? = null) = createStyled(tooltipComponent) {
    enterDelay?.let { attrs.enterDelay = enterDelay }
    enterTouchDelay?.let { attrs.enterTouchDelay = enterTouchDelay }
    id?.let { attrs.id = id }
    leaveDelay?.let { attrs.leaveDelay = leaveDelay }
    leaveTouchDelay?.let { attrs.leaveTouchDelay = leaveTouchDelay }
    attrs.placement = placement.toString()
    attrs.title = title

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mTooltip(
        title: String,
        placement: TooltipPlacement = TooltipPlacement.Bottom,

        open: Boolean? = null,
        popperProps: RProps? = null,

        disableFocusListener: Boolean = false,
        disableHoverListener: Boolean = false,
        disableTouchListener: Boolean = false,

        onOpen: Event? = null,
        onClose: Event? = null,

        enterDelay: Int? = null,
        enterTouchDelay: Int? = null,
        leaveDelay: Int? = null,
        leaveTouchDelay: Int? = null,

        id: String? = null,

        className: String? = null,
        handler: StyledHandler<MTooltipProps>? = null) = createStyled(tooltipComponent) {
    attrs.disableFocusListener = disableFocusListener
    attrs.disableHoverListener = disableHoverListener
    attrs.disableTouchListener = disableTouchListener
    enterDelay?.let { attrs.enterDelay = enterDelay }
    enterTouchDelay?.let { attrs.enterTouchDelay = enterTouchDelay }
    id?.let { attrs.id = id }
    leaveDelay?.let { attrs.leaveDelay = leaveDelay }
    leaveTouchDelay?.let { attrs.leaveTouchDelay = leaveTouchDelay }
    onClose?.let { attrs.onClose = onClose }
    onOpen?.let { attrs.onOpen = onOpen }
    open?.let { attrs.open = open }
    attrs.placement = placement.toString()
    popperProps?.let { attrs.popperProps = popperProps }
    attrs.title = title

    setStyledPropsAndRunHandler(className, handler)
}

