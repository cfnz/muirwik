package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import org.w3c.dom.events.Event
import react.ComponentType
import react.ElementType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Tooltip")
private external val tooltipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tooltipComponentType: ComponentType<TooltipProps> = tooltipModule.default

@Suppress("EnumEntryName")
enum class TooltipPlacement {
    bottomEnd, bottomStart, bottom, leftEnd, leftStart, left, rightEnd, rightStart, right, topEnd, topStart, top;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface TooltipProps : StyledPropsWithCommonAttributes {
    var arrow: Boolean
    var components: dynamic
    var componentsProps: Props
    var describeChild: Boolean
    var disableFocusListener: Boolean
    var disableHoverListener: Boolean
    var disableInteractive: Boolean
    var disableTouchListener: Boolean
    var enterDelay: Int
    var enterNextDelay: Int
    var enterTouchDelay: Int
    var followCursor: Boolean
    var leaveDelay: Int
    var leaveTouchDelay: Int
    var onClose: Event
    var onOpen: Event
    var open: Boolean

    @JsName("PopperComponent")
    var popperComponent: ElementType<Props>

    @JsName("PopperProps")
    var popperProps: Props

    @JsName("TransitionComponent")
    var transitionComponent: ElementType<TransitionProps>

    @JsName("TransitionProps")
    var transitionProps: Props
}
var TooltipProps.placement by EnumPropToString(TooltipPlacement.values())


fun RBuilder.tooltip(
    title: String,
    placement: TooltipPlacement = TooltipPlacement.bottom,
    arrow: Boolean = false,
    handler: StyledHandler<TooltipProps>
) {
    createStyled(tooltipComponentType, handler) {
        attrs.arrow = arrow
        attrs.placement = placement
        attrs.title = title
    }
}

@Deprecated("Use the simpler 'non m' version.")
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
    handler: StyledHandler<TooltipProps>? = null
) {
    createStyled(tooltipComponentType, className, handler) {
        attrs.arrow = arrow
        enterDelay?.let { attrs.enterDelay = enterDelay }
        enterTouchDelay?.let { attrs.enterTouchDelay = enterTouchDelay }
        id?.let { attrs.id = id }
        leaveDelay?.let { attrs.leaveDelay = leaveDelay }
        leaveTouchDelay?.let { attrs.leaveTouchDelay = leaveTouchDelay }
        attrs.placement = placement
        attrs.title = title
    }
}


