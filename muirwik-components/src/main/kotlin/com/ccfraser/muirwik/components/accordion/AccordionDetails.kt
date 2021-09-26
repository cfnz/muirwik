package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/AccordionDetails")
private external val accordionDetailsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionComponentType: ComponentType<StyledProps> = accordionDetailsModule.default

fun RBuilder.mAccordionDetails(
	className: String? = null,
	handler: StyledHandler<StyledProps>? = null
) {
	createStyled(accordionComponentType, className, handler)
}
