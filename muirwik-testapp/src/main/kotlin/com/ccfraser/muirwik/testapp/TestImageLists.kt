package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.icon
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.imageList
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.rootDiv
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.titleBar
import kotlinx.css.*
import react.*
import react.dom.img
import styled.StyleSheet
import styled.css


class TestImageLists : RComponent<Props, State>() {
    private data class TileData(val img: String, val title: String, val author: String, val rows: Int = 1, val cols: Int = 1, val featured: Boolean = false)
    private val tileData = listOf(
            TileData("breakfast.jpg", "Breakfast", "jill111", 2, 2, true),
            TileData("burgers.jpg", "Tasty Burger", "director90"),
            TileData("camera.jpg", "Camera", "Danson67"),
            TileData("morning.jpg", "Morning", "fancycrave1", 1, 2, true),
            TileData("hats.jpg", "Hats", "Hans", 1, 2),
            TileData("honey.jpg", "Honey", "fancycravel", 2, 2),
            TileData("vegetables.jpg", "Vegetables", "jill111"),
            TileData("plant.jpg", "Water Plant", "BkrmadtyaKarki"),
            TileData("mushroom.jpg", "Mushrooms", "PublicDomainPictures", 2, 2),
            TileData("olive.jpg", "Olive Oil", "congerdesign"),
            TileData("starfish.jpg", "Star Fish", "821292"),
            TileData("bike.jpg", "Bike", "danfador", 1, 2)
    )

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val rootDiv by css {
            display = Display.flex
            flexWrap = FlexWrap.wrap
            justifyContent = JustifyContent.flexStart
            overflow = Overflow.hidden
        }

        val imageList by css {
            width = 500.px
            height = 450.px
            margin(0.px)
        }

        val icon by css {
            color = rgba(255, 255, 255, 0.54)
        }

        val titleBar by css {
            put("background", "linear-gradient(to bottom, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.3) 70%, rgba(0,0,0,0) 100%)")
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Image Only Grid List") {
                css(rootDiv)
                imageList(4) {
                    css(imageList)
                    attrs.rowHeight = 121
                    attrs.variant = ImageListVariant.quilted
                    tileData.forEach {
                        imageListItem(it.cols, it.rows, it.img) {
                            img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                        }
                    }
                }
            }
            demoPanel("Grid List with Title Bars") {
                css(rootDiv)
                imageList {
                    attrs.rowHeight = 180
                    css(imageList)
                    imageListItem(2, 1,"Subheader") {
                        css { put("height", (6.spacingUnits).toString() + " !important") }
                        listSubheader("December") {
                            attrs.component = "div"
                            css { height = LinearDimension.auto }
                        }
                    }
                    tileData.forEach {
                        imageListItem(key = it.img) {
                            img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                            mImageListItemBar(it.title, "by ${it.author}",
                                buildElement { iconButton("info", color = IconButtonColor.inherit) {
                                    css(icon)
                                }}
                            )
                        }
                    }
                }
            }
            demoPanel("With Rows and Cols") {
                css(rootDiv)
                imageList {
                    attrs.rowHeight = 200
                    css(imageList)
                    tileData.forEach {
                        imageListItem(if (it.featured) 2 else 1, if (it.featured) 2 else 1, it.img) {
                            img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                            mImageListItemBar(
                                it.title,
                                position = ImageListItemBarPosition.top,
                                actionPosition = ActionPosition.left,
                                actionIcon = buildElement { iconButton("star-border", IconButtonColor.inherit) {
                                    css(icon)
                                }}
                            ) {
                                css(titleBar)
                            }
                        }
                    }
                }
            }
            demoPanel("Single Line List") {
                css(rootDiv)
                imageList(1) {
                    css(imageList)
                    attrs.rowHeight = 200
                    attrs.variant = ImageListVariant.quilted
                    tileData.forEach {
                        imageListItem(key = it.img) {
                            img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                        }
                    }
                }
            }
        }
    }
}
