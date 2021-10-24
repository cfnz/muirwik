package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/AccordionDetails")
private external val accordionDetailsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionComponentType: ComponentType<StyledProps> = accordionDetailsModule.default


fun RBuilder.accordionDetails(handler: StyledHandler<StyledProps>) {
	createStyled(accordionComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAccordionDetails(
	className: String? = null,
	handler: StyledHandler<StyledProps>? = null
) {
	createStyled(accordionComponentType, className, handler)
}