package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
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
private val tableSortLabelComponentType: ComponentType<TableSortLabelProps> = tableSortLabelModule.default

@Suppress("EnumEntryName")
enum class TableSortLabelDirection {
    asc, desc
}

external interface TableSortLabelProps : StyledProps {
    var active: Boolean
    var hideSortIcon: Boolean

    @JsName("IconComponent")
    var iconCompoent: (() -> ReactElement)?

    var onClick: (Event) -> Unit
}
var TableSortLabelProps.direction by EnumPropToString(TableSortLabelDirection.values())

fun RBuilder.tableSortLabel(
    label: String,
    active: Boolean = false,
    direction: TableSortLabelDirection = TableSortLabelDirection.desc,
    handler: StyledHandler<TableSortLabelProps>? = null
) {
    createStyled(tableSortLabelComponentType, handler) {
        attrs.active = active
        attrs.direction = direction

        +label
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableSortLabel(
    active: Boolean = false,
    direction: TableSortLabelDirection = TableSortLabelDirection.desc,
    onClick: ((event: Event) -> Unit)? = null,
    iconCompoent: (() -> ReactElement)? = null,
    className: String? = null,
    handler: StyledHandler<TableSortLabelProps>? = null
) {
    createStyled(tableSortLabelComponentType, className, handler) {
        attrs.active = active
        attrs.direction = direction
        iconCompoent?.let { attrs.iconCompoent = iconCompoent }
        onClick?.let { attrs.onClick = onClick }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableSortLabel(
    label: String,
    active: Boolean = false,
    direction: TableSortLabelDirection = TableSortLabelDirection.desc,
    onClick: ((event: Event) -> Unit)? = null,
    iconCompoent: (() -> ReactElement)? = null,
    className: String? = null,
    handler: StyledHandler<TableSortLabelProps>? = null
) {
    createStyled(tableSortLabelComponentType, className, handler) {
        attrs.active = active
        attrs.direction = direction
        iconCompoent?.let { attrs.iconCompoent = iconCompoent }
        onClick?.let { attrs.onClick = onClick }

        childList.add(ReactNode(label))
    }
}
