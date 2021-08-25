package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/TableContainer")
private external val tableContainerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableContainerComponentType: ComponentType<MTableContainerProps> = tableContainerModule.default

external interface MTableContainerProps : StyledPropsWithCommonAttributes {
    var component: String
}

fun RBuilder.mTableContainer(
    component: String? = "div",

    className: String? = null,
    handler: StyledHandler<MTableContainerProps>? = null
) {
    createStyled(tableContainerComponentType, className, handler) {
        component?.let { attrs.component = it }
    }
}
