package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.green
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.orange
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.pink
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.standard
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.Props
import react.State
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestAvatars : RComponent<Props, State>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val standard by css {
            margin(1.spacingUnits)
            color = Colors.white
        }
        val pink by css {
            margin(1.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.Pink.shade500
        }
        val green by css {
            margin(1.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.Green.shade500
        }
        val orange by css {
            margin(1.spacingUnits)
            color = Colors.white
            backgroundColor = Colors.DeepOrange.shade500
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Image Avatars") {
                css { display = Display.flex }
                mAvatar {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                }
                mAvatar {
                    css {
                        marginLeft = 2.spacingUnits
                        width = 60.px
                        height = 60.px
                    }
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                }
            }

            demoPanel("Icon Avatars") {
                css { display = Display.flex }
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

            demoPanel("Letter Avatars") {
                css { display = Display.flex }
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

            demoPanel("Variants") {
                css { display = Display.flex }
                mAvatar(MAvatarVariant.rounded) {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                    css { margin(1.spacingUnits) }
                }
                mAvatar(MAvatarVariant.square) {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                    css { margin(1.spacingUnits) }
                }
            }
        }
    }
}
