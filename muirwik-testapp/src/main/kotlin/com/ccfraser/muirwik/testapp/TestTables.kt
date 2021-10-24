package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.PaletteMode
import com.ccfraser.muirwik.components.styles.lighten
import com.ccfraser.muirwik.components.styles.mode
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.math.min


class TestTables : RComponent<Props, State>() {
    private data class Dessert(val id: Int, val dessertName: String, val calories: Int, val fat: Double, val carbs: Int, val protein: Double)
    private val androidDeserts = mutableListOf(
        Dessert(1, "Cupcake", 305, 3.7, 67, 4.3),
        Dessert(2, "Donut", 452, 25.0, 51, 4.9),
        Dessert(3, "Eclair", 262, 16.0, 24, 6.0),
        Dessert(4, "Frozen yoghurt", 159, 6.0, 24, 4.0),
        Dessert(5, "Gingerbread", 356, 16.0, 49, 3.9),
        Dessert(6, "Honeycomb", 408, 3.2, 87, 6.5),
        Dessert(7, "Ice cream sandwich", 237, 9.0, 37, 4.3),
        Dessert(8, "Jelly bean", 375, 0.0, 94, 0.0),
        Dessert(9, "KitKat", 518, 26.0, 65, 7.0),
        Dessert(10, "Lollipop", 392, 0.2, 98, 0.0),
        Dessert(11, "Marshmallow", 318, 0.0, 81, 2.0),
        Dessert(12, "Nougat", 360, 19.0, 9, 37.0),
        Dessert(13, "Oreo", 437, 18.0, 63, 4.0)
    )

    // Column Data used for the sort and select example
    private enum class ColumnId { Name, Calories, Fat, Carbs, Protein }
    private data class ColumnData(val name: ColumnId, val rightAligned: Boolean, val disablePadding: Boolean, val label: String)
    private val columnData = listOf(
        ColumnData(ColumnId.Name, false, true, "Dessert (100g serving)"),
        ColumnData(ColumnId.Calories, true, false, "Calories"),
        ColumnData(ColumnId.Fat, true, false, "Fat (g)"),
        ColumnData(ColumnId.Carbs, true, false, "Carbs (g)"),
        ColumnData(ColumnId.Protein, true, false, "Protein (g)")
    )

    // State for sort and select example
    private val selectedIds = mutableSetOf<Int>()
    private var order = TableCellSortDirection.asc
    private var orderByColumn: ColumnId = ColumnId.Name
    private var page = 0
    private var rowsPerPage = 5


    override fun RBuilder.render() {
        typography("Simple Table")
        simpleTable()
        br {  }
        br {  }
        typography("Sorting and Selecting")
        sortingAndSelectingTable()
    }

    private fun RBuilder.simpleTable() {
        paper {
            css {
                width = 100.pct
                marginTop = 3.spacingUnits
                overflowX = Overflow.auto
            }
            table {
                css { minWidth = 700.px }
                tableHead {
                    tableRow {
                        tableCell("Dessert (100g serving)")
                        tableCell("Calories") { attrs.align = TableCellAlign.right }
                        tableCell("Fat (g)") { attrs.align = TableCellAlign.right }
                        tableCell("Carbs (g)") { attrs.align = TableCellAlign.right }
                        tableCell("Protein (g)") { attrs.align = TableCellAlign.right }
                    }
                }
                tableBody {
                    androidDeserts.subList(0, 4).forEach {
                        tableRow(key = it.id) {
                            tableCell(it.dessertName)
                            tableCell(it.calories.toString()) { attrs.align = TableCellAlign.right }
                            tableCell(it.fat.toString()) { attrs.align = TableCellAlign.right }
                            tableCell(it.carbs.toString()) { attrs.align = TableCellAlign.right }
                            tableCell(it.protein.toString()) { attrs.align = TableCellAlign.right }
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectAllClick(checked: Boolean): Unit {
        setState {
            if (checked) {
                selectedIds.addAll(androidDeserts.map { dessert -> dessert.id })
            } else {
                selectedIds.clear()
            }
        }
    }

    private fun handleClick(id: Int) {
        setState {
            if (selectedIds.contains(id)) {
                selectedIds.remove(id)
            } else {
                selectedIds.add(id)
            }
        }
    }

    private fun handleRequestSort(id: ColumnId) {
        setState {
            order = if (orderByColumn == id) {
                if (order == TableCellSortDirection.asc) TableCellSortDirection.desc else TableCellSortDirection.asc
            } else {
                TableCellSortDirection.asc
            }

            orderByColumn = id

            fun compareDesserts(a: Dessert, b: Dessert) = when (id) {
                ColumnId.Name -> a.dessertName.compareTo(b.dessertName)
                ColumnId.Calories -> a.calories.compareTo(b.calories)
                ColumnId.Carbs -> a.carbs.compareTo(b.carbs)
                ColumnId.Fat -> a.fat.compareTo(b.fat)
                ColumnId.Protein -> a.protein.compareTo(b.protein)
            }

            if (order == TableCellSortDirection.asc) {
                androidDeserts.sortWith { a, b -> compareDesserts(a, b) }
            } else {
                androidDeserts.sortWith { a, b -> compareDesserts(b, a) }
            }
        }
    }

    private fun RBuilder.sortingAndSelectingTable() {
        paper {
            css {
                width = 100.pct
                marginTop = 3.spacingUnits
            }
            enhancedTableToolbar(selectedIds.size)
            styledDiv { css { overflowX = Overflow.auto }
                table {
                    css { minWidth = 700.px }
                    enhancedTableHead(selectedIds.size, order, orderByColumn, androidDeserts.size, ::handleSelectAllClick, ::handleRequestSort)
                    tableBody {
                        androidDeserts.subList(page * rowsPerPage, min((page + 1) * rowsPerPage, androidDeserts.size)).forEach {
                            val isSelected = selectedIds.contains(it.id)
                            tableRow(it.id, isSelected) {
                                attrs.onClick = { _ -> handleClick(it.id) }
//                                attrs.asDynamic().tabIndex = -1
//                                attrs.asDynamic().role = "checkbox"

                                tableCell  {
                                    attrs.padding = TableCellPadding.checkbox
                                    checkbox(isSelected)
                                }
                                tableCell(it.dessertName) {
                                    attrs.align = TableCellAlign.left
                                    attrs.padding = TableCellPadding.none
                                }
                                tableCell(it.calories.toString()) { attrs.align = TableCellAlign.right }
                                tableCell(it.fat.toString()) { attrs.align = TableCellAlign.right }
                                tableCell(it.carbs.toString()) { attrs.align = TableCellAlign.right }
                                tableCell(it.protein.toString()) { attrs.align = TableCellAlign.right }
                            }
                        }
                        val emptyRows = rowsPerPage - min(rowsPerPage, androidDeserts.size - page * rowsPerPage)
                        if (emptyRows > 0) {
                            tableRow {
                                css { height = (53 * emptyRows).px }
                                tableCell { attrs.colSpan = 6 }
                            }
                        }
                    }
                }
            }
            tablePagination(page, androidDeserts.size, rowsPerPage, { _, newPage -> setState { page = newPage } }) {
                attrs.component = "div"
                attrs.rowsPerPageOptions = arrayOf(5, 10, 25)
                attrs.onRowsPerPageChange = {
                    setState {
                        rowsPerPage = it.target.asDynamic().value
                        page = 0
                    }
                }
            }
        }
    }

    private fun RBuilder.enhancedTableHead(
        numSelected: Int,
        order: TableCellSortDirection,
        orderByColumn: ColumnId,
        rowCount: Int,
        onSelectAllClick: (checked: Boolean) -> Unit,
        onRequestSort: (id: ColumnId) -> Unit
    ) {
        tableHead {
            tableRow {
                tableCell {
                    attrs.padding = TableCellPadding.checkbox
                    checkbox(numSelected == rowCount) {
                        attrs.indeterminate = numSelected > 0 && numSelected < rowCount
                        attrs.onChange = {_, checked -> onSelectAllClick(checked) }
                    }
                }
                columnData.forEach { data ->
                    tableCell(key = data.name) {
                        attrs.align = if (data.rightAligned) TableCellAlign.right else TableCellAlign.left
                        attrs.padding = if (data.disablePadding) TableCellPadding.none else TableCellPadding.normal
                        attrs.sortDirection = if (orderByColumn == data.name) order else TableCellSortDirection.False

                        tooltip("Sort", if (data.rightAligned) TooltipPlacement.bottomEnd else TooltipPlacement.bottomStart) {
                            attrs.enterDelay = 300
                            tableSortLabel(
                                data.label,
                                orderByColumn == data.name,
                                if (order == TableCellSortDirection.asc) TableSortLabelDirection.asc else TableSortLabelDirection.desc
                            ) {
                                attrs.onClick = { onRequestSort(data.name) }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.enhancedTableToolbar(numSelected: Int) {
        themeContext.Consumer { theme ->
            val styles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val spacer by css {
                    flex(1.0, 1.0, 100.pct)
                }
                val highlight by css {
                    if (theme.palette.mode == PaletteMode.light) {
                        color = Color(theme.palette.secondary.main)
                        backgroundColor = Color(lighten(theme.palette.secondary.light, 0.85))
                    } else {
                        color = Color(theme.palette.text.primary)
                        backgroundColor = Color(lighten(theme.palette.secondary.dark, 0.85))
                    }
                }
                val actions by css {
                    color = Color(theme.palette.text.secondary)
                }
            }

            toolbar {
                if (numSelected > 0) css(styles.highlight)
                styledDiv {
                    css { flex(0.0, 0.0, FlexBasis.auto) }
                    if (numSelected > 0) {
                        typography("$numSelected selected", variant = TypographyVariant.subtitle1)
                    } else {
                        typography("Nutrition", variant = TypographyVariant.h6)
                    }
                }
                styledDiv { css(styles.spacer) }
                styledDiv {
                    css(styles.actions)
                    if (numSelected > 0) {
                        tooltip("Delete") {
                            iconButton("delete")
                        }
                    } else {
                        tooltip("Filter list") {
                            iconButton("filter_list")
                        }
                    }
                }
            }
        }
    }
}
