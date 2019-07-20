package com.ccfraser.muirwik.testapp

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

// Seems to work better without hot reloading for now
//    val hotWrapper = hot(module)
    render(document.getElementById("root")) {
        app()
//        hotWrapper(app())
    }
}


