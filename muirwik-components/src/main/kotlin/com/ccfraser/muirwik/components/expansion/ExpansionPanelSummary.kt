package com.ccfraser.muirwik.components.expansion

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.*
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/ExpansionPanelSummary")
private external val expansionPanelSummaryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelSummaryComponent: RComponent<MExpansionPanelSummaryProps, RState> = expansionPanelSummaryModule.default

interface MExpansionPanelSummaryProps : StyledProps
{
	var expandIcon: ReactElement?
	var iconButtonProps: RProps?
}

fun RBuilder.mExpansionPanelSummary(expandIcon: ReactElement? = null,
                                    iconButtonProps: RProps? = null,
                                    className: String? = null,
                                    handler: StyledHandler<MExpansionPanelSummaryProps>? = null) =
		createStyled(expansionPanelSummaryComponent) {
			expandIcon?.let { attrs.expandIcon = it }
			iconButtonProps?.let { attrs.iconButtonProps = it }
			setStyledPropsAndRunHandler(className, handler)
		}
