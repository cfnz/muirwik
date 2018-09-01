package com.ccfraser.muirwik.wrapper

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

enum class MChipVariant {
    Default, Outlined;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MChipColor {
    Default, Primary, Secondary;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MChipProps : StyledProps {
    var avatar: ReactElement
    var clickable: Boolean
    var color: String
    var component: String
    var deleteIcon: ReactElement
    var label: Node
    var key: Any
    var onClick: (Event) -> Unit
    var onDelete: (Event) -> Unit
    var variant: String

}

/**
 * This is the full version of the chip component.
 */
fun RBuilder.mChip(
        label: Node,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,
        deleteIcon: ReactElement? = null,
        key: Any? = null,
        color: MChipColor = MChipColor.Default,
        variant: MChipVariant = MChipVariant.Default,
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
    attrs.label = label
    key?.let { attrs.key = it }
    onClick?.let { attrs.onClick = it }
    onDelete?.let { attrs.onDelete = it }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

        /**
 * This is the simpler version of the chip component allowing you to pass in a label for the chip
 */
fun RBuilder.mChip(
        label: String,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,
        key: Any? = null,
        color: MChipColor = MChipColor.Default,
        variant: MChipVariant = MChipVariant.Default,

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
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

