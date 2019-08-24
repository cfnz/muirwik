package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import react.RBuilder
import react.RComponent
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
    spaceAround;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class MGridAlignItems {
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
enum class MGridDirection {
    row,
    rowReverse,
    column,
    columnReverse;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class MGridJustify {
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
    noWrap, wrap, wrapReverse;

    override fun toString(): String {
        return when(this) {
            noWrap -> "nowrap"
            wrap -> "wrap"
            wrapReverse -> "wrap-reverse"
        }
    }
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
    var alignContent: MGridAlignContent
    var alignItems: MGridAlignItems
    var component: String
    var container: Boolean
    var direction: MGridDirection
    var item: Boolean
    var justify: MGridJustify
    var lg: MGridSize
    var md: MGridSize
    var sm: MGridSize
    var spacing: MGridSpacing
    var wrap: MGridWrap
    var xl: MGridSize
    var xs: MGridSize
    var zeroMinWidth: Boolean
}

private fun MGridProps.redefineTypedProps() {
    if (alignContent != undefined) this.asDynamic().alignContent = alignContent.toString()
    if (alignItems != undefined) this.asDynamic().alignItems = alignItems.toString()
    if (direction != undefined) this.asDynamic().direction = direction.toString()
    if (justify != undefined) this.asDynamic().justify = justify.toString()
    if (lg != undefined) this.asDynamic().lg = lg.sizeVal
    if (md != undefined) this.asDynamic().md = md.sizeVal
    if (sm != undefined) this.asDynamic().sm = sm.sizeVal
    if (spacing != undefined) this.asDynamic().spacing = spacing.size
    if (wrap != undefined) this.asDynamic().wrap = wrap.toString()
    if (xl != undefined) this.asDynamic().xl = xl.sizeVal
    if (xs != undefined) this.asDynamic().xs = xs.sizeVal
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
        justify: MGridJustify = MGridJustify.flexStart,
        wrap: MGridWrap = MGridWrap.wrap,

        className: String? = null,
        handler: StyledHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.alignContent = alignContent
    attrs.alignItems = alignItems
    attrs.container = true
    attrs.justify = justify
    attrs.spacing = spacing
    attrs.wrap = wrap

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}

fun RBuilder.mGridItem(
        xs: MGridSize = MGridSize.cellsFalse,
        sm: MGridSize = MGridSize.cellsFalse,
        md: MGridSize = MGridSize.cellsFalse,
        lg: MGridSize = MGridSize.cellsFalse,
        xl: MGridSize = MGridSize.cellsFalse,
        zeroMinWidth: Boolean? = null,

        className: String? = null,
        handler: StyledHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.item = true
    attrs.sm = sm
    attrs.md = md
    attrs.lg = lg
    attrs.xs = xs
    attrs.xl = xl
    zeroMinWidth?.let { attrs.zeroMinWidth = it }

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}

fun RBuilder.mGridItem(
        breakpoints: MGridBreakpoints,
        className: String? = null,
        handler: StyledHandler<MGridProps>? = null) =
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
