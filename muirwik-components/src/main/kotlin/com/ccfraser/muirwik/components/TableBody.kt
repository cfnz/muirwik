package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableBody")
private external val tableBodyModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableBodyComponentType: ComponentType<TableBodyProps> = tableBodyModule.default

external interface TableBodyProps : StyledProps {
    var component: ElementType
}

fun RBuilder.tableBody(handler: StyledHandler<TableBodyProps>) {
    createStyled(tableBodyComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableBody(
    component: ElementType = "tbody",
    className: String? = null,
    handler: StyledHandler<TableBodyProps>? = null
) {
    createStyled(tableBodyComponentType, className, handler) {
        attrs.component = component
    }
}
