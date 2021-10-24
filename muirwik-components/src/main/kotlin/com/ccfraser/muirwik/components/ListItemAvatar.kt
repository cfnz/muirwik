package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ListItemAvatar")
private external val listItemAvatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listItemAvatarComponentType: ComponentType<ButtonBaseProps> = listItemAvatarModule.default

fun RBuilder.listItemAvatar(handler: StyledHandler<ButtonBaseProps>) {
    createStyled(listItemAvatarComponentType, handler)
}

/** Just combines an mListItemAvatar and mAvatar */
fun RBuilder.listItemAvatar(
    src: String,
    alt: String? = null,
    srcSet: String? = null,
    variant: AvatarVariant = AvatarVariant.circular,
    handler: StyledHandler<ButtonBaseProps>? = null
) {
    createStyled(listItemAvatarComponentType,  handler) {
        avatar(src, srcSet, alt, variant)
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mListItemAvatar(
    className: String? = null,
    handler: StyledHandler<ButtonBaseProps>? = null
) {
    createStyled(listItemAvatarComponentType, className, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
/** Just combines an mListItemAvatar and mAvatar */
fun RBuilder.mListItemAvatar(
    src: String? = null,
    alt: String? = null,
    srcSet: String? = null,
    variant: AvatarVariant = AvatarVariant.circular,
    component: String = "div",
    imgProps: Props? = null,
    sizes: String? = null,
    className: String? = null,
    handler: StyledHandler<ButtonBaseProps>? = null
) {
    createStyled(listItemAvatarComponentType, className, handler) {
        mAvatar(src, srcSet, alt, variant, component, imgProps, sizes)
    }
}