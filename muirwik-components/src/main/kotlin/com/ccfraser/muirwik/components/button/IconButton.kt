package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/IconButton")
private external val iconButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconButtonComponent: RComponent<MIconButtonProps, RState> = iconButtonModule.default

@Suppress("EnumEntryName")
enum class MIconButtonSize {
    small, medium
}

enum class MIconEdge {
    start, end // We assume if the prop is null, then the default false will be used, so we don't have this as a value
}

external interface MIconButtonProps : MButtonBaseProps {
    var disableFocusRipple: Boolean
    var href: String
}
var MIconButtonProps.color by EnumPropToString(MColor.values())
var MIconButtonProps.edge by EnumPropToStringNullable(MIconEdge.values())
var MIconButtonProps.size by EnumPropToString(MIconButtonSize.values())


/**
 * If the icon name is given, we shall create a child mIcon with the given name and try and match the size and color.
 * If the icon name is not given, a child mIcon should be given. This also allows more options and styling
 * to be given to the icon.
 */
fun RBuilder.mIconButton(
        iconName: String? = null,
        color: MColor = MColor.default,
        disabled: Boolean = false,
        onClick: ((Event) -> Unit)? = null,
        size: MIconButtonSize = MIconButtonSize.medium,
        hRefOptions: HRefOptions? = null,
        iconColor: MIconColor? = null,
        edge: MIconEdge? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MIconButtonProps>? = null) = createStyled(iconButtonComponent, addAsChild) {
    attrs.color = color
    attrs.disabled = disabled
    edge?.let { attrs.edge = it }
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }

    var iconColorToUse = iconColor
    // If the iconColor is null, we shall map to the button color if we can
    if (iconColorToUse == null) {
        iconColorToUse = when (color) {
            MColor.inherit -> MIconColor.inherit
            MColor.default -> MIconColor.action
            MColor.secondary -> MIconColor.secondary
            MColor.primary -> MIconColor.primary
        }
    }
    attrs.size = size
    if (iconName != null) {
        val fontSize = when (size) {
            MIconButtonSize.small -> MIconFontSize.small
            MIconButtonSize.medium -> MIconFontSize.default
        }

        mIcon(iconName, color = iconColorToUse, fontSize = fontSize)
    }

    setStyledPropsAndRunHandler(className, handler)
}
