package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import react.ElementType
import styled.StyledHandler


@JsModule("@mui/material/Popover")
private external val popoverModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val popoverComponentType: ComponentType<PopoverProps> = popoverModule.default


enum class PopoverAnchorRef {
    anchorEl, anchorPosition, none
}

enum class PopoverHorizontalPosition {
    left, center, right
}

enum class PopoverVerticalPosition {
    top, center, bottom
}

external interface PopoverProps : ModalProps {
    var action: Ref<*>
    var anchorEl: Node?
    var elevation: Int
    var marginThreshold: Int

    @JsName("PaperProps")
    var paperProps: PaperProps?

    @JsName("TransitionProps")
    var transitionProps: Props?

    @JsName("TransitionComponent")
    var transitionComponent: ElementType<PropsWithRef<Any>>
}

/**
 * anchorOriginHorizontal and anchorOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.anchorOriginHorizontal by EnumPropToStringNullable(
        PopoverHorizontalPosition.values(), "anchorOrigin", "horizontal")

/**
 * anchorOriginVertical and anchorOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.anchorOriginVertical by EnumPropToStringNullable(
        PopoverVerticalPosition.values(), "anchorOrigin", "vertical")

/**
 * anchorOriginHorizontal and anchorOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.anchorOriginHorizontalNumeric: Int? by ChildPropDelegateNullable("anchorOrigin", "horizontal")

/**
 * anchorOriginVertical and anchorOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.anchorOriginVerticalNumeric: Int? by ChildPropDelegateNullable("anchorOrigin", "vertical")

var PopoverProps.anchorPositionLeft: Int? by ChildPropDelegateNullable("anchorPosition", "left")
var PopoverProps.anchorPositionTop: Int? by ChildPropDelegateNullable("anchorPosition", "top")
var PopoverProps.anchorReference by EnumPropToString(PopoverAnchorRef.values())

/**
 * transformOriginHorizontal and transformOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.transformOriginHorizontal by EnumPropToStringNullable(
        PopoverHorizontalPosition.values(), "transformOrigin", "horizontal")

/**
 * transformOriginVertical and transformOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.transformOriginVertical by EnumPropToStringNullable(
        PopoverVerticalPosition.values(), "transformOrigin", "vertical")

/**
 * transformOriginHorizontal and transformOriginHorizontalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.transformOriginHorizontalNumeric: Int? by ChildPropDelegateNullable("TransformOrigin", "horizontal")

/**
 * transformOriginVertical and transformOriginVerticalNumeric are mutually exclusive. Setting one will cause the other to be null.
 */
var PopoverProps.transformOriginVerticalNumeric: Int? by ChildPropDelegateNullable("TransformOrigin", "vertical")

var PopoverProps.transitionDuration by TransitionDurationWithAutoDelegate()


fun RBuilder.popover(
    open: Boolean = false,
    handler: StyledHandler<PopoverProps>
) {
    createStyled(popoverComponentType, handler) {
        attrs.open = open
    }
}


@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mPopover(
    open: Boolean = false,
    container: ReactElement? = null,
    anchorOriginHorizontal: PopoverHorizontalPosition = PopoverHorizontalPosition.left,
    anchorOriginVertical: PopoverVerticalPosition = PopoverVerticalPosition.top,
    hideBackdrop: Boolean = false,
    keepMounted: Boolean = false,
    closeAfterTransition: Boolean = false,
    onBackdropClick: SimpleEvent? = null,
    onClose: ((Event, reason: ModalOnCloseReason) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<PopoverProps>
) {
    createStyled(popoverComponentType, className, handler) {
        attrs.anchorOriginHorizontal = anchorOriginHorizontal
        attrs.anchorOriginVertical = anchorOriginVertical
        attrs.closeAfterTransition = closeAfterTransition
        container?.let { attrs.container = it }
        attrs.hideBackdrop = hideBackdrop
        attrs.keepMounted = keepMounted
//    manager?.let { attrs.manager = manager }
        onBackdropClick?.let { attrs.onBackdropClick = it }
        attrs.onClose = onClose
        attrs.open = open
    }
}



