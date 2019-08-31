package com.ccfraser.muirwik.components.expansion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/ExpansionPanel")
private external val expansionPanelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val expansionPanelComponent: RComponent<MExpansionPanelProps, RState> = expansionPanelModule.default

interface MExpansionPanelProps : StyledPropsWithCommonAttributes
{
	var defaultExpanded: Boolean
	var disabled: Boolean
	var expanded: Boolean
	var onChange: (Event, Boolean) -> Unit
}

fun RBuilder.mExpansionPanel(defaultExpanded: Boolean? = null,
                             disabled: Boolean? = null,
                             expanded: Boolean? = null,
                             onChange: ((Event, Boolean) -> Unit)? = null,
                             className: String? = null,
                             handler: StyledHandler<MExpansionPanelProps>? = null) = createStyled(expansionPanelComponent) {
	defaultExpanded?.let { attrs.defaultExpanded = it }
	disabled?.let { attrs.disabled = it }
	expanded?.let { attrs.expanded = it }
	onChange?.let { attrs.onChange = it }
	setStyledPropsAndRunHandler(className, handler)
}
