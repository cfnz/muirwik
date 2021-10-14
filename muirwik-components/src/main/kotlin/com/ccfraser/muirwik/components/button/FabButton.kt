package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Fab")
private external val fabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fabComponentType: ComponentType<MFabProps> = fabModule.default

@Suppress("EnumEntryName")
enum class MFabVariant {
    circular, extended
}

external interface MFabProps : MButtonBaseProps {
    var disableFocusRipple: Boolean
    var href: String
}

enum class MFabColor {
    default, inherit, primary, secondary
}

var MFabProps.color by EnumPropToString(MFabColor.values())
var MFabProps.size by EnumPropToString(MButtonSize.values())
var MFabProps.variant by EnumPropToString(MFabVariant.values())

/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.mFab(
    iconName: String? = null,
    color: MFabColor = MFabColor.default,
    size: MButtonSize = MButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<MFabProps>? = null
) {
    createStyled(fabComponentType, handler) {
        attrs.color = color
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        attrs.size = size
        attrs.variant = MFabVariant.circular

        if (iconName != null) {
            val fontSize = when (size) {
                MButtonSize.small -> MIconFontSize.small
                MButtonSize.medium -> MIconFontSize.default
                MButtonSize.large -> MIconFontSize.large
            }
            mIcon(iconName, fontSize = fontSize)
        }
    }
}

/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.mFab(
    iconName: String,
    caption: String,
    color: MFabColor = MFabColor.default,
    size: MButtonSize = MButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<MFabProps>? = null
) {
    createStyled(fabComponentType, handler) {
        attrs.color = color
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        attrs.size = size
        attrs.variant = MFabVariant.extended

        mIcon(iconName)
        childList.add(ReactNode(caption))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.mFab(
    iconName: String? = null,
    color: MFabColor = MFabColor.default,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: MButtonSize = MButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    className: String? = null,
    handler: StyledHandler<MFabProps>? = null
) {
    createStyled(fabComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = onClick }
        attrs.size = size
        attrs.variant = MFabVariant.circular

        if (iconName != null) {
            val fontSize = when (size) {
                MButtonSize.small -> MIconFontSize.small
                MButtonSize.medium -> MIconFontSize.default
                MButtonSize.large -> MIconFontSize.large
            }
            mIcon(iconName, fontSize = fontSize)
        }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.mFab(
    iconName: String,
    caption: String,
    color: MFabColor = MFabColor.default,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: MButtonSize = MButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    className: String? = null,
    handler: StyledHandler<MFabProps>? = null
) {
    createStyled(fabComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = onClick }
        attrs.size = size
        attrs.variant = MFabVariant.extended

        mIcon(iconName)
        childList.add(ReactNode(caption))
    }
}