package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.ElementType
import react.Props
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/Accordion")
private external val accordionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val accordionComponentType: ComponentType<AccordionProps> = accordionModule.default

external interface AccordionProps : StyledPropsWithCommonAttributes {
    var defaultExpanded: Boolean
    var disabled: Boolean
    var disableGutters: Boolean
    var expanded: Boolean
    var onChange: (Event, Boolean) -> Unit
    var square: Boolean

    @JsName("TransitionProps")
    var transitionProps: Props?

    @JsName("TransitionComponent")
    var transitionComponent: ElementType<Props>
}


fun RBuilder.accordion(handler: StyledHandler<AccordionProps>) {
    createStyled(accordionComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAccordion(
    expanded: Boolean? = null,
    defaultExpanded: Boolean = false,
    disabled: Boolean = false,
    square: Boolean = false,
    onChange: ((Event, Boolean) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<AccordionProps>? = null
) {
    createStyled(accordionComponentType, className, handler) {
        attrs.defaultExpanded = defaultExpanded
        attrs.disabled = disabled
        attrs.square = square
        expanded?.let { attrs.expanded = it }
        onChange?.let { attrs.onChange = it }
    }
}

