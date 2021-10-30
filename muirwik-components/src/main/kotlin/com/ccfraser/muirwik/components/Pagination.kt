package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/Pagination")
private external val paginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paginationComponentType: ComponentType<PaginationProps> = paginationModule.default

external interface PaginationProps : StyledProps {
    var boundaryCount: Int
    var count: Int
    var defaultPage: Int
    var disabled: Boolean
    var getItemAriaLabel: (type: String, page: Int, selected: Boolean) -> String
    var hideNextButton: Boolean
    var hidePrevButton: Boolean
    var onChange: (event: Event, page: Int) -> Unit
    var page: Int
    var renderItem: (Any/*PaginationRenderItemParams*/) -> ReactNode
    var showFirstButton: Boolean
    var showLastButton: Boolean
    var siblingCount: Int
}

var PaginationProps.color by EnumPropToString(PaginationItemColor.values())
var PaginationProps.shape by EnumPropToString(PaginationItemShape.values())
var PaginationProps.size by EnumPropToString(PaginationItemSize.values())
var PaginationProps.variant by EnumPropToString(PaginationItemVariant.values())

fun RBuilder.pagination(
    count: Int = 1,
    page: Int? = null,
    defaultPage: Int? = null,

    color: PaginationItemColor = PaginationItemColor.standard,
    shape: PaginationItemShape = PaginationItemShape.circular,
    size: PaginationItemSize = PaginationItemSize.medium,
    variant: PaginationItemVariant = PaginationItemVariant.text,

    handler: StyledHandler<PaginationProps>? = null
) {
    createStyled(paginationComponentType, handler) {
        attrs.count = count
        defaultPage?.let { attrs.defaultPage = it }
        page?.let { attrs.page = it }
        attrs.color = color
        attrs.shape = shape
        attrs.size = size
        attrs.variant = variant
    }
}