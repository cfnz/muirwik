package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.ElementType
import com.ccfraser.muirwik.components.OnClosePropWithReasonDelegate
import com.ccfraser.muirwik.components.SimpleEvent
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import react.ComponentType
import react.Props
import react.ReactElement


@JsModule("@mui/material/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponentType: ComponentType<MDialogProps> = dialogModule.default

@Suppress("EnumEntryName")
enum class ModalOnCloseReason {
    escapeKeyDown, backdropClick
}


external interface MModalProps : StyledPropsWithCommonAttributes {
    @JsName("BackdropComponent")
    var backdropComponent: ReactElement

    @Suppress("BackdropProps")
    var backdropProps: Props

    var closeAfterTransition: Boolean
    var component: ElementType
    var components: Array<ElementType>
    var componentProps: Props
    var container: ReactElement
    var disableAutoFocus: Boolean
    var disableEnforceFocus: Boolean
    var disableEscapeKeyDown: Boolean
    var disablePortal: Boolean
    var disableRestoreFocus: Boolean
    var disableScrollLock: Boolean
    var hideBackdrop: Boolean
    var keepMounted: Boolean
    var onBackdropClick: SimpleEvent
    var onRendered: SimpleEvent

    var open: Boolean
}
var MModalProps.onClose by OnClosePropWithReasonDelegate(ModalOnCloseReason.values())

