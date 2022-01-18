package com.ccfraser.muirwik.components.styles

import kotlinext.js.Object
import kotlinext.js.jso
import org.w3c.dom.Element
import react.ComponentType
import react.Props
import react.RBuilder
import react.RHandler

@JsModule("@mui/styles/StylesProvider")
private external val stylesProviderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val stylesProviderComponentType: ComponentType<MStylesProviderProps> = stylesProviderModule.default

@JsModule("@mui/styles/jssPreset")
private external val jssPresetModule: dynamic

@JsModule("jss")
private external val jss: dynamic


external interface MStylesProviderProps : Props {
    var disableGeneration: Boolean
    var generateClassName: () -> Unit
    var injectFirst: Boolean
    var jss: Object
}

/**
 * Among other things, this allows the positioning of the css created by styled components.
 * Sometimes the css is injected into the index.html page (or whatever is used) above the MaterialUI css, so they
 * don't override them.
 *
 * Note, I am not sure why, but sometimes you don't need this and sometimes you do, i.e. sometimes the styled css will
 * be injected before the Material UI css and sometimes it will be after... to make sure, you can use this.
 *
 * Tweaking this provider (e.g. injectFirst = true) or using the mStylesProvider(insertionPointComment) version
 * allows you do move the insertion point and change the order of the css inheritance.
 *
 * E.g., having this comment: <!-- jss-insertion-point --> at the bottom of the head of the index html page, and using
 * mStylesProvider("jss-insertion-point") the jss should inject the MaterialUI code just below this comment, and
 * any styled components css should be injected later.
 *
 * This should be used as a top level component... there are a couple of other useful top level components, so
 * you might have something like the following to create and use a default theme:
 *
 *     render(document.getElementById("root")) {
 *         hotWrapper(
 *             stylesProvider("jss-insertion-point") {
 *                 mCssBaseline()
 *                 mThemeProvider {
 *                     app()
 *                 }
 *             }
 *         )
 *     }
 */
fun RBuilder.mStylesProvider(
    injectFirst: Boolean = false,
    disableGeneration: Boolean = false,
    generateClassName: (() -> Unit)? = null,
    jss: Object? = null,
    handler: RHandler<MStylesProviderProps>? = null
) {
    child(stylesProviderComponentType, jso()) {
        attrs.injectFirst = injectFirst
        attrs.disableGeneration = disableGeneration
        generateClassName?.let { attrs.generateClassName = it }
        jss?.let { attrs.jss = it }

        handler?.let { it() }
    }
}

/**
 * See the comments for the full mStylesProvider version.
 */
fun RBuilder.mStylesProvider(
    insertionPointComment: String,
    handler: RHandler<MStylesProviderProps>? = null
) {
    val jssPresets = jssPresetModule.default()
    jssPresets.insertionPoint = insertionPointComment

    val jss = jss.create(jssPresets)

    mStylesProvider(false, false, null, jss, handler)
}

/**
 * See the comments for the full mStylesProvider version.
 */
fun RBuilder.mStylesProvider(
    insertionPointElement: Element,
    handler: RHandler<MStylesProviderProps>? = null
) {
    val jssPresets = jssPresetModule.default()
    jssPresets.insertionPoint = insertionPointElement

    val jss = jss.create(jssPresets)

    mStylesProvider(false, false, null, jss, handler)
}
