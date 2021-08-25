package com.ccfraser.muirwik.components.gridlist

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.key
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridListTile")
private external val gridListTileModule: dynamic
private val gridListTileComponentType: ComponentType<MGridListTileProps> = gridListTileModule.default

external interface MGridListTileProps: StyledProps {
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
    handler: StyledHandler<MGridListTileProps>? = null
) {
    createStyled(gridListTileComponentType, className, handler) {
        attrs.cols = cols
        attrs.component = component
        attrs.key = key
        attrs.rows = rows
    }
}
