package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/TableRow")
private external val tableRowModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableRowComponentType: ComponentType<TableRowProps> = tableRowModule.default

external interface TableRowProps : ButtonBaseProps {
    var hover: Boolean
    var key: Any
    var selected: Boolean
}


fun RBuilder.tableRow(key: Any? = null, selected: Boolean = false, handler: StyledHandler<TableRowProps>) {
    createStyled(tableRowComponentType, handler) {
        key?.let { attrs.key = key }
        attrs.selected = selected
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableRow(
    key: Any? = null,
    selected: Boolean = false,
    hover: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    component: String = "tr",
    className: String? = null,
    handler: StyledHandler<TableRowProps>? = null
) {
    createStyled(tableRowComponentType, className, handler) {
        key?.let { attrs.key = key }
        attrs.component = component
        attrs.hover = hover
        onClick?.let { attrs.onClick = it }
        attrs.selected = selected
    }
}
