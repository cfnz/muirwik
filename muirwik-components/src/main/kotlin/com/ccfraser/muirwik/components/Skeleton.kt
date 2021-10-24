@file:Suppress("EnumEntryName")

package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import kotlinx.css.LinearDimension
import react.ComponentType
import react.PropsWithChildren
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/Skeleton")
private external val skeletonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val skeletonComponentType: ComponentType<SkeletonProps> = skeletonModule.default

enum class SkeletonAnimation {
    pulse, wave, none;
}

enum class SkeletonVariant {
    text, rect, circle;
}

external interface SkeletonProps : StyledPropsWithCommonAttributes, PropsWithChildren {
    var component: ElementType
}
var SkeletonProps.animation
    get() = if (this.asDynamic().animation == false) SkeletonAnimation.none else SkeletonAnimation.valueOf(this.asDynamic().animation as String)
    set(value) {
        this.asDynamic().animation = when(value) {
            SkeletonAnimation.none -> false
            else -> value.toString()
        }
    }
var SkeletonProps.height by LinearDimensionDelegate()
var SkeletonProps.variant by EnumPropToString(SkeletonVariant.values())
var SkeletonProps.width by LinearDimensionDelegate()

fun RBuilder.skeleton(
    width: LinearDimension? = null,
    height: LinearDimension? = null,
    variant: SkeletonVariant = SkeletonVariant.text,
    handler: StyledHandler<SkeletonProps>? = null,
) {
    createStyled(skeletonComponentType, handler) {
        width?.let { attrs.width = it }
        height?.let { attrs.height = it }
        attrs.variant = variant
    }
}
