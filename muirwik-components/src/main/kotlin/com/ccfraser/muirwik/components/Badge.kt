package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Badge")
private external val badgeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val badgeComponent: RComponent<MBadgeProps, RState> = badgeModule.default

@Suppress("EnumEntryName")
enum class MBadgeColor {
    primary, secondary, error, default
}

@Suppress("EnumEntryName")
enum class MBadgeVariant {
    standard, dot
}

interface MBadgeProps: StyledProps {
    var badgeContent: ReactElement
    var color: String
    var component: String
    var invisible: Boolean
    var max: Number
    var showZero: Boolean
    var variant: String
}

fun RBuilder.mBadge(
        badgeContent: String,
        color: MBadgeColor = MBadgeColor.default,
        showZero: Boolean = false,
        max: Number = 99,
        variant: MBadgeVariant = MBadgeVariant.standard,
        component: String? = "span",

        invisible: Boolean? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null): ReactElement {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    return mBadge(badgeContentAsElement, color, showZero, max, variant, component, invisible, addAsChild, className, handler)
}

fun RBuilder.mBadge(
        badgeContent: Number,
        color: MBadgeColor = MBadgeColor.default,
        showZero: Boolean = false,
        max: Number = 99,
        variant: MBadgeVariant = MBadgeVariant.standard,
        component: String? = "span",

        invisible: Boolean? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null): ReactElement {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    return mBadge(badgeContentAsElement, color, showZero, max, variant, component, invisible, addAsChild, className, handler)
}

fun RBuilder.mBadge(
        badgeContent: ReactElement,
        color: MBadgeColor = MBadgeColor.default,
        showZero: Boolean = false,
        max: Number = 99,
        variant: MBadgeVariant = MBadgeVariant.standard,
        component: String? = "span",

        invisible: Boolean? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null) = createStyled(badgeComponent, addAsChild) {
    attrs.badgeContent = badgeContent
    attrs.color = color.toString()
    invisible?.let { attrs.invisible = it }
    attrs.showZero = showZero
    attrs.max = max
    attrs.variant = variant.toString()

    component?.let { attrs.component = component }

    setStyledPropsAndRunHandler(className, handler)
}

