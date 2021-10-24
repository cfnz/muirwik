package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.SkeletonAnimation
import com.ccfraser.muirwik.components.SkeletonVariant
import com.ccfraser.muirwik.components.animation
import com.ccfraser.muirwik.components.skeleton
import kotlinx.css.px
import kotlinx.css.width
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.css

private val smallContainerWidth = 242.px

class TestSkeletons : RComponent<Props, State>() {

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Variants") {
                css {
                    width = smallContainerWidth
                }
                skeleton(variant = SkeletonVariant.text)
                skeleton(40.px, 40.px, variant = SkeletonVariant.circle)
                skeleton(210.px, 118.px, variant = SkeletonVariant.rect)
            }
            demoPanel("Animations") {
                css {
                    width = smallContainerWidth
                }
                skeleton { attrs.animation = SkeletonAnimation.none }
                skeleton { attrs.animation = SkeletonAnimation.wave }
                skeleton { attrs.animation = SkeletonAnimation.pulse }
            }
        }
    }
}
