package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler

@JsModule("@mui/material/BottomNavigationAction")
private external val bottomNavigationActionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val bottomNavigationActionComponentType: ComponentType<BottomNavigationActionProps> = bottomNavigationActionModule.default

external interface BottomNavigationActionProps: ButtonBaseProps {
    var icon: ReactNode
    var label: ReactNode
    var showLabel: Boolean
    var value: Any
}

fun RBuilder.bottomNavigationAction(handler: StyledHandler<BottomNavigationActionProps>) {
    createStyled(bottomNavigationActionComponentType, handler)
}

fun RBuilder.bottomNavigationAction(
    label: String,
    icon: ReactElement? = null,
    value: Any? = null,
    handler: StyledHandler<BottomNavigationActionProps>? = null
) {
    createStyled(bottomNavigationActionComponentType, handler) {
        icon?.let { attrs.icon = icon }
        attrs.label = ReactNode(label)
        if (label.isNotBlank()) attrs.showLabel = true
        value?.let { attrs.value = it }
    }
}

fun RBuilder.bottomNavigationAction(
    icon: ReactElement,
    value: Any? = null,
    handler: StyledHandler<BottomNavigationActionProps>? = null
) {
    createStyled(bottomNavigationActionComponentType, handler) {
        attrs.icon = icon
        attrs.showLabel = false
        value?.let { attrs.value = it }
    }
}

@Deprecated("Use the simpler 'non m' version.")
@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun RBuilder.mBottomNavigationAction(
    label: String,
    icon: ReactElement? = null,
    showLabel: Boolean? = null,
    value: Any? = null,
    disabled: Boolean = false,
    className: String? = null,
    handler: StyledHandler<BottomNavigationActionProps>? = null
) {
    @Suppress("DEPRECATION")
    mBottomNavigationAction(label.asDynamic() as ReactElement?, icon, showLabel, value, disabled, className, handler)
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mBottomNavigationAction(
    label: ReactElement? = null,
    icon: ReactElement? = null,
    showLabel: Boolean? = null,
    value: Any? = null,
    disabled: Boolean = false,
    className: String? = null,
    handler: StyledHandler<BottomNavigationActionProps>? = null
) {
    createStyled(bottomNavigationActionComponentType, className, handler) {
        attrs.disabled = disabled
        icon?.let { attrs.icon = icon }
        label?.let { attrs.label = it }
        showLabel?.let { attrs.showLabel = showLabel }
        value?.let { attrs.value = it }
    }
}

