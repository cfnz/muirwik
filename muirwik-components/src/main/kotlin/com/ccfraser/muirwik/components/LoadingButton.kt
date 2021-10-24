package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/lab/LoadingButton")
private external val loadingButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val loadingButtonComponentType: ComponentType<LoadingButtonProps> = loadingButtonModule.default

@Suppress("EnumEntryName")
enum class LoadingPosition {
    start, end, center
}

external interface LoadingButtonProps : ButtonProps {
    var loading: Boolean
    var loadingIndicator: ReactNode
}
var LoadingButtonProps.loadingPosition by EnumPropToStringNullable(LoadingPosition.values())


fun RBuilder.loadingButton(
    caption: String,
    loading: Boolean = false,
    color: ButtonColor = ButtonColor.primary,
    variant: ButtonVariant = ButtonVariant.text,
    handler: StyledHandler<LoadingButtonProps>? = null
) {
    createStyled(loadingButtonComponentType, handler) {
        attrs.color = color
        attrs.loading = loading
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}

fun RBuilder.loadingButton(handler: StyledHandler<LoadingButtonProps>) {
    createStyled(loadingButtonComponentType, handler)
}
