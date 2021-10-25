package com.ccfraser.muirwik.components.lab.pagination

import com.ccfraser.muirwik.components.ElementType
import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/lab/PaginationItem")
private external val paginationModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paginationComponentType: ComponentType<MPaginationItemProps> = paginationModule.default

@Suppress("EnumEntryName")
enum class MPaginationItemColor {
  standard,
  primary,
  secondary
}

@Suppress("EnumEntryName")
enum class MPaginationItemShape {
  round,
  rounded
}

@Suppress("EnumEntryName")
enum class MPaginationItemSize {
  large,
  medium,
  small
}

@Suppress("EnumEntryName")
enum class MPaginationItemType {
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
enum class MPaginationItemVariant {
  text,
  outlined
}


external interface MPaginationItemProps : StyledPropsWithCommonAttributes {
  var component: ElementType
  var disabled: Boolean
  var page: Number
  var selected: Boolean
}

var MPaginationItemProps.color by EnumPropToString(MPaginationItemColor.values())
var MPaginationItemProps.shape by EnumPropToString(MPaginationItemShape.values())
var MPaginationItemProps.size by EnumPropToString(MPaginationItemSize.values())
var MPaginationItemProps.type by EnumPropToString(MPaginationItemType.values())
var MPaginationItemProps.variant by EnumPropToString(MPaginationItemVariant.values())

fun RBuilder.mPaginationItem(
    component: ElementType? = null,
    disabled: Boolean = false,
    page: Number? = null,
    selected: Boolean = false,
    color: MPaginationItemColor = MPaginationItemColor.standard,
    shape: MPaginationItemShape = MPaginationItemShape.round,
    size: MPaginationItemSize = MPaginationItemSize.medium,
    type: MPaginationItemType = MPaginationItemType.page,
    variant: MPaginationItemVariant = MPaginationItemVariant.text,
    className: String? = null,
    handler: StyledHandler<MPaginationItemProps>? = null
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