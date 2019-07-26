package com.ccfraser.muirwik.components.button

import org.w3c.dom.events.Event
import react.RProps
import styled.StyledProps


interface MButtonBaseProps: StyledProps {
    var centerRipple: Boolean
    var component: String
    var disabled: Boolean
    var disableRipple: Boolean
    var focusRipple: Boolean
    var onClick: ((Event) -> Unit)
    var onKeyboardFocus: (Event) -> Unit

    @JsName("TouchRippleProps")
    var touchRippleProps: RProps?
}

@Suppress("EnumEntryName")
enum class MButtonSize {
    small, medium, large
}

@Suppress("EnumEntryName")
enum class MButtonVariant {
    text, outlined, contained
}

