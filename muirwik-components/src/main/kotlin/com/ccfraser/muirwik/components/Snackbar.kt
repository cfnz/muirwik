package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.TransitionTimeout
import kotlinext.js.asJsObject
import kotlinext.js.jsObject
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

interface MSnackbarProps : StyledProps {
    var action: ReactElement

    var anchorOriginHorizontal: MSnackbarHorizAnchor
    var anchorOriginVertical: MSnackbarVertAnchor

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
    var transitionComponent: KClass<out RComponent<MTransitionProps, RState>>

    var transitionDuration: TransitionTimeout

    @JsName("TransitionProps")
    var transitionProps: RProps
}

private fun MSnackbarProps.redefineTypedProps() {
    this.asDynamic().anchorOrigin = jsObject {}
    this.asDynamic().anchorOrigin.horizontal = anchorOriginHorizontal.toString()
    this.asDynamic().anchorOrigin.vertical = anchorOriginVertical.toString()

    if (transitionComponent != undefined) this.asDynamic().TransitionComponent = transitionComponent.js
    if (transitionDuration != undefined) this.asDynamic().transitionDuration = transitionDuration.value()

    // Make sure the dummy params are not defined... assigning them to undefined was not enough... we have to delete them
    val propsAsJsObject = this.asJsObject()
    js("""
        delete propsAsJsObject.anchorOriginHorizontal;
        delete propsAsJsObject.anchorOriginVertical;
    """)
}

fun RBuilder.mSnackbar(
        message: ReactElement,
        open: Boolean? = null,
        onClose: ((Event, MSnackbarOnCloseReason) -> Unit)? = null,
        horizAnchor: MSnackbarHorizAnchor = MSnackbarHorizAnchor.center,
        vertAnchor: MSnackbarVertAnchor = MSnackbarVertAnchor.bottom,
        key: String? = null,
        autoHideDuration: Int? = null,
        resumeHideDuration: Int? = null,

        className: String? = null,
        handler: StyledHandler<MSnackbarProps>? = null) = createStyled(snackbarComponent) {
    attrs.anchorOriginHorizontal = horizAnchor
    attrs.anchorOriginVertical = vertAnchor
    autoHideDuration?.let { attrs.autoHideDuration = it }
    key?.let { attrs.key = it }
    attrs.message = message
    onClose?.let { attrs.onClose = { event, string -> it(event, MSnackbarOnCloseReason.valueOf(string)) }}
    open?.let { attrs.open = it }
    resumeHideDuration?.let { attrs.resumeHideDuration = it }

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}

/**
 * Just one with a string for the message
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
        handler: StyledHandler<MSnackbarProps>? = null): ReactElement {
    @Suppress("UnsafeCastFromDynamic")
    val dynamicElement: ReactElement = message.asDynamic()
    return mSnackbar(dynamicElement, open, onClose, horizAnchor, vertAnchor, key, autoHideDuration, resumeHideDuration,
            className, handler)
}
