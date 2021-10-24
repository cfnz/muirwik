package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.utils.BreakpointNullToFalseDelegate
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.SimpleEvent
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.ElementType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponentType: ComponentType<DialogProps> = dialogModule.default

@Suppress("EnumEntryName")
enum class DialogScroll {
    paper, body
}

external interface DialogProps : ModalProps {
    var fullScreen: Boolean
    var fullWidth: Boolean

    var onEnter: SimpleEvent
    var onEntered: SimpleEvent
    var onEntering: SimpleEvent
    var onExit: SimpleEvent
    var onExited: SimpleEvent
    var onExiting: SimpleEvent

    @JsName("PaperComponent")
    var paperComponent: ElementType<Props>

    @JsName("PaperProps")
    var paperProps: PaperProps?

    @JsName("TransitionProps")
    var transitionProps: Props?

    @JsName("TransitionComponent")
    var transitionComponent: ElementType<Props>?
}
var DialogProps.maxWidth by BreakpointNullToFalseDelegate()
var DialogProps.scroll by EnumPropToString(DialogScroll.values())
//var MDialogProps.transitionComponent by TransitionComponentDelegate()
var DialogProps.transitionDuration by TransitionDurationDelegateNullable()


fun RBuilder.dialog(
    open: Boolean = false,
    handler: StyledHandler<DialogProps>
) {
    createStyled(dialogComponentType, handler) {
        attrs.open = open
    }
}


@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
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

    scroll: DialogScroll = DialogScroll.paper,

    transitionComponent: ElementType<Props>? = null,
    // Can't seem to get the transitionDuration working, but you can use the transitionProps, e.g.
    //     val transitionProps = EmptyProps()
    //     transitionProps.asDynamic().timeout = 5000
//        transitionDuration: TransitionTimeout? = null,
    transitionProps: Props? = null,

    className: String? = null,
    handler: StyledHandler<DialogProps>
) {
    createStyled(dialogComponentType, className, handler) {
        attrs.closeAfterTransition = closeAfterTransition
        attrs.hideBackdrop = hideBackdrop
        attrs.keepMounted = keepMounted
//    manager?.let { attrs.manager = manager }
        onBackdropClick?.let { attrs.onBackdropClick = it }
        attrs.onClose = onClose
        attrs.open = open
        attrs.fullScreen = fullScreen
        attrs.fullWidth = fullWidth
        attrs.maxWidth = maxWidth
        attrs.scroll = scroll
        transitionComponent?.let {  attrs.transitionComponent = it }
//    transitionDuration?.let { attrs.transitionDuration = it }
        transitionProps?.let { attrs.transitionProps = it }
    }
}