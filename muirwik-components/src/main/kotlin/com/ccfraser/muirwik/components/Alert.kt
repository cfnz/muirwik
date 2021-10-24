package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/Alert")
private external val alertModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val alertComponentType: ComponentType<AlertProps> = alertModule.default

@Suppress("EnumEntryName")
enum class AlertVariant {
    filled, outlined, standard
}

@Suppress("EnumEntryName")
enum class AlertSeverity {
    error, info, success, warning
}

external interface AlertProps : StyledPropsWithCommonAttributes {
    var action: ReactElement
    var closeText: String
    var icon: ReactElement
    var onClose: (Event) -> Unit
    var role: String
}

var AlertProps.color by EnumPropToStringNullable(AlertSeverity.values())
var AlertProps.severity by EnumPropToStringNullable(AlertSeverity.values())
var AlertProps.variant by EnumPropToStringNullable(AlertVariant.values())


fun RBuilder.alert(
    message: String? = null,
    severity: AlertSeverity = AlertSeverity.success,
    variant: AlertVariant = AlertVariant.standard,
    handler: StyledHandler<AlertProps>? = null
) {
    createStyled(alertComponentType, handler) {
        message?.let { +message }
        attrs.severity = severity
        attrs.variant = variant
    }
}

fun RBuilder.alert(
    message: String,
    title: String,
    severity: AlertSeverity = AlertSeverity.success,
    variant: AlertVariant = AlertVariant.standard,
    handler: StyledHandler<AlertProps>? = null
) {
    createStyled(alertComponentType, handler) {
        alertTitle(title)
        +message
        attrs.severity = severity
        attrs.variant = variant
    }
}


@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAlert(
    message: String?,
    variant: AlertVariant = AlertVariant.standard,
    severity: AlertSeverity = AlertSeverity.success,
    closeText: String = "Close",
    onClose: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<AlertProps>? = null
) {
    createStyled(alertComponentType, className, handler) {
        message?.let { +message }
        attrs.variant = variant
        attrs.severity = severity
        attrs.closeText = closeText
        onClose?.let { attrs.onClose = onClose }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAlert(
    title: String,
    message: String?,
    variant: AlertVariant = AlertVariant.standard,
    severity: AlertSeverity = AlertSeverity.success,
    closeText: String = "Close",
    onClose: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<AlertProps>? = null
){
    createStyled(alertComponentType, className, handler) {
        attrs.variant = variant
        attrs.severity = severity
        attrs.closeText = closeText
        onClose?.let { attrs.onClose = onClose }

        alertTitle(title)
        message?.let { +message }
    }
}
