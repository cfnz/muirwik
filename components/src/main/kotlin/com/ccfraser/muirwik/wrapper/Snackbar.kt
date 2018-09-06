package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.transitions.MTransitionProps
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

interface MSnackbarProps : StyledProps {
    var action: ReactElement
    var anchorOrigin: dynamic
    var autoHideDuration: Int
    var disableWindowBlurListener: Boolean
    var key: String
    var message: ReactElement
    var onClose: (Event) -> Unit
    var onEnter: Event
    var onEntered: Event
    var onEntering: Event
    var onExit: Event
    var onExited: Event
    var onExiting: Event
    var open: Boolean
    var resumeHideDuration: Int

    @JsName("ContentProps")
    var contentProps: RProps

    @JsName("TransitionComponent")
    var transitionComponent: dynamic
//    var transitionDuration: dynamic
}

fun RBuilder.mSnackbar(
        message: ReactElement,
        action: ReactElement? = null,
        open: Boolean? = null,

        horizAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.center,
        vertAnchor: SnackbarVertAnchor = SnackbarVertAnchor.bottom,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,

        key: String? = null,

        contentProps: RProps? = null,

        onClose: ((Event) -> Unit)? = null,
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
    attrs.disableWindowBlurListener = disableWindowBlurListener
    key?.let { attrs.key = it }
    attrs.message = message
    onClose?.let { attrs.onClose = it }
    onEnter?.let { attrs.onEnter = it }
    onEntered?.let { attrs.onEntered = it }
    onEntering?.let { attrs.onEntering = it }
    onExit?.let { attrs.onExit = it }
    onExited?.let { attrs.onExited = it }
    onExiting?.let { attrs.onExiting = it }
    open?.let { attrs.open = it }
    resumeHideDuration?.let { attrs.resumeHideDuration = it }
    contentProps?.let { attrs.contentProps = it }
    transitionComponent?.let { attrs.transitionComponent = it.js }
//        transitionDuration?.let { attrs.transitionDuration = transitionDuration }

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

        key: String? = null,

        contentProps: RProps? = null,

        onClose: ((Event) -> Unit)? = null,
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
    return mSnackbar(dynamicElement, action, open, horizAnchor, vertAnchor, transitionComponent,
                key, contentProps, onClose, onEnter, onEntered, onEntering, onExit, onExited, onExiting,
                autoHideDuration, resumeHideDuration, disableWindowBlurListener, className, handler)
}
