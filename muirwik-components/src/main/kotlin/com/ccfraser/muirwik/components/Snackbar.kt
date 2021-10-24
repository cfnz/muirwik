package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.OnEventWithReasonDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Snackbar")
private external val SnackbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val snackbarComponentType: ComponentType<SnackbarProps> = SnackbarModule.default

@Suppress("EnumEntryName")
enum class SnackbarAnchorHorizontal {
    left, center, right
}

@Suppress("EnumEntryName")
enum class SnackbarAnchorVertical {
    top, bottom
}

@Suppress("EnumEntryName")
enum class SnackbarOnCloseReason {
    timeout, clickaway
}

external interface SnackbarProps : StyledProps {
    var action: ReactNode

    var autoHideDuration: Int

    @JsName("ClickAwayListenerProps")
    var clickAwayListenerProps: Props

    @JsName("ContentProps")
    var contentProps: Props

    var disableWindowBlurListener: Boolean
    var key: String
    var message: ReactNode
    var open: Boolean
    var resumeHideDuration: Int

    @JsName("TransitionComponent")
    var transitionComponent: ElementType<Props>

    @JsName("TransitionProps")
    var transitionProps: Props
}

var SnackbarProps.anchorOriginHorizontal by EnumPropToStringNullable(SnackbarAnchorHorizontal.values(), "anchorOrigin", "horizontal")
var SnackbarProps.anchorOriginVertical by EnumPropToStringNullable(SnackbarAnchorVertical.values(), "anchorOrigin", "vertical")
var SnackbarProps.onClose by OnEventWithReasonDelegate(SnackbarOnCloseReason.values())
//var MSnackbarProps.transitionComponent by TransitionComponentDelegate()
var SnackbarProps.transitionDuration by TransitionDurationDelegate()


fun RBuilder.snackbar(handler: StyledHandler<SnackbarProps>) {
    createStyled(snackbarComponentType, handler)
}

fun RBuilder.snackbar(
    open: Boolean? = null,
    anchorOriginHorizontal: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center,
    anchorOriginVertical: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom,
    key: String? = null,
    handler: StyledHandler<SnackbarProps>? = null
) {
    createStyled(snackbarComponentType, handler) {
        attrs.anchorOriginHorizontal = anchorOriginHorizontal
        attrs.anchorOriginVertical = anchorOriginVertical
        key?.let { attrs.key = it }
        open?.let { attrs.open = it }
    }
}

fun RBuilder.snackbar(
    message: String,
    open: Boolean? = null,
    anchorOriginHorizontal: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center,
    anchorOriginVertical: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom,
    key: String? = null,
    handler: StyledHandler<SnackbarProps>? = null
) {
    createStyled(snackbarComponentType, handler) {
        attrs.anchorOriginHorizontal = anchorOriginHorizontal
        attrs.anchorOriginVertical = anchorOriginVertical
        key?.let { attrs.key = it }
        attrs.message = ReactNode(message)
        open?.let { attrs.open = it }
    }
}

/**
 * Base builder for Snackbar.
 */
@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mSnackbar(
    message: ReactElement?,
    open: Boolean? = null,
    onClose: ((Event, SnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center,
    vertAnchor: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,
    className: String? = null,
    handler: StyledHandler<SnackbarProps>? = null
) {
    createStyled(snackbarComponentType, className, handler) {
        attrs.anchorOriginHorizontal = horizAnchor
        attrs.anchorOriginVertical = vertAnchor
        autoHideDuration?.let { attrs.autoHideDuration = it }
        key?.let { attrs.key = it }
        message?.let { attrs.message = message}
        attrs.onClose = onClose
        open?.let { attrs.open = it }
        resumeHideDuration?.let { attrs.resumeHideDuration = it }
    }
}

/**
 * Builder for Snackbar with a message of type string.
 */
@Deprecated("Use the simpler 'non m' version.")
@Suppress("DEPRECATION")
fun RBuilder.mSnackbar(
    message: String,
    open: Boolean? = null,
    onClose: ((Event, SnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center,
    vertAnchor: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,

    className: String? = null,
    handler: StyledHandler<SnackbarProps>? = null
) {
    @Suppress("UnsafeCastFromDynamic")
    val dynamicElement: ReactElement = message.asDynamic()
    mSnackbar(dynamicElement, open, onClose, horizAnchor, vertAnchor, key, autoHideDuration, resumeHideDuration, className, handler)
}


/**
 * Builder for Snackbar without message as the content is based on the child.
 */
@Deprecated("Use the simpler 'non m' version.")
@Suppress("DEPRECATION")
fun RBuilder.mSnackbar(
    open: Boolean? = null,
    onClose: ((Event, SnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center,
    vertAnchor: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,

    className: String? = null,
    handler: StyledHandler<SnackbarProps>? = null
) {
    @Suppress("UnsafeCastFromDynamic")
    mSnackbar(null, open, onClose, horizAnchor, vertAnchor, key, autoHideDuration, resumeHideDuration, className, handler)
}
