package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.utils.Colors
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
            gridContainer(GridSpacing.spacing2) {
                val breakpoints = GridBreakpoints(GridSize.cells6)
                        .up(Breakpoint.lg, GridSize.cells4)
                        .down(Breakpoint.sm, GridSize.cells12)

                gridItem(breakpoints) {
                    card {
                        cardHeader("Shrimp and Chorizo Paella", "September 14") {
                            attrs.avatar = buildElement {
                                avatar {
                                    css { backgroundColor = Colors.Red.shade500}
                                    +"R"
                                }
                            }
                            attrs.action = buildElement { iconButton("more_vert") }
                        }
                        cardMedia("/images/cards/paella.jpg") {
                            css { height = 150.px }
                        }
                        cardContent {
                            typography("This impressive paella is a perfect party dish and a fun meal to cook together " +
                                    "with your guests. Add 1 cup of frozen peas along with the mussels, if you like.")
                        }
                        cardActions {
                            iconButton("star")
                            iconButton("send")
                            iconButton("expand_more") {
                                css {
                                    if (rightExpanded) transform.rotate(180.deg)
                                    else transform.rotate(0.deg)

                                    transition("transform", 500.ms, Timing.easeInOut)
                                    put("marginLeft", "auto !important")
                                }
                                attrs.onClick = { setState { rightExpanded = !rightExpanded; count++ } }
                            }
                        }
                        collapse(show = rightExpanded) {
                            cardContent {
                                typography("Heat 1/2 cup of the broth in a pot until simmering, add saffron and set aside for 10 minutes.") {
                                    attrs.paragraph = true
                                }
                            }
                        }
                    }
                }
                gridItem(breakpoints) {
                    card {
                        cardActionArea {
                            attrs.onClick = { window.alert("You clicked the action area.") }
                            cardMedia("/images/cards/contemplative-reptile.jpg") {
                                css { height = 140.px }
                            }
                            cardContent {
                                typography("Lizard", variant = TypographyVariant.h5) {
                                    attrs.component = "h2"
                                    attrs.gutterBottom = true
                                }
                                typography(
                                    "Lizards are a widespread group of squamate reptiles, with over 6,000 species, ranging \n" +
                                            "across all continents except Antarctica"
                                ) {
                                    attrs.component = "p"
                                }
                            }
                        }
                        cardActions {
                            button("Share", ButtonColor.primary, size = ButtonSize.small)
                            button("Learn More", ButtonColor.primary, size = ButtonSize.small)
                        }
                    }
                }
                gridItem(breakpoints) {
                    card(raised = true) {
                        cardHeader("Expand for more info") {
                            attrs.action = buildElement {
                                iconButton("expand_more") {
                                    css {
                                        if (leftExpanded) transform.rotate(180.deg)
                                        else transform.rotate(0.deg)

                                        transition("transform", 500.ms, Timing.easeInOut)
                                    }
                                    attrs.onClick = { setState { leftExpanded = !leftExpanded; count++ } }
                                }
                            }
                        }
                        collapse(show = leftExpanded) {
                            cardContent {
                                typography("This is a raised card. As well as being a card demo, this shows use " +
                                        "of the Grid component. Resize the window to see the responsive positioning of " +
                                        "the items") {
                                    attrs.paragraph = true
                                }
                            }
                        }
                    }
                }
                gridItem(breakpoints) {
                    card {
                        css { display = Display.flex }
                        styledDiv {
                            css {
                                display = Display.flex
                                flexDirection = FlexDirection.column
                                flexGrow = 1.0
                            }
                            cardContent {
                                css { flex(1.0, 0.0, FlexBasis.auto) }
                                typography("Live From Space", variant = TypographyVariant.h5)
                                typography("Mac Miller", variant = TypographyVariant.subtitle1, color = TypographyColor.textSecondary)
                            }
                            styledDiv {
                                css {
                                    display = Display.flex
                                    alignItems = Align.center
                                    paddingLeft = 1.spacingUnits
                                    paddingBottom = 1.spacingUnits
                                }
                                iconButton("skip_previous")

                                // Demo was using an svg icon, so could easily change the size...we will keep it the same for now
                                iconButton("play_arrow")

                                iconButton("skip_next")
                            }
                        }
                        cardMedia("/images/cards/live-from-space.jpg") {
                            css { height = 151.px; width = 151.px }
                        }
                    }
                }
            }
        }
    }
}
