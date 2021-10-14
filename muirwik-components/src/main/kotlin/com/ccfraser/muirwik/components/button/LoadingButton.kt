package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/lab/LoadingButton")
private external val loadingButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val loadingButtonComponentType: ComponentType<MLoadingButtonProps> = loadingButtonModule.default

@Suppress("EnumEntryName")
enum class MLoadingPosition {
    start, end, center
}

external interface MLoadingButtonProps : MButtonProps {
    var loading: Boolean
    var loadingIndicator: ReactNode
}
var MLoadingButtonProps.loadingPosition by EnumPropToStringNullable(MLoadingPosition.values())


fun RBuilder.mLoadingButton(
    caption: String,
    loading: Boolean = false,
    color: MButtonColor = MButtonColor.primary,
    variant: MButtonVariant = MButtonVariant.text,
    handler: StyledHandler<MLoadingButtonProps>? = null
) {
    createStyled(loadingButtonComponentType, handler) {
        attrs.color = color
        attrs.loading = loading
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}

fun RBuilder.mLoadingButton(handler: StyledHandler<MLoadingButtonProps>) {
    createStyled(loadingButtonComponentType, handler)
}
