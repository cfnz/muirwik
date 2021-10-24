package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Tabs")
private external val tabsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tabsComponentType: ComponentType<TabsProps> = tabsModule.default

@Suppress("EnumEntryName")
enum class TabIndicatorColor {
    primary, secondary
}

@Suppress("EnumEntryName")
enum class TabTextColor {
    inherit, primary, secondary
}

@Suppress("EnumEntryName")
enum class TabScrollButtons {
    auto, on, off
}

@Suppress("EnumEntryName")
enum class TabVariant {
    fullWidth, scrollable, standard
}

@Suppress("EnumEntryName")
enum class TabOrientation {
    horizontal, vertical
}

external interface TabsProps: StyledProps {
    var action: (actions: Any) -> Unit
    var allowScrollButtonsMobile: Boolean
    var centered: Boolean
    var component: ElementType
    var onChange: (event: Event, indexValue: Any) -> Unit

    @JsName("ScrollButtonComponent")
    var scrollButtonComponent: ReactElement

    var selectionFollowsFocus: Boolean

    @JsName("TabIndicatorProps")
    var tabIndicatorProps: Props

    @JsName("TabScrollButtonProps")
    var tabScrollButtonProps: Props

    var value: Any

    var visibleScrollbar: Boolean
}
var TabsProps.indicatorColor by EnumPropToString(TabIndicatorColor.values())
var TabsProps.orientation by EnumPropToString(TabOrientation.values())
var TabsProps.scrollButtons: TabScrollButtons
    get() { return when (this.asDynamic().scrollButtons) {
        true -> TabScrollButtons.on
        false -> TabScrollButtons.off
        else -> TabScrollButtons.auto
    }}
    set(value) { this.asDynamic().scrollButtons = when(value) {
        TabScrollButtons.on -> true
        TabScrollButtons.off -> false
        else -> "auto"
    } }
var TabsProps.textColor by EnumPropToString(TabTextColor.values())
var TabsProps.variant by EnumPropToString(TabVariant.values())

fun RBuilder.tabs(
    value: Any = false, // false means none selected
    handler: StyledHandler<TabsProps>? = null
) {
    createStyled(tabsComponentType, handler) {
        attrs.value = value
    }
}
