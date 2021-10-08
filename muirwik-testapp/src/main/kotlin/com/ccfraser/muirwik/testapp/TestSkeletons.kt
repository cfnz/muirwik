package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.lab.MSkeletonAnimation
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
import styled.css
import styled.styledDiv

private val smallContainerWidth = 242.px

class TestSkeletons : RComponent<Props, State>() {

  override fun RBuilder.render() {
    styledDiv {
      css {
        padding(16.px)
      }

      mTypography("Variants", MTypographyVariant.h5, gutterBottom = true)
      styledDiv {
        css {
          width = smallContainerWidth
        }
        mSkeleton(variant = MSkeletonVariant.text)
        mSkeleton(40.px, 40.px, variant = MSkeletonVariant.circle)
        mSkeleton(210.px, 118.px, variant = MSkeletonVariant.rect)
      }

      mTypography("Animations", MTypographyVariant.h5, gutterBottom = true)
      styledDiv {
        css {
          width = smallContainerWidth
        }
        mSkeleton(animation = MSkeletonAnimation.pulse)
        mSkeleton(animation = MSkeletonAnimation.none)
        mSkeleton(animation = MSkeletonAnimation.wave)
      }
    }
  }
}
