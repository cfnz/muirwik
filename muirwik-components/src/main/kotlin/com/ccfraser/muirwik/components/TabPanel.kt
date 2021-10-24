package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Tab")
private external val tabPanelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tabPanelComponentType: ComponentType<TabPanelProps> = tabPanelModule.default

external interface TabPanelProps: StyledProps {
    var value: String
}

fun RBuilder.tabPanel(value: String, handler: StyledHandler<TabPanelProps>) {
    createStyled(tabPanelComponentType, handler) {
        attrs.value = value
    }
}
