package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableFooter")
private external val tableFooterModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableFooterComponent: RComponent<MTableFooterProps, RState> = tableFooterModule.default

external interface MTableFooterProps : StyledProps {
    var component: String
}

fun RBuilder.mTableFooter(
        component: String = "tfoot",

        className: String? = null,
        handler: StyledHandler<MTableFooterProps>? = null) = createStyled(TableFooterComponent) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}
