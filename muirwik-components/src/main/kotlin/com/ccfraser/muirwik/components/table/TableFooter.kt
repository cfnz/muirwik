package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableFooter")
private external val tableFooterModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val TableFooterComponentType: ComponentType<MTableFooterProps> = tableFooterModule.default

external interface MTableFooterProps : StyledProps {
    var component: String
}

fun RBuilder.mTableFooter(
        component: String = "tfoot",

        className: String? = null,
        handler: StyledHandler<MTableFooterProps>? = null) = createStyled(TableFooterComponentType) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}
