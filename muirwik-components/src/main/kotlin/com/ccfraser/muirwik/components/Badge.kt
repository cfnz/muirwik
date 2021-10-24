package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@mui/material/Badge")
private external val badgeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val badgeComponentType: ComponentType<BadgeProps> = badgeModule.default

@Suppress("EnumEntryName")
enum class BadgeColor {
    default, primary, secondary, error, info, success, warning
}

@Suppress("EnumEntryName")
enum class BadgeVariant {
    standard, dot
}

@Suppress("EnumEntryName")
enum class BadgeOverlap {
    circular, rectangular
}

@Suppress("EnumEntryName")
enum class BadgeAnchorOriginHorizontal {
    left, right
}

@Suppress("EnumEntryName")
enum class BadgeAnchorOriginVertical {
    top, bottom
}


external interface BadgeProps: StyledPropsWithCommonAttributes {
    var badgeContent: ReactElement
    var component: ElementType
    var components: dynamic
    var componentsProps: Props
    var invisible: Boolean
    var max: Number
    var showZero: Boolean
}
var BadgeProps.anchorOriginHorizontal by EnumPropToString(
        BadgeAnchorOriginHorizontal.values(), "anchorOrigin", "horizontal")
var BadgeProps.anchorOriginVertical by EnumPropToString(
        BadgeAnchorOriginVertical.values(), "anchorOrigin", "vertical")
var BadgeProps.color by EnumPropToString(BadgeColor.values())
var BadgeProps.overlap by EnumPropToString(BadgeOverlap.values())
var BadgeProps.variant by EnumPropToString(BadgeVariant.values())

fun RBuilder.badge(
    badgeContent: String,
    color: BadgeColor = BadgeColor.default,
    handler: StyledHandler<BadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    createStyled(badgeComponentType, handler) {
        attrs.badgeContent = badgeContentAsElement
        attrs.color = color
    }
}

fun RBuilder.badge(
    badgeContent: Number,
    showZero: Boolean = false,
    max: Number = 99,
    color: BadgeColor = BadgeColor.default,
    handler: StyledHandler<BadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    createStyled(badgeComponentType, handler) {
        attrs.badgeContent = badgeContentAsElement
        attrs.color = color
        attrs.showZero = showZero
        attrs.max = max
    }
}

fun RBuilder.badgeDot(
    color: BadgeColor = BadgeColor.default,
    handler: StyledHandler<BadgeProps>? = null
) {
    createStyled(badgeComponentType, handler) {
        attrs.color = color
        attrs.variant = BadgeVariant.dot
    }
}



@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
fun RBuilder.mBadge(
    badgeContent: String,
    color: BadgeColor = BadgeColor.default,
    variant: BadgeVariant = BadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<BadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    mBadge(badgeContentAsElement, color, false, 99, variant, component, invisible, className, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mBadge(
    badgeContent: Number,
    color: BadgeColor = BadgeColor.default,
    showZero: Boolean = false,
    max: Number = 99,
    variant: BadgeVariant = BadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<BadgeProps>? = null
) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val badgeContentAsElement = badgeContent.asDynamic() as ReactElement

    @Suppress("DEPRECATION")
    mBadge(badgeContentAsElement, color, showZero, max, variant, component, invisible, className, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mBadge(
    badgeContent: ReactElement,
    color: BadgeColor = BadgeColor.default,
    showZero: Boolean = false,
    max: Number = 99,
    variant: BadgeVariant = BadgeVariant.standard,
    component: String? = "span",
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<BadgeProps>? = null
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

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mBadgeDot(
    color: BadgeColor = BadgeColor.default,
    invisible: Boolean? = null,
    className: String? = null,
    handler: StyledHandler<BadgeProps>? = null
) {
    createStyled(badgeComponentType, className, handler) {
        attrs.color = color
        invisible?.let { attrs.invisible = it }
        attrs.variant = BadgeVariant.dot
    }
}
