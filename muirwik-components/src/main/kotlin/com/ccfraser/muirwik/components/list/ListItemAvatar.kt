package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.MAvatarVariant
import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mAvatar
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ListItemAvatar")
private external val listItemAvatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listItemAvatarComponentType: ComponentType<MButtonBaseProps> = listItemAvatarModule.default

fun RBuilder.mListItemAvatar(
    className: String? = null,
    handler: StyledHandler<MButtonBaseProps>? = null
) {
    createStyled(listItemAvatarComponentType, className, handler)
}

/** Just combines an mListItemAvatar and mAvatar */
fun RBuilder.mListItemAvatar(
    src: String? = null,
    alt: String? = null,
    srcSet: String? = null,
    variant: MAvatarVariant = MAvatarVariant.circular,
    component: String = "div",
    imgProps: Props? = null,
    sizes: String? = null,
    className: String? = null,
    handler: StyledHandler<MButtonBaseProps>? = null
) {
    createStyled(listItemAvatarComponentType, className, handler) {
        mAvatar(src, srcSet, alt, variant, component, imgProps, sizes)
    }
}