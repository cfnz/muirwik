package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/TableRow")
private external val tableRowModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableRowComponentType: ComponentType<MTableRowProps> = tableRowModule.default

external interface MTableRowProps : MButtonBaseProps {
    var hover: Boolean
    var key: Any
    var selected: Boolean
}

fun RBuilder.mTableRow(
    key: Any? = null,
    selected: Boolean = false,
    hover: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    component: String = "tr",
    className: String? = null,
    handler: StyledHandler<MTableRowProps>? = null
) {
    createStyled(tableRowComponentType, className, handler) {
        key?.let { attrs.key = key }
        attrs.component = component
        attrs.hover = hover
        onClick?.let { attrs.onClick = it }
        attrs.selected = selected
    }
}
