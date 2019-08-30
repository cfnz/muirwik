package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonSize
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.transitions.mCollapse
import kotlinx.css.*
import kotlinx.css.properties.*
import react.*
import styled.css
import styled.styledDiv
import kotlin.browser.window

class TestCards : RComponent<RProps, RState>() {
    private var count = 0
    private var leftExpanded = false
    private var rightExpanded = false

    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(16.px)
            }
            mGridContainer(MGridSpacing.spacing2) {
                val breakpoints = MGridBreakpoints(MGridSize.cells6)
                        .up(Breakpoint.lg, MGridSize.cells4)
                        .down(Breakpoint.sm, MGridSize.cells12)

                mGridItem(breakpoints) {
                    mCard {
                        mCardHeader(title = "Shrimp and Chorizo Paella", subHeader = "September 14",
                                avatar = mAvatar(addAsChild = false) {+"R"},
                                action = mIconButton("more_vert", addAsChild = false)
                        )
                        mCardMedia("/images/cards/paella.jpg") {
                            css { height = 150.px }
                        }
                        mCardContent {
                            mTypography {
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

                                    transition("transform", 500.ms, Timing.easeInOut)
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
                mGridItem(breakpoints) {
                    mCard {
                        mCardActionArea(onClick = { window.alert("You clicked the action area.") }) {
                            mCardMedia("/images/cards/contemplative-reptile.jpg", "Contemplative Reptile") {
                                css { height = 140.px }
                            }
                            mCardContent {
                                mTypography("Lizard", gutterBottom = true, variant = MTypographyVariant.h5, component = "h2")
                                mTypography("Lizards are a widespread group of squamate reptiles, with over 6,000 species, ranging\n" +
                                        "            across all continents except Antarctica", component = "p")
                            }
                        }
                        mCardActions {
                            mButton("Share", MColor.primary, size = MButtonSize.small)
                            mButton("Learn More", MColor.primary, size = MButtonSize.small)
                        }
                    }
                }
                mGridItem(breakpoints) {
                    mCard(raised = true) {
                        mCardHeader(title = "Expand for more info",
                                action = mIconButton("expand_more",
                                        onClick = { setState { leftExpanded = !leftExpanded; count++ }},
                                        addAsChild = false) {
                                    css {
                                        if (leftExpanded) transform.rotate(180.deg)
                                        else transform.rotate(0.deg)

                                        transition("transform", 500.ms, Timing.easeInOut)
                                    }
                                }
                        )
                        mCollapse(show = leftExpanded) {
                            mCardContent {
                                mTypography("This is a raised card. As well as being a card demo, this shows use " +
                                        "of the Grid component. Resize the window to see the responsive positioning of " +
                                        "the items", paragraph = true)
                            }
                        }
                    }
                }
                mGridItem(breakpoints) {
                    mCard {
                        css { display = Display.flex }
                        styledDiv {
                            css {
                                display = Display.flex
                                flexDirection = FlexDirection.column
                                flexGrow = 1.0
                            }
                            mCardContent {
                                css { flex(1.0, 0.0, FlexBasis.auto) }
                                mTypography("Live From Space", variant = MTypographyVariant.h5)
                                mTypography("Mac Miller", variant = MTypographyVariant.subtitle1, color = MTypographyColor.textSecondary)
                            }
                            styledDiv {
                                css {
                                    display = Display.flex
                                    alignItems = Align.center
                                    paddingLeft = 1.spacingUnits
                                    paddingBottom = 1.spacingUnits
                                }
                                mIconButton("skip_previous")

                                // Demo was using an svg icon, so could easily change the size...we will keep it the same for now
                                mIconButton("play_arrow")

                                mIconButton("skip_next")
                            }
                        }
                        mCardMedia("/images/cards/live-from-space.jpg", "Live from space album cover") {
                            css { css { height = 151.px; width = 151.px } }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testCards() = child(TestCards::class) {}
