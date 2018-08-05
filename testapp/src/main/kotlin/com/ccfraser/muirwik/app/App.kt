package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.styles.ThemeOptions
import kotlinext.js.Object
import kotlinext.js.js
import kotlinext.js.jsObject
import react.*

class App : RComponent<RProps, RState>() {
    private var themeColor = "light"

    override fun RBuilder.render() {
        mCssBaseline()

//        val themeProps = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
//        themeProps.palette.type = themeColor
//        themeProps.palette.primary.main = Colors.Blue.shade500.toString()

        // Slightly more typed version... create an object and effectively cast it to ThemeOptions
        @Suppress("UnsafeCastFromDynamic")
        val themeProps: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeProps.palette?.type = themeColor
        themeProps.palette?.primary.main = Colors.Blue.shade500.toString()

        currentTheme = createMuiThemeFunction(themeProps)

        mMuiThemeProvider(currentTheme) {
            mainFrame({themeType -> setState { themeColor = themeType } })
        }
    }
}


fun RBuilder.app() = child(App::class) {}



