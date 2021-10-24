package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/AccordionActions")
private external val accordionActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val jsComponentType: ComponentType<AccordionActionsProps> = accordionActionsModule.default

external interface AccordionActionsProps : StyledPropsWithCommonAttributes {
	var disableSpacing: Boolean
}


fun RBuilder.accordionActions(handler: StyledHandler<AccordionActionsProps>) {
	createStyled(jsComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAccordionActions(
	disableSpacing: Boolean? = null,
	className: String? = null,
	handler: StyledHandler<AccordionActionsProps>? = null
) {
	createStyled(jsComponentType, className, handler) {
		disableSpacing?.let { attrs.disableSpacing = it }
	}
}