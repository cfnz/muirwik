package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.alpha
import com.ccfraser.muirwik.components.styles.up
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestAppBar : RComponent<Props, State>() {
    var loggedIn = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val searchIcon by css {
            width = 9.spacingUnits
            height = 100.pct
            position = Position.absolute
            pointerEvents = PointerEvents.none
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }
    }

    override fun RBuilder.render() {
        typography("This demo shows the AppBar and Toolbar components")

        themeContext.Consumer { theme: Theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val search by css {
                    position = Position.relative
                    borderRadius = theme.shape.borderRadius.px
                    backgroundColor = Color(alpha(theme.palette.common.white, 0.15))
                    hover {
                        backgroundColor = Color(alpha(theme.palette.common.white, 0.25))
                    }
                    marginLeft = 0.px
                    width = 100.pct
                    media(theme.breakpoints.up(Breakpoint.sm)) {
                        marginLeft = 1.spacingUnits
                        width = LinearDimension.auto
                    }
                }

                val inputInput by css {
                    paddingTop = 1.spacingUnits
                    paddingRight = 1.spacingUnits
                    paddingBottom = 1.spacingUnits
                    paddingLeft = 10.spacingUnits
                    transition += Transition("width", theme.transitions.duration.standard.ms, Timing.easeInOut, 0.ms)
                    width = 100.pct
                    media(theme.breakpoints.up(Breakpoint.sm)) {
                        width = 120.px
                        focus {
                            width = 200.px
                        }
                    }
                }
            }

            styledDiv {
                css { maxWidth = 800.px }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        attrs.color = AppBarColor.default
                        toolbar {
                            typography("Title", variant = TypographyVariant.h6, color = TypographyColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar {
                            iconButton("menu", color = IconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            typography("Title", variant = TypographyVariant.h6, color = TypographyColor.inherit) {
                                css { flexGrow = 1.0 }
                            }
                            if (loggedIn) {
                                iconButton ("account_circle", color = IconButtonColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                button("Login", color = ButtonColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar {
                            iconButton("menu", color = IconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            toolbarTitle("Toolbar Title One Liner")
                            if (loggedIn) {
                                iconButton ("account_circle", color = IconButtonColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                button("Login", color = ButtonColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar {
                            attrs.disableGutters = true
                            iconButton("menu", color = IconButtonColor.inherit)
                            toolbarTitle("Toolbar No Gutters")
                            iconButton ("account_circle", color = IconButtonColor.inherit )
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar {
                            iconButton("menu", color = IconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            toolbarTitle("Toolbar with Search")
                            styledDiv {
                                css(themeStyles.search)
                                styledDiv {
                                    css(ComponentStyles.searchIcon)
                                    icon("search")
                                }

                                val inputProps = js("({})")
                                inputProps.className = "${ComponentStyles.name}-inputInput"

                                input {
                                    attrs.placeholder = "Search..."
                                    attrs.disableUnderline = true
                                    attrs.inputProps = inputProps
                                    css {
                                        color = Color.inherit
                                    }
                                }
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar(ToolbarVariant.dense) {
                            iconButton("menu", color = IconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            toolbarTitle("Toolbar dense (for Desktop use)")
                            button("Login", color = ButtonColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        toolbar(ToolbarVariant.dense) {
                            attrs.disableGutters = true
                            iconButton("menu", color = IconButtonColor.inherit)
                            toolbarTitle("Toolbar dense and no gutters")
                            iconButton ("account_circle", color = IconButtonColor.inherit )
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    appBar(AppBarPosition.static) {
                        attrs.elevation = 0
                        toolbar {
                            iconButton("menu", color = IconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            toolbarTitle("Flat (elevation = 0)")
                            iconButton ("account_circle", color = IconButtonColor.inherit )
                        }
                    }
                }
            }
        }
    }
}
