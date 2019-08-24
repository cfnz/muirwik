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
    var indicatorColor: MTabIndicatorColor
    var onChange: (event: Event, indexValue: Any) -> Unit
    var orientation: MTabOrientation

    @JsName("ScrollButtonComponent")
    var scrollButtonComponent: ReactElement

    var scrollButtons: MTabScrollButtons

    @JsName("TabIndicatorProps")
    var tabIndicatorProps: RProps

    var textColor: MTabTextColor
    var value: Any
    var variant: MTabVariant
}

private fun MTabsProps.redefineTypedProps() {
    this.asDynamic().indicatorColor = indicatorColor.toString()
    this.asDynamic().orientation = orientation.toString()
    this.asDynamic().scrollButtons = scrollButtons.toString()
    this.asDynamic().textColor = textColor.toString()
    this.asDynamic().variant = variant.toString()
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
    attrs.indicatorColor = indicatorColor
    onChange?.let { attrs.onChange = it }
    attrs.orientation = orientation
    scrollButtonComponent?.let { attrs.scrollButtonComponent = it }
    attrs.scrollButtons = scrollButtons
    tabIndicatorProps?.let { attrs.tabIndicatorProps = it }
    attrs.textColor = textColor
    attrs.value = value
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
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
