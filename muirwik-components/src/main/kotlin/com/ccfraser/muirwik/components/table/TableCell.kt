package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/TableCell")
private external val tableCellModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableCellComponent: RComponent<MTableCellProps, RState> = tableCellModule.default

@Suppress("EnumEntryName")
enum class MTableCellPadding {
    default, checkbox, none
}

@Suppress("EnumEntryName")
enum class MTableCellSortDirection {
    asc, desc, False;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

@Suppress("EnumEntryName")
enum class MTableCellAlign {
    inherit, left, center, right, justify
}

@Suppress("EnumEntryName")
enum class MTableCellVariant {
    head, body, footer
}

@Suppress("EnumEntryName")
enum class MTableCellSize {
    small, medium
}

interface MTableCellProps : StyledPropsWithCommonAttributes {
    var colSpan: Int
    var component: String
    var key: Any
    var scope: String

    @JsName("sortDirection")
    var rawSortDirection: dynamic
}
var MTableCellProps.align by EnumPropToString(MTableCellAlign.values())
var MTableCellProps.padding by EnumPropToString(MTableCellPadding.values())
var MTableCellProps.size by EnumPropToString(MTableCellSize.values())
var MTableCellProps.sortDirection: MTableCellSortDirection
    get() = if (rawSortDirection == false) MTableCellSortDirection.False else MTableCellSortDirection.valueOf(rawSortDirection)
    set(value) {
        rawSortDirection = if (value == MTableCellSortDirection.False) false else value.toString()
    }
var MTableCellProps.variant by EnumPropToString(MTableCellVariant.values())


fun RBuilder.mTableCell(
        key: Any? = null,
        variant: MTableCellVariant = MTableCellVariant.body,
        sortDirection: MTableCellSortDirection = MTableCellSortDirection.False,
        align: MTableCellAlign = MTableCellAlign.inherit,
        padding: MTableCellPadding = MTableCellPadding.default,
        size: MTableCellSize = MTableCellSize.medium,
        colSpan: Int? = null,
        component: String? = null,
        scope: String? = null,

        className: String? = null,
        handler: StyledHandler<MTableCellProps>? = null) = createStyled(TableCellComponent) {
    attrs.align = align
    colSpan?.let { attrs.colSpan = it }
    component?.let { attrs.component = component }
    key?.let { attrs.key = it }
    attrs.padding = padding
    scope?.let { attrs.scope = it }
    attrs.size = size
    attrs.sortDirection = sortDirection
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}
