package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Table")
private external val tableModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableComponent: RComponent<MTableProps, RState> = tableModule.default

interface MTableProps : StyledProps {
    var component: String
}

fun RBuilder.mTable(
        component: String = "table",

        className: String? = null,
        handler: StyledHandler<MTableProps>? = null) = createStyled(tableComponent) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}
