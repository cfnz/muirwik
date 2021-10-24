package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Zoom")
private external val zoomModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val zoomComponentType: ComponentType<ZoomProps> = zoomModule.default

external interface ZoomProps : TransitionProps
var ZoomProps.timeout by TransitionDurationDelegate()

fun RBuilder.zoom(show: Boolean = false, handler: StyledHandler<ZoomProps>) {
    createStyled(zoomComponentType, handler) {
        attrs.show = show
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mZoom(
    show: Boolean = false,
    timeout: TransitionDuration? = null,
    className: String? = null,
    handler: StyledHandler<ZoomProps>? = null
) {
    createStyled(zoomComponentType, className, handler) {
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

