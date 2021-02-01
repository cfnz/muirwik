package com.ccfraser.muirwik.components.styles

import kotlinext.js.jsObject
import org.w3c.dom.MediaQueryList

@JsModule("@material-ui/core/useMediaQuery")
private external val module: dynamic

@Suppress("UnsafeCastFromDynamic")
private val useMediaQueryRaw: ((query: Any, options: dynamic) -> Boolean) = module.default

typealias MatchMedia = ((query: String) -> MediaQueryList)

fun useMediaQuery(
    query: String,
    defaultMatches: Boolean? = null,
    matchMedia: MatchMedia? = null,
    noSsr: Boolean? = null,
    ssrMatchMedia: MatchMedia? = null
): Boolean {
    return useMediaQueryRaw(query, convertOptions(defaultMatches, matchMedia, noSsr, ssrMatchMedia))
}

fun useMediaQuery(
    query: (theme: Theme) -> String,
    defaultMatches: Boolean? = null,
    matchMedia: MatchMedia? = null,
    noSsr: Boolean? = null,
    ssrMatchMedia: MatchMedia? = null
): Boolean {
    return useMediaQueryRaw(query, convertOptions(defaultMatches, matchMedia, noSsr, ssrMatchMedia))
}

private fun convertOptions(
    defaultMatches: Boolean?,
    matchMedia: MatchMedia?,
    noSsr: Boolean?,
    ssrMatchMedia: MatchMedia?
): dynamic = jsObject {
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
}