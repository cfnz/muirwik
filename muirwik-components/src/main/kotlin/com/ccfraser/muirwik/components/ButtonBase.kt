package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import react.Props
import react.Ref


external interface ButtonBaseProps: StyledPropsWithCommonAttributes {
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
enum class ButtonSize {
    small, medium, large
}

@Suppress("EnumEntryName")
enum class ButtonVariant {
    text, outlined, contained
}

