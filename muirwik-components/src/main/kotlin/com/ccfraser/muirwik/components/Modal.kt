package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.OnEventWithReasonDelegate
import com.ccfraser.muirwik.components.utils.SimpleEvent
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import react.ComponentType
import react.Props
import react.ReactElement


@JsModule("@mui/material/Dialog")
private external val dialogModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogComponentType: ComponentType<DialogProps> = dialogModule.default

@Suppress("EnumEntryName")
enum class ModalOnCloseReason {
    escapeKeyDown, backdropClick
}


external interface ModalProps : StyledPropsWithCommonAttributes {
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
var ModalProps.onClose by OnEventWithReasonDelegate(ModalOnCloseReason.values())

