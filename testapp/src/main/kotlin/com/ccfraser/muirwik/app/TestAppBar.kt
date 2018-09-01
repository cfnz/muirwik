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

                mAppBar(MColor.Default, MAppBarPosition.Static) {
                    mToolbar {
                        mTypography("Title", variant = MTypographyVariant.Title, color = MTypographyColor.Inherit)
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.Static) {
                    mToolbar {
                        mIconButton("menu", color = MColor.Inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                        mTypography("Title", variant = MTypographyVariant.Title, color = MTypographyColor.Inherit) {
                            css { flexGrow = 1.0 }
                        }
                        if (loggedIn) {
                            mIconButton ("account_circle", color = MColor.Inherit )
//                            mMenu(true, anchorEl = ) {  }
                        } else {
                            mButton("Login", color = MColor.Inherit)
                        }
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.Static) {
                    mToolbar {
                        mIconButton("menu", color = MColor.Inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                        mToolbarTitle("Toolbar Title One Liner")
                        if (loggedIn) {
                            mIconButton ("account_circle", color = MColor.Inherit )
//                            mMenu(true, anchorEl = ) {  }
                        } else {
                            mButton("Login", color = MColor.Inherit)
                        }
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.Static) {
                    mToolbar(disableGutters = true) {
                        mIconButton("menu", color = MColor.Inherit)
                        mToolbarTitle("Toolbar No Gutters")
                        mIconButton ("account_circle", color = MColor.Inherit )
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.Static) {
                    mToolbar(variant = ToolbarVariant.Dense) {
                        mIconButton("menu", color = MColor.Inherit) { css { marginLeft = -12.px; marginRight = 20.px }}
                        mToolbarTitle("Toolbar Dense (for Desktop use)")
                        mIconButton ("account_circle", color = MColor.Inherit )
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.Static) {
                    mToolbar(variant = ToolbarVariant.Dense, disableGutters = true) {
                        mIconButton("menu", color = MColor.Inherit)
                        mToolbarTitle("Toolbar Dense and no gutters")
                        mButton("Login", color = MColor.Inherit)
                    }
                }
            }
        }
    }
}

fun RBuilder.testAppBar() = child(TestAppBar::class) {}
