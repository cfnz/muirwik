package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/lab/ToggleButtonGroup")
private external val toggleButtonGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toggleButtonGroupComponentType: ComponentType<MToggleButtonGroupProps> = toggleButtonGroupModule.default

@Suppress("EnumEntryName")
enum class MToggleButtonGroupOrientation {
  horizontal, vertical
}

@Suppress("EnumEntryName")
enum class MToggleButtonGroupSize {
  large, medium, small
}

external interface MToggleButtonGroupProps : StyledPropsWithCommonAttributes {
  var exclusive: Boolean
  var onChange: (event: Object, value: Any) -> Unit
  var value: Any
}

var MToggleButtonGroupProps.orientation by EnumPropToStringNullable(MToggleButtonGroupOrientation.values())
var MToggleButtonGroupProps.size by EnumPropToStringNullable(MToggleButtonGroupSize.values())

fun RBuilder.mToggleButtonGroup(
    exclusive: Boolean = false,
    onChange: ((event: Object, value: Any) -> Unit)? = null,
    orientation: MToggleButtonGroupOrientation = MToggleButtonGroupOrientation.horizontal,
    size: MToggleButtonGroupSize = MToggleButtonGroupSize.medium,
    value: Any? = null,
    className: String? = null,
    handler: StyledHandler<MToggleButtonGroupProps>? = null,
) {
  createStyled(toggleButtonGroupComponentType, className, handler) {
    attrs.exclusive = exclusive
    onChange?.let { attrs.onChange = it }
    attrs.orientation = orientation
    attrs.size = size
    value?.let { attrs.value = it }
  }
}