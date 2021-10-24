package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.mode
import kotlinx.css.marginBottom
import react.Props
import react.RBuilder
import react.dom.div
import react.fc
import styled.css

private val testThemesComponentFunctional = fc<Props> {
    val theme = useTheme()

    div {
        appBar(AppBarPosition.static) {
            attrs.color = AppBarColor.primary
            css { marginBottom = 2.spacingUnits }
            toolbar {
                typography("Theme Functional Component Using Hook - Theme Mode '${theme.palette.mode}', " +
                        "Primary Color '${theme.palette.primary.main}'", TypographyVariant.h6, TypographyColor.inherit)
            }
        }
    }
}

fun RBuilder.testThemesComponentFunctional() =  child(testThemesComponentFunctional) {
}
