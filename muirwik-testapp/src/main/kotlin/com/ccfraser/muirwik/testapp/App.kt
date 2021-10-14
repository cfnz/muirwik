package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.styles.PaletteMode
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createTheme
import com.ccfraser.muirwik.components.styles.mode
import react.*

external interface AppState: State {
    var themePaletteMode: PaletteMode
}

class App(props: Props) : RComponent<Props, AppState>(props) {
    override fun AppState.init(props: Props) {
        themePaletteMode = PaletteMode.light
    }
    override fun RBuilder.render() {
        mCssBaseline()

        // Create an object with child objects already created and effectively cast it to ThemeOptions - our demo theme
        // has a lighter primary color than the default theme
        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { mode: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.mode = state.themePaletteMode
        themeOptions.palette?.primary?.main = Colors.Blue.shade500.toString()

        mThemeProvider(createTheme(themeOptions)) {
            mainFrame(Page.Intro) { setState { themePaletteMode = if (themePaletteMode == PaletteMode.dark) PaletteMode.light else PaletteMode.dark } }
        }
    }
}

fun RBuilder.app() {
    child(App::class) {}
}



