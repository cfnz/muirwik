package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/Avatar")
private external val avatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val avatarComponent: RComponent<MAvatarProps, RState> = avatarModule.default

interface MAvatarProps : StyledProps {
    var alt: String
    var component: String
    var imgProps: RProps
    var sizes: String
    var src: String
    var srcSet: String
}

fun RBuilder.mAvatar(
        src: String? = null,
        alt: String? = null,
        srcSet: String? = null,
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

    setStyledPropsAndRunHandler(className, handler)
}
