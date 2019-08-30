package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import styled.StyledProps
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


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

enum class MGridSpacing(internal val size: Int) {
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
    var component: String
    var container: Boolean
    var item: Boolean
    var zeroMinWidth: Boolean
}
var MGridProps.alignContent by EnumPropToString(MGridAlignContent.values())
var MGridProps.alignItems by EnumPropToString(MGridAlignItems.values())
var MGridProps.direction by EnumPropToString(MGridDirection.values())
var MGridProps.justify by EnumPropToString(MGridJustify.values())
var MGridProps.lg by GridSizeDelegate()
var MGridProps.md by GridSizeDelegate()
var MGridProps.sm by GridSizeDelegate()
var MGridProps.spacing by GridSpacingDelegate()
var MGridProps.wrap by EnumPropToString(MGridWrap.values())
var MGridProps.xl by GridSizeDelegate()
var MGridProps.xs by GridSizeDelegate()

class GridSizeDelegate : ReadWriteProperty<RProps, MGridSize?> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): MGridSize? {
        return when (thisRef.asDynamic()[property.name]) {
            null -> null
            undefined -> null
            true -> MGridSize.cellsTrue
            false -> MGridSize.cellsFalse
            1 -> MGridSize.cells1
            2 -> MGridSize.cells2
            3 -> MGridSize.cells3
            4 -> MGridSize.cells4
            5 -> MGridSize.cells5
            6 -> MGridSize.cells6
            7 -> MGridSize.cells7
            8 -> MGridSize.cells8
            9 -> MGridSize.cells9
            10 -> MGridSize.cells10
            11 -> MGridSize.cells11
            12 -> MGridSize.cells12
            else -> MGridSize.cellsAuto
        }
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: MGridSize?) {
        thisRef.asDynamic()[property.name] = value?.sizeVal
    }
}

class GridSpacingDelegate : ReadWriteProperty<RProps, MGridSpacing?> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): MGridSpacing? {
        return when (thisRef.asDynamic()[property.name]) {
            null -> null
            undefined -> null
            1 -> MGridSpacing.spacing1
            2 -> MGridSpacing.spacing2
            3 -> MGridSpacing.spacing3
            4 -> MGridSpacing.spacing4
            5 -> MGridSpacing.spacing5
            6 -> MGridSpacing.spacing6
            7 -> MGridSpacing.spacing7
            8 -> MGridSpacing.spacing8
            9 -> MGridSpacing.spacing9
            10 -> MGridSpacing.spacing10
            else -> MGridSpacing.spacing0
        }
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: MGridSpacing?) {
        thisRef.asDynamic()[property.name] = value?.size
    }
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
