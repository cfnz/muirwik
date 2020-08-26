package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/AccordionActions")
private external val module: dynamic

@Suppress("UnsafeCastFromDynamic")
private val component: RComponent<MAccordionActionsProps, RState> = module.default

interface MAccordionActionsProps : StyledPropsWithCommonAttributes {
	var disableSpacing: Boolean
}

fun RBuilder.mAccordionActions(
		disableSpacing: Boolean? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionActionsProps>? = null) = createStyled(component) {
			disableSpacing?.let { attrs.disableSpacing = it }
			setStyledPropsAndRunHandler(className, handler)
		}
