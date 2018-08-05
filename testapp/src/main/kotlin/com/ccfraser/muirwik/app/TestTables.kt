package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.styles.lighten
import com.ccfraser.muirwik.wrapper.table.*
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.math.min


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
    private var order = MTableCellSortDirection.Asc
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
                        mTableCell(alignRight = true) { +"Calories" }
                        mTableCell(alignRight = true) { +"Fat (g)" }
                        mTableCell(alignRight = true) { +"Carbs (g)" }
                        mTableCell(alignRight = true) { +"Protein (g)" }
                    }
                }
                mTableBody {
                    androidDeserts.subList(0, 4).forEach {
                        mTableRow(key = it.id) {
                            mTableCell { +it.dessertName }
                            mTableCell(alignRight = true) { +it.calories.toString() }
                            mTableCell(alignRight = true) { +it.fat.toString() }
                            mTableCell(alignRight = true) { +it.carbs.toString() }
                            mTableCell(alignRight = true) { +it.protein.toString() }
                        }
                    }
                }
            }
        }
    }

    // That was pretty easy, the below is pretty overwhelming... will look at providing a simpler wrapper below
    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val spacer by css {
            flex(1.0, 1.0, 100.pct)
        }
        val highlight by css {
            if (currentTheme.palette.type == "light") {
                color = Color(currentTheme.palette.secondary.main)
                backgroundColor = Color(lighten(currentTheme.palette.secondary.light, 0.85))
            } else {
                color = Color(currentTheme.palette.text.primary)
                backgroundColor = Color(lighten(currentTheme.palette.secondary.dark, 0.85))
            }
        }
        val actions by css {
            color = Color(currentTheme.palette.text.secondary)
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
                order = if (order == MTableCellSortDirection.Asc) MTableCellSortDirection.Desc else MTableCellSortDirection.Asc
            } else {
                order = MTableCellSortDirection.Asc
            }

            orderByColumn = id

            fun compareDesserts(a: Dessert, b: Dessert) = when (id) {
                ColumnId.Name -> a.dessertName.compareTo(b.dessertName)
                ColumnId.Calories -> a.calories.compareTo(b.calories)
                ColumnId.Carbs -> a.carbs.compareTo(b.carbs)
                ColumnId.Fat -> a.fat.compareTo(b.fat)
                ColumnId.Protein -> a.protein.compareTo(b.protein)
            }

            if (order == MTableCellSortDirection.Asc) {
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

                                mTableCell(padding = MTableCellPadding.Checkbox) {
                                    mCheckbox(isSelected, primary = false)
                                }
                                mTableCell(alignRight = false, padding = MTableCellPadding.None) { +it.dessertName }
                                mTableCell(alignRight = true) { +it.calories.toString() }
                                mTableCell(alignRight = true) { +it.fat.toString() }
                                mTableCell(alignRight = true) { +it.carbs.toString() }
                                mTableCell(alignRight = true) { +it.protein.toString() }
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
                mTableCell(padding = MTableCellPadding.Checkbox) {
                    mCheckbox(indeterminate = numSelected > 0 && numSelected < rowCount,
                            primary = false,
                            checked = numSelected == rowCount,
                            onChange = {_, checked -> onSelectAllClick(checked) })
                }
                columnData.forEach { data ->
                    mTableCell(data.name, 
                            alignRight = data.rightAligned,
                            padding = if (data.disablePadding) MTableCellPadding.None else MTableCellPadding.Default,
                            sortDirection = if (orderByColumn == data.name) order else MTableCellSortDirection.False) {
                        mTooltip("Sort", if (data.rightAligned) TooltipPlacement.BottomEnd else TooltipPlacement.BottomStart, enterDelay = 300) {
                            mTableSortLabel(data.label, orderByColumn == data.name,
                                    direction = if (order == MTableCellSortDirection.Asc) MTableSortLabelDirection.Asc else MTableSortLabelDirection.Desc,
                                    onClick = { onRequestSort(data.name) }) 
                        }
                    }
                }
            }
        }
    }

    fun RBuilder.enhancedTableToolbar(numSelected: Int) {
        mToolbar {
            if (numSelected > 0) css(ComponentStyles.highlight)
            styledDiv {
                css { flex(0.0, 0.0, FlexBasis.auto) }
                if (numSelected > 0) {
                    mTypography("$numSelected selected", variant = MTypographyVariant.Subheading)
                } else {
                    mTypography("Nutrition", variant = MTypographyVariant.Title)
                }
            }
            styledDiv { css(ComponentStyles.spacer) }
            styledDiv { css(ComponentStyles.actions)
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

fun RBuilder.testTables() = child(TestTables::class) {}
