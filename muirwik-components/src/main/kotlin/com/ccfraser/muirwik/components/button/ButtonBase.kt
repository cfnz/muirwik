package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.ElementType
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import org.w3c.dom.events.Event
import react.Props
import react.Ref


external interface MButtonBaseProps: StyledPropsWithCommonAttributes {
    var action: Ref<Any>
    var centerRipple: Boolean
    var component: ElementType
    var disabled: Boolean
    var disableRipple: Boolean
    var disableTouchRipple: Boolean
    var focusRipple: Boolean
    var focusVisibleClassName: String
    @JsName("LinkComponent")
    var linkComponent: String
    var onFocusVisible: () -> Unit

    @JsName("TouchRippleProps")
    var touchRippleProps: Props?
}

@Suppress("EnumEntryName")
enum class MButtonSize {
    small, medium, large
}

@Suppress("EnumEntryName")
enum class MButtonVariant {
    text, outlined, contained
}

