package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.button
import com.ccfraser.muirwik.components.styles.*
import com.ccfraser.muirwik.components.themeProvider
import com.ccfraser.muirwik.components.typography
import com.ccfraser.muirwik.components.utils.Colors
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import react.*
import react.dom.br
import styled.css
import styled.styledDiv


class TestThemes : RComponent<Props, State>() {
    var themePaletteMode = PaletteMode.light

    override fun RBuilder.render() {
        val themeOptions: ThemeOptions = js("({palette: { mode: 'placeholder', }})")
        themeOptions.palette?.mode = themePaletteMode

        // Create a new theme with the default colours (darker primary colours than the demo)
        val theme: Theme = createTheme(themeOptions)

        themeProvider(theme) {
            styledDiv {
                css { backgroundColor = Color(theme.palette.background.default) }
                typography("First is the Default theme (darker than the demo theme), then a lighter theme using a functional component with a theme hook, then a repeat of the demo app")
                button("Dark/Light Switch") {
                    attrs.onClick = { setState { themePaletteMode = if (themePaletteMode == PaletteMode.light) PaletteMode.dark else PaletteMode.light } }
                }
                br {  }
                testThemeComponent()

                val theme2Options: ThemeOptions = js("({palette: { mode: 'placeholder', primary: {main: 'placeholder'}}})")
                theme2Options.palette?.mode = themePaletteMode
                theme2Options.palette?.primary?.main = Colors.Blue.shade200.toString()

                themeProvider(createTheme(theme2Options)) {
                    testThemesComponentFunctional()
                }

                app()
            }
        }
    }
}

fun RBuilder.testThemes() = child(TestThemes::class) {}

