package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/lab/ToggleButton")
private external val toggleButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toggleButtonComponentType: ComponentType<MToggleButtonProps> = toggleButtonModule.default

external interface MToggleButtonProps : StyledPropsWithCommonAttributes {
  var disabled: Boolean
  var disableFocusRipple: Boolean
  var disableRipple: Boolean
  var selected: Boolean
  var value: Any
}

fun RBuilder.mToggleButton(
    disabled: Boolean = false,
    disableFocusRipple: Boolean = false,
    disableRipple: Boolean? = null,
    selected: Boolean? = null,
    value: Any? = null,
    className: String? = null,
    handler: StyledHandler<MToggleButtonProps>? = null
) {
  createStyled(toggleButtonComponentType, className, handler) {
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    disableRipple?.let { attrs.disableRipple = it }
    selected?.let { attrs.selected = it }
    value?.let { attrs.value = it }
  }
}