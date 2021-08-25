package com.ccfraser.muirwik.components.expansionpanel

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@material-ui/core/ExpansionPanelSummary")
private external val expansionPanelSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelSummaryComponentType: ComponentType<MExpansionPanelSummaryProps> = expansionPanelSummaryModule.default

external interface MExpansionPanelSummaryProps : StyledPropsWithCommonAttributes {
	var expandIcon: ReactElement
	var iconButtonProps: Props
}

@Deprecated("Getting removed in Material-UI 5", ReplaceWith("mAccordionSummary(expandIcon, iconButtonProps, className, handler)",
		"com.ccfraser.muirwik.components.accordion.mAccordionSummary"))
fun RBuilder.mExpansionPanelSummary(
	expandIcon: ReactElement? = null,
	iconButtonProps: Props? = null,
	className: String? = null,
	handler: StyledHandler<MExpansionPanelSummaryProps>? = null
) {
	createStyled(expansionPanelSummaryComponentType, className, handler) {
		expandIcon?.let { attrs.expandIcon = it }
		iconButtonProps?.let { attrs.iconButtonProps = it }
	}
}
