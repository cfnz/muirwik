package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Tab")
private external val tabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tabComponentType: ComponentType<TabProps> = tabModule.default

external interface TabProps: ButtonBaseProps {
    var disableFocusRipple: Boolean
    var icon: ReactNode
    var label: ReactNode
    var value: Any
    var wrapped: Boolean
}

fun RBuilder.tab(
    label: String? = null,
    value: Any? = null,
    icon: ReactNode? = null,
    handler: StyledHandler<TabProps>? = null
) {
    createStyled(tabComponentType, handler) {
        icon?.let { attrs.icon = it }
        label?.let {attrs.label = ReactNode(it) }
        value?.let { attrs.value = it }
    }
}
