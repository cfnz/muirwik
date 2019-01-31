package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.ThemeOptions
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
        val themeProps: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}, typography: {useNextVariants: 'placeholder'}})")
        themeProps.palette?.type = themeColor
        themeProps.palette?.primary.main = Colors.Blue.shade500.toString()

        // Material UI 3.3.2 (or a bit earlier) has depreciated some typography enums. We do the following
        // so we don't get any warning messages.
        themeProps.typography?.useNextVariants = true

        currentTheme = createMuiThemeFunction(themeProps)

        mMuiThemeProvider(currentTheme) {
            mainFrame({themeType -> setState { themeColor = themeType } })
        }
    }
}


fun RBuilder.app() = child(App::class) {}



