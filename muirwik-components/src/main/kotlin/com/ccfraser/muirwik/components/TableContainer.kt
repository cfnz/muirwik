package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/TableContainer")
private external val tableContainerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableContainerComponentType: ComponentType<MTableContainerProps> = tableContainerModule.default

external interface MTableContainerProps : StyledPropsWithCommonAttributes {
    var component: ElementType
}

fun RBuilder.tableContainer(handler: StyledHandler<MTableContainerProps>) {
    createStyled(tableContainerComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mTableContainer(
    component: String? = "div",

    className: String? = null,
    handler: StyledHandler<MTableContainerProps>? = null
) {
    createStyled(tableContainerComponentType, className, handler) {
        component?.let { attrs.component = it }
    }
}
