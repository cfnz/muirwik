package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/AccordionDetails")
private external val module: dynamic

@Suppress("UnsafeCastFromDynamic")
private val component: RComponent<StyledProps, RState> = module.default

fun RBuilder.mAccordionDetails(
		className: String? = null,
		handler: StyledHandler<StyledProps>? = null) = createStyled(component) {
			setStyledPropsAndRunHandler(className, handler)
		}
