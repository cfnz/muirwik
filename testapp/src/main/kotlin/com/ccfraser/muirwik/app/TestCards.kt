package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.card.*
import com.ccfraser.muirwik.wrapper.transitions.TransitionDuration
import com.ccfraser.muirwik.wrapper.transitions.mCollapse
import kotlinext.js.js
import kotlinx.css.LinearDimension
import kotlinx.css.padding
import kotlinx.css.properties.*
import kotlinx.css.px
import react.*
import react.dom.p
import styled.css
import styled.styledDiv

class TestCards : RComponent<RProps, RState>() {
    private var count = 0
    private var leftExpanded = false
    private var rightExpanded = false

    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(16.px)
            }
            mGridContainer {
                mGridItem(md = MGridSize.Cells6, xs = MGridSize.Cells12) {
                    mCard {
                        mCardHeader(title = "Hello there, I am the title",
                                action = mIconButton("expand_more",
                                        onClick = { setState { leftExpanded = !leftExpanded; count++ }},
                                        addAsChild = false) {
                                    css {
                                        if (leftExpanded) transform.rotate(180.deg)
                                        else transform.rotate(0.deg)

                                        transition(::transform, 500.ms, Timing.easeInOut)
                                    }
                                }
                        )
                        mCollapse(show = leftExpanded) {
                            mCardContent {
                                mTypography(paragraph = true) {
                                    +"Heat 1/2 cup of the broth in a pot until simmering, add saffron and set aside for 10 minutes."
                                }
                            }
                        }
                    }
                }
                mGridItem(md = MGridSize.Cells6, xs = MGridSize.Cells12) {
                    mCard {
                        mCardHeader(title = "Hello there, I am the title", subHeader = "And my subtitle",
                                avatar = mAvatar(addAsChild = false) {+"R"},
                                action = mIconButton("more_vert", addAsChild = false)
                        )
                        mCardMedia(image = "/static/images/cards/paella.jpg") {
                            css { height = 150.px }
                        }
                        mCardContent {
                            mTypography(variant = MTypographyVariant.Display1) {
                                +"This impressive paella"
                            }
                            mTypography {
                                +"This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like."
                            }
                            mTypography(variant = MTypographyVariant.Body2) {
                                +"This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like."
                            }
                        }
                        mCardActions {
                            mIconButton("star")
                            mIconButton("send")

                            mIconButton("expand_more", onClick = { setState { rightExpanded = !rightExpanded; count++ } }) {
                                css {
                                    if (rightExpanded) transform.rotate(180.deg)
                                    else transform.rotate(0.deg)

                                    transition(::transform, 500.ms, Timing.easeInOut)
                                    marginLeft = LinearDimension.auto
                                }
                            }
                        }
                        mCollapse(show = rightExpanded) {
                            mCardContent {
                                mTypography(paragraph = true) {
                                    +"Heat 1/2 cup of the broth in a pot until simmering, add saffron and set aside for 10 minutes."
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testCards() = child(TestCards::class) {}
