package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import com.ccfraser.muirwik.components.themeProvider
import react.*

data class AppProps(var themeColor: String, var initialView: String = "An Intro") : RProps

class App(props: AppProps) : RComponent<AppProps, RState>(props) {
//    private var themeColor = props.themeColor
    private var themeColor = "light"

    override fun RBuilder.render() {
        mCssBaseline()

        // Create an object with child objects already created and effectively cast it to ThemeOptions - our demo theme
        // has a lighter primary color than the default theme
        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.type = themeColor
        themeOptions.palette?.primary.main = Colors.Blue.shade500.toString()

        themeProvider(createMuiTheme(themeOptions)) {
            mainFrame(props.initialView, { themeType -> setState { themeColor = themeType } })
        }
    }
}

fun RBuilder.app(initialProps: AppProps) = child(App::class) {
    attrs {
        initialView = initialProps.initialView
        themeColor = initialProps.themeColor
    }
}



