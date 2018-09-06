package com.ccfraser.muirwik.wrapper

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
    var mini: Boolean
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
// will be removed in the future
@Suppress("EnumEntryName")
enum class MButtonVariant {
    text, outlined, contained, fab, extendedFab
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
    attrs.mini = false
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let { attrs.onKeyboardFocus = onKeyboardFocus }
    attrs.size = size.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = variant.toString()

    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * mButtonFab automatically changes the variant to be fab and has a convenience iconName.
 */
fun RBuilder.mButtonFab(
        iconName: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        mini: Boolean = false,
        href: String? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MButtonProps>? = null) = createStyled(buttonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    href?.let { attrs.href = href }
    attrs.mini = mini
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = MButtonSize.medium.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MButtonVariant.fab.toString()

    mIcon(iconName)

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * mButtonFab but with a caption which turns into an extendedFab type.
 */
fun RBuilder.mButtonFab(
        iconName: String,
        caption: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        mini: Boolean = false,
        href: String? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MButtonProps>? = null) = createStyled(buttonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    href?.let { attrs.href = href }
    attrs.mini = mini
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = MButtonSize.medium.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MButtonVariant.extendedFab.toString()

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
        mIcon(iconName, primary = primary, color = iconColorToUse)
    }

    setStyledPropsAndRunHandler(className, handler)
}
