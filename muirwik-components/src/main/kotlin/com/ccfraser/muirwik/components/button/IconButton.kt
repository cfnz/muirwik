package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/IconButton")
private external val iconButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconButtonComponent: RComponent<MIconButtonProps, RState> = iconButtonModule.default

interface MIconButtonProps : MButtonBaseProps {
    var color: String
    var disableFocusRipple: Boolean
    var edge: String
    var href: String
    var size: String
}

@Suppress("EnumEntryName")
enum class MIconButtonSize {
    small, medium
}

enum class MIconEdge {
    start, end // We assume if the prop is null, then the default false will be used, so we don't have this as a value
}


/**
 * If the icon name is given, we shall create a child mIcon with the given name and try and match the size and color.
 * If the icon name is not given, a child mIcon should be given. This also allows more options and styling
 * to be given to the icon.
 */
fun RBuilder.mIconButton(
        iconName: String? = null,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        size: MIconButtonSize = MIconButtonSize.medium,
        hRefOptions: HRefOptions? = null,
        iconColor: MIconColor? = null,
        edge: MIconEdge? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = false,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        onKeyboardFocus: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MIconButtonProps>? = null) = createStyled(iconButtonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    edge?.let { attrs.edge = it.toString() }
    attrs.focusRipple = focusRipple
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }

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
    attrs.size = size.toString()
    if (iconName != null) {
        val fontSize = when (size) {
            MIconButtonSize.small -> MIconFontSize.small
            MIconButtonSize.medium -> MIconFontSize.default
        }

        mIcon(iconName, primary = primary, color = iconColorToUse, fontSize = fontSize)
    }

    setStyledPropsAndRunHandler(className, handler)
}
