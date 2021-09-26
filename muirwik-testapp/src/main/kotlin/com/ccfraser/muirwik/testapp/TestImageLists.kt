package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MIconColor
import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.imagelist.*
import com.ccfraser.muirwik.components.list.mListSubheader
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.icon
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.imageList
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.rootDiv
import com.ccfraser.muirwik.testapp.TestImageLists.ComponentStyles.titleBar
import kotlinx.css.*
import react.*
import react.dom.img
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.styledImg


class TestImageLists : RComponent<Props, State>() {
    private data class TileData(val img: String, val title: String, val author: String, val cols: Int = 1, val featured: Boolean = false)
    private val tileData = listOf(
            TileData("breakfast.jpg", "Breakfast", "jill111", 2, true),
            TileData("burgers.jpg", "Tasty Burger", "director90"),
            TileData("camera.jpg", "Camera", "Danson67"),
            TileData("morning.jpg", "Morning", "fancycrave1", 1, true),
            TileData("hats.jpg", "Hats", "Hans"),
            TileData("honey.jpg", "Honey", "fancycravel"),
            TileData("vegetables.jpg", "Vegetables", "jill111", 2),
            TileData("plant.jpg", "Water Plant", "BkrmadtyaKarki"),
            TileData("mushroom.jpg", "Mushrooms", "PublicDomainPictures"),
            TileData("olive.jpg", "Olive Oil", "congerdesign"),
            TileData("starfish.jpg", "Star Fish", "821292", 2),
            TileData("bike.jpg", "Bike", "danfador")
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
        }

        val icon by css {
            color = rgba(255, 255, 255, 0.54)
        }

        val titleBar by css {
            put("background", "linear-gradient(to bottom, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.3) 70%, rgba(0,0,0,0) 100%)")
        }
    }

    override fun RBuilder.render() {
        mTypography("Image Only Grid List", MTypographyVariant.h5)
        styledDiv {
            css(rootDiv)
            mImageList(3, cellHeight = 160) {
                css(imageList)
                tileData.forEach {
                    mGridImage(key = it.img, cols = it.cols) {
                        img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                    }
                }
            }
        }

        mTypography("Grid List with Title Bars", MTypographyVariant.h5) { css { paddingTop = 3.spacingUnits }}
        styledDiv {
            css(rootDiv)
            mImageList(cellHeight = 180) {
                css(imageList)
                mGridImage("Subheader", 2) {
                    css { put("height", (6.spacingUnits).toString() + " !important") }
                    mListSubheader("December", component = "div") {
                        css { height = LinearDimension.auto }
                    }
                }
                tileData.forEach {
                    mGridImage(key = it.img) {
                        img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                        mImageListItemBar(it.title, "by ${it.author}",
                            buildElement { mIconButton("info", iconColor = MIconColor.inherit) {
                                css(icon)
                            }}
                        )
                    }
                }
            }
        }

        mTypography("With Rows and Cols", MTypographyVariant.h5) { css { paddingTop = 3.spacingUnits }}
        styledDiv {
            css(rootDiv)
            mImageList(cellHeight = 200, spacing = 1) {
                css(imageList)
                tileData.forEach {
                    mGridImage(key = it.img, cols = if (it.featured) 2 else 1, rows = if (it.featured) 2 else 1) {
                        img(src = "/images/grid-list/${it.img}", alt = it.title) {}
                        mImageListItemBar(
                            it.title,
                            titlePosition = MTitlePosition.top,
                            actionPosition = MActionPosition.left,
                            actionIcon = buildElement { mIconButton("star-border", iconColor = MIconColor.inherit) {
                                css(icon)
                            }}
                        ) {
                            css(titleBar)
                        }
                    }
                }
            }
        }

        mTypography("Single Line List", MTypographyVariant.h5) { css { paddingTop = 3.spacingUnits }}
        styledDiv {
            css(rootDiv)
            mImageList(cellHeight = 200, cols = 2.5) {
                css {
                    flexWrap = FlexWrap.nowrap
                }
                tileData.forEach {
                    mGridImage(key = it.img) {
                        styledImg(src = "/images/grid-list/${it.img}", alt = it.title) {
//                            css { width = 100.pct }
                        }
                    }
                }
            }
        }
    }
}
