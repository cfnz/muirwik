package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.transitions.MTransitionProps
import com.ccfraser.muirwik.wrapper.transitions.TransitionTimeout
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
    top, center, bottom
}

@Suppress("EnumEntryName")
enum class OnCloseReason {
    timeout, clickAway
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

/**
 * We want to get ourselves in-between the javascript callback and the user who may have registered a callback so
 * we can change the type of the parameter. Rather than a class, we just create a couple of private "local globals"
 * to track things.
 */
private var onCloseParam: ((Event, OnCloseReason) -> Unit)? = null
private fun internalOnClose(event: Event, reason: String) {
    onCloseParam?.let {
        if (reason.toLowerCase() == "timeout") {
            it(event, OnCloseReason.timeout)
        } else {
            it(event, OnCloseReason.clickAway)
        }
    }
}

fun RBuilder.mSnackbar(
        message: ReactElement,
        action: ReactElement? = null,
        open: Boolean? = null,

        horizAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.center,
        vertAnchor: SnackbarVertAnchor = SnackbarVertAnchor.bottom,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        key: String? = null,

        contentProps: RProps? = null,
        clickAwayListenerProps: RProps? = null,

        onClose: ((Event, OnCloseReason) -> Unit)? = null,
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
    onClose?.let { attrs.onClose = ::internalOnClose; onCloseParam = it }
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
        action: ReactElement? = null,
        open: Boolean? = null,

        horizAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.center,
        vertAnchor: SnackbarVertAnchor = SnackbarVertAnchor.bottom,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        key: String? = null,

        contentProps: RProps? = null,
        clickAwayListenerProps: RProps? = null,

        onClose: ((Event, OnCloseReason) -> Unit)? = null,
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
    return mSnackbar(dynamicElement, action, open, horizAnchor, vertAnchor, transitionComponent, transitionDuration,
            transitionProps, key, contentProps, clickAwayListenerProps, onClose, onEnter, onEntered, onEntering, onExit,
            onExited, onExiting, autoHideDuration, resumeHideDuration, disableWindowBlurListener, className, handler)
}
