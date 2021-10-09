package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/AccordionActions")
private external val accordionActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val jsComponentType: ComponentType<MAccordionActionsProps> = accordionActionsModule.default

external interface MAccordionActionsProps : StyledPropsWithCommonAttributes {
	var disableSpacing: Boolean
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAccordionActions(
		disableSpacing: Boolean? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionActionsProps>? = null
) {
	createStyled(jsComponentType, className, handler) {
		disableSpacing?.let { attrs.disableSpacing = it }
	}
}

fun RBuilder.mAccordionActions(handler: StyledHandler<MAccordionActionsProps>) {
	createStyled(jsComponentType, handler)
}
