package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableHead")
private external val tableHeadModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableHeadComponentType: ComponentType<TableHeadProps> = tableHeadModule.default

external interface TableHeadProps : StyledProps {
    var component: ElementType
}

fun RBuilder.tableHead(handler: StyledHandler<TableHeadProps>) {
    createStyled(TableHeadComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableHead(
    component: String = "thead",
    className: String? = null,
    handler: StyledHandler<TableHeadProps>? = null
) {
    createStyled(TableHeadComponentType, className, handler) {
        attrs.component = component
    }
}
