package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.jsObject
import react.ComponentType
import react.Props
import react.RBuilder
import react.RHandler
import styled.StyledProps


@JsModule("@mui/material/ClickAwayListener")
private external val clickAwayListenerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val clickAwayListenerComponentType: ComponentType<ClickAwayListenerProps> = clickAwayListenerModule.default

@Suppress("EnumEntryName")
enum class ClickAwayListenerMouseEvent {
    onClick, onMouseDown, onMouseUp, disable;

    fun value() = if (this == disable) false else super.toString()
}

@Suppress("EnumEntryName")
enum class ClickAwayListenerTouchEvent {
    onTouchStart, onTouchEnd,  disable;

    fun value() = if (this == disable) false else super.toString()
}

external interface ClickAwayListenerProps : Props {
    var onClickAway: () -> Unit
}
var ClickAwayListenerProps.mouseEvent: ClickAwayListenerMouseEvent
    get() = when (this.asDynamic().mouseEvent) {
        is String -> ClickAwayListenerMouseEvent.valueOf(this.asDynamic().mouseEvent)
        else -> ClickAwayListenerMouseEvent.disable
    }
    set(value) { this.asDynamic().mouseEvent = value.value() }

var ClickAwayListenerProps.touchEvent: ClickAwayListenerTouchEvent
    get() = when (this.asDynamic().touchEvent) {
        is String -> ClickAwayListenerTouchEvent.valueOf(this.asDynamic().touchEvent)
        else -> ClickAwayListenerTouchEvent.disable
    }
    set(value) { this.asDynamic().touchEvent = value.value() }


fun RBuilder.clickAwayListener(handler: RHandler<ClickAwayListenerProps>) {
    child(clickAwayListenerComponentType, jsObject()) {
        handler()
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mClickAwayListener(
    onClickAway: () -> Unit,
    mouseEvent: ClickAwayListenerMouseEvent = ClickAwayListenerMouseEvent.onClick,
    touchEvent: ClickAwayListenerTouchEvent = ClickAwayListenerTouchEvent.onTouchStart,
    handler: RHandler<ClickAwayListenerProps>? = null
) {
    child(clickAwayListenerComponentType, jsObject()) {
        attrs.mouseEvent = mouseEvent
        attrs.touchEvent = touchEvent
        attrs.onClickAway = onClickAway

        if (handler != null) handler()

        attrs.asDynamic().mouseEvent = attrs.mouseEvent.value()
        attrs.asDynamic().touchEvent = attrs.touchEvent.value()
    }
}

