package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.MPaperProps
import com.ccfraser.muirwik.components.SimpleEvent
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponent: RComponent<MDialogProps, RState> = dialogModule.default

@Suppress("EnumEntryName")
enum class DialogScroll {
    paper, body
}

interface MDialogProps : MModalProps {
    var fullScreen: Boolean
    var fullWidth: Boolean
    var maxWidth: Any

    var onEnter: SimpleEvent
    var onEntered: SimpleEvent
    var onEntering: SimpleEvent
    var onExit: SimpleEvent
    var onExited: SimpleEvent
    var onExiting: SimpleEvent

    @JsName("PaperComponent")
    var paperComponent: RComponent<RProps, RState>?

    @JsName("PaperProps")
    var paperProps: MPaperProps?

    @JsName("TransitionComponent")
    var transitionComponent: dynamic

//    var transitionDuration: dynamic

    @JsName("TransitionProps")
    var transitionProps: RProps?

    var scroll: DialogScroll?
}

private fun MDialogProps.redefineTypedProps() {
    this.asDynamic().scroll = scroll.toString()
}

/**
 * Note setting maxWidth to null will disable maxWidth (i.e. pass false to the underlying Material UI component)
 * We will leave some of the props to be set by javascript
 */
fun RBuilder.mDialog(
        open: Boolean = false,
        fullScreen: Boolean = false,
        fullWidth: Boolean = false,
        maxWidth: Breakpoint? = Breakpoint.sm,
        hideBackdrop: Boolean = false,
        keepMounted: Boolean = false,
        closeAfterTransition: Boolean = false,

        onBackdropClick: SimpleEvent? = null,
        onClose: ((Event, reason: ModalOnCloseReason) -> Unit)? = null,
        onEscapeKeyDown: SimpleEvent? = null,

        scroll: DialogScroll = DialogScroll.paper,

        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        // Can't seem to get the transitionDuration working, but you can use the transitionProps, e.g.
        //     val transitionProps = EmptyProps()
        //     transitionProps.asDynamic().timeout = 5000
//        transitionDuration: TransitionTimeout? = null,
        transitionProps: RProps? = null,

        className: String? = null,
        handler: StyledHandler<MDialogProps>) = createStyled(dialogComponent) {
    attrs.closeAfterTransition = closeAfterTransition
    attrs.hideBackdrop = hideBackdrop
    attrs.keepMounted = keepMounted
//    manager?.let { attrs.manager = manager }
    onBackdropClick?.let { attrs.onBackdropClick = it }
    onClose?.let { attrs.onClose = { event, string -> it(event, ModalOnCloseReason.valueOf(string)) }}
    onEscapeKeyDown?.let { attrs.onEscapeKeyDown = it }
    attrs.open = open
    attrs.fullScreen = fullScreen
    attrs.fullWidth = fullWidth
    attrs.maxWidth = maxWidth?.toString() ?: false
    attrs.scroll = scroll
    transitionComponent?.let { attrs.transitionComponent = it.js }
//    transitionDuration?.let { attrs.transitionDuration = it }
    transitionProps?.let { attrs.transitionProps = it }

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}



