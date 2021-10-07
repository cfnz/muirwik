package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.lab.MSkeletonVariant
import com.ccfraser.muirwik.components.lab.mSkeleton
import com.ccfraser.muirwik.components.mTypography
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.css.width
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import styled.css
import styled.styledDiv

class TestSkeletons : RComponent<Props, State>() {

  override fun RBuilder.render() {
    styledDiv {
      css {
        padding(16.px)
        //todo I need to define a width here as otherwise the first skeleton will use max-width.
        // Not sure why this behaviour is different from the example on MUI.
        width = 242.px
      }
      mTypography("Skeleton Variants", MTypographyVariant.h5, gutterBottom = true)
      div {
        mSkeleton(variant = MSkeletonVariant.text)
        mSkeleton(40, 40, variant = MSkeletonVariant.circle)
        mSkeleton(210, 118, variant = MSkeletonVariant.rect)

      }
    }
  }
}
