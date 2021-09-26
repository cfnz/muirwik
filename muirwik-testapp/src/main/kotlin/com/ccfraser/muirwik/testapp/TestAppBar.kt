package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonColor
import com.ccfraser.muirwik.components.button.MIconButtonColor
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.input.mInput
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.alpha
import com.ccfraser.muirwik.components.styles.up
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import react.*
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
        mTypography("This demo shows the AppBar and Toolbar components")

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

                    mAppBar(MAppBarColor.default, MAppBarPosition.static) {
                        mToolbar {
                            mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MIconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit) {
                                css { flexGrow = 1.0 }
                            }
                            if (loggedIn) {
                                mIconButton ("account_circle", color = MIconButtonColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                mButton("Login", color = MButtonColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MIconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar Title One Liner")
                            if (loggedIn) {
                                mIconButton ("account_circle", color = MIconButtonColor.inherit )
//                            mMenu(true, anchorEl = ) {  }
                            } else {
                                mButton("Login", color = MButtonColor.inherit)
                            }
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(disableGutters = true) {
                            mIconButton("menu", color = MIconButtonColor.inherit)
                            mToolbarTitle("Toolbar No Gutters")
                            mIconButton ("account_circle", color = MIconButtonColor.inherit )
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar {
                            mIconButton("menu", color = MIconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar with Search")
                            styledDiv {
                                css(themeStyles.search)
                                styledDiv {
                                    css(ComponentStyles.searchIcon)
                                    mIcon("search")
                                }

                                val inputProps = js("({})")
                                inputProps.className = "${ComponentStyles.name}-inputInput"

                                mInput(placeholder = "Search...", disableUnderline = true) {
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

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(variant = ToolbarVariant.dense) {
                            mIconButton("menu", color = MIconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Toolbar dense (for Desktop use)")
                            mButton("Login", color = MButtonColor.inherit)
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static) {
                        mToolbar(variant = ToolbarVariant.dense, disableGutters = true) {
                            mIconButton("menu", color = MIconButtonColor.inherit)
                            mToolbarTitle("Toolbar dense and no gutters")
                            mIconButton ("account_circle", color = MIconButtonColor.inherit )
                        }
                    }
                }

                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    mAppBar(position = MAppBarPosition.static, className = "hello") {
                        attrs.elevation = 0
                        mToolbar {
                            mIconButton("menu", color = MIconButtonColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                            mToolbarTitle("Flat (elevation = 0)")
                            mIconButton ("account_circle", color = MIconButtonColor.inherit )
                        }
                    }
                }
            }
        }
    }
}
