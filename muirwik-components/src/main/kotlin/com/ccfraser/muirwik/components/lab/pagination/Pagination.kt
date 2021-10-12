package com.ccfraser.muirwik.components.lab.pagination

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler

@JsModule("@material-ui/lab/Pagination")
private external val paginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paginationComponentType: ComponentType<MPaginationProps> = paginationModule.default

@Suppress("EnumEntryName")
enum class MPaginationColor {
  standard,
  primary,
  secondary
}

@Suppress("EnumEntryName")
enum class MPaginationSize {
  large,
  medium,
  small
}

@Suppress("EnumEntryName")
enum class MPaginationVariant {
  text,
  outlined
}

@Suppress("EnumEntryName")
enum class MPaginationShape {
  round,
  rounded
}

external interface MPaginationProps : StyledPropsWithCommonAttributes {
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

var MPaginationProps.color by EnumPropToString(MPaginationColor.values())
var MPaginationProps.shape by EnumPropToString(MPaginationShape.values())
var MPaginationProps.size by EnumPropToString(MPaginationSize.values())
var MPaginationProps.variant by EnumPropToString(MPaginationVariant.values())

fun RBuilder.mPagination(
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

    color: MPaginationColor = MPaginationColor.standard,
    shape: MPaginationShape = MPaginationShape.round,
    size: MPaginationSize = MPaginationSize.medium,
    variant: MPaginationVariant = MPaginationVariant.text,

    className: String? = null,
    handler: StyledHandler<MPaginationProps>? = null
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