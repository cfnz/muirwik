package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Fab")
private external val fabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fabComponent: RComponent<MFabProps, RState> = fabModule.default

interface MFabProps : MButtonBaseProps {
    var color: String
    var disableFocusRipple: Boolean
    var href: String
    var size: String
    var variant: String
}

@Suppress("EnumEntryName")
enum class MFabVariant {
    round, extended
}

/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.mFab(
        iconName: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        size: MButtonSize = MButtonSize.medium,
        hRefOptions: HRefOptions? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        onKeyboardFocus: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFabProps>? = null) = createStyled(fabComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
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
        size: MButtonSize = MButtonSize.medium,
        hRefOptions: HRefOptions? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        onKeyboardFocus: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFabProps>? = null) = createStyled(fabComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = size.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MFabVariant.extended.toString()

    mIcon(iconName)
    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
}
