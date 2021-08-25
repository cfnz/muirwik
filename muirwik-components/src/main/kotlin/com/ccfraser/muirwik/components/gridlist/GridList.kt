package com.ccfraser.muirwik.components.gridlist

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridList")
private external val gridListModule: dynamic
@Suppress("UnsafeCastFromDynamic")
private val gridListComponentType: ComponentType<MGridListProps> = gridListModule.default

external interface MGridListProps: StyledProps {

    @JsName("cellHeight")
    var rawCellHeight: Any
    var cols: Number
    var component: String
    var spacing: Int
}
var MGridListProps.cellHeight: Int
    get() = rawCellHeight.let { if (it is Int) it else 0 }
    set(value) {
        rawCellHeight = if (value == 0) "auto" else value
    }

/**
 * Creates a GridList. For auto cell height, set cellHeight to 0.
 */
fun RBuilder.mGridList(
    cols: Number = 2,
    spacing: Int = 4,
    cellHeight: Int = 180,
    component: String = "ul",

    className: String? = null,
    handler: StyledHandler<MGridListProps>? = null
) {
    createStyled(gridListComponentType, className, handler) {
        attrs.cellHeight = cellHeight
        attrs.cols = cols
        attrs.component = component
        attrs.spacing = spacing
    }
}
