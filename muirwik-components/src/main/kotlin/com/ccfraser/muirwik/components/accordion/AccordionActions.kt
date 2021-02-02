package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/AccordionActions")
private external val accordionActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val jsComponent: RComponent<MAccordionActionsProps, RState> = accordionActionsModule.default

external interface MAccordionActionsProps : StyledPropsWithCommonAttributes {
	var disableSpacing: Boolean
}

fun RBuilder.mAccordionActions(
		disableSpacing: Boolean? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionActionsProps>? = null) = createStyled(jsComponent) {
			disableSpacing?.let { attrs.disableSpacing = it }
			setStyledPropsAndRunHandler(className, handler)
		}
