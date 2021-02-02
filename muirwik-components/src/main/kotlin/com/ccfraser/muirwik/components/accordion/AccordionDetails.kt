package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/AccordionDetails")
private external val accordionDetailsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionComponent: RComponent<StyledProps, RState> = accordionDetailsModule.default

fun RBuilder.mAccordionDetails(
		className: String? = null,
		handler: StyledHandler<StyledProps>? = null) = createStyled(accordionComponent) {
			setStyledPropsAndRunHandler(className, handler)
		}
