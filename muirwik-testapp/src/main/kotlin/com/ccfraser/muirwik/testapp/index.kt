package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.styles.mStylesProvider
import react.dom.render
import kotlin.browser.document

//@JsModule("react-hot-loader/root")
@JsModule("react-hot-loader")
private external val hotModule: dynamic
private val hot = hotModule.hot
private val module = js("module")

fun main(args: Array<String>) {
// Seems to work better without hot reloading for now
//    val hotWrapper = hot(module)
    render(document.getElementById("root")) {
        mStylesProvider("jss-insertion-point") {
            app()
    //        hotWrapper(app())
        }
    }
}


