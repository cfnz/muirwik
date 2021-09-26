package com.ccfraser.muirwik.components.imagelist

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.key
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageListItem")
private external val imageListItemModule: dynamic
private val imageListItemComponentType: ComponentType<MImageListItemProps> = imageListItemModule.default

external interface MImageListItemProps: StyledProps {
    var cols: Int
    var component: String
    var rows: Int
}

fun RBuilder.mGridImage(
    key: String,
    cols: Int = 1,
    component: String = "li",
    rows: Int = 1,

    className: String? = null,
    handler: StyledHandler<MImageListItemProps>? = null
) {
    createStyled(imageListItemComponentType, className, handler) {
        attrs.cols = cols
        attrs.component = component
        attrs.key = key
        attrs.rows = rows
    }
}
