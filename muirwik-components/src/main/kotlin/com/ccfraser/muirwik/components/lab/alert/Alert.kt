package com.ccfraser.muirwik.components.lab.alert

import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler

@JsModule("@material-ui/lab/Alert")
private external val alertModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val jsComponent: RComponent<MAlertProps, RState> = alertModule.default

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
    addAsChild: Boolean = true,

    className: String? = null,
    handler: StyledHandler<MAlertProps>? = null
) = createStyled(jsComponent, addAsChild) {
    message?.let { +message }
    attrs.variant = variant
    attrs.severity = severity
    attrs.closeText = closeText
    onClose?.let { attrs.onClose = onClose }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mAlert(
    title: String,
    message: String?,
    variant: MAlertVariant = MAlertVariant.standard,
    severity: MAlertSeverity = MAlertSeverity.success,
    closeText: String = "Close",
    onClose: ((Event) -> Unit)? = null,
    addAsChild: Boolean = true,

    className: String? = null,
    handler: StyledHandler<MAlertProps>? = null
) = createStyled(jsComponent, addAsChild) {
    attrs.variant = variant
    attrs.severity = severity
    attrs.closeText = closeText
    onClose?.let { attrs.onClose = onClose }

    +mAlertTitle(title, false)
    message?.let { +message }

    setStyledPropsAndRunHandler(className, handler)
}
