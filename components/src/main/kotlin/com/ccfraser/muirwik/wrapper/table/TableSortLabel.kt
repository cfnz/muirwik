package com.ccfraser.muirwik.wrapper.table

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/TableSortLabel")
private external val tableSortLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tableSortLabelComponent: RComponent<MTableSortLabelProps, RState> = tableSortLabelModule.default

interface MTableSortLabelProps : StyledProps {
    var active: Boolean
    var direction: String

    @JsName("IconComponent")
    var iconFunction: (() -> ReactElement)?

    var onClick: (Event) -> Unit
}

@Suppress("EnumEntryName")
enum class MTableSortLabelDirection {
    asc, desc
}

fun RBuilder.mTableSortLabel(
        active: Boolean = false,
        direction: MTableSortLabelDirection = MTableSortLabelDirection.desc,
        onClick: ((event: Event) -> Unit)? = null,
        iconFunction: (() -> ReactElement)? = null,
        className: String? = null,
        handler: StyledHandler<MTableSortLabelProps>? = null) = createStyled(tableSortLabelComponent) {
    attrs.active = active
    attrs.direction = direction.toString()
    iconFunction?.let { attrs.iconFunction = iconFunction }
    onClick?.let { attrs.onClick = onClick }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mTableSortLabel(
        label: String,
        active: Boolean = false,
        direction: MTableSortLabelDirection = MTableSortLabelDirection.desc,
        onClick: ((event: Event) -> Unit)? = null,
        iconFunction: (() -> ReactElement)? = null,

        className: String? = null,
        handler: StyledHandler<MTableSortLabelProps>? = null) = createStyled(tableSortLabelComponent) {
    attrs.active = active
    attrs.direction = direction.toString()
    iconFunction?.let { attrs.iconFunction = iconFunction }
    onClick?.let { attrs.onClick = onClick }

    childList.add(label)

    setStyledPropsAndRunHandler(className, handler)
}
