package com.ccfraser.muirwik.wrapper.table

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableSortLabel")
private external val tableSortLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableSortLabelComponent: RComponent<MTableSortLabelProps, RState> = tableSortLabelModule.default

interface MTableSortLabelProps : StyledProps {
    var active: Boolean
    var direction: String
    var onClick: (Event) -> Unit
}

enum class MTableSortLabelDirection {
    Asc, Desc;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

fun RBuilder.mTableSortLabel(
        active: Boolean = false,
        direction: MTableSortLabelDirection = MTableSortLabelDirection.Desc,
        onClick: ((event: Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MTableSortLabelProps>? = null) = createStyled(tableSortLabelComponent) {
    attrs.active = active
    attrs.direction = direction.toString()
    onClick?.let { attrs.onClick = onClick }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mTableSortLabel(
        label: String,
        active: Boolean = false,
        direction: MTableSortLabelDirection = MTableSortLabelDirection.Desc,
        onClick: ((event: Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MTableSortLabelProps>? = null) = createStyled(tableSortLabelComponent) {
    attrs.active = active
    attrs.direction = direction.toString()
    onClick?.let { attrs.onClick = onClick }

    childList.add(label)

    setStyledPropsAndRunHandler(className, handler)
}
