package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import react.dom.events.MouseEvent
import react.dom.events.NativeMouseEvent
import styled.StyledHandler


@JsModule("@mui/material/TablePagination")
private external val tablePaginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tablePaginationComponentType: ComponentType<TablePaginationProps> = tablePaginationModule.default

external interface TablePaginationProps : ButtonBaseProps {
    var actions: String

    @JsName("ActionsComponent")
    var actionsComponent: ElementType

    var backIconButtonProps: IconButtonProps
    var count: Int
    var getItemAriaLabel: (type: String) -> String
    var labelDisplayedRows: (from: Int, to: Int, count: Int) -> Unit
    var labelRowsPerPage: Node
    var nextIconButtonProps: IconButtonProps
    var onPageChange: (event: MouseEvent<HTMLButtonElement, NativeMouseEvent>, page: Int) -> Unit
    var onRowsPerPageChange: (event: Event) -> Unit
    var page: Int
    var rowsPerPage: Int
    var rowsPerPageOptions: Array<Int>

    @JsName("selectProps")
    var selectProps: Props
    var showFirstButton: Boolean
    var showLastButton: Boolean
}

fun RBuilder.tablePagination(
    page: Int,
    count: Int,
    rowsPerPage: Int,
    onPageChange: (event: MouseEvent<HTMLButtonElement, NativeMouseEvent>, page: Int) -> Unit,
    handler: StyledHandler<TablePaginationProps>? = null
) {
    createStyled(tablePaginationComponentType, handler) {
        attrs.count = count
        attrs.onPageChange = onPageChange
        attrs.page = page
        attrs.rowsPerPage = rowsPerPage
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTablePagination(
    actions: String? = null,
    backIconButtonProps: IconButtonProps? = null,
    component: String? = "div",
    count: Int? = null,
    labelDisplayedRows: ((from: Int, to: Int, count: Int) -> Unit)? = null,
    labelRowsPerPage: Node? = null,
    nextIconButtonProps: IconButtonProps? = null,
    onPageChange: ((event: MouseEvent<HTMLButtonElement, NativeMouseEvent>, page: Int) -> Unit)? = null,
    onRowsPerPageChange: ((event: Event) -> Unit)? = null,
    page: Int? = null,
    rowsPerPage: Int? = null,
    rowsPerPageOptions: Array<Int>? = null,
    selectProps: Props? = null,
    className: String? = null,
    handler: StyledHandler<TablePaginationProps>? = null
) {
    createStyled(tablePaginationComponentType, className, handler) {
        actions?.let { attrs.actions = it }
        backIconButtonProps?.let { attrs.backIconButtonProps = it }
        component?.let { attrs.component = it }
        count?.let { attrs.count = it }
        labelDisplayedRows?.let { attrs.labelDisplayedRows = it }
        labelRowsPerPage?.let { attrs.labelRowsPerPage = it }
        nextIconButtonProps?.let { attrs.nextIconButtonProps = it }
        onPageChange?.let { attrs.onPageChange = it }
        onRowsPerPageChange?.let { attrs.onRowsPerPageChange = it }
        page?.let { attrs.page = it }
        rowsPerPage?.let { attrs.rowsPerPage = it }
        rowsPerPageOptions?.let { attrs.rowsPerPageOptions = it }
        selectProps?.let { attrs.selectProps = it }
    }
}
