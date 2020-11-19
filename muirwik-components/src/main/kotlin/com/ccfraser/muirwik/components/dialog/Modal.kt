package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.OnClosePropWithReasonDelegate
import com.ccfraser.muirwik.components.SimpleEvent
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement


@JsModule("@material-ui/core/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponent: RComponent<MDialogProps, RState> = dialogModule.default

@Suppress("EnumEntryName")
enum class ModalOnCloseReason {
    escapeKeyDown, backdropClick
}


external interface MModalProps : StyledPropsWithCommonAttributes {
    @JsName("BackdropComponent")
    var backdropComponent: ReactElement

    @Suppress("BackdropProps")
    var backdropProps: RProps

    var closeAfterTransition: Boolean
    var container: ReactElement
    var disableAutoFocus: Boolean
    var disableBackdropClick: Boolean
    var disableEnforceFocus: Boolean
    var disableEscapeKeyDown: Boolean
    var disableRestoreFocus: Boolean
    var hideBackdrop: Boolean
    var keepMounted: Boolean
    var onBackdropClick: SimpleEvent

    var onEscapeKeyDown: SimpleEvent
    var onRendered: SimpleEvent

    var open: Boolean
}
var MModalProps.onClose by OnClosePropWithReasonDelegate(ModalOnCloseReason.values())

