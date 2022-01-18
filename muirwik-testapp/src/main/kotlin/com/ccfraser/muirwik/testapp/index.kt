package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.styles.mStylesProvider
import kotlinx.browser.document
import react.dom.render

//@JsModule("react-hot-loader/root")
//@JsModule("react-hot-loader")
//private external val hotModule: dynamic
//private val hot = hotModule.hot
//private val indexModule = js("module")

fun main() {
// Seems to work better without hot reloading for now
//    val hotWrapper = hot(module)
    render(document.getElementById("root")!!) {
        mStylesProvider("jss-insertion-point") {
            app()
    //        hotWrapper(app())
        }
    }
}


