package com.ccfraser.muirwik.components

import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Chip")
private external val chipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val chipComponent: RComponent<MChipProps, RState> = chipModule.default

@Suppress("EnumEntryName")
enum class MChipVariant {
    default, outlined
}

@Suppress("EnumEntryName")
enum class MChipColor {
    default, primary, secondary
}

enum class MChipSize {
    small, medium
}

interface MChipProps : StyledProps {
    var avatar: ReactElement
    var clickable: Boolean
    var color: String
    var component: String
    var deleteIcon: ReactElement
    var icon: ReactElement
    var label: Node
    var size: String
    var key: Any
    var onClick: (Event) -> Unit
    var onDelete: (Event) -> Unit
    var variant: String

}

/**
 * This is the simpler version of the chip component allowing you to pass in a string label for the chip
 */
fun RBuilder.mChip(
        label: String,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,
        key: Any? = null,
        color: MChipColor = MChipColor.default,
        size: MChipSize = MChipSize.medium,
        variant: MChipVariant = MChipVariant.default,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MChipProps>? = null) = createStyled(chipComponent, addAsChild) {
    avatar?.let { attrs.avatar = it }
    attrs.color = color.toString()
    attrs.component = "div"

    @Suppress("UnsafeCastFromDynamic")
    attrs.label = label.asDynamic()

    key?.let { attrs.key = it }
    onClick?.let { attrs.onClick = it }
    onDelete?.let { attrs.onDelete = it }
    attrs.size = size.toString()
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * This is the full version of the chip component.
 */
fun RBuilder.mChip(
        label: Node,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,
        icon: ReactElement? = null,
        deleteIcon: ReactElement? = null,
        key: Any? = null,
        color: MChipColor = MChipColor.default,
        size: MChipSize = MChipSize.medium,
        variant: MChipVariant = MChipVariant.default,
        component: String = "div",
        clickable: Boolean = false, /* Note if onClick is set, the component will be clickable regardless of this. See material UI docs for more info */
        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MChipProps>? = null) = createStyled(chipComponent, addAsChild) {
    avatar?.let { attrs.avatar = it }
    attrs.clickable = clickable
    attrs.color = color.toString()
    attrs.component = component
    deleteIcon?.let { attrs.deleteIcon = it }
    icon?.let { attrs.icon = it }
    attrs.label = label
    key?.let { attrs.key = it }
    onClick?.let { attrs.onClick = it }
    onDelete?.let { attrs.onDelete = it }
    attrs.size = size.toString()
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

