package com.ccfraser.muirwik.components

import kotlinext.js.Object

@JsModule("@mui/material/locale")
private external val localeModule: dynamic

object Locales {
    /**
     * Returns an array of strings keys, any of which can be used with [findByKey]
     */
    fun keys() = Object.keys(localeModule as Any)

    /**
     * Returns a Locale which can be passed as the last parameter to [createMuiTheme][com.ccfraser.muirwik.components.styles.createTheme]
     */
    fun findByKey(key: String) = localeModule[key]
}