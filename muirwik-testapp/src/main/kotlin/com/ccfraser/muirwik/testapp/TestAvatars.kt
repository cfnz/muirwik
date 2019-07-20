package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.green
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.orange
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.pink
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.standard
import kotlinx.css.Display
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestAvatars : RComponent<RProps, RState>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val standard by css {
            margin(2.spacingUnits)
            color = Colors.white
        }
        val pink by css {
            margin(2.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.Pink.shade500
        }
        val green by css {
            margin(2.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.Green.shade500
        }
        val orange by css {
            margin(2.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.DeepOrange.shade500
        }
    }


    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(16.px)
            }
            mTypography("Image Avatars", MTypographyVariant.h5)
            styledDiv {
                css { display = Display.flex; marginBottom = 3.spacingUnits }
                mAvatar(src = "/images/cards/contemplative-reptile.jpg", alt = "Reptile") {
                    css { margin(2.spacingUnits) }
                }
                mAvatar(src = "/images/cards/contemplative-reptile.jpg", alt = "Reptile") {
                    css {
                        margin(2.spacingUnits)
                        width = 60.px
                        height = 60.px
                    }
                }
            }

            mTypography("Icon Avatars", MTypographyVariant.h5)
            styledDiv {
                css { display = Display.flex; marginBottom = 3.spacingUnits }
                mAvatar {
                    css(standard)
                    mIcon("folder")
                }
                mAvatar {
                    css(pink)
                    mIcon("pageview")
                }
                mAvatar {
                    css(green)
                    mIcon("assignment")
                }
            }

            mTypography("Letter Avatars", MTypographyVariant.h5)
            styledDiv {
                css { display = Display.flex; marginBottom = 3.spacingUnits }
                mAvatar {
                    css(green)
                    +"H"
                }
                mAvatar {
                    css(orange)
                    +"N"
                }
                mAvatar {
                    css(pink)
                    +"OP"
                }
            }
        }
    }
}

fun RBuilder.testAvatars() = child(TestAvatars::class) {}
