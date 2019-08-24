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
    var color: MColor
    var disableFocusRipple: Boolean
    var href: String
    var size: MButtonSize
    var variant: MFabVariant
}

private fun MFabProps.redefineTypedProps() {
    this.asDynamic().color = color.toString()
    this.asDynamic().size = size.toString()
    this.asDynamic().variant = variant.toString()
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
        color: MColor = MColor.default,
        disabled: Boolean = false,
        onClick: ((Event) -> Unit)? = null,
        size: MButtonSize = MButtonSize.medium,
        hRefOptions: HRefOptions? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFabProps>? = null) = createStyled(fabComponent, addAsChild) {
    attrs.color = color
    attrs.disabled = disabled
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    attrs.size = size
    attrs.variant = MFabVariant.round

    mIcon(iconName)

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}

/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.mFab(
        iconName: String,
        caption: String,
        color: MColor = MColor.default,
        disabled: Boolean = false,
        onClick: ((Event) -> Unit)? = null,
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
    attrs.color = color
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let {attrs.onKeyboardFocus = onKeyboardFocus}
    attrs.size = size
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = MFabVariant.extended

    mIcon(iconName)
    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}
