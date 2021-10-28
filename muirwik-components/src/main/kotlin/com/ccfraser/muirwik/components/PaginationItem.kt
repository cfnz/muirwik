package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/PaginationItem")
private external val paginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paginationComponentType: ComponentType<PaginationItemProps> = paginationModule.default

@Suppress("EnumEntryName")
enum class PaginationItemColor {
  standard,
  primary,
  secondary
}

@Suppress("EnumEntryName")
enum class PaginationItemShape {
  round,
  rounded
}

@Suppress("EnumEntryName")
enum class PaginationItemSize {
  large,
  medium,
  small
}

@Suppress("EnumEntryName")
enum class PaginationItemType {
  page,
  first,
  last,
  next,
  previous,
  startEllipsis,
  endEllipsis;

  override fun toString(): String {
    return when (this) {
      startEllipsis -> "start-ellipsis"
      endEllipsis   -> "end-ellipsis"
      else          -> this.name
    }
  }
}

@Suppress("EnumEntryName")
enum class PaginationItemVariant {
  text,
  outlined
}


external interface PaginationItemProps : StyledProps {
  var component: ElementType
  var disabled: Boolean
  var page: Number
  var selected: Boolean
}

var PaginationItemProps.color by EnumPropToString(PaginationItemColor.values())
var PaginationItemProps.shape by EnumPropToString(PaginationItemShape.values())
var PaginationItemProps.size by EnumPropToString(PaginationItemSize.values())
var PaginationItemProps.type by EnumPropToString(PaginationItemType.values())
var PaginationItemProps.variant by EnumPropToString(PaginationItemVariant.values())

fun RBuilder.paginationItem(
    component: ElementType? = null,
    disabled: Boolean = false,
    page: Number? = null,
    selected: Boolean = false,
    color: PaginationItemColor = PaginationItemColor.standard,
    shape: PaginationItemShape = PaginationItemShape.round,
    size: PaginationItemSize = PaginationItemSize.medium,
    type: PaginationItemType = PaginationItemType.page,
    variant: PaginationItemVariant = PaginationItemVariant.text,
    className: String? = null,
    handler: StyledHandler<PaginationItemProps>? = null
) {
  createStyled(paginationComponentType, className, handler) {
    component?.let { attrs.component = it }
    attrs.disabled = disabled
    page?.let { attrs.page = it }
    attrs.selected = selected
    attrs.color = color
    attrs.shape = shape
    attrs.size = size
    attrs.type = type
    attrs.variant = variant
  }
}