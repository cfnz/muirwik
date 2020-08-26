package com.ccfraser.muirwik.components.expansionpanel

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.*
import styled.StyledHandler

@JsModule("@material-ui/core/ExpansionPanelSummary")
private external val expansionPanelSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelSummaryComponent: RComponent<MExpansionPanelSummaryProps, RState> = expansionPanelSummaryModule.default

interface MExpansionPanelSummaryProps : StyledPropsWithCommonAttributes {
	var expandIcon: ReactElement
	var iconButtonProps: RProps
}

@Deprecated("Getting removed in Material-UI 5", ReplaceWith("mAccordionSummary(expandIcon, iconButtonProps, className, handler)",
		"com.ccfraser.muirwik.components.accordion.mAccordionSummary"))
fun RBuilder.mExpansionPanelSummary(expandIcon: ReactElement? = null,
                                    iconButtonProps: RProps? = null,
                                    className: String? = null,
                                    handler: StyledHandler<MExpansionPanelSummaryProps>? = null) =
		createStyled(expansionPanelSummaryComponent) {
			expandIcon?.let { attrs.expandIcon = it }
			iconButtonProps?.let { attrs.iconButtonProps = it }
			setStyledPropsAndRunHandler(className, handler)
		}
