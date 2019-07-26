package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.StyledProps


@JsModule("@material-ui/core/ClickAwayListener")
private external val clickAwayListenerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val clickAwayListenerComponent: RComponent<MClickAwayListenerProps, RState> = clickAwayListenerModule.default

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

interface MClickAwayListenerProps : StyledProps {
    var mouseEvent: dynamic
    var onClickAway: () -> Unit
    var touchEvent: dynamic
}

fun RBuilder.mClickAwayListener(
        onClickAway: () -> Unit,
        mouseEvent: MClickAwayListenerMouseEvent = MClickAwayListenerMouseEvent.onClick,
        touchEvent: MClickAwayListenerTouchEvent = MClickAwayListenerTouchEvent.onTouchStart,
        handler: RHandler<MClickAwayListenerProps>? = null) = child(clickAwayListenerComponent) {
    attrs.mouseEvent = mouseEvent.value()
    attrs.touchEvent = touchEvent.value()
    attrs.onClickAway = onClickAway

    if (handler != null) handler()
}

