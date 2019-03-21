package com.ccfraser.index

import com.ccfraser.muirwik.testapp.AppProps
import com.ccfraser.muirwik.testapp.app
import react.dom.render
import kotlin.browser.document

//@JsModule("react-hot-loader/root")
@JsModule("react-hot-loader")
private external val hotModule: dynamic
private val hot = hotModule.hot
private val module = js("module")

fun main(args: Array<String>) {
    // Allows the css to be down with the component
//    requireAll(require.context("", true, js("/\\.css$/")))
    println("Running main in muirwik testapp...")

    val hotWrapper = hot(module)
    render(document.getElementById("root")) {
//        app()
//        val app = app(AppProps("light", "An Intro"))
//        app.props
        hotWrapper(app(AppProps("light", "An Intro")))
//        hot(app(AppProps2("light", "Cards")))
    }
}


