package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.mode
import kotlinx.css.marginBottom
import react.*
import react.dom.div
import styled.css

class TestThemeComponent : RComponent<Props, State>() {

    override fun RBuilder.render() {
        div {
            themeContext.Consumer { theme ->
                mAppBar(MAppBarColor.primary, MAppBarPosition.static) {
                    css { marginBottom = 2.spacingUnits }
                    mToolbar {
                        mTypography("Theme Component Using Context - Theme Type '${theme.palette.mode}', Primary Color '${theme.palette.primary.main}'", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                    }
                }
            }
        }
    }
}

fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}
