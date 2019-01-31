package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.MPaperProps
import com.ccfraser.muirwik.components.SimpleEvent
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponent: RComponent<MDialogProps, RState> = dialogModule.default

@Suppress("EnumEntryName")
enum class DialogMaxWidth {
    xs, sm, md, lg, disable
}

@Suppress("EnumEntryName")
enum class DialogScroll {
    paper, body
}

interface MModalProps : StyledProps {
    @JsName("BackdropComponent")
    var backdropComponent: ReactElement

    @Suppress("BackdropProps")
    var backdropProps: RProps

    var container: ReactElement
    var disableAutoFocus: Boolean
    var disableBackdropClick: Boolean
    var disableEnforceFocus: Boolean
    var disableEscapeKeyDown: Boolean
    var disableRestoreFocus: Boolean
    var hideBackdrop: Boolean
    var keepMounted: Boolean

//    var manager: Any?

    var onBackdropClick: SimpleEvent
    var onClose: (Event, reason: String) -> Unit
    var onEscapeKeyDown: SimpleEvent
    var onRendered: SimpleEvent

    var open: Boolean
}

interface MDialogProps : MModalProps {
    var fullScreen: Boolean
    var fullWidth: Boolean
    var maxWidth: String

    var onEnter: SimpleEvent
    var onEntered: SimpleEvent
    var onEntering: SimpleEvent
    var onExit: SimpleEvent
    var onExited: SimpleEvent
    var onExiting: SimpleEvent

    @JsName("PaperProps")
    var paperProps: MPaperProps

    @JsName("TransitionComponent")
    var transitionComponent: dynamic

//    var transitionDuration: dynamic

    @JsName("TransitionProps")
    var transitionProps: RProps

    var scroll: String
}

/**
 * We will leave some of the props to be set by javascript
 */
fun RBuilder.mDialog(
        open: Boolean = false,
        container: ReactElement? = null,
        fullScreen: Boolean = false,
        fullWidth: Boolean = false,
        maxWidth: DialogMaxWidth = DialogMaxWidth.sm,
        disableAutoFocus: Boolean = false,
        disableBackdropClick: Boolean = false,
        disableEnforceFocus: Boolean = false,
        disableEscapeKeyDown: Boolean = false,
        disableRestoreFocus: Boolean = false,
        hideBackdrop: Boolean = false,
        keepMounted: Boolean = false,
        backdropComponent: ReactElement? = null,
        backdropProps: RProps? = null,

        onBackdropClick: SimpleEvent? = null,
        onClose: ((Event, reason: String) -> Unit)? = null,
        onEscapeKeyDown: SimpleEvent? = null,
        onRendered: SimpleEvent? = null,
        onEnter: SimpleEvent? = null,
        onEntered: SimpleEvent? = null,
        onEntering: SimpleEvent? = null,
        onExit: SimpleEvent? = null,
        onExited: SimpleEvent? = null,
        onExiting: SimpleEvent? = null,

        paperProps: MPaperProps? = null,

        scroll: DialogScroll = DialogScroll.paper,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        // Can't seem to get the transitionDuration working, but you can use the transitionProps, e.g.
        //     val transitionProps = EmptyProps()
        //     transitionProps.asDynamic().timeout = 5000
//        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        className: String? = null,
        handler: StyledHandler<MDialogProps>) = createStyled(dialogComponent) {
    backdropComponent?.let { attrs.backdropComponent = it }
    backdropProps?.let { attrs.backdropProps = it }
    container?.let { attrs.container = it }
    attrs.disableAutoFocus = disableAutoFocus
    attrs.disableBackdropClick = disableBackdropClick
    attrs.disableEnforceFocus = disableEnforceFocus
    attrs.disableEscapeKeyDown = disableEscapeKeyDown
    attrs.disableRestoreFocus = disableRestoreFocus
    attrs.hideBackdrop = hideBackdrop
    attrs.keepMounted = keepMounted
//    manager?.let { attrs.manager = manager }
    onBackdropClick?.let { attrs.onBackdropClick = it }
    onClose?.let { attrs.onClose = it }
    onEscapeKeyDown?.let { attrs.onEscapeKeyDown = it }
    onRendered?.let { attrs.onRendered = it }
    attrs.open = open
    attrs.fullScreen = fullScreen
    attrs.fullWidth = fullWidth
    attrs.maxWidth = maxWidth.toString()
    onEnter?.let { attrs.onEnter = it }
    onEntered?.let { attrs.onEntered = it }
    onEntering?.let { attrs.onEntering = it }
    onExit?.let { attrs.onExit = it }
    onExited?.let { attrs.onExited = it }
    onExiting?.let { attrs.onExiting = it }
    paperProps?.let { attrs.paperProps = it }
    attrs.scroll = scroll.toString()
    transitionComponent?.let { attrs.transitionComponent = it.js }
//    transitionDuration?.let { attrs.transitionDuration = it }
    transitionProps?.let { attrs.transitionProps = it }

    setStyledPropsAndRunHandler(className, handler)
}



