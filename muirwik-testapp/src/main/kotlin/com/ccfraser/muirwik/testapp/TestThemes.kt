package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.currentTheme
import com.ccfraser.muirwik.components.mButton
import com.ccfraser.muirwik.components.mMuiThemeProvider
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.ThemeOptions
import kotlinx.css.Color
import react.*
import react.dom.br
import styled.css
import styled.styledDiv

@JsModule("@material-ui/core/styles/withTheme")
private external val withThemeModule: dynamic
private val withTheme = withThemeModule.default

@JsModule("@material-ui/core/styles/themeListener")
private external val themeListener: dynamic

@JsModule("@material-ui/core/styles/createMuiTheme")
private external val createMuiThemeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val createMuiThemeFunction: dynamic = createMuiThemeModule.default

class TestThemes : RComponent<RProps, RState>() {
    var themeColor = "light"

    override fun RBuilder.render() {
        val themeProps: ThemeOptions = js("({palette: { type: 'placeholder', }, typography: {useNextVariants: 'placeholder'}})")
        themeProps.palette?.type = themeColor

        // Material UI 3.3.2 (or a bit earlier) has depreciated some typography enums. We do the following
        // so we don't get any warning messages.
        themeProps.typography?.useNextVariants = true

        val theme: Theme = createMuiThemeFunction(themeProps)
        currentTheme = theme

        mMuiThemeProvider(theme) {
            mTypography("The spacing unit from attrs.theme is ${attrs.theme.asDynamic().spacing.unit}.")
            styledDiv {
                css { backgroundColor = Color(theme.palette.background.default) }
                mTypography("The spacing unit from currentTheme is ${currentTheme.spacing.unit}")
                mButton("Dark/Light Switch", onClick = { setState {
                    themeColor = if (themeColor == "light") "dark" else "light" }})
                br {  }
                testThemeComponent()
                app()
            }
        }
    }
}


fun RBuilder.testThemes() = child(TestThemes::class) {}

