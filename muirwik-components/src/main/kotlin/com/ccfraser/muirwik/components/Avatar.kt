package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import react.Props
import styled.StyledHandler

@JsModule("@material-ui/core/Avatar")
private external val avatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val avatarComponentType: ComponentType<MAvatarProps> = avatarModule.default

@Suppress("EnumEntryName")
enum class MAvatarVariant {
    circle, circular, rounded, square
}

external interface MAvatarProps : StyledPropsWithCommonAttributes {
    var alt: String
    var component: String
    var imgProps: Props
    var sizes: String
    var src: String
    var srcSet: String
}
var MAvatarProps.variant by EnumPropToStringNullable(MAvatarVariant.values())

fun RBuilder.mAvatar(
    src: String? = null,
    alt: String? = null,
    srcSet: String? = null,
    variant: MAvatarVariant = MAvatarVariant.circular,
    component: String = "div",
    imgProps: Props? = null,
    sizes: String? = null,
    className: String? = null,
    handler: StyledHandler<MAvatarProps>? = null
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
