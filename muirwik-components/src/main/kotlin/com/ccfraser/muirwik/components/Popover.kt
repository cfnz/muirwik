package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.dialog.MModalProps
import com.ccfraser.muirwik.components.dialog.ModalOnCloseReason
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import kotlinext.js.Object
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Popover")
private external val popoverModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val popoverComponent: RComponent<MPopoverProps, RState> = popoverModule.default


enum class MPopoverAnchorRef {
    anchorE1, anchorPosition, none
}

interface MPopoverProps : MModalProps {
    var action: (actions: Object) -> Unit
    var anchorEl: Node
    var anchorOrigin: dynamic
    var anchorPosition: dynamic
    var anchorReference: MPopoverAnchorRef
    var elevation: Int
    var getContentAnchorEl: () -> Node
    var marginThreshold: Int

    @JsName("ModalClasses")
    var modalClasses: Object

    var onEnter: SimpleEvent
    var onEntered: SimpleEvent
    var onEntering: SimpleEvent
    var onExit: SimpleEvent
    var onExited: SimpleEvent
    var onExiting: SimpleEvent

    @JsName("PaperProps")
    var paperProps: MPaperProps?

    var transformOrigin: dynamic

    @JsName("TransitionComponent")
    var transitionComponent: dynamic

    var transitionDuration: dynamic

    @JsName("TransitionProps")
    var transitionProps: RProps?
}

// TODO: Finish this
/**
 * Note setting maxWidth to null will disable maxWidth (i.e. pass false to the underlying Material UI component)
 * We will leave some of the props to be set by javascript
 */
fun RBuilder.mPopover(
        open: Boolean = false,
        container: ReactElement? = null,
        fullScreen: Boolean = false,
        fullWidth: Boolean = false,
        maxWidth: Breakpoint? = Breakpoint.sm,
        disableAutoFocus: Boolean = false,
        disableBackdropClick: Boolean = false,
        disableEnforceFocus: Boolean = false,
        disableEscapeKeyDown: Boolean = false,
        disableRestoreFocus: Boolean = false,
        hideBackdrop: Boolean = false,
        keepMounted: Boolean = false,
        backdropComponent: ReactElement? = null,
        backdropProps: RProps? = null,
        closeAfterTransition: Boolean = false,

        onBackdropClick: SimpleEvent? = null,
        onClose: ((Event, reason: ModalOnCloseReason) -> Unit)? = null,
        onEscapeKeyDown: SimpleEvent? = null,
        onRendered: SimpleEvent? = null,
        onEnter: SimpleEvent? = null,
        onEntered: SimpleEvent? = null,
        onEntering: SimpleEvent? = null,
        onExit: SimpleEvent? = null,
        onExited: SimpleEvent? = null,
        onExiting: SimpleEvent? = null,

        paperComponent: RComponent<RProps, RState>? = null,
        paperProps: MPaperProps? = null,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        // Can't seem to get the transitionDuration working, but you can use the transitionProps, e.g.
        //     val transitionProps = EmptyProps()
        //     transitionProps.asDynamic().timeout = 5000
//        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        className: String? = null,
        handler: StyledHandler<MPopoverProps>) = createStyled(popoverComponent) {
    backdropComponent?.let { attrs.backdropComponent = it }
    backdropProps?.let { attrs.backdropProps = it }
    attrs.closeAfterTransition = closeAfterTransition
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
    onClose?.let { attrs.onClose = { event, string -> it(event, ModalOnCloseReason.valueOf(string)) }}
    onEscapeKeyDown?.let { attrs.onEscapeKeyDown = it }
    onRendered?.let { attrs.onRendered = it }
    attrs.open = open
    onEnter?.let { attrs.onEnter = it }
    onEntered?.let { attrs.onEntered = it }
    onEntering?.let { attrs.onEntering = it }
    onExit?.let { attrs.onExit = it }
    onExited?.let { attrs.onExited = it }
    onExiting?.let { attrs.onExiting = it }
    paperProps?.let { attrs.paperProps = it }
    transitionComponent?.let { attrs.transitionComponent = it.js }
//    transitionDuration?.let { attrs.transitionDuration = it }
    transitionProps?.let { attrs.transitionProps = it }

    setStyledPropsAndRunHandler(className, handler)
}



