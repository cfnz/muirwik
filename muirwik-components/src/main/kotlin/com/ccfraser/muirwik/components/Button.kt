package com.ccfraser.muirwik.components

import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Button")
private external val buttonModule: dynamic
private val buttonComponent: RComponent<MButtonProps, RState> = buttonModule.default


interface MButtonBaseProps: StyledProps {
    var centerRipple: Boolean
    var component: String
    var disabled: Boolean
    var disableRipple: Boolean
    var focusRipple: Boolean
    var onClick: (Event) -> Unit
    var onKeyboardFocus: (Event) -> Unit

    @JsName("TouchRippleProps")
    var touchRippleProps: RProps
}

interface MButtonProps : MButtonBaseProps {
    var color: String
    var disableFocusRipple: Boolean
    var fullWidth: Boolean
    var href: String
    var size: String
    var variant: String
}

interface MFabProps : MButtonBaseProps {
    var color: String
    var disableFocusRipple: Boolean
    var href: String
    var size: String
    var variant: String
}

interface MIconButtonProps : MButtonBaseProps {
    var color: String
    var href: String
}

@Suppress("EnumEntryName")
enum class MButtonSize {
    small, medium, large
}

// Have removed Flat and Raised as these terms are depreciated for Text and Contained respectively and look like they
// will be removed in the future. Have also removed fab and extendedFab as they are going to be removed.
@Suppress("EnumEntryName")
enum class MButtonVariant {
    text, outlined, contained
}

// Have removed Flat and Raised as these terms are depreciated for Text and Contained respectively and look like they
// will be removed in the future. Have also removed fab and extendedFab as they are going to be removed.
@Suppress("EnumEntryName")
enum class MFabVariant {
    round, extended
}

fun RBuilder.mButton(
        caption: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        variant: MButtonVariant = MButtonVariant.text,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        size: MButtonSize = MButtonSize.medium,
        href: String? = null,
        target: String? = null,
        fullWidth: Boolean = false,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        component: String? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MButtonProps>? = null) = createStyled(buttonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    component?.let { attrs.component = component}
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    attrs.fullWidth = fullWidth
    href?.let { attrs.href = href }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let { attrs.onKeyboardFocus = onKeyboardFocus }
    attrs.size = size.toString()
    target?.let {
        // We have not got a prop for target, so we will let a parent element sort it.
        attrs.asDynamic().target = it
    }
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = variant.toString()

    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/Fab")
private external val fabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fabComponent: RComponent<MFabProps, RState> = fabModule.default

/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.mFab(
        iconName: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        size: MButtonSize = MButtonSize.medium,
        href: String? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFabProps>? = null) = createStyled(fabComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    href?.let { attrs.href = href }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = size.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MFabVariant.round.toString()

    mIcon(iconName)

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.mFab(
        iconName: String,
        caption: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        size: MButtonSize = MButtonSize.medium,
        href: String? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFabProps>? = null) = createStyled(fabComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    href?.let { attrs.href = href }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = size.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MFabVariant.extended.toString()

    mIcon(iconName)
    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/IconButton")
private external val iconButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconButtonComponent: RComponent<MIconButtonProps, RState> = iconButtonModule.default

/**
 * If the icon name is given, we shall create a child mIcon with the given name and try and match the color.
 * If the icon name is not given, a child mIcon should be given. This also allows more options and styling
 * to be given to the icon.
 */
fun RBuilder.mIconButton(
        iconName: String? = null,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,

        size: MButtonSize = MButtonSize.medium,
        href: String? = null,

        disableRipple: Boolean = false,
        centerRipple: Boolean = false,
        focusRipple: Boolean = false,
        onKeyboardFocus: ((Event) -> Unit)? = null,
        touchRippleProps: RProps? = null,

        iconColor: MIconColor? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MIconButtonProps>? = null) = createStyled(iconButtonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    href?.let { attrs.href = href }
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

    if (iconName != null) {
        val fontSize = when (size) {
            MButtonSize.small -> MIconFontSize.small
            MButtonSize.medium -> MIconFontSize.inherit
            MButtonSize.large -> MIconFontSize.large
        }

        mIcon(iconName, primary = primary, color = iconColorToUse, fontSize = fontSize)
    }

    setStyledPropsAndRunHandler(className, handler)
}
