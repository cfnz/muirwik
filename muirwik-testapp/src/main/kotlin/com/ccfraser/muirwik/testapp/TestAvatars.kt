package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.container
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.green
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.orange
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.pink
import com.ccfraser.muirwik.testapp.TestAvatars.ComponentStyles.standard
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.StyleSheet
import styled.css

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
        val container by css {
            display = Display.flex
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Image Avatars") {
                css(container)
                avatar {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                }
                avatar {
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
                css(container)
                avatar {
                    css(standard)
                    icon("folder")
                }
                avatar {
                    css(pink)
                    icon("pageview")
                }
                avatar {
                    css(green)
                    icon("assignment")
                }
            }
            demoPanel("Letter Avatars") {
                css(container)
                avatar {
                    css(green)
                    +"H"
                }
                avatar {
                    css(orange)
                    +"N"
                }
                avatar {
                    css(pink)
                    +"OP"
                }
            }
            demoPanel("Variants") {
                css(container)
                avatar(AvatarVariant.rounded) {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                    css { margin(1.spacingUnits) }
                }
                avatar(AvatarVariant.square) {
                    attrs.src = "/images/cards/contemplative-reptile.jpg"
                    attrs.alt = "Reptile"
                    css { margin(1.spacingUnits) }
                }
            }
            demoPanel("Groups") {
                css(container)
                avatarGroup(4) {
                    avatar("/images/grid-list/plant.jpg", "Plant")
                    avatar("/images/grid-list/burgers.jpg", "Burger")
                    avatar("/images/grid-list/starfish.jpg", "Starfish")
                    avatar("/images/grid-list/hats.jpg", "Hats")
                    avatar("/images/grid-list/olive.jpg", "Olive")
                    avatar("/images/grid-list/honey.jpg", "Honey")
                }
            }
        }
    }
}
