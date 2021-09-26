package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableBody")
private external val tableBodyModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableBodyComponentType: ComponentType<MTableBodyProps> = tableBodyModule.default

external interface MTableBodyProps : StyledProps {
    var component: String
}

fun RBuilder.mTableBody(
    component: String = "tbody",
    className: String? = null,
    handler: StyledHandler<MTableBodyProps>? = null
) {
    createStyled(tableBodyComponentType, className, handler) {
        attrs.component = component
    }
}
