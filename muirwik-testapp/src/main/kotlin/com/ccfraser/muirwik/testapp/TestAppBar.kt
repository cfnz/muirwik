package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.input.mInput
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.fade
import com.ccfraser.muirwik.components.styles.up
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestAppBar : RComponent<RProps, RState>() {
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
        mTypography("This demo shows the AppBar and Toolbar components")

        themeContext.Consumer { theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val search by css {
                    position = Position.relative
                    borderRadius = theme.shape.borderRadius.px
                    backgroundColor = Color(fade(theme.palette.common.white, 0.15))
                    hover {
                        backgroundColor = Color(fade(theme.palette.common.white, 0.25))
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

                    mAppBar(MColor.default, MAppBarPosition.static) {
                        mToolbar {
                            mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit) {
                                css { flexGrow = 1.0 }
                            }
                            if (loggedIn) {
                                mIconButton ("account_circle", color = MColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                mButton("Login", color = MColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar Title One Liner")
                            if (loggedIn) {
                                mIconButton ("account_circle", color = MColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                mButton("Login", color = MColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(disableGutters = true) {
                            mIconButton("menu", color = MColor.inherit)
                            mToolbarTitle("Toolbar No Gutters")
                            mIconButton ("account_circle", color = MColor.inherit )
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar with Search")
                            styledDiv {
                                css(themeStyles.search)
                                styledDiv {
                                    css(ComponentStyles.searchIcon)
                                    mIcon("search")
                                }
                                val inputProps = object: RProps {
                                    val className = "${ComponentStyles.name}-inputInput"
                                }

                                mInput(placeholder = "Search...", disableUnderline = true, inputProps = inputProps) {
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

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(variant = ToolbarVariant.dense) {
                            mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar dense (for Desktop use)")
                            mButton("Login", color = MColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(variant = ToolbarVariant.dense, disableGutters = true) {
                            mIconButton("menu", color = MColor.inherit)
                            mToolbarTitle("Toolbar dense and no gutters")
                            mIconButton ("account_circle", color = MColor.inherit )
                        }
                    }
                }
            }

        }
    }
}

fun RBuilder.testAppBar() = child(TestAppBar::class) {}
