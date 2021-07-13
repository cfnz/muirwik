package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@material-ui/core/BottomNavigation")
private external val bottomNavigationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val bottomNavigationComponentType: ComponentType<MBottomNavigationProps> = bottomNavigationModule.default


external interface MBottomNavigationProps: StyledPropsWithCommonAttributes {
    var component: String
    var onChange: (event: Event, value: Any) -> Unit
    var showLabels: Boolean
    var value: Any
}

fun RBuilder.mBottomNavigation(
        value: Any = false, // false means none selected
        showLabels: Boolean = false,
        component: String = "div",
        onChange: ((event: Event, indexValue: Any) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MBottomNavigationProps>? = null) = createStyled(bottomNavigationComponentType) {
    attrs.component = component
    onChange?.let { attrs.onChange = it }
    attrs.showLabels = showLabels
    attrs.value = value

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/BottomNavigationAction")
private external val bottomNavigationActionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val bottomNavigationActionComponentType: ComponentType<MBottomNavigationActionProps> = bottomNavigationActionModule.default

external interface MBottomNavigationActionProps: MButtonBaseProps {
    var icon: ReactElement
    var label: ReactElement
    var showLabel: Boolean
    var value: Any
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun RBuilder.mBottomNavigationAction(
        label: String,
        icon: ReactElement? = null,
        showLabel: Boolean? = null,
        value: Any? = null,
        disabled: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MBottomNavigationActionProps>? = null) = mBottomNavigationAction(
    label.asDynamic() as ReactElement?, icon, showLabel, value, disabled, className, handler
)


fun RBuilder.mBottomNavigationAction(
        label: ReactElement? = null,
        icon: ReactElement? = null,
        showLabel: Boolean? = null,
        value: Any? = null,
        disabled: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MBottomNavigationActionProps>? = null) = createStyled(bottomNavigationActionComponentType) {
    attrs.disabled = disabled
    icon?.let { attrs.icon = icon }
    label?.let { attrs.label = it }
    showLabel?.let { attrs.showLabel = showLabel }
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}

