package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/TableCell")
private external val tableCellModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableCellComponentType: ComponentType<TableCellProps> = tableCellModule.default

@Suppress("EnumEntryName")
enum class TableCellPadding {
    checkbox, none, normal
}

@Suppress("EnumEntryName")
enum class TableCellSortDirection {
    asc, desc, False;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

@Suppress("EnumEntryName")
enum class TableCellAlign {
    inherit, left, center, right, justify
}

@Suppress("EnumEntryName")
enum class TableCellVariant {
    head, body, footer
}

@Suppress("EnumEntryName")
enum class TableCellSize {
    small, medium
}

external interface TableCellProps : StyledPropsWithCommonAttributes {
    var colSpan: Int
    var component: ElementType
    var key: Any
    var scope: String

    @JsName("sortDirection")
    var rawSortDirection: dynamic
}

var TableCellProps.align by EnumPropToString(TableCellAlign.values())
var TableCellProps.padding by EnumPropToString(TableCellPadding.values())
var TableCellProps.size by EnumPropToString(TableCellSize.values())
var TableCellProps.sortDirection: TableCellSortDirection
    get() = if (rawSortDirection == false) TableCellSortDirection.False else TableCellSortDirection.valueOf(
        rawSortDirection
    )
    set(value) {
        rawSortDirection = if (value == TableCellSortDirection.False) false else value.toString()
    }
var TableCellProps.variant by EnumPropToString(TableCellVariant.values())


fun RBuilder.tableCell(text: String? = null, key: Any? = null, handler: StyledHandler<TableCellProps>? = null) {
    createStyled(TableCellComponentType, handler) {
        if (key != null) {
            attrs.key = key
        } else {
            text?.let {attrs.key = it}
        }
        text?.let { +it }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableCell(
    key: Any? = null,
    variant: TableCellVariant? = null,
    sortDirection: TableCellSortDirection? = null,
    align: TableCellAlign? = null,
    padding: TableCellPadding? = null,
    size: TableCellSize? = null,
    colSpan: Int? = null,
    component: String? = null,
    scope: String? = null,
    className: String? = null,
    handler: StyledHandler<TableCellProps>? = null
) {
    createStyled(TableCellComponentType, className, handler) {
        align?.let { attrs.align = it }
        colSpan?.let { attrs.colSpan = it }
        component?.let { attrs.component = component }
        key?.let { attrs.key = it }
        padding?.let { attrs.padding = it }
        scope?.let { attrs.scope = it }
        size?.let { attrs.size = it }
        sortDirection?.let { attrs.sortDirection = it }
        variant?.let { attrs.variant = it }
    }
}
