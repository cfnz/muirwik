package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ListItemSecondaryAction")
private external val listItemSecondaryActionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemSecondaryActionComponentType: ComponentType<MButtonBaseProps> = listItemSecondaryActionModule.default

fun RBuilder.mListItemSecondaryAction(
    className: String? = null,
    handler: StyledHandler<MButtonBaseProps>? = null
) {
    createStyled(listItemSecondaryActionComponentType, className, handler)
}
