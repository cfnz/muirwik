package com.ccfraser.muirwik.components.expansion

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/ExpansionPanelDetails")
private external val expansionPanelDetailsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelDetailsComponent: RComponent<StyledProps, RState> = expansionPanelDetailsModule.default

fun RBuilder.mExpansionPanelDetails(className: String? = null,
                                    handler: StyledHandler<StyledProps>? = null) =
		createStyled(expansionPanelDetailsComponent) {
			setStyledPropsAndRunHandler(className, handler)
		}
