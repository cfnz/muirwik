package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Fab")
private external val fabModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fabComponentType: ComponentType<FabProps> = fabModule.default

@Suppress("EnumEntryName")
enum class FabVariant {
    circular, extended
}

external interface FabProps : ButtonBaseProps {
    var disableFocusRipple: Boolean
}

enum class FabColor {
    default, inherit, primary, secondary
}

var FabProps.color by EnumPropToString(FabColor.values())
var FabProps.hrefOptions by HrefOptionsDelegate()
var FabProps.size by EnumPropToString(ButtonSize.values())
var FabProps.variant by EnumPropToString(FabVariant.values())

/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.fab(
    iconName: String? = null,
    color: FabColor = FabColor.default,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<FabProps>? = null
) {
    createStyled(fabComponentType, handler) {
        attrs.color = color
        hRefOptions?.let { attrs.hrefOptions = it }
        attrs.size = size
        attrs.variant = FabVariant.circular

        if (iconName != null) {
            val fontSize = when (size) {
                ButtonSize.small -> IconFontSize.small
                ButtonSize.medium -> IconFontSize.medium
                ButtonSize.large -> IconFontSize.large
            }
            icon(iconName) { attrs.fontSize = fontSize }
        }
    }
}

/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.fab(
    iconName: String,
    caption: String,
    color: FabColor = FabColor.default,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<FabProps>? = null
) {
    createStyled(fabComponentType, handler) {
        attrs.color = color
        hRefOptions?.let { attrs.hrefOptions = it }
        attrs.size = size
        attrs.variant = FabVariant.extended

        icon(iconName)
        childList.add(ReactNode(caption))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
/**
 * FAB button that is round and has a convenience iconName.
 */
fun RBuilder.mFab(
    iconName: String? = null,
    color: FabColor = FabColor.default,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    className: String? = null,
    handler: StyledHandler<FabProps>? = null
) {
    createStyled(fabComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = onClick }
        attrs.size = size
        attrs.variant = FabVariant.circular

        if (iconName != null) {
            val fontSize = when (size) {
                ButtonSize.small -> IconFontSize.small
                ButtonSize.medium -> IconFontSize.medium
                ButtonSize.large -> IconFontSize.large
            }
            mIcon(iconName, fontSize = fontSize)
        }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
/**
 * FAB button with a caption which turns into an extended FAB type.
 */
fun RBuilder.mFab(
    iconName: String,
    caption: String,
    color: FabColor = FabColor.default,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    className: String? = null,
    handler: StyledHandler<FabProps>? = null
) {
    createStyled(fabComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = onClick }
        attrs.size = size
        attrs.variant = FabVariant.extended

        mIcon(iconName)
        childList.add(ReactNode(caption))
    }
}