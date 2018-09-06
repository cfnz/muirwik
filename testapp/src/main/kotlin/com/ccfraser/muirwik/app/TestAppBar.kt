package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv


class TestAppBar : RComponent<RProps, RState>() {
    var loggedIn = false

    override fun RBuilder.render() {

        mTypography("This demo also shows the Toolbar component")

        styledDiv {
            css { maxWidth = 800.px }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(MColor.default, MAppBarPosition.static) {
                    mToolbar {
                        mTypography("Title", variant = MTypographyVariant.title, color = MTypographyColor.inherit)
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.static) {
                    mToolbar {
                        mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                        mTypography("Title", variant = MTypographyVariant.title, color = MTypographyColor.inherit) {
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
                    mToolbar(variant = ToolbarVariant.dense) {
                        mIconButton("menu", color = MColor.inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                        mToolbarTitle("Toolbar dense (for Desktop use)")
                        mIconButton ("account_circle", color = MColor.inherit )
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.static) {
                    mToolbar(variant = ToolbarVariant.dense, disableGutters = true) {
                        mIconButton("menu", color = MColor.inherit)
                        mToolbarTitle("Toolbar dense and no gutters")
                        mButton("Login", color = MColor.inherit)
                    }
                }
            }
        }
    }
}

fun RBuilder.testAppBar() = child(TestAppBar::class) {}
