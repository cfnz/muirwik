package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.TransitionComponentDelegate
import com.ccfraser.muirwik.components.transitions.TransitionDurationDelegate
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Snackbar")
private external val SnackbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val snackbarComponentType: ComponentType<MSnackbarProps> = SnackbarModule.default

@Suppress("EnumEntryName")
enum class MSnackbarHorizAnchor {
    left, center, right
}

@Suppress("EnumEntryName")
enum class MSnackbarVertAnchor {
    top, bottom
}

@Suppress("EnumEntryName")
enum class MSnackbarOnCloseReason {
    timeout, clickaway
}

external interface MSnackbarProps : StyledProps {
    var action: ReactElement

    var autoHideDuration: Int

    @JsName("ClickAwayListenerProps")
    var clickAwayListenerProps: Props

    @JsName("ContentProps")
    var contentProps: Props

    var disableWindowBlurListener: Boolean
    var key: String
    var message: ReactElement
    var onEnter: Event
    var onEntered: Event
    var onEntering: Event
    var onExit: Event
    var onExited: Event
    var onExiting: Event
    var open: Boolean
    var resumeHideDuration: Int

    @JsName("TransitionProps")
    var transitionProps: Props
}

var MSnackbarProps.anchorOriginHorizontal by EnumPropToStringNullable(MSnackbarHorizAnchor.values(), "anchorOrigin", "horizontal")
var MSnackbarProps.anchorOriginVertical by EnumPropToStringNullable(MSnackbarVertAnchor.values(), "anchorOrigin", "vertical")
var MSnackbarProps.onClose by OnClosePropWithReasonDelegate(MSnackbarOnCloseReason.values())
var MSnackbarProps.transitionComponent by TransitionComponentDelegate()
var MSnackbarProps.transitionDuration by TransitionDurationDelegate()

/**
 * Base builder for Snackbar.
 */
fun RBuilder.mSnackbar(
    message: ReactElement?,
    open: Boolean? = null,
    onClose: ((Event, MSnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: MSnackbarHorizAnchor = MSnackbarHorizAnchor.center,
    vertAnchor: MSnackbarVertAnchor = MSnackbarVertAnchor.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,
    className: String? = null,
    handler: StyledHandler<MSnackbarProps>? = null
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
fun RBuilder.mSnackbar(
    message: String,
    open: Boolean? = null,
    onClose: ((Event, MSnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: MSnackbarHorizAnchor = MSnackbarHorizAnchor.center,
    vertAnchor: MSnackbarVertAnchor = MSnackbarVertAnchor.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,

    className: String? = null,
    handler: StyledHandler<MSnackbarProps>? = null
) {
    @Suppress("UnsafeCastFromDynamic")
    val dynamicElement: ReactElement = message.asDynamic()
    mSnackbar(dynamicElement, open, onClose, horizAnchor, vertAnchor, key, autoHideDuration, resumeHideDuration, className, handler)
}


/**
 * Builder for Snackbar without message as the content is based on the child.
 */
fun RBuilder.mSnackbar(
    open: Boolean? = null,
    onClose: ((Event, MSnackbarOnCloseReason) -> Unit)? = null,
    horizAnchor: MSnackbarHorizAnchor = MSnackbarHorizAnchor.center,
    vertAnchor: MSnackbarVertAnchor = MSnackbarVertAnchor.bottom,
    key: String? = null,
    autoHideDuration: Int? = null,
    resumeHideDuration: Int? = null,

    className: String? = null,
    handler: StyledHandler<MSnackbarProps>? = null
) {
    @Suppress("UnsafeCastFromDynamic")
    mSnackbar(null, open, onClose, horizAnchor, vertAnchor, key, autoHideDuration, resumeHideDuration, className, handler)
}
