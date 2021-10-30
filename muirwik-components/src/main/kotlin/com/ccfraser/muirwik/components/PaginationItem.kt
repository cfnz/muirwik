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
    primary,
    secondary,
    standard
}

@Suppress("EnumEntryName")
enum class PaginationItemShape {
    circular,
    rounded
}

@Suppress("EnumEntryName")
enum class PaginationItemSize {
    small,
    medium,
    large
}

@Suppress("EnumEntryName")
enum class PaginationItemType {
    endEllipsis,
    first,
    last,
    next,
    page,
    previous,
    startEllipsis;

    override fun toString(): String {
        return when (this) {
            startEllipsis -> "start-ellipsis"
            endEllipsis -> "end-ellipsis"
            else -> this.name
        }
    }
}

@Suppress("EnumEntryName")
enum class PaginationItemVariant {
    outlined,
    text
}


external interface PaginationItemProps : StyledProps {
    var component: ElementType
    var disabled: Boolean
    var page: Int
    var selected: Boolean
}

var PaginationItemProps.color by EnumPropToString(PaginationItemColor.values())
var PaginationItemProps.shape by EnumPropToString(PaginationItemShape.values())
var PaginationItemProps.size by EnumPropToString(PaginationItemSize.values())
var PaginationItemProps.type by EnumPropToString(PaginationItemType.values())
var PaginationItemProps.variant by EnumPropToString(PaginationItemVariant.values())


fun RBuilder.paginationItem(
    page: Int? = null,
    selected: Boolean = false,

    color: PaginationItemColor = PaginationItemColor.standard,
    shape: PaginationItemShape = PaginationItemShape.circular,
    size: PaginationItemSize = PaginationItemSize.medium,
    type: PaginationItemType = PaginationItemType.page,
    variant: PaginationItemVariant = PaginationItemVariant.text,

    handler: StyledHandler<PaginationItemProps>? = null
) {
    createStyled(paginationComponentType, handler) {
        page?.let { attrs.page = it }
        attrs.selected = selected
        attrs.color = color
        attrs.shape = shape
        attrs.size = size
        attrs.type = type
        attrs.variant = variant
    }
}