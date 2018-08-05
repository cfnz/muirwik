package com.ccfraser.muirwik.wrapper

import kotlinext.js.Object
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Tabs")
private external val tabsModule: dynamic
private val tabsComponent: RComponent<MTabsProps, RState> = tabsModule.default

enum class MTabTextColor {
    Secondary, Primary, Inherit;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MTabScrollButtons {
    Auto, On, Off;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MTabIndicatorColor {
    Secondary, Primary;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MTabsProps: StyledProps {
    var action: (actions: Any) -> Unit
    var centered: Boolean
    var fullWidth: Boolean
    var indicatorColor: String
    var onChange: (event: Event, value: Int) -> Unit
    var scrollable: Boolean

    @JsName("ScrollButtonComponent")
    var scrollButtonComponent: ReactElement

    var scrollButtons: String

    @JsName("TabIndicatorProps")
    var tabIndicatorProps: RProps

    var textColor: String
    var value: Any
}

fun RBuilder.mTabs(
        value: Any = false,
        centered: Boolean = false,
        fullWidth: Boolean = false,
        scrollable: Boolean = false,
        indicatorColor: MTabIndicatorColor = MTabIndicatorColor.Secondary,
        textColor: MTabTextColor = MTabTextColor.Inherit,
        tabIndicatorProps: RProps? = null,
        scrollButtons: MTabScrollButtons = MTabScrollButtons.Auto,
        scrollButtonComponent: ReactElement? = null,

        onChange: ((event: Event, value: Any) -> Unit)? = null,
        action: ((actions: Any) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MTabsProps>? = null) = createStyled(tabsComponent) {
    action?.let { attrs.action = it }
    attrs.centered = centered
    attrs.fullWidth = fullWidth
    attrs.indicatorColor = indicatorColor.toString()
    onChange?.let { attrs.onChange = it }
    attrs.scrollable = scrollable
    scrollButtonComponent?.let { attrs.scrollButtonComponent = it }
    attrs.scrollButtons = scrollButtons.toString()
    tabIndicatorProps?.let { attrs.tabIndicatorProps = it }
    attrs.textColor = textColor.toString()
    attrs.value = value

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/Tab")
private external val tabModule: dynamic
private val tabComponent: RComponent<MTabProps, RState> = tabModule.default

interface MTabProps: StyledProps {
    var disabled: Boolean
    var icon: ReactElement
    var label: ReactElement
    var value: Any
}

fun RBuilder.mTab(
        label: ReactElement? = null,
        value: Any? = null,
        icon: ReactElement? = null,
        disabled: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MTabProps>? = null) = createStyled(tabComponent) {
    attrs.disabled = disabled
    icon?.let { attrs.icon = icon }
    label?.let {attrs.label = label }
    value?.let { attrs.value = value }

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
