package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.*
import styled.StyledHandler

@JsModule("@material-ui/core/AccordionSummary")
private external val accordionSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionSummaryComponent: RComponent<MAccordionSummaryProps, RState> = accordionSummaryModule.default

external interface MAccordionSummaryProps : StyledPropsWithCommonAttributes {
	var expandIcon: ReactElement
	var iconButtonProps: RProps
}

fun RBuilder.mAccordionSummary(
		expandIcon: ReactElement? = null,
		iconButtonProps: RProps? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionSummaryProps>? = null) = createStyled(accordionSummaryComponent) {
			expandIcon?.let { attrs.expandIcon = it }
			iconButtonProps?.let { attrs.iconButtonProps = it }
			setStyledPropsAndRunHandler(className, handler)
		}
