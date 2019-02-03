package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.TransitionTimeout
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Snackbar")
private external val SnackbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val snackbarComponent: RComponent<MSnackbarProps, RState> = SnackbarModule.default

@Suppress("EnumEntryName")
enum class SnackbarHorizAnchor {
    left, center, right
}

@Suppress("EnumEntryName")
enum class SnackbarVertAnchor {
    top, bottom
}

@Suppress("EnumEntryName")
enum class SnackbarOnCloseReason {
    timeout, clickaway
}

interface MSnackbarProps : StyledProps {
    var action: ReactElement
    var anchorOrigin: dynamic
    var autoHideDuration: Int

    @JsName("ClickAwayListenerProps")
    var clickAwayListenerProps: RProps

    @JsName("ContentProps")
    var contentProps: RProps

    var disableWindowBlurListener: Boolean
    var key: String
    var message: ReactElement
    var onClose: (Event, String) -> Unit
    var onEnter: Event
    var onEntered: Event
    var onEntering: Event
    var onExit: Event
    var onExited: Event
    var onExiting: Event
    var open: Boolean
    var resumeHideDuration: Int


    @JsName("TransitionComponent")
    var transitionComponent: dynamic

    var transitionDuration: dynamic

    @JsName("TransitionProps")
    var transitionProps: RProps
}

fun RBuilder.mSnackbar(
        message: ReactElement,
        open: Boolean? = null,
        onClose: ((Event, SnackbarOnCloseReason) -> Unit)? = null,

        horizAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.center,
        vertAnchor: SnackbarVertAnchor = SnackbarVertAnchor.bottom,

        action: ReactElement? = null,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        key: String? = null,

        contentProps: RProps? = null,
        clickAwayListenerProps: RProps? = null,

        onEnter: Event? = null,
        onEntered: Event? = null,
        onEntering: Event? = null,
        onExit: Event? = null,
        onExited: Event? = null,
        onExiting: Event? = null,

        autoHideDuration: Int? = null,
        resumeHideDuration: Int? = null,

        disableWindowBlurListener: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MSnackbarProps>? = null) = createStyled(snackbarComponent) {
    action?.let { attrs.action = it }

    attrs.anchorOrigin = asDynamic()
    attrs.anchorOrigin.horizontal = horizAnchor.toString()
    attrs.anchorOrigin.vertical = vertAnchor.toString()

    autoHideDuration?.let { attrs.autoHideDuration = it }
    clickAwayListenerProps?.let { attrs.clickAwayListenerProps = it }
    contentProps?.let { attrs.contentProps = it }
    attrs.disableWindowBlurListener = disableWindowBlurListener
    key?.let { attrs.key = it }
    attrs.message = message
    onClose?.let { attrs.onClose = { event, string -> it(event, SnackbarOnCloseReason.valueOf(string)) }}
    onEnter?.let { attrs.onEnter = it }
    onEntered?.let { attrs.onEntered = it }
    onEntering?.let { attrs.onEntering = it }
    onExit?.let { attrs.onExit = it }
    onExited?.let { attrs.onExited = it }
    onExiting?.let { attrs.onExiting = it }
    open?.let { attrs.open = it }
    resumeHideDuration?.let { attrs.resumeHideDuration = it }
    transitionComponent?.let { attrs.transitionComponent = it.js }
    transitionDuration?.let { attrs.transitionDuration = transitionDuration.value() }
    transitionProps?.let { attrs.transitionProps = transitionProps }

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * Just one with a string for the message
 */
fun RBuilder.mSnackbar(
        message: String,
        open: Boolean? = null,
        onClose: ((Event, SnackbarOnCloseReason) -> Unit)? = null,

        horizAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.center,
        vertAnchor: SnackbarVertAnchor = SnackbarVertAnchor.bottom,

        action: ReactElement? = null,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        key: String? = null,

        contentProps: RProps? = null,
        clickAwayListenerProps: RProps? = null,

        onEnter: Event? = null,
        onEntered: Event? = null,
        onEntering: Event? = null,
        onExit: Event? = null,
        onExited: Event? = null,
        onExiting: Event? = null,

        autoHideDuration: Int? = null,
        resumeHideDuration: Int? = null,

        disableWindowBlurListener: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MSnackbarProps>? = null): ReactElement {
    @Suppress("UnsafeCastFromDynamic")
    val dynamicElement: ReactElement = message.asDynamic()
    return mSnackbar(dynamicElement, open, onClose, horizAnchor, vertAnchor, action, transitionComponent, transitionDuration,
            transitionProps, key, contentProps, clickAwayListenerProps, onEnter, onEntered, onEntering, onExit,
            onExited, onExiting, autoHideDuration, resumeHideDuration, disableWindowBlurListener, className, handler)
}
