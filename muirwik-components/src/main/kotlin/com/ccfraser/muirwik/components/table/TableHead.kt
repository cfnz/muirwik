package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableHead")
private external val tableHeadModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableHeadComponent: RComponent<MTableHeadProps, RState> = tableHeadModule.default

external interface MTableHeadProps : StyledProps {
    var component: String
}

fun RBuilder.mTableHead(
        component: String = "thead",

        className: String? = null,
        handler: StyledHandler<MTableHeadProps>? = null) = createStyled(TableHeadComponent) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}
