package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import react.RProps
import react.ReactElement
import styled.StyledHandler

@JsModule("@material-ui/core/AccordionSummary")
private external val accordionSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionSummaryComponentType: ComponentType<MAccordionSummaryProps> = accordionSummaryModule.default

external interface MAccordionSummaryProps : StyledPropsWithCommonAttributes {
	var expandIcon: ReactElement
	var iconButtonProps: RProps
}

fun RBuilder.mAccordionSummary(
		expandIcon: ReactElement? = null,
		iconButtonProps: RProps? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionSummaryProps>? = null) = createStyled(accordionSummaryComponentType) {
			expandIcon?.let { attrs.expandIcon = it }
			iconButtonProps?.let { attrs.iconButtonProps = it }
			setStyledPropsAndRunHandler(className, handler)
		}
