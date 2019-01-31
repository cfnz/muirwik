package com.ccfraser.muirwik.components.gridlist

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridList")
private external val gridListModule: dynamic
private val gridListComponent: RComponent<MGridListProps, RState> =gridListModule.default

interface MGridListProps: StyledProps {
    var cellHeight: Any
    var cols: Number
    var component: String
    var spacing: Int
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
        handler: StyledHandler<MGridListProps>? = null) = createStyled(gridListComponent) {
    attrs.cellHeight = if (cellHeight == 0) "auto" else cellHeight
    attrs.cols = cols
    attrs.component = component
    attrs.spacing = spacing

    setStyledPropsAndRunHandler(className, handler)
}
