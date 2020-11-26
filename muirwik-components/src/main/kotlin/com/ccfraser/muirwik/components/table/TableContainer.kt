package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/TableContainer")
private external val tableContainerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableContainerComponent: RComponent<MTableContainerProps, RState> = tableContainerModule.default

external interface MTableContainerProps : StyledPropsWithCommonAttributes {
    var component: String
}

fun RBuilder.mTableContainer(
        component: String? = "div",

        className: String? = null,
        handler: StyledHandler<MTableContainerProps>? = null) = createStyled(tableContainerComponent) {
    component?.let { attrs.component = it }

    setStyledPropsAndRunHandler(className, handler)
}
