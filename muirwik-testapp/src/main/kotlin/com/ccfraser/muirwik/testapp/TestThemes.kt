package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import react.*
import react.dom.br
import styled.css
import styled.styledDiv


class TestThemes : RComponent<RProps, RState>() {
    var themeColor = "light"

    override fun RBuilder.render() {
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', }})")
        themeOptions.palette?.type = themeColor

        // Create a new theme with the default colours (darker primary colours than the demo)
        val theme: Theme = createMuiTheme(themeOptions)

        mThemeProvider(theme) {
            styledDiv {
                css { backgroundColor = Color(theme.palette.background.default) }
                mTypography("First is the Default theme (darker than the demo theme), then a lighter theme, then a repeat of the demo app")
                mButton("Dark/Light Switch", onClick = {
                    setState { themeColor = if (themeColor == "light") "dark" else "light" }
                })
                br {  }
                testThemeComponent()

                val theme2Options: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
                theme2Options.palette?.type = themeColor
                theme2Options.palette?.primary.main = Colors.Blue.shade200.toString()

                mThemeProvider(createMuiTheme(theme2Options)) {
                    testThemeComponent()
                }

                app()
            }
        }
    }
}


fun RBuilder.testThemes() = child(TestThemes::class) {}

