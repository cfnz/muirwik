package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.mode
import kotlinx.css.marginBottom
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import styled.css

class TestThemeComponent : RComponent<Props, State>() {

    override fun RBuilder.render() {
        div {
            themeContext.Consumer { theme ->
                appBar(AppBarPosition.static) {
                    attrs.color = AppBarColor.primary
                    css { marginBottom = 2.spacingUnits }
                    toolbar {
                        typography("Theme Component Using Context - Theme Type '${theme.palette.mode}', " +
                                "Primary Color '${theme.palette.primary.main}'", TypographyVariant.h6, TypographyColor.inherit)
                    }
                }
            }
        }
    }
}

fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}
