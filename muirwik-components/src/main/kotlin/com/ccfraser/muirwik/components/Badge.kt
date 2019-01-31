package com.ccfraser.muirwik.components

import org.w3c.dom.Node
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Badge")
private external val badgeModule: dynamic
private val badgeComponent: RComponent<MBadgeProps, RState> = badgeModule.default

@Suppress("EnumEntryName")
enum class MBadgeColor {
    primary, secondary, error, default
}

interface MBadgeProps: StyledProps {
    var badgeContent: Node
    var color: String
    var component: String
}

fun RBuilder.mBadge(
        badgeContent: Node,
        color: MBadgeColor = MBadgeColor.default,
        component: String? = "span",

        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null) = createStyled(badgeComponent) {
    attrs.badgeContent = badgeContent
    attrs.color = color.toString()
    component?.let { attrs.component = component }

    setStyledPropsAndRunHandler(className, handler)
}


fun RBuilder.mBadge(
        badgeContent: String,
        color: MBadgeColor = MBadgeColor.default,
        component: String? = "span",

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null) = createStyled(badgeComponent, addAsChild) {
    attrs.badgeContent = badgeContent.asDynamic()
    attrs.color = color.toString()
    component?.let { attrs.component = component }

    setStyledPropsAndRunHandler(className, handler)
}
