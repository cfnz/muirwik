package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ListItemSecondaryAction")
private external val listItemSecondaryActionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemSecondaryActionComponentType: ComponentType<ButtonBaseProps> = listItemSecondaryActionModule.default

fun RBuilder.listItemSecondaryAction(handler: StyledHandler<ButtonBaseProps>) {
    createStyled(listItemSecondaryActionComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mListItemSecondaryAction(
    className: String? = null,
    handler: StyledHandler<ButtonBaseProps>? = null
) {
    createStyled(listItemSecondaryActionComponentType, className, handler)
}
