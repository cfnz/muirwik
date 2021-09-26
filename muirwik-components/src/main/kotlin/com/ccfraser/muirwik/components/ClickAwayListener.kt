package com.ccfraser.muirwik.components

import kotlinext.js.jsObject
import react.ComponentType
import react.RBuilder
import react.RHandler
import styled.StyledProps


@JsModule("@mui/material/ClickAwayListener")
private external val clickAwayListenerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val clickAwayListenerComponentType: ComponentType<MClickAwayListenerProps> = clickAwayListenerModule.default

@Suppress("EnumEntryName")
enum class MClickAwayListenerMouseEvent {
    onClick, onMouseDown, onMouseUp, disable;

    fun value() = if (this == disable) false else super.toString()
}

@Suppress("EnumEntryName")
enum class MClickAwayListenerTouchEvent {
    onTouchStart, onTouchEnd,  disable;

    fun value() = if (this == disable) false else super.toString()
}

external interface MClickAwayListenerProps : StyledProps {
    var onClickAway: () -> Unit
}
var MClickAwayListenerProps.mouseEvent: MClickAwayListenerMouseEvent
    get() = when (this.asDynamic().mouseEvent) {
        is String -> MClickAwayListenerMouseEvent.valueOf(this.asDynamic().mouseEvent)
        else -> MClickAwayListenerMouseEvent.disable
    }
    set(value) { this.asDynamic().mouseEvent = value.value() }

var MClickAwayListenerProps.touchEvent: MClickAwayListenerTouchEvent
    get() = when (this.asDynamic().touchEvent) {
        is String -> MClickAwayListenerTouchEvent.valueOf(this.asDynamic().touchEvent)
        else -> MClickAwayListenerTouchEvent.disable
    }
    set(value) { this.asDynamic().touchEvent = value.value() }


fun RBuilder.mClickAwayListener(
    onClickAway: () -> Unit,
    mouseEvent: MClickAwayListenerMouseEvent = MClickAwayListenerMouseEvent.onClick,
    touchEvent: MClickAwayListenerTouchEvent = MClickAwayListenerTouchEvent.onTouchStart,
    handler: RHandler<MClickAwayListenerProps>? = null
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

