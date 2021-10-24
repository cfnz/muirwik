package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/Avatar")
private external val avatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val avatarComponentType: ComponentType<AvatarProps> = avatarModule.default

@Suppress("EnumEntryName")
enum class AvatarVariant {
    circular, rounded, square
}

external interface AvatarProps : StyledPropsWithCommonAttributes {
    var alt: String
    var component: ElementType
    var imgProps: Props
    var sizes: String
    var src: String
    var srcSet: String
}
var AvatarProps.variant by EnumPropToStringNullable(AvatarVariant.values())

fun RBuilder.avatar(variant: AvatarVariant = AvatarVariant.circular, handler: StyledHandler<AvatarProps>) {
    createStyled(avatarComponentType, handler) {
        attrs.variant = variant
    }
}

fun RBuilder.avatar(
    src: String,
    alt: String? = null,
    srcSet: String? = null,
    variant: AvatarVariant = AvatarVariant.circular,
    handler: StyledHandler<AvatarProps>? = null
) {
    createStyled(avatarComponentType, handler) {
        alt?.let { attrs.alt = it }
        attrs.src = src
        srcSet?.let { attrs.srcSet = it }
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mAvatar(
    src: String? = null,
    alt: String? = null,
    srcSet: String? = null,
    variant: AvatarVariant = AvatarVariant.circular,
    component: String = "div",
    imgProps: Props? = null,
    sizes: String? = null,
    className: String? = null,
    handler: StyledHandler<AvatarProps>? = null
) {
    createStyled(avatarComponentType, className, handler) {
        alt?.let { attrs.alt = alt }
        attrs.component = component
        imgProps?.let { attrs.imgProps = imgProps }
        sizes?.let { attrs.sizes = sizes }
        src?.let { attrs.src = src }
        srcSet?.let { attrs.srcSet = srcSet }
        attrs.variant = variant
    }
}
