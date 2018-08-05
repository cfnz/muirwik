package com.ccfraser.muirwik.wrapper

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/InputAdornment")
private external val inputAdornmentDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputAdornmentComponent: RComponent<MInputAdornmentProps, RState> = inputAdornmentDefault.default

enum class MInputAdornmentPosition {
    Start, End;
}

interface MInputAdornmentProps : StyledProps {
    var disableTypography: Boolean
    var position: String
}

fun RBuilder.mInputAdornment(
        position: MInputAdornmentPosition = MInputAdornmentPosition.Start,
        disableTypography: Boolean = false,

        className: String? = null,

        handler: StyledHandler<MInputAdornmentProps>? = null) = createStyled(inputAdornmentComponent, false) {
    attrs.disableTypography = disableTypography
    attrs.position = position.toString().toLowerCase()

    setStyledPropsAndRunHandler(className, handler)
}





