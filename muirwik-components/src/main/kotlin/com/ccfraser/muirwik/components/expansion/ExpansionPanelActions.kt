package com.ccfraser.muirwik.components.expansion

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/ExpansionPanelActions")
private external val expansionPanelActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelActionsComponent: RComponent<MExpansionPanelActionsProps, RState> = expansionPanelActionsModule.default

interface MExpansionPanelActionsProps : StyledProps
{
	var disableSpacing: Boolean?
}

fun RBuilder.mExpansionPanelActions(disableSpacing: Boolean? = null,
                                    className: String? = null,
                                    handler: StyledHandler<MExpansionPanelActionsProps>? = null) =
		createStyled(expansionPanelActionsComponent) {
			disableSpacing?.let { attrs.disableSpacing = it }
			setStyledPropsAndRunHandler(className, handler)
		}
