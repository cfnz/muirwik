package com.ccfraser.muirwik.wrapper.table

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableCell")
private external val tableCellModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableCellComponent: RComponent<MTableCellProps, RState> = tableCellModule.default

enum class MTableCellPadding {
    Default, Checkbox, Dense, None;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MTableCellSortDirection {
    Asc, Desc, False;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MTableCellVariant {
    Head, Body, Footer;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MTableCellProps : StyledProps {
    var colSpan: Int
    var component: String
    var key: Any
    var numeric: Boolean
    var padding: String
    var scope: String
    var sortDirection: Any
    var variant: String
}

fun RBuilder.mTableCell(
        key: Any? = null,
        variant: MTableCellVariant = MTableCellVariant.Body,
        sortDirection: MTableCellSortDirection = MTableCellSortDirection.False,
        alignRight: Boolean = false,
        padding: MTableCellPadding = MTableCellPadding.Default,
        colSpan: Int? = null,
        component: String? = null,
        scope: String? = null,

        className: String? = null,
        handler: StyledHandler<MTableCellProps>? = null) = createStyled(TableCellComponent) {
    colSpan?.let { attrs.colSpan = it }
    component?.let { attrs.component = component }
    key?.let { attrs.key = it }
    attrs.numeric = alignRight
    attrs.padding = padding.toString()
    scope?.let { attrs.scope = it }
    attrs.sortDirection = if (sortDirection == MTableCellSortDirection.False) false else sortDirection.toString()
    attrs.variant = variant.toString()
    setStyledPropsAndRunHandler(className, handler)
}
