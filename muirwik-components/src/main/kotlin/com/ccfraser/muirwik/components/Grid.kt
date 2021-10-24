package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


@JsModule("@mui/material/Grid")
private external val gridDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val gridComponentType: ComponentType<GridProps> = gridDefault.default

@Suppress("EnumEntryName")
enum class GridAlignContent {
    stretch,
    center,
    flexStart,
    flexEnd,
    spaceBetween,
    spaceAround;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class GridAlignItems {
    stretch,
    center,
    flexStart,
    flexEnd,
    baseline;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class GridDirection {
    row,
    rowReverse,
    column,
    columnReverse;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class GridJustify {
    flexStart,
    center,
    flexEnd,
    spaceBetween,
    spaceAround;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class GridSize(internal val sizeVal: Any) {
    cellsFalse(false),
    cellsAuto("auto"),
    cellsTrue(true),
    cells1(1),
    cells2(2),
    cells3(3),
    cells4(4),
    cells5(5),
    cells6(6),
    cells7(7),
    cells8(8),
    cells9(9),
    cells10(10),
    cells11(11),
    cells12(12);
}

@Suppress("EnumEntryName")
enum class GridWrap {
    noWrap, wrap, wrapReverse;

    override fun toString(): String {
        return when(this) {
            noWrap -> "nowrap"
            wrap -> "wrap"
            wrapReverse -> "wrap-reverse"
        }
    }
}

@Suppress("EnumEntryName")
enum class GridSpacing(internal val size: Int) {
    spacing0(0),
    spacing1(1),
    spacing2(2),
    spacing3(3),
    spacing4(4),
    spacing5(5),
    spacing6(6),
    spacing7(7),
    spacing8(8),
    spacing9(9),
    spacing10(10)
}

/**
 * This class has no companion in MaterialUI. We just use it to make setting grid breakpoints a bit easier
 */
data class GridBreakpoints(
    val xs: GridSize = GridSize.cellsAuto,
    val sm: GridSize = GridSize.cellsAuto,
    val md: GridSize = GridSize.cellsAuto,
    val lg: GridSize = GridSize.cellsAuto,
    val xl: GridSize = GridSize.cellsAuto) {
    constructor(defaultGridSize: GridSize) : this(defaultGridSize, defaultGridSize, defaultGridSize, defaultGridSize, defaultGridSize)

    fun down(breakpoint: Breakpoint, gridSize: GridSize): GridBreakpoints {
        return when (breakpoint) {
            Breakpoint.xs -> copy(xs = gridSize)
            Breakpoint.sm -> copy(xs = gridSize, sm = gridSize)
            Breakpoint.md -> copy(xs = gridSize, sm = gridSize, md = gridSize)
            Breakpoint.lg -> copy(xs = gridSize, sm = gridSize, md = gridSize, lg = gridSize)
            Breakpoint.xl -> copy(xs = gridSize, sm = gridSize, md = gridSize, lg = gridSize, xl = gridSize)
        }
    }

    fun up(breakpoint: Breakpoint, gridSize: GridSize): GridBreakpoints {
        return when (breakpoint) {
            Breakpoint.xs -> copy(xl = gridSize, lg = gridSize, md = gridSize, sm = gridSize, xs = gridSize)
            Breakpoint.sm -> copy(xl = gridSize, lg = gridSize, md = gridSize, sm = gridSize)
            Breakpoint.md -> copy(xl = gridSize, lg = gridSize, md = gridSize)
            Breakpoint.lg -> copy(xl = gridSize, lg = gridSize)
            Breakpoint.xl -> copy(xl = gridSize)
        }
    }
}


external interface GridProps : StyledProps {
    var columns: Int
    var component: ElementType
    var container: Boolean
    var item: Boolean
    var zeroMinWidth: Boolean
}
var GridProps.alignContent by EnumPropToString(GridAlignContent.values())
var GridProps.alignItems by EnumPropToString(GridAlignItems.values())
var GridProps.columnSpacing by GridSpacingDelegate()
var GridProps.direction by EnumPropToString(GridDirection.values())
var GridProps.justify by EnumPropToString(GridJustify.values())
var GridProps.lg by GridSizeDelegate()
var GridProps.md by GridSizeDelegate()
var GridProps.rowSpacing by GridSpacingDelegate()
var GridProps.sm by GridSizeDelegate()
var GridProps.spacing by GridSpacingDelegate()
var GridProps.wrap by EnumPropToString(GridWrap.values())
var GridProps.xl by GridSizeDelegate()
var GridProps.xs by GridSizeDelegate()

class GridSizeDelegate : ReadWriteProperty<Props, GridSize?> {
    override fun getValue(thisRef: Props, property: KProperty<*>): GridSize? {
        return when (thisRef.asDynamic()[property.name]) {
            null -> null
            undefined -> null
            true -> GridSize.cellsTrue
            false -> GridSize.cellsFalse
            1 -> GridSize.cells1
            2 -> GridSize.cells2
            3 -> GridSize.cells3
            4 -> GridSize.cells4
            5 -> GridSize.cells5
            6 -> GridSize.cells6
            7 -> GridSize.cells7
            8 -> GridSize.cells8
            9 -> GridSize.cells9
            10 -> GridSize.cells10
            11 -> GridSize.cells11
            12 -> GridSize.cells12
            else -> GridSize.cellsAuto
        }
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: GridSize?) {
        thisRef.asDynamic()[property.name] = value?.sizeVal
    }
}

class GridSpacingDelegate : ReadWriteProperty<Props, GridSpacing?> {
    override fun getValue(thisRef: Props, property: KProperty<*>): GridSpacing? {
        return when (thisRef.asDynamic()[property.name]) {
            null -> null
            undefined -> null
            1 -> GridSpacing.spacing1
            2 -> GridSpacing.spacing2
            3 -> GridSpacing.spacing3
            4 -> GridSpacing.spacing4
            5 -> GridSpacing.spacing5
            6 -> GridSpacing.spacing6
            7 -> GridSpacing.spacing7
            8 -> GridSpacing.spacing8
            9 -> GridSpacing.spacing9
            10 -> GridSpacing.spacing10
            else -> GridSpacing.spacing0
        }
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: GridSpacing?) {
        thisRef.asDynamic()[property.name] = value?.size
    }
}

fun RBuilder.grid(handler: StyledHandler<GridProps>) {
    createStyled(gridComponentType, handler)
}

fun RBuilder.gridContainer(spacing: GridSpacing = GridSpacing.spacing0, handler: StyledHandler<GridProps>) {
    createStyled(gridComponentType, handler) {
        attrs.container = true
        attrs.spacing = spacing
    }
}

fun RBuilder.gridItem(handler: StyledHandler<GridProps>) {
    createStyled(gridComponentType, handler) {
        attrs.item = true
        attrs.container = false
    }
}

fun RBuilder.gridItem(breakpoints: GridBreakpoints, handler: StyledHandler<GridProps>? = null) {
    gridItem {
        attrs.xs = breakpoints.xs
        attrs.sm = breakpoints.sm
        attrs.md = breakpoints.md
        attrs.lg = breakpoints.lg
        attrs.xl = breakpoints.xl
        handler?.invoke(this)
    }
}


@Deprecated("Use the simpler 'non m' version.")
/**
 * The material design components allows a grid item to be a container and an item. We have simplified things here
 * since different properties apply depending on if it is a container or an item. So, if you want both, you will have
 * to add an extra child item.
 */
fun RBuilder.mGridContainer(
    spacing: GridSpacing = GridSpacing.spacing0,
    alignContent: GridAlignContent = GridAlignContent.stretch,
    alignItems: GridAlignItems = GridAlignItems.stretch,
    justify: GridJustify = GridJustify.flexStart,
    wrap: GridWrap = GridWrap.wrap,

    className: String? = null,
    handler: StyledHandler<GridProps>? = null
) {
    createStyled(gridComponentType, className, handler) {
        attrs.alignContent = alignContent
        attrs.alignItems = alignItems
        attrs.container = true
        attrs.justify = justify
        attrs.spacing = spacing
        attrs.wrap = wrap
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mGridItem(
    xs: GridSize = GridSize.cellsFalse,
    sm: GridSize = GridSize.cellsFalse,
    md: GridSize = GridSize.cellsFalse,
    lg: GridSize = GridSize.cellsFalse,
    xl: GridSize = GridSize.cellsFalse,
    zeroMinWidth: Boolean? = null,

    className: String? = null,
    handler: StyledHandler<GridProps>? = null
) {
    createStyled(gridComponentType, className, handler) {
        attrs.item = true
        attrs.sm = sm
        attrs.md = md
        attrs.lg = lg
        attrs.xs = xs
        attrs.xl = xl
        zeroMinWidth?.let { attrs.zeroMinWidth = it }
    }
}

@Deprecated("Use the simpler 'non m' version.")
@Suppress("DEPRECATION")
fun RBuilder.mGridItem(
    breakpoints: GridBreakpoints,
    className: String? = null,
    handler: StyledHandler<GridProps>? = null
) {
    mGridItem(
        breakpoints.xs,
        breakpoints.sm,
        breakpoints.md,
        breakpoints.lg,
        breakpoints.xl,
        null,
        className,
        handler
    )
}
