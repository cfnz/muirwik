package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/ListItemAvatar")
private external val listItemAvatarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listItemAvatarComponent: RComponent<MButtonBaseProps, RState> = listItemAvatarModule.default

fun RBuilder.mListItemAvatar(
        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemAvatarComponent) {
    setStyledPropsAndRunHandler(className, handler)
}

/** Just combines an mListItemAvatar and mAvatar */
fun RBuilder.mListItemAvatar(
        src: String? = null,
        srcSet: String? = null,
        alt: String? = null,
        component: String = "div",
        imgProps: RProps? = null,
        sizes: String? = null,

        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemAvatarComponent) {

    mAvatar(src, srcSet, alt, component, imgProps, sizes)

    setStyledPropsAndRunHandler(className, handler)
}