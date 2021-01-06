package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/Avatar")
private external val avatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val avatarComponent: RComponent<MAvatarProps, RState> = avatarModule.default

@Suppress("EnumEntryName")
enum class MAvatarVariant {
    circle, circular, rounded, square
}

external interface MAvatarProps : StyledPropsWithCommonAttributes {
    var alt: String
    var component: String
    var imgProps: RProps
    var sizes: String
    var src: String
    var srcSet: String
}
var MAvatarProps.variant by EnumPropToStringNullable(MAvatarVariant.values())

fun RBuilder.mAvatar(
        src: String? = null,
        alt: String? = null,
        srcSet: String? = null,
        variant: MAvatarVariant = MAvatarVariant.circle,
        component: String = "div",
        imgProps: RProps? = null,
        sizes: String? = null,
        addAsChild: Boolean = true,

        className: String? = null,
        handler: StyledHandler<MAvatarProps>? = null) = createStyled(avatarComponent, addAsChild) {
    alt?.let { attrs.alt = alt }
    attrs.component = component
    imgProps?.let { attrs.imgProps = imgProps }
    sizes?.let { attrs.sizes = sizes }
    src?.let { attrs.src = src }
    srcSet?.let { attrs.srcSet = srcSet }
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}
