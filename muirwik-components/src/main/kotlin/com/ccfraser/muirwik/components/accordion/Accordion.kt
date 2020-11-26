package com.ccfraser.muirwik.components.accordion

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import com.ccfraser.muirwik.components.transitions.TransitionComponentDelegate
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/Accordion")
private external val accordionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionComponent: RComponent<MAccordionProps, RState> = accordionModule.default

external interface MAccordionProps : StyledPropsWithCommonAttributes {
	var defaultExpanded: Boolean
	var disabled: Boolean
	var expanded: Boolean
	var onChange: (Event, Boolean) -> Unit
	var square: Boolean

	@JsName("TransitionProps")
	var transitionProps: RProps?
}
var MAccordionProps.transitionComponent by TransitionComponentDelegate()


fun RBuilder.mAccordion(
		expanded: Boolean? = null,
		defaultExpanded: Boolean = false,
		disabled: Boolean = false,
		square: Boolean = false,
		onChange: ((Event, Boolean) -> Unit)? = null,
		className: String? = null,
		handler: StyledHandler<MAccordionProps>? = null) = createStyled(accordionComponent) {
			attrs.defaultExpanded = defaultExpanded
			attrs.disabled = disabled
			attrs.square = square
			expanded?.let { attrs.expanded = it }
			onChange?.let { attrs.onChange = it }
			setStyledPropsAndRunHandler(className, handler)
		}
