package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TableHead")
private external val tableHeadModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableHeadComponentType: ComponentType<MTableHeadProps> = tableHeadModule.default

external interface MTableHeadProps : StyledProps {
    var component: String
}

fun RBuilder.mTableHead(
    component: String = "thead",
    className: String? = null,
    handler: StyledHandler<MTableHeadProps>? = null
) {
    createStyled(TableHeadComponentType, className, handler) {
        attrs.component = component
    }
}
