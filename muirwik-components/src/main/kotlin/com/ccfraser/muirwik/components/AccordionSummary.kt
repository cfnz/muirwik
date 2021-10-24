package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/AccordionSummary")
private external val accordionSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionSummaryComponentType: ComponentType<AccordionSummaryProps> = accordionSummaryModule.default

external interface AccordionSummaryProps : StyledPropsWithCommonAttributes {
	var expandIcon: ReactElement
	var iconButtonProps: Props
}

fun RBuilder.accordionSummary(handler: StyledHandler<AccordionSummaryProps>) {
	createStyled(accordionSummaryComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAccordionSummary(
	expandIcon: ReactElement? = null,
	iconButtonProps: Props? = null,
	className: String? = null,
	handler: StyledHandler<AccordionSummaryProps>? = null
) {
	createStyled(accordionSummaryComponentType, className, handler) {
		expandIcon?.let { attrs.expandIcon = it }
		iconButtonProps?.let { attrs.iconButtonProps = it }
	}
}
