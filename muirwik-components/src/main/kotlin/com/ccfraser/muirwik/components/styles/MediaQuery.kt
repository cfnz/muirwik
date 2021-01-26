package com.ccfraser.muirwik.components.styles

import kotlinext.js.jsObject
import kotlinx.browser.window
import org.w3c.dom.MediaQueryList

@JsModule("@material-ui/core/useMediaQuery")
private external val useMediaQueryModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val useMediaQueryRaw: ((query: String, options: dynamic) -> Boolean) = useMediaQueryModule.default

typealias MatchMedia = ((query: String) -> MediaQueryList)

fun useMediaQuery(
    query: String,
    defaultMatches: Breakpoint? = null,
    matchMedia: MatchMedia? = null,
    noSsr: Boolean? = null,
    ssrMatchMedia: MatchMedia? = null
): Boolean {
    return useMediaQueryRaw(query, jsObject {
        defaultMatches?.let {
            this.defaultMatches = defaultMatches
        }
        matchMedia?.let {
            this.matchMedia = matchMedia
        }
        noSsr?.let {
            this.noSsr = noSsr
        }
        ssrMatchMedia?.let {
            this.ssrMatchMedia = ssrMatchMedia
        }
    })
}