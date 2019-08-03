package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Tabs")
private external val tabsModule: dynamic
private val tabsComponent: RComponent<MTabsProps, RState> = tabsModule.default

@Suppress("EnumEntryName")
enum class MTabTextColor {
    secondary, primary, inherit
}

@Suppress("EnumEntryName")
enum class MTabScrollButtons {
    auto, desktop, on, off
}

@Suppress("EnumEntryName")
enum class MTabIndicatorColor {
    secondary, primary
}

@Suppress("EnumEntryName")
enum class MTabVariant {
    standard, scrollable, fullWidth
}

@Suppress("EnumEntryName")
enum class MTabOrientation {
    horizontal, vertical
}

interface MTabsProps: StyledProps {
    var action: (actions: Any) -> Unit
    var centered: Boolean
    var indicatorColor: String
    var onChange: (event: Event, indexValue: Any) -> Unit
    var orientation: String

    @JsName("ScrollButtonComponent")
    var scrollButtonComponent: ReactElement

    var scrollButtons: String

    @JsName("TabIndicatorProps")
    var tabIndicatorProps: RProps

    var textColor: String
    var value: Any
    var variant: String
}

fun RBuilder.mTabs(
        value: Any = false, // false means none selected
        centered: Boolean = false,
        variant: MTabVariant = MTabVariant.standard,
        orientation: MTabOrientation = MTabOrientation.horizontal,
        indicatorColor: MTabIndicatorColor = MTabIndicatorColor.secondary,
        textColor: MTabTextColor = MTabTextColor.inherit,
        tabIndicatorProps: RProps? = null,
        scrollButtons: MTabScrollButtons = MTabScrollButtons.auto,
        scrollButtonComponent: ReactElement? = null,

        onChange: ((event: Event, indexValue: Any) -> Unit)? = null,
        action: ((actions: Any) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MTabsProps>? = null) = createStyled(tabsComponent) {
    action?.let { attrs.action = it }
    attrs.centered = centered
    attrs.indicatorColor = indicatorColor.toString()
    onChange?.let { attrs.onChange = it }
    attrs.orientation = orientation.toString()
    scrollButtonComponent?.let { attrs.scrollButtonComponent = it }
    attrs.scrollButtons = scrollButtons.toString()
    tabIndicatorProps?.let { attrs.tabIndicatorProps = it }
    attrs.textColor = textColor.toString()
    attrs.value = value
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/Tab")
private external val tabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tabComponent: RComponent<MTabProps, RState> = tabModule.default

interface MTabProps: MButtonBaseProps {
    var disableFocusRipple: Boolean
    var icon: ReactElement
    var label: ReactElement
    var value: Any
    var wrapped: Boolean
}

fun RBuilder.mTab(
        label: ReactElement? = null,
        value: Any? = null,
        icon: ReactElement? = null,
        disabled: Boolean = false,
        disableRipple: Boolean? = null,
        disableFocusRipple: Boolean? = null,
        wrapped: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MTabProps>? = null) = createStyled(tabComponent) {
    attrs.disabled = disabled
    disableFocusRipple?.let { attrs.disableFocusRipple = it }
    disableRipple?.let { attrs.disableRipple = it }
    icon?.let { attrs.icon = icon }
    label?.let {attrs.label = label }
    value?.let { attrs.value = value }
    attrs.wrapped = wrapped

    setStyledPropsAndRunHandler(className, handler)
}


fun RBuilder.mTab(
        label: String,
        value: Any = label,
        icon: ReactElement? = null,
        disabled: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MTabProps>? = null) = createStyled(tabComponent) {
    attrs.disabled = disabled
    icon?.let { attrs.icon = icon }
    @Suppress("UnsafeCastFromDynamic")
    attrs.label = label.asDynamic()
    attrs.value = value

    setStyledPropsAndRunHandler(className, handler)
}
