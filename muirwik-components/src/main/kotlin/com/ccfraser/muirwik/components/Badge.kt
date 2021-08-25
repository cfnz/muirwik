package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@material-ui/core/Badge")
private external val badgeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val badgeComponentType: ComponentType<MBadgeProps> = badgeModule.default

@Suppress("EnumEntryName")
enum class MBadgeColor {
    primary, secondary, error, default
}

@Suppress("EnumEntryName")
enum class MBadgeVariant {
    standard, dot
}

@Suppress("EnumEntryName")
enum class MBadgeOverlap {
    circle, rectangle
}

@Suppress("EnumEntryName")
enum class MBadgeAnchorOriginHorizontal {
    left, right
}

@Suppress("EnumEntryName")
enum class MBadgeAnchorOriginVertical {
    top, bottom
}


external interface MBadgeProps: StyledPropsWithCommonAttributes {
    var badgeContent: ReactElement
    var component: String
    var invisible: Boolean
    var max: Number
    var showZero: Boolean
}
var MBadgeProps.anchorOriginHorizontal by EnumPropToString(
        MBadgeAnchorOriginHorizontal.values(), "anchorOrigin", "horizontal")
var MBadgeProps.anchorOriginVertical by EnumPropToString(
        MBadgeAnchorOriginVertical.values(), "anchorOrigin", "vertical")
var MBadgeProps.color by EnumPropToString(MBadgeColor.values())
var MBadgeProps.overlap by EnumPropToString(MBadgeOverlap.values())
var MBadgeProps.variant by EnumPropToString(MBadgeVariant.values())

fun RBuilder.mBadge(
    badgeContent: String,
    color: MBadgeColor = MBadgeColor.default,
    variant: MBadgeVariant = MBadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<MBadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    mBadge(badgeContentAsElement, color, false, 99, variant, component, invisible, className, handler)
}

fun RBuilder.mBadge(
    badgeContent: Number,
    color: MBadgeColor = MBadgeColor.default,
    showZero: Boolean = false,
    max: Number = 99,
    variant: MBadgeVariant = MBadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<MBadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    mBadge(badgeContentAsElement, color, showZero, max, variant, component, invisible, className, handler)
}

fun RBuilder.mBadge(
    badgeContent: ReactElement,
    color: MBadgeColor = MBadgeColor.default,
    showZero: Boolean = false,
    max: Number = 99,
    variant: MBadgeVariant = MBadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<MBadgeProps>? = null
) {
    createStyled(badgeComponentType, className, handler) {
        attrs.badgeContent = badgeContent
        attrs.color = color
        invisible?.let { attrs.invisible = it }
        attrs.showZero = showZero
        attrs.max = max
        attrs.variant = variant

        component?.let { attrs.component = component }
    }
}

fun RBuilder.mBadgeDot(
    color: MBadgeColor = MBadgeColor.default,
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<MBadgeProps>? = null
) {
    createStyled(badgeComponentType, className, handler) {
        attrs.color = color
        invisible?.let { attrs.invisible = it }
        attrs.variant = MBadgeVariant.dot
    }
}
