package com.ccfraser.muirwik.components.table

import com.ccfraser.muirwik.components.EnumPropToString
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

external interface MTableProps : StyledProps {
    var component: String

    /**
     * Material UI docs say this does not work with IE 11
     */
    var stickyHeader: Boolean
}
var MTableProps.padding by EnumPropToString(MTableCellPadding.values())
var MTableProps.size by EnumPropToString(MTableCellSize.values())

fun RBuilder.mTable(
        component: String = "table",

        className: String? = null,
        handler: StyledHandler<MTableProps>? = null) = createStyled(tableComponent) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}
