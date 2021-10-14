package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonColor
import com.ccfraser.muirwik.components.button.MButtonSize
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.transitions.mCollapse
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import react.*
import styled.css
import styled.styledDiv

class TestCards : RComponent<Props, State>() {
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
                        mCardHeader("Shrimp and Chorizo Paella", "September 14") {
                            attrs.avatar = buildElement {
                                mAvatar {
                                    css { backgroundColor = Colors.Red.shade500}
                                    +"R"
                                }
                            }
                            attrs.action = buildElement { mIconButton("more_vert") }
                        }
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
                            mIconButton("expand_more") {
                                css {
                                    if (rightExpanded) transform.rotate(180.deg)
                                    else transform.rotate(0.deg)

                                    transition("transform", 500.ms, Timing.easeInOut)
                                    css { put("marginLeft", "auto !important") }
                                }
                                attrs.onClick = { setState { rightExpanded = !rightExpanded; count++ } }
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
                        mCardActionArea {
                            attrs.onClick = { window.alert("You clicked the action area.") }
                            mCardMedia("/images/cards/contemplative-reptile.jpg", "Contemplative Reptile") {
                                css { height = 140.px }
                            }
                            mCardContent {
                                mTypography("Lizard", gutterBottom = true, variant = MTypographyVariant.h5, component = "h2")
                                mTypography("Lizards are a widespread group of squamate reptiles, with over 6,000 species, ranging \n" +
                                        "across all continents except Antarctica", component = "p")
                            }
                        }
                        mCardActions {
                            mButton("Share", MButtonColor.primary, size = MButtonSize.small)
                            mButton("Learn More", MButtonColor.primary, size = MButtonSize.small)
                        }
                    }
                }
                mGridItem(breakpoints) {
                    mCard(raised = true) {
                        mCardHeader("Expand for more info") {
                            attrs.action = buildElement {
                                mIconButton("expand_more") {
                                    css {
                                        if (leftExpanded) transform.rotate(180.deg)
                                        else transform.rotate(0.deg)

                                        transition("transform", 500.ms, Timing.easeInOut)
                                    }
                                    attrs.onClick = { setState { leftExpanded = !leftExpanded; count++ } }
                                }
                            }
                        }
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
