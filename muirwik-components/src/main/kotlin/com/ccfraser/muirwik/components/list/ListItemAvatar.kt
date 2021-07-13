package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.MAvatarVariant
import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import react.RProps
import styled.StyledHandler


@JsModule("@material-ui/core/ListItemAvatar")
private external val listItemAvatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listItemAvatarComponentType: ComponentType<MButtonBaseProps> = listItemAvatarModule.default

fun RBuilder.mListItemAvatar(
        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemAvatarComponentType) {
    setStyledPropsAndRunHandler(className, handler)
}

/** Just combines an mListItemAvatar and mAvatar */
fun RBuilder.mListItemAvatar(
        src: String? = null,
        alt: String? = null,
        srcSet: String? = null,
        variant: MAvatarVariant = MAvatarVariant.circular,
        component: String = "div",
        imgProps: RProps? = null,
        sizes: String? = null,

        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemAvatarComponentType) {

    mAvatar(src, srcSet, alt, variant, component, imgProps, sizes)

    setStyledPropsAndRunHandler(className, handler)
}