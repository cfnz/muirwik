package com.ccfraser.muirwik.components.imagelist

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageList")
private external val imageListModule: dynamic
@Suppress("UnsafeCastFromDynamic")
private val imageListComponentType: ComponentType<MImageListProps> = imageListModule.default

external interface MImageListProps: StyledProps {

    @JsName("cellHeight")
    var rawCellHeight: Any
    var cols: Number
    var component: String
    var spacing: Int
}
var MImageListProps.cellHeight: Int
    get() = rawCellHeight.let { if (it is Int) it else 0 }
    set(value) {
        rawCellHeight = if (value == 0) "auto" else value
    }

/**
 * Creates a ImageList. For auto cell height, set cellHeight to 0.
 */
fun RBuilder.mImageList(
    cols: Number = 2,
    spacing: Int = 4,
    cellHeight: Int = 180,
    component: String = "ul",

    className: String? = null,
    handler: StyledHandler<MImageListProps>? = null
) {
    createStyled(imageListComponentType, className, handler) {
        attrs.cellHeight = cellHeight
        attrs.cols = cols
        attrs.component = component
        attrs.spacing = spacing
    }
}
