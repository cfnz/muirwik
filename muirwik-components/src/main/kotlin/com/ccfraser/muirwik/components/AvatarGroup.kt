package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/AvatarGroup")
private external val avatarGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val avatarGroupComponentType: ComponentType<AvatarGroupProps> = avatarGroupModule.default

@Suppress("EnumEntryName")
enum class AvatarGroupSpacing {
    medium, small
}

external interface AvatarGroupProps : StyledPropsWithCommonAttributes {
    var max: Int
}
var AvatarGroupProps.spacing by EnumPropToStringNullable(AvatarGroupSpacing.values())
var AvatarGroupProps.variant by EnumPropToStringNullable(AvatarVariant.values())

fun RBuilder.avatarGroup(max: Int = 5, variant: AvatarVariant = AvatarVariant.circular, handler: StyledHandler<AvatarGroupProps>) {
    createStyled(avatarGroupComponentType, handler) {
        attrs.max = max
        attrs.variant = variant
    }
}
