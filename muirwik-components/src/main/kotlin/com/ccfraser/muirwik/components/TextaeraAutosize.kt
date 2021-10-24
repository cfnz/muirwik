package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/TextareaAutosize")
private external val textAreaAutosizeDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val textAreaAutosizeComponentType: ComponentType<TextareaAutosizeProps> = textAreaAutosizeDefault.default

external interface TextareaAutosizeProps : FormControlProps {
    var maxRows: Int
    var minRows: Int
}

fun RBuilder.textAreaAutosize(
    minRows: Int = 1,
    maxRows: Int? = null,
    handler: StyledHandler<TextareaAutosizeProps>? = null
) {
    createStyled(textAreaAutosizeComponentType, handler) {
        maxRows?.let { attrs.maxRows = it }
        attrs.minRows = minRows
    }
}
