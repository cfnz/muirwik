package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Table")
private external val tableModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableComponentType: ComponentType<TableProps> = tableModule.default

external interface TableProps : StyledProps {
    var component: ElementType

    /**
     * Material UI docs say this does not work with IE 11
     */
    var stickyHeader: Boolean
}
var TableProps.padding by EnumPropToString(TableCellPadding.values())
var TableProps.size by EnumPropToString(TableCellSize.values())


fun RBuilder.table(handler: StyledHandler<TableProps>) {
    createStyled(tableComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTable(
    component: ElementType = "table",
    className: String? = null,
    handler: StyledHandler<TableProps>? = null
) {
    createStyled(tableComponentType, className, handler) {
        attrs.component = component
    }
}
