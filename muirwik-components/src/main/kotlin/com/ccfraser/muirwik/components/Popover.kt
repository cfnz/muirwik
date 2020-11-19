package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.dialog.MModalProps
import com.ccfraser.muirwik.components.dialog.ModalOnCloseReason
import com.ccfraser.muirwik.components.dialog.onClose
import com.ccfraser.muirwik.components.transitions.TransitionComponentDelegate
import com.ccfraser.muirwik.components.transitions.TransitionDurationWithAutoDelegate
import kotlinext.js.Object
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/Popover")
private external val popoverModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val popoverComponent: RComponent<MPopoverProps, RState> = popoverModule.default


enum class MPopoverAnchorRef {
    anchorEl, anchorPosition, none
}

enum class MPopoverHorizontalPosition {
    left, center, right
}

enum class MPopoverVerticalPosition {
    top, center, bottom
}

external interface MPopoverProps : MModalProps {
    var action: (actions: Object) -> Unit
    var anchorEl: Node?
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

    @JsName("TransitionProps")
    var transitionProps: RProps?
}

/**
 * anchorOriginHorizontal and anchorOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.anchorOriginHorizontal by EnumPropToStringNullable(
        MPopoverHorizontalPosition.values(), "anchorOrigin", "horizontal")

/**
 * anchorOriginVertical and anchorOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.anchorOriginVertical by EnumPropToStringNullable(
        MPopoverVerticalPosition.values(), "anchorOrigin", "vertical")

/**
 * anchorOriginHorizontal and anchorOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.anchorOriginHorizontalNumeric: Int? by ChildPropDelegateNullable("anchorOrigin", "horizontal")

/**
 * anchorOriginVertical and anchorOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.anchorOriginVerticalNumeric: Int? by ChildPropDelegateNullable("anchorOrigin", "vertical")

var MPopoverProps.anchorPositionLeft: Int? by ChildPropDelegateNullable("anchorPosition", "left")
var MPopoverProps.anchorPositionTop: Int? by ChildPropDelegateNullable("anchorPosition", "top")
var MPopoverProps.anchorReference by EnumPropToString(MPopoverAnchorRef.values())

/**
 * transformOriginHorizontal and transformOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.transformOriginHorizontal by EnumPropToStringNullable(
        MPopoverHorizontalPosition.values(), "transformOrigin", "horizontal")

/**
 * transformOriginVertical and transformOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.transformOriginVertical by EnumPropToStringNullable(
        MPopoverVerticalPosition.values(), "transformOrigin", "vertical")

/**
 * transformOriginHorizontal and transformOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.transformOriginHorizontalNumeric: Int? by ChildPropDelegateNullable("TransformOrigin", "horizontal")

/**
 * transformOriginVertical and transformOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var MPopoverProps.transformOriginVerticalNumeric: Int? by ChildPropDelegateNullable("TransformOrigin", "vertical")

var MPopoverProps.transitionComponent by TransitionComponentDelegate()
var MPopoverProps.transitionDuration by TransitionDurationWithAutoDelegate()


/**
 * Note setting maxWidth to null will disable maxWidth (i.e. pass false to the underlying Material UI component)
 */
fun RBuilder.mPopover(
        open: Boolean = false,
        container: ReactElement? = null,
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
    attrs.onClose = onClose
    onEscapeKeyDown?.let { attrs.onEscapeKeyDown = it }
    attrs.open = open

    setStyledPropsAndRunHandler(className, handler)
}



