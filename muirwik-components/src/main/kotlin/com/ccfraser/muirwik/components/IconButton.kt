package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/IconButton")
private external val iconButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconButtonComponentType: ComponentType<IconButtonProps> = iconButtonModule.default

@Suppress("EnumEntryName")
enum class MIconButtonSize {
    small, medium
}

enum class IconEdge {
    start, end // We assume if the prop is null, then the default false will be used, so we don't have this as a value
}

enum class IconButtonColor {
    inherit, default, primary, secondary, error, info, success, warning
}

external interface IconButtonProps : ButtonBaseProps {
    var disableFocusRipple: Boolean
}
var IconButtonProps.color by EnumPropToString(IconButtonColor.values())
var IconButtonProps.edge by EnumPropToStringNullable(IconEdge.values())
var IconButtonProps.hrefOptions by HrefOptionsDelegate()
var IconButtonProps.size by EnumPropToString(MIconButtonSize.values())

/**
 * If the icon name is given, we shall create a child mIcon with the given name and try and match the size and color.
 * If the icon name is not given, a child mIcon should be given. This also allows more options and styling
 * to be given to the icon.
 */
fun RBuilder.iconButton(
    iconName: String? = null,
    color: IconButtonColor = IconButtonColor.default,
    size: MIconButtonSize = MIconButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<IconButtonProps>? = null
) {
    createStyled(iconButtonComponentType, handler) {
        attrs.color = color
        hRefOptions?.let { attrs.hrefOptions = it }

        val iconColorToUse = when (color) {
            IconButtonColor.inherit -> IconColor.inherit
            IconButtonColor.default -> IconColor.action
            IconButtonColor.primary -> IconColor.primary
            IconButtonColor.secondary -> IconColor.secondary
            IconButtonColor.error -> IconColor.error
            IconButtonColor.info -> IconColor.info
            IconButtonColor.success -> IconColor.success
            IconButtonColor.warning -> IconColor.warning
        }
        attrs.size = size
        if (iconName != null) {
            val fontSize = when (size) {
                MIconButtonSize.small -> IconFontSize.small
                MIconButtonSize.medium -> IconFontSize.medium
            }

            icon(iconName, color = iconColorToUse) { attrs.fontSize = fontSize }
        }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
/**
 * If the icon name is given, we shall create a child mIcon with the given name and try and match the size and color.
 * If the icon name is not given, a child mIcon should be given. This also allows more options and styling
 * to be given to the icon.
 */
fun RBuilder.mIconButton(
    iconName: String? = null,
    color: IconButtonColor = IconButtonColor.default,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    iconColor: IconColor? = null,
    edge: IconEdge? = null,
    className: String? = null,
    handler: StyledHandler<IconButtonProps>? = null
) {
    createStyled(iconButtonComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        edge?.let { attrs.edge = it }
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = onClick }

        var iconColorToUse = iconColor
        // If the iconColor is null, we shall map to the button color if we can
        if (iconColorToUse == null) {
            iconColorToUse = when (color) {
                IconButtonColor.inherit -> IconColor.inherit
                IconButtonColor.default -> IconColor.action
                IconButtonColor.primary -> IconColor.primary
                IconButtonColor.secondary -> IconColor.secondary
                IconButtonColor.error -> IconColor.error
                IconButtonColor.info -> IconColor.info
                IconButtonColor.success -> IconColor.success
                IconButtonColor.warning -> IconColor.warning
            }
        }
        attrs.size = size
        if (iconName != null) {
            val fontSize = when (size) {
                MIconButtonSize.small -> IconFontSize.small
                MIconButtonSize.medium -> IconFontSize.medium
            }

            mIcon(iconName, color = iconColorToUse, fontSize = fontSize)
        }
    }
}