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


interface MChipProps : StyledProps {
    var avatar: ReactElement
    var component: String
    var deleteIcon: ReactElement
    var label: Node
    var key: Any
    var onClick: (Event) -> Unit
    var onDelete: (Event) -> Unit
}

fun RBuilder.mChip(
        label: Node,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,
        deleteIcon: ReactElement? = null,
        key: Any? = null,
        component: String = "div",

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MChipProps>? = null) = createStyled(chipComponent, addAsChild) {
    avatar?.let { attrs.avatar = it }
    attrs.component = component
    deleteIcon?.let { attrs.deleteIcon = it }
    attrs.label = label
    key?.let { attrs.key = it }
    onClick?.let { attrs.onClick = it }
    onDelete?.let { attrs.onDelete = it }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mChip(
        label: String,
        avatar: ReactElement? = null,
        onClick: ((Event) -> Unit)? = null,
        onDelete: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MChipProps>? = null) = createStyled(chipComponent, addAsChild) {
    avatar?.let { attrs.avatar = it }
    attrs.component = "div"
    attrs.label = label.asDynamic()
    onClick?.let { attrs.onClick = it }
    onDelete?.let { attrs.onDelete = it }

    setStyledPropsAndRunHandler(className, handler)
}

