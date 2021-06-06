package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.styles.lighten
import com.ccfraser.muirwik.components.table.*
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.math.min


@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestTables : RComponent<RProps, RState>() {
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
    private var order = MTableCellSortDirection.asc
    private var orderByColumn: ColumnId = ColumnId.Name
    private var page = 0
    private var rowsPerPage = 5


    override fun RBuilder.render() {
        mTypography("Simple Table")
        simpleTable()
        br {  }
        br {  }
        mTypography("Sorting and Selecting")
        sortingAndSelecting()
    }

    fun RBuilder.simpleTable() {
        mPaper {
            css {
                width = 100.pct
                marginTop = 3.spacingUnits
                overflowX = Overflow.auto
            }
            mTable() {
                css { minWidth = 700.px }
                mTableHead {
                    mTableRow {
                        mTableCell { +"Dessert (100g serving)" }
                        mTableCell(align = MTableCellAlign.right) { +"Calories" }
                        mTableCell(align = MTableCellAlign.right) { +"Fat (g)" }
                        mTableCell(align = MTableCellAlign.right) { +"Carbs (g)" }
                        mTableCell(align = MTableCellAlign.right) { +"Protein (g)" }
                    }
                }
                mTableBody {
                    androidDeserts.subList(0, 4).forEach {
                        mTableRow(key = it.id) {
                            mTableCell { +it.dessertName }
                            mTableCell(align = MTableCellAlign.right) { +it.calories.toString() }
                            mTableCell(align = MTableCellAlign.right) { +it.fat.toString() }
                            mTableCell(align = MTableCellAlign.right) { +it.carbs.toString() }
                            mTableCell(align = MTableCellAlign.right) { +it.protein.toString() }
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

    private fun handleClick(id: Int): Unit {
        setState {
            if (selectedIds.contains(id)) {
                selectedIds.remove(id)
            } else {
                selectedIds.add(id)
            }
        }
    }

    private fun handleRequestSort(id: ColumnId): Unit {
        setState {
            if (orderByColumn == id) {
                order = if (order == MTableCellSortDirection.asc) MTableCellSortDirection.desc else MTableCellSortDirection.asc
            } else {
                order = MTableCellSortDirection.asc
            }

            orderByColumn = id

            fun compareDesserts(a: Dessert, b: Dessert) = when (id) {
                ColumnId.Name -> a.dessertName.compareTo(b.dessertName)
                ColumnId.Calories -> a.calories.compareTo(b.calories)
                ColumnId.Carbs -> a.carbs.compareTo(b.carbs)
                ColumnId.Fat -> a.fat.compareTo(b.fat)
                ColumnId.Protein -> a.protein.compareTo(b.protein)
            }

            if (order == MTableCellSortDirection.asc) {
                androidDeserts.sortWith( Comparator { a, b -> compareDesserts(a, b) })
            } else {
                androidDeserts.sortWith( Comparator { a, b -> compareDesserts(b, a) })
            }
        }
    }

    private fun RBuilder.sortingAndSelecting() {
        mPaper {
            css {
                width = 100.pct
                marginTop = 3.spacingUnits
            }
            enhancedTableToolbar(selectedIds.size)
            styledDiv { css { overflowX = Overflow.auto }
                mTable {
                    css { minWidth = 700.px }
                    enhancedTableHead(selectedIds.size, order, orderByColumn, androidDeserts.size,
                            ::handleSelectAllClick, ::handleRequestSort)
                    mTableBody {
                        androidDeserts.subList(page * rowsPerPage, min((page + 1) * rowsPerPage, androidDeserts.size)).forEach {
                            val isSelected = selectedIds.contains(it.id)
                            mTableRow(it.id, isSelected, true, onClick = { _ -> handleClick(it.id) }) {
//                                attrs.asDynamic().tabIndex = -1
//                                attrs.asDynamic().role = "checkbox"

                                mTableCell(padding = MTableCellPadding.checkbox) {
                                    mCheckbox(isSelected)
                                }
                                mTableCell(align = MTableCellAlign.left, padding = MTableCellPadding.none) { +it.dessertName }
                                mTableCell(align = MTableCellAlign.right) { +it.calories.toString() }
                                mTableCell(align = MTableCellAlign.right) { +it.fat.toString() }
                                mTableCell(align = MTableCellAlign.right) { +it.carbs.toString() }
                                mTableCell(align = MTableCellAlign.right) { +it.protein.toString() }
                            }
                        }
                        val emptyRows = rowsPerPage - min(rowsPerPage, androidDeserts.size - page * rowsPerPage)
                        if (emptyRows > 0) {
                            mTableRow {
                                css { height = (49 * emptyRows).px }
                                mTableCell(colSpan = 6)
                            }
                        }
                    }
                }
            }
            mTablePagination(count = androidDeserts.size, rowsPerPage = rowsPerPage, page = page,
                    onChangePage = { _, newPage -> setState { page = newPage }},
                    onChangeRowsPerPage = { setState {
                        rowsPerPage = it.target.asDynamic().value
                        page = 0
                    }})
        }
    }

    private fun RBuilder.enhancedTableHead(numSelected: Int,
                                   order: MTableCellSortDirection,
                                   orderByColumn: ColumnId,
                                   rowCount: Int,
                                   onSelectAllClick: (checked: Boolean) -> Unit,
                                   onRequestSort: (id: ColumnId) -> Unit) {
        mTableHead {
            mTableRow {
                mTableCell(padding = MTableCellPadding.checkbox) {
                    mCheckbox(indeterminate = numSelected > 0 && numSelected < rowCount,
                            checked = numSelected == rowCount,
                            onChange = {_, checked -> onSelectAllClick(checked) })
                }
                columnData.forEach { data ->
                    mTableCell(data.name, 
                            align = if (data.rightAligned) MTableCellAlign.right else MTableCellAlign.left,
                            padding = if (data.disablePadding) MTableCellPadding.none else MTableCellPadding.default,
                            sortDirection = if (orderByColumn == data.name) order else MTableCellSortDirection.False) {
                        mTooltip("Sort", if (data.rightAligned) TooltipPlacement.bottomEnd else TooltipPlacement.bottomStart, enterDelay = 300) {
                            mTableSortLabel(data.label, orderByColumn == data.name,
//                                    iconFunction = { mIcon("star", addAsChild = false) },
                                    direction = if (order == MTableCellSortDirection.asc) MTableSortLabelDirection.asc else MTableSortLabelDirection.desc,
                                    onClick = { onRequestSort(data.name) }) 
                        }
                    }
                }
            }
        }
    }

    fun RBuilder.enhancedTableToolbar(numSelected: Int) {
        themeContext.Consumer { theme ->
            val styles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val spacer by css {
                    flex(1.0, 1.0, 100.pct)
                }
                val highlight by css {
                    if (theme.palette.type == "light") {
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

            mToolbar {
                if (numSelected > 0) css(styles.highlight)
                styledDiv {
                    css { flex(0.0, 0.0, FlexBasis.auto) }
                    if (numSelected > 0) {
                        mTypography("$numSelected selected", variant = MTypographyVariant.subtitle1)
                    } else {
                        mTypography("Nutrition", variant = MTypographyVariant.h6)
                    }
                }
                styledDiv { css(styles.spacer) }
                styledDiv {
                    css(styles.actions)
                    if (numSelected > 0) {
                        mTooltip("Delete") {
                            mIconButton("delete")
                        }
                    } else {
                        mTooltip("Filter list") {
                            mIconButton("filter_list")
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testTables() = child(TestTables::class) {}
