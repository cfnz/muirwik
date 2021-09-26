package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.button.MIconButtonProps
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/TablePagination")
private external val tablePaginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tablePaginationComponentType: ComponentType<MTablePaginationProps> = tablePaginationModule.default

external interface MTablePaginationProps : MButtonBaseProps {
    @JsName("actions")
    var actions: String
    var backIconButtonProps: MIconButtonProps
    var count: Int
    var labelDisplayedRows: (from: Int, to: Int, count: Int) -> Unit
    var labelRowsPerPage: Node
    var nextIconButtonProps: MIconButtonProps
    var onChangePage: (event: Event, page: Int) -> Unit
    var onChangeRowsPerPage: (event: Event) -> Unit
    var page: Int
    var rowsPerPage: Int
    var rowsPerPageOptions: Array<Int>
    @JsName("selectProps")
    var selectProps: Props
}

fun RBuilder.mTablePagination(
    actions: String? = null,
    backIconButtonProps: MIconButtonProps? = null,
    component: String? = "div",
    count: Int? = null,
    labelDisplayedRows: ((from: Int, to: Int, count: Int) -> Unit)? = null,
    labelRowsPerPage: Node? = null,
    nextIconButtonProps: MIconButtonProps? = null,
    onChangePage: ((event: Event, page: Int) -> Unit)? = null,
    onChangeRowsPerPage: ((event: Event) -> Unit)? = null,
    page: Int? = null,
    rowsPerPage: Int? = null,
    rowsPerPageOptions: Array<Int>? = null,
    selectProps: Props? = null,
    className: String? = null,
    handler: StyledHandler<MTablePaginationProps>? = null
) {
    createStyled(tablePaginationComponentType, className, handler) {
        actions?.let { attrs.actions = it }
        backIconButtonProps?.let { attrs.backIconButtonProps = it }
        component?.let { attrs.component = it }
        count?.let { attrs.count = it }
        labelDisplayedRows?.let { attrs.labelDisplayedRows = it }
        labelRowsPerPage?.let { attrs.labelRowsPerPage = it }
        nextIconButtonProps?.let { attrs.nextIconButtonProps = it }
        onChangePage?.let { attrs.onChangePage = it }
        onChangeRowsPerPage?.let { attrs.onChangeRowsPerPage = it }
        page?.let { attrs.page = it }
        rowsPerPage?.let { attrs.rowsPerPage = it }
        rowsPerPageOptions?.let { attrs.rowsPerPageOptions = it }
        selectProps?.let { attrs.selectProps = it }
    }
}
