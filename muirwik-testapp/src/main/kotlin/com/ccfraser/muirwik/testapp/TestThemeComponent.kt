package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.marginBottom
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import styled.css

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestThemeComponent : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div {
            themeContext.Consumer { theme ->
                mAppBar(MAppBarColor.primary, MAppBarPosition.static) {
                    css { marginBottom = 2.spacingUnits }
                    mToolbar {
                        mTypography("Theme Component Using Context - Theme Type '${theme.palette.type}', Primary Color '${theme.palette.primary.main}'", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                    }
                }
            }
        }
    }
}


fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}

