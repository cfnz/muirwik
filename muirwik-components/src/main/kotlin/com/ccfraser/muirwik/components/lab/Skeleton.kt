@file:Suppress("EnumEntryName")

package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.ElementType
import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import kotlinx.css.LinearDimension
import react.ComponentType
import react.PropsWithChildren
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/lab/Skeleton")
private external val skeletonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val skeletonComponentType: ComponentType<MSkeletonProps> = skeletonModule.default

enum class MSkeletonAnimation {
  pulse,
  wave,
  none;

  override fun toString(): String {
    return when (this) {
      pulse -> "pulse"
      wave  -> "wave"
      none  -> "false"
    }
  }
}

enum class MSkeletonVariant {
  text,
  rect,
  circle;
}

external interface MSkeletonProps : StyledPropsWithCommonAttributes, PropsWithChildren {

  var component: ElementType
  var height: String
  var width: String
}

var MSkeletonProps.variant by EnumPropToString(MSkeletonVariant.values())
var MSkeletonProps.animation by EnumPropToString(MSkeletonAnimation.values())

fun RBuilder.mSkeleton(
    width: LinearDimension? = null,
    height: LinearDimension? = null,
    variant: MSkeletonVariant = MSkeletonVariant.text,
    animation: MSkeletonAnimation = MSkeletonAnimation.pulse,
    component: ElementType? = null,
    className: String? = null,
    handler: StyledHandler<MSkeletonProps>? = null,
) {
  createStyled(skeletonComponentType, className = className, handler) {
    component?.let { attrs.component = it }
    width?.let { attrs.width = it.toString() }
    height?.let { attrs.height = it.toString() }
    attrs.variant = variant
    attrs.animation = animation
  }
}