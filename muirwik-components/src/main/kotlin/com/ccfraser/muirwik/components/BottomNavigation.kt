package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/BottomNavigation")
private external val bottomNavigationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val bottomNavigationComponentType: ComponentType<MBottomNavigationProps> = bottomNavigationModule.default


external interface MBottomNavigationProps: StyledPropsWithCommonAttributes {
    var component: ElementType
    var onChange: (event: Event, value: Any) -> Unit
    var showLabels: Boolean
    var value: Any
}

fun RBuilder.bottomNavigation(
    value: Any = false, // false means none selected
    showLabels: Boolean = false,
    handler: StyledHandler<MBottomNavigationProps>
) {
    createStyled(bottomNavigationComponentType, handler) {
        attrs.showLabels = showLabels
        attrs.value = value
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mBottomNavigation(
    value: Any = false, // false means none selected
    showLabels: Boolean = false,
    component: String = "div",
    onChange: ((event: Event, indexValue: Any) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MBottomNavigationProps>? = null
) {
    createStyled(bottomNavigationComponentType, className, handler) {
        attrs.component = component
        onChange?.let { attrs.onChange = it }
        attrs.showLabels = showLabels
        attrs.value = value
    }
}
