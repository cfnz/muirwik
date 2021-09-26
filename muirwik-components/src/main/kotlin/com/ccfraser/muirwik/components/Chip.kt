package com.ccfraser.muirwik.components

import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@mui/material/Chip")
private external val chipModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val chipComponentType: ComponentType<MChipProps> = chipModule.default

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

external interface MChipProps : StyledPropsWithCommonAttributes {
    var avatar: ReactElement
    var clickable: Boolean
    var component: String
    var deleteIcon: ReactElement
    var icon: ReactElement
    var label: Node
    var key: Any
    var onDelete: (Event) -> Unit
}
var MChipProps.color by EnumPropToString(MChipColor.values())
var MChipProps.size by EnumPropToString(MChipSize.values())
var MChipProps.variant by EnumPropToString(MChipVariant.values())

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
    className: String? = null,
    handler: StyledHandler<MChipProps>? = null
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
