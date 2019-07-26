package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Grid")
private external val gridDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val gridComponent: RComponent<MGridProps, RState> = gridDefault.default

@Suppress("EnumEntryName")
enum class MGridAlignContent {
    stretch,
    center,
    flexStart,
    flexEnd,
    spaceBetween,
    spaceAround
}

@Suppress("EnumEntryName")
enum class MGridAlignItems {
    stretch,
    center,
    flexStart,
    flexEnd,
    baseline
}

@Suppress("EnumEntryName")
enum class MGridDirection {
    row,
    rowReverse,
    column,
    columnReverse
}

@Suppress("EnumEntryName")
enum class MGridJustify {
    flexStart,
    center,
    flexEnd,
    spaceBetween,
    spaceAround
}

@Suppress("EnumEntryName")
enum class MGridSize(internal val sizeVal: Any) {
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
enum class MGridWrap {
    noWrap, wrap, wrapReverse
}

enum class MGridSpacing(val size: Int) {
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
 * This class as no companion in MaterialUI. We just use it to make setting grid breakpoints a bit easier
 */
data class MGridBreakpoints(
        val xs: MGridSize = MGridSize.cellsAuto,
        val sm: MGridSize = MGridSize.cellsAuto,
        val md: MGridSize = MGridSize.cellsAuto,
        val lg: MGridSize = MGridSize.cellsAuto,
        val xl: MGridSize = MGridSize.cellsAuto) {
    constructor(defaultGridSize: MGridSize) : this(defaultGridSize, defaultGridSize, defaultGridSize, defaultGridSize, defaultGridSize)

    fun down(breakpoint: Breakpoint, gridSize: MGridSize): MGridBreakpoints {
        return when (breakpoint) {
            Breakpoint.xs -> copy(xs = gridSize)
            Breakpoint.sm -> copy(xs = gridSize, sm = gridSize)
            Breakpoint.md -> copy(xs = gridSize, sm = gridSize, md = gridSize)
            Breakpoint.lg -> copy(xs = gridSize, sm = gridSize, md = gridSize, lg = gridSize)
            Breakpoint.xl -> copy(xs = gridSize, sm = gridSize, md = gridSize, lg = gridSize, xl = gridSize)
        }
    }

    fun up(breakpoint: Breakpoint, gridSize: MGridSize): MGridBreakpoints {
        return when (breakpoint) {
            Breakpoint.xs -> copy(xl = gridSize, lg = gridSize, md = gridSize, sm = gridSize, xs = gridSize)
            Breakpoint.sm -> copy(xl = gridSize, lg = gridSize, md = gridSize, sm = gridSize)
            Breakpoint.md -> copy(xl = gridSize, lg = gridSize, md = gridSize)
            Breakpoint.lg -> copy(xl = gridSize, lg = gridSize)
            Breakpoint.xl -> copy(xl = gridSize)
        }
    }
}


interface MGridProps : StyledProps {
    var alignContent: String
    var alignItems: String
    var container: Boolean
    var item: Boolean
    var lg: Any
    var md: Any
    var sm: Any
    var spacing: Int
    var xl: Any
    var xs: Any
    var wrap: String
    var zeroMinWidth: Boolean
}

/**
 * The material design components allows a grid item to be a container and an item. We have simplified things here
 * since different properties apply depending on if it is a container or an item. So, if you want both, you will have
 * to add an extra child item.
 */
fun RBuilder.mGridContainer(
        spacing: MGridSpacing = MGridSpacing.spacing0,
        alignContent: MGridAlignContent = MGridAlignContent.stretch,
        alignItems: MGridAlignItems = MGridAlignItems.stretch,
        wrap: MGridWrap = MGridWrap.wrap,

        className: String? = null,
        handler: StyledHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.alignContent = alignContent.toString()
    attrs.alignItems = alignItems.toString()
    attrs.container = true
    attrs.spacing = spacing.size
    attrs.wrap = wrap.toString()

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mGridItem(
        xs: MGridSize = MGridSize.cellsFalse,
        sm: MGridSize = MGridSize.cellsFalse,
        md: MGridSize = MGridSize.cellsFalse,
        lg: MGridSize = MGridSize.cellsFalse,
        xl: MGridSize = MGridSize.cellsFalse,
        zeroMinWidth: Boolean? = null,

        className: String? = null,
        handler: RHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.item = true
    attrs.sm = sm.sizeVal
    attrs.md = md.sizeVal
    attrs.lg = lg.sizeVal
    attrs.xs = xs.sizeVal
    attrs.xl = xl.sizeVal
    zeroMinWidth?.let { attrs.zeroMinWidth = it }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mGridItem(
        breakpoints: MGridBreakpoints,
        className: String? = null,
        handler: RHandler<MGridProps>? = null) =
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
