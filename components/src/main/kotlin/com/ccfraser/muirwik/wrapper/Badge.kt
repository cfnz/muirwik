package com.ccfraser.muirwik.wrapper

import org.w3c.dom.Node
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Badge")
private external val badgeModule: dynamic
private val badgeComponent: RComponent<MBadgeProps, RState> = badgeModule.default

enum class MBadgeColor {
    Primary, Secondary, Error, Default;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MBadgeProps: StyledProps {
    var badgeContent: Node
    var color: String
    var component: String
}

fun RBuilder.mBadge(
        badgeContent: Node,
        color: MBadgeColor = MBadgeColor.Default,
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
        color: MBadgeColor = MBadgeColor.Default,
        component: String? = "span",

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MBadgeProps>? = null) = createStyled(badgeComponent, addAsChild) {
    attrs.badgeContent = badgeContent.asDynamic()
    attrs.color = color.toString()
    component?.let { attrs.component = component }

    setStyledPropsAndRunHandler(className, handler)
}
