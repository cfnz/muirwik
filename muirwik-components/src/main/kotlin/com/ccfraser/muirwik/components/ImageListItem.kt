package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.key
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageListItem")
private external val imageListItemModule: dynamic
private val imageListItemComponentType: ComponentType<ImageListItemProps> = imageListItemModule.default

external interface ImageListItemProps: StyledProps {
    var cols: Int
    var component: String
    var rows: Int
}

fun RBuilder.imageListItem(
    cols: Int = 1,
    rows: Int = 1,
    key: String,
    component: String = "li",
    handler: StyledHandler<ImageListItemProps>? = null
) {
    createStyled(imageListItemComponentType, handler) {
        attrs.cols = cols
        attrs.component = component
        attrs.key = key
        attrs.rows = rows
    }
}

