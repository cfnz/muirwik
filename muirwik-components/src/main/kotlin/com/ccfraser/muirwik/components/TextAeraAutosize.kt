package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlProps
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/TextareaAutosize")
private external val textAreaAutosizeDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val textAreaAutosizeComponentType: ComponentType<MTextAreaAutosizeProps> = textAreaAutosizeDefault.default

external interface MTextAreaAutosizeProps : MFormControlProps {
    var rowsMax: Int
    var rowsMin: Int
}

fun RBuilder.mTextAreaAutosize(
    rowsMin: Int = 1,
    rowsMax: Int? = null,

    className: String? = null,
    handler: StyledHandler<MTextAreaAutosizeProps>? = null
) {
    createStyled(textAreaAutosizeComponentType, className, handler) {
        rowsMax?.let { attrs.rowsMax = it }
        attrs.rowsMin = rowsMin
    }
}
