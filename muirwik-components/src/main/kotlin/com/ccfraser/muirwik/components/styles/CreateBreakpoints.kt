package com.ccfraser.muirwik.components.styles

import kotlin.js.Json

/**
 * Types from mui/material/styles breakpoints.
 * The default Breakpoints are as below. MUI 5 allows custom named breakpoints in a theme, but we have not implemented
 * those.
 */

@Suppress("EnumEntryName")
enum class Breakpoint {
    xs, sm, md, lg, xl
}

/**
 * The js up, down, etc calls all return a string beginning with "@media". We usually use these functions in
 * css { media {...} } calls (e.g. media(currentTheme.breakpoints.up(Breakpoint.md)) ), so we don't need the
 * "@media" prefix as css { media {...} } adds the prefix as well.
 *
 * Also note that we are using String keys in the js calls as for some calls using the breakpoint value compiles and
 * runs, but returns nonsense values... so not sure what is getting called there, but calling with a string works.
 */
external interface Breakpoints {
    var keys: Array<Breakpoint>
    //    var values: BreakpointValues

    @JsName("up")
    fun upWithMediaTerm(key: String): String

    @JsName("up")
    fun upWithMediaTerm(key: Int): String

    @JsName("down")
    fun downWithMediaTerm(key: String): String

    @JsName("down")
    fun downWithMediaTerm(key: Int): String

    @JsName("between")
    fun betweenWithMediaTerm(start: String, end: String): String

    @JsName("only")
    fun onlyWithMediaTerm(key: String): String

    @JsName("values")
    val values: Json
}

private fun removeMediaString(query: String) = query.removePrefix("@media")

fun Breakpoints.up(key: Breakpoint): String {
    return removeMediaString(upWithMediaTerm(key.toString()))
}

fun Breakpoints.down(key: Breakpoint): String {
    return removeMediaString(downWithMediaTerm(key.toString()))
}

fun Breakpoints.between(startKey: Breakpoint, endKey: Breakpoint): String {
    return removeMediaString(betweenWithMediaTerm(startKey.toString(), endKey.toString()))
}

fun Breakpoints.only(key: Breakpoint): String {
    return removeMediaString(onlyWithMediaTerm(key.toString()))
}

fun Breakpoints.value(key: Breakpoint): Int {
    return values[key.toString()] as Int
}
