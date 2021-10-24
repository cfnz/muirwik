package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageList")
private external val imageListModule: dynamic
@Suppress("UnsafeCastFromDynamic")
private val imageListComponentType: ComponentType<ImageListProps> = imageListModule.default

enum class ImageListVariant {
    masonry, quilted, standard, woven
}

external interface ImageListProps: StyledProps {
    var cols: Int
    var component: ElementType
    var gap: Number
    @JsName("rowHeight")
    var rawRowHeight: Any
}
var ImageListProps.variant by EnumPropToString(ImageListVariant.values())
var ImageListProps.rowHeight: Number
    get() = rawRowHeight.let { if (it is Number) it else 0 }
    set(value) {
        rawRowHeight = if (value == 0) "auto" else value
    }

/**
 * Creates a ImageList. For auto cell height, set cellHeight to 0.
 */
fun RBuilder.imageList(cols: Int = 2, gap: Int = 4, handler: StyledHandler<ImageListProps>) {
    createStyled(imageListComponentType, handler) {
        attrs.cols = cols
        attrs.gap = gap
    }
}

@Deprecated("Use imageList")
/**
 * Creates a ImageList. For auto cell height, set cellHeight to 0.
 */
fun RBuilder.mImageList(
    cols: Int = 2,
    gap: Int = 4,
    rowHeight: Number = 0,
    component: ElementType = "ul",

    className: String? = null,
    handler: StyledHandler<ImageListProps>? = null
) {
    createStyled(imageListComponentType, className, handler) {
        attrs.rowHeight = rowHeight
        attrs.cols = cols
        attrs.component = component
        attrs.gap = gap
    }
}
