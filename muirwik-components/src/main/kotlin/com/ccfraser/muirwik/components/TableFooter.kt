package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableFooter")
private external val tableFooterModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableFooterComponentType: ComponentType<TableFooterProps> = tableFooterModule.default

external interface TableFooterProps : StyledProps {
    var component: ElementType
}

fun RBuilder.tableFooter(handler: StyledHandler<TableFooterProps>) {
    createStyled(TableFooterComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableFooter(
    component: String = "tfoot",

    className: String? = null,
    handler: StyledHandler<TableFooterProps>? = null
) {
    createStyled(TableFooterComponentType, className, handler) {
        attrs.component = component
    }
}
