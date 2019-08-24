package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.dialog.MModalProps
import com.ccfraser.muirwik.components.dialog.ModalOnCloseReason
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.TransitionTimeout
import kotlinext.js.Object
import kotlinext.js.asJsObject
import kotlinext.js.jsObject
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

enum class MPopoverHorizontalPosition {
    left, center, right
}

enum class MPopoverVerticalPosition {
    top, center, bottom
}


interface MPopoverProps : MModalProps {
    var action: (actions: Object) -> Unit
    var anchorEl: Node?
    var anchorOriginHorizontal: MPopoverHorizontalPosition
    var anchorOriginVertical: MPopoverVerticalPosition

    /**
     * If anchorOriginHorizontalNumeric is defined, then anchorOriginHorizontal will not be used.
     */
    var anchorOriginHorizontalNumeric: Int

    /**
     * If anchorOriginVerticalNumeric is defined, then anchorOriginVertical will not be used.
     */
    var anchorOriginVerticalNumeric: Int
    var anchorPositionLeft: Int
    var anchorPositionRight: Int
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

    var transformOriginHorizontal: MPopoverHorizontalPosition
    var transformOriginVertical: MPopoverVerticalPosition
    var transformOriginHorizontalNumeric: Int
    var transformOriginVerticalNumeric: Int

    @JsName("TransitionComponent")
    var transitionComponent: KClass<out RComponent<MTransitionProps, RState>>?

    var transitionDuration: TransitionTimeout

    @JsName("TransitionProps")
    var transitionProps: RProps?
}

private fun MPopoverProps.redefineTypedProps() {
    // Need to do a bit more in this one as the real props have some java objects and we have
    // defined some props that don't exist in the real component to try and match it.

    if (anchorOriginHorizontal != undefined || anchorOriginHorizontalNumeric != undefined) {
        this.asDynamic().anchorOrigin = jsObject { }
        if (anchorOriginHorizontalNumeric != undefined) {
            this.asDynamic().anchorOrigin.horizontal = anchorOriginHorizontalNumeric
        } else {
            this.asDynamic().anchorOrigin.horizontal = anchorOriginHorizontal.toString()
        }
    }

    if (anchorOriginVertical != undefined || anchorOriginVerticalNumeric != undefined) {
        if (this.asDynamic().anchorOrigin == undefined) this.asDynamic().anchorOrigin = jsObject { }
        if (anchorOriginVerticalNumeric != undefined) {
            this.asDynamic().anchorOrigin.vertical = anchorOriginVerticalNumeric
        } else {
            this.asDynamic().anchorOrigin.vertical = anchorOriginVertical.toString()
        }
    }

    if (anchorPositionLeft != undefined || anchorPositionRight != undefined) {
        this.asDynamic().anchorPosition = jsObject {}
        this.asDynamic().anchorPosition.left = anchorPositionLeft
        this.asDynamic().anchorPosition.right = anchorPositionRight
    }

    if (anchorReference != undefined) this.asDynamic().anchorReference = anchorReference.toString()

    if (transformOriginHorizontal != undefined || transformOriginHorizontalNumeric != undefined) {
        this.asDynamic().transformOrigin = jsObject { }
        if (transformOriginHorizontalNumeric != undefined) {
            this.asDynamic().transformOrigin.horizontal = transformOriginHorizontalNumeric
        } else {
            this.asDynamic().transformOrigin.horizontal = transformOriginHorizontal.toString()
        }
    }

    if (transformOriginVertical != undefined || transformOriginVerticalNumeric != undefined) {
        if (this.asDynamic().transformOrigin == undefined) this.asDynamic().transformOrigin = jsObject { }
        if (transformOriginVerticalNumeric != undefined) {
            this.asDynamic().transformOrigin.vertical = transformOriginVerticalNumeric
        } else {
            this.asDynamic().transformOrigin.vertical = transformOriginVertical.toString()
        }
    }

    if (transitionDuration != undefined) {
        this.asDynamic().transitionDuration = transitionDuration.value()
    }

    // Make sure the dummy params are not defined... assigning them to undefined was not enough... we have to delete them
    val propsAsJsObject = this.asJsObject()
    js("""
        delete propsAsJsObject.anchorOriginHorizontal;
        delete propsAsJsObject.anchorOriginHorizontalNumeric;
        delete propsAsJsObject.anchorOriginVertical;
        delete propsAsJsObject.anchorOriginVerticalNumeric;
        delete propsAsJsObject.anchorReference;
        delete propsAsJsObject.transformOriginHorizontal;
        delete propsAsJsObject.transformOriginVertical;
    """)
}

/**
 * Note setting maxWidth to null will disable maxWidth (i.e. pass false to the underlying Material UI component)
 */
fun RBuilder.mPopover(
        open: Boolean = false,
        container: ReactElement? = null,
        fullScreen: Boolean = false,
        fullWidth: Boolean = false,
        maxWidth: Breakpoint? = Breakpoint.sm,
        anchorOriginHorizontal: MPopoverHorizontalPosition = MPopoverHorizontalPosition.left,
        anchorOriginVertical: MPopoverVerticalPosition = MPopoverVerticalPosition.top,
        hideBackdrop: Boolean = false,
        keepMounted: Boolean = false,
        closeAfterTransition: Boolean = false,

        onBackdropClick: SimpleEvent? = null,
        onClose: ((Event, reason: ModalOnCloseReason) -> Unit)? = null,
        onEscapeKeyDown: SimpleEvent? = null,

        className: String? = null,
        handler: StyledHandler<MPopoverProps>) = createStyled(popoverComponent) {
    attrs.anchorOriginHorizontal = anchorOriginHorizontal
    attrs.anchorOriginVertical = anchorOriginVertical
    attrs.closeAfterTransition = closeAfterTransition
    container?.let { attrs.container = it }
    attrs.hideBackdrop = hideBackdrop
    attrs.keepMounted = keepMounted
//    manager?.let { attrs.manager = manager }
    onBackdropClick?.let { attrs.onBackdropClick = it }
    onClose?.let { attrs.onClose = { event, string -> it(event, ModalOnCloseReason.valueOf(string)) }}
    onEscapeKeyDown?.let { attrs.onEscapeKeyDown = it }
    attrs.open = open

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}



