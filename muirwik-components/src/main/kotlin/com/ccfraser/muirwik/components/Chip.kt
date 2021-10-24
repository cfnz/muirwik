package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Chip")
private external val chipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val chipComponentType: ComponentType<ChipProps> = chipModule.default

@Suppress("EnumEntryName")
enum class ChipVariant {
    default, outlined
}

enum class ChipSize {
    small, medium
}

typealias ChipColor = ControlColor

external interface ChipProps : StyledPropsWithCommonAttributes {
    var avatar: ReactElement
    var clickable: Boolean
    var component: ElementType
    var deleteIcon: ReactElement
    var disabled: Boolean
    var icon: ReactElement
    var label: ReactNode
    var key: Any
    var onDelete: (Event) -> Unit
}
var ChipProps.color by EnumPropToString(ChipColor.values())
var ChipProps.size by EnumPropToString(ChipSize.values())
var ChipProps.variant by EnumPropToString(ChipVariant.values())


fun RBuilder.chip(handler: StyledHandler<ChipProps>) {
    createStyled(chipComponentType, handler)
}

fun RBuilder.chip(
    label: String,
    avatar: ReactElement? = null,
    key: Any? = null,
    color: ChipColor = ChipColor.default,
    handler: StyledHandler<ChipProps>? = null
) {
    createStyled(chipComponentType, handler) {
        avatar?.let { attrs.avatar = it }
        attrs.color = color
        attrs.label = ReactNode(label)
        key?.let { attrs.key = it }
    }
}

@Deprecated("Use the simpler 'non m' version.")
/**
 * This is the simpler version of the chip component allowing you to pass in a string label for the chip
 */
fun RBuilder.mChip(
    label: String,
    avatar: ReactElement? = null,
    onClick: ((Event) -> Unit)? = null,
    onDelete: ((Event) -> Unit)? = null,
    key: Any? = null,
    color: ControlColor = ControlColor.default,
    size: ChipSize = ChipSize.medium,
    variant: ChipVariant = ChipVariant.default,
    className: String? = null,
    handler: StyledHandler<ChipProps>? = null
) {
    createStyled(chipComponentType, className, handler) {
        avatar?.let { attrs.avatar = it }
        attrs.color = color
        attrs.component = "div"

        @Suppress("UnsafeCastFromDynamic")
        attrs.label = label.asDynamic()

        key?.let { attrs.key = it }
        onClick?.let { attrs.onClick = it }
        onDelete?.let { attrs.onDelete = it }
        attrs.size = size
        attrs.variant = variant
    }
}
