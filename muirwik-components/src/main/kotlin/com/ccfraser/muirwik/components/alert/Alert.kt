package com.ccfraser.muirwik.components.alert

import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/Alert")
private external val alertModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val alertComponentType: ComponentType<MAlertProps> = alertModule.default

@Suppress("EnumEntryName")
enum class MAlertVariant {
    filled, outlined, standard
}

@Suppress("EnumEntryName")
enum class MAlertSeverity {
    error, info, success, warning
}

external interface MAlertProps : StyledPropsWithCommonAttributes {
    var action: ReactElement
    var icon: ReactElement
    var onClose: (Event) -> Unit
    var closeText: String
}

var MAlertProps.variant by EnumPropToStringNullable(MAlertVariant.values())
var MAlertProps.severity by EnumPropToStringNullable(MAlertSeverity.values())

fun RBuilder.mAlert(
    message: String?,
    variant: MAlertVariant = MAlertVariant.standard,
    severity: MAlertSeverity = MAlertSeverity.success,
    closeText: String = "Close",
    onClose: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MAlertProps>? = null
) {
    createStyled(alertComponentType, className, handler) {
        message?.let { +message }
        attrs.variant = variant
        attrs.severity = severity
        attrs.closeText = closeText
        onClose?.let { attrs.onClose = onClose }
    }
}

fun RBuilder.mAlert(
    title: String,
    message: String?,
    variant: MAlertVariant = MAlertVariant.standard,
    severity: MAlertSeverity = MAlertSeverity.success,
    closeText: String = "Close",
    onClose: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MAlertProps>? = null
){
    createStyled(alertComponentType, className, handler) {
        attrs.variant = variant
        attrs.severity = severity
        attrs.closeText = closeText
        onClose?.let { attrs.onClose = onClose }

        mAlertTitle(title)
        message?.let { +message }
    }
}
