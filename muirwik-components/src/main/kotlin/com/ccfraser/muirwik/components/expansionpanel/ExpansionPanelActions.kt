package com.ccfraser.muirwik.components.expansionpanel

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/core/ExpansionPanelActions")
private external val expansionPanelActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelActionsComponentType: ComponentType<MExpansionPanelActionsProps> = expansionPanelActionsModule.default

external interface MExpansionPanelActionsProps : StyledPropsWithCommonAttributes {
	var disableSpacing: Boolean
}

@Deprecated("Getting removed in Material-UI 5", ReplaceWith("mAccordionActions(disableSpacing, className, handler)",
		"com.ccfraser.muirwik.components.accordion.mAccordionActions"))
fun RBuilder.mExpansionPanelActions(disableSpacing: Boolean? = null,
                                    className: String? = null,
                                    handler: StyledHandler<MExpansionPanelActionsProps>? = null) =
		createStyled(expansionPanelActionsComponentType) {
			disableSpacing?.let { attrs.disableSpacing = it }
			setStyledPropsAndRunHandler(className, handler)
		}
