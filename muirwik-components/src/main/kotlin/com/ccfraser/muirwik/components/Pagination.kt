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

@Suppress("EnumEntryName")
enum class PaginationColor {
  standard,
  primary,
  secondary
}

@Suppress("EnumEntryName")
enum class PaginationSize {
  large,
  medium,
  small
}

@Suppress("EnumEntryName")
enum class PaginationVariant {
  text,
  outlined
}

@Suppress("EnumEntryName")
enum class PaginationShape {
  round,
  rounded
}

external interface PaginationProps : StyledProps {
  var boundaryCount: Number
  var count: Number
  var defaultPage: Number
  var disabled: Boolean
  var getItemAriaLabel: (String, Number, Boolean) -> String
  var hideNextButton: Boolean
  var hidePrevButton: Boolean
  var onChange: (Event, Number) -> Unit
  var page: Number
  var renderItem: (Any/*PaginationRenderItemParams*/) -> ReactNode
  var showFirstButton: Boolean
  var showLastButton: Boolean
  var siblingCount: Number

}

var PaginationProps.color by EnumPropToString(PaginationColor.values())
var PaginationProps.shape by EnumPropToString(PaginationShape.values())
var PaginationProps.size by EnumPropToString(PaginationSize.values())
var PaginationProps.variant by EnumPropToString(PaginationVariant.values())

fun RBuilder.pagination(
    boundaryCount: Number = 1,
    count: Number = 1,
    defaultPage: Number = 1,
    disabled: Boolean = false,
    getItemAriaLabel: ((String, Number, Boolean) -> String)? = null,
    hideNextButton: Boolean = false,
    hidePrevButton: Boolean = false,
    onChange: ((Event, Number) -> Unit)? = null,
    page: Number? = null,
    renderItem: ((Any) -> ReactNode)? = null,
    showFirstButton: Boolean = false,
    showLastButton: Boolean = false,
    siblingCount: Number = 1,

    color: PaginationColor = PaginationColor.standard,
    shape: PaginationShape = PaginationShape.round,
    size: PaginationSize = PaginationSize.medium,
    variant: PaginationVariant = PaginationVariant.text,

    className: String? = null,
    handler: StyledHandler<PaginationProps>? = null
) {
  createStyled(paginationComponentType, className, handler) {
    attrs.boundaryCount = boundaryCount
    attrs.count = count
    attrs.defaultPage = defaultPage
    attrs.disabled = disabled
    getItemAriaLabel?.let { attrs.getItemAriaLabel = it }
    attrs.hideNextButton = hideNextButton
    attrs.hidePrevButton = hidePrevButton
    onChange?.let { attrs.onChange = it }
    page?.let { attrs.page = it }
    renderItem?.let { attrs.renderItem = it }
    attrs.showFirstButton = showFirstButton
    attrs.showLastButton = showLastButton
    attrs.siblingCount = siblingCount
    attrs.color = color
    attrs.shape = shape
    attrs.size = size
    attrs.variant = variant
  }
}