package com.ccfraser.muirwik.wrapper.gridlist

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import react.key
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridListTile")
private external val gridListTileModule: dynamic
private val gridListTileComponent: RComponent<MGridListTileProps, RState> =gridListTileModule.default

interface MGridListTileProps: StyledProps {
    var cols: Int
    var component: String
    var rows: Int
}

fun RBuilder.mGridListTile(
        key: String,
        cols: Int = 1,
        component: String = "li",
        rows: Int = 1,

        className: String? = null,
        handler: StyledHandler<MGridListTileProps>? = null) = createStyled(gridListTileComponent) {
    attrs.cols = cols
    attrs.component = component
    attrs.key = key
    attrs.rows = rows

    setStyledPropsAndRunHandler(className, handler)
}
