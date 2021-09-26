package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableSortLabel")
private external val tableSortLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableSortLabelComponentType: ComponentType<MTableSortLabelProps> = tableSortLabelModule.default

@Suppress("EnumEntryName")
enum class MTableSortLabelDirection {
    asc, desc
}

external interface MTableSortLabelProps : StyledProps {
    var active: Boolean

    @JsName("IconComponent")
    var iconFunction: (() -> ReactElement)?

    var onClick: (Event) -> Unit
}
var MTableSortLabelProps.direction by EnumPropToString(MTableSortLabelDirection.values())

fun RBuilder.mTableSortLabel(
    active: Boolean = false,
    direction: MTableSortLabelDirection = MTableSortLabelDirection.desc,
    onClick: ((event: Event) -> Unit)? = null,
    iconFunction: (() -> ReactElement)? = null,
    className: String? = null,
    handler: StyledHandler<MTableSortLabelProps>? = null
) {
    createStyled(tableSortLabelComponentType, className, handler) {
        attrs.active = active
        attrs.direction = direction
        iconFunction?.let { attrs.iconFunction = iconFunction }
        onClick?.let { attrs.onClick = onClick }
    }
}

fun RBuilder.mTableSortLabel(
    label: String,
    active: Boolean = false,
    direction: MTableSortLabelDirection = MTableSortLabelDirection.desc,
    onClick: ((event: Event) -> Unit)? = null,
    iconFunction: (() -> ReactElement)? = null,
    className: String? = null,
    handler: StyledHandler<MTableSortLabelProps>? = null
) {
    createStyled(tableSortLabelComponentType, className, handler) {
        attrs.active = active
        attrs.direction = direction
        iconFunction?.let { attrs.iconFunction = iconFunction }
        onClick?.let { attrs.onClick = onClick }

        childList.add(ReactNode(label))
    }
}
