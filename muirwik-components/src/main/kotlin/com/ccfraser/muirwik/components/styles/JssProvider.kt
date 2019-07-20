package com.ccfraser.muirwik.components.styles

import react.RBuilder
import react.RHandler
import react.RProps
import react.ReactElement

@JsModule("react-jss/lib/JssProvider")
private external val jssProviderModule: dynamic

@JsModule("jss")
private external val jssModule: dynamic

@JsModule("@material-ui/core/styles/createGenerateClassName")
private external val createGenerateClassName: dynamic

@JsModule("@material-ui/core/styles/jssPreset")
private external val jssPresetModule: dynamic

private val generateClassName = createGenerateClassName.default()
private val presets = jssPresetModule.default()

/**
 * This allows the positioning of the css created by styled components. Sometimes the css is injected into the
 * index.html page above the MaterialUI css, so they don't override them. By having this comment:
 * <!-- jss-insertion-point --> at the bottom of the head, the jss should inject below the MaterialUI code, and
 * everything should hopefully work.
 *
 * This should be used as a top level component... there are a couple of other useful top level components, so
 * you might have something like the following to create and use a default theme:
 *
 *     render(document.getElementById("root")) {
 *         hotWrapper(
 *             jssProvider {
 *                 mCssBaseline()
 *                 themeProvider {
 *                     app()
 *                 }
 *             }
 *         )
 *     }
 */
fun RBuilder.jssProvider(handler: RHandler<RProps>): ReactElement {
    presets.insertionPoint = "jss-insertion-point"
    val jss = jssModule.create(presets)

    val props = js("({})")
    props.jss = jss
    props.generateClassName = generateClassName

    return child(jssProviderModule.default as Any, props, handler)
}

