package com.ccfraser.muirwik.components

import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Tooltip")
private external val tooltipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tooltipComponent: RComponent<MTooltipProps, RState> = tooltipModule.default

@Suppress("EnumEntryName")
enum class TooltipPlacement {
    bottomEnd, bottomStart, bottom, leftEnd, leftStart, left, rightEnd, rightStart, right, topEnd, topStart, top;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface MTooltipProps : StyledPropsWithCommonAttributes {
    var arrow: Boolean
    var disableFocusListener: Boolean
    var disableHoverListener: Boolean
    var disableTouchListener: Boolean
    var enterDelay: Int
    var enterNextDelay: Int
    var enterTouchDelay: Int
    var leaveDelay: Int
    var leaveTouchDelay: Int
    var onClose: Event
    var onOpen: Event
    var open: Boolean

    @JsName("PopperProps")
    var popperProps: RProps
}
var MTooltipProps.placement by EnumPropToString(TooltipPlacement.values())


fun RBuilder.mTooltip(
        title: String,
        placement: TooltipPlacement = TooltipPlacement.bottom,
        arrow: Boolean = false,
        enterDelay: Int? = null,
        enterTouchDelay: Int? = null,
        leaveDelay: Int? = null,
        leaveTouchDelay: Int? = null,

        id: String? = null,

        className: String? = null,
        handler: StyledHandler<MTooltipProps>? = null) = createStyled(tooltipComponent) {
    attrs.arrow = arrow
    enterDelay?.let { attrs.enterDelay = enterDelay }
    enterTouchDelay?.let { attrs.enterTouchDelay = enterTouchDelay }
    id?.let { attrs.id = id }
    leaveDelay?.let { attrs.leaveDelay = leaveDelay }
    leaveTouchDelay?.let { attrs.leaveTouchDelay = leaveTouchDelay }
    attrs.placement = placement
    attrs.title = title

    setStyledPropsAndRunHandler(className, handler)
    attrs.asDynamic().placement = attrs.placement.toString()
}


