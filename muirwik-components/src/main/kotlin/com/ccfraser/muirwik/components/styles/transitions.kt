package com.ccfraser.muirwik.components.styles

/**
 * ts2kt types with tweaks from material-ui/styles/transitions
 */
external interface Easing {
    var easeInOut: String
    var easeOut: String
    var easeIn: String
    var sharp: String
}
external interface Duration {
    var shortest: Int
    var shorter: Int
    var short: Int
    var standard: Int
    var complex: Int
    var enteringScreen: Int
    var leavingScreen: Int
}
external interface Transitions {
    var easing: Easing
    var duration: Duration
    fun create(props: String, options: Any? = definedExternally /* null */): String
    fun create(props: Array<String>, options: Any? = definedExternally /* null */): String
    fun getAutoHeightDuration(height: Number): Int
}
external interface TransitionsOptions {
    var easing: Any? get() = definedExternally; set(value) = definedExternally
    var duration: Any? get() = definedExternally; set(value) = definedExternally
    var create: ((props: dynamic /* String | Array<String> */, options: Any? /*= null*/) -> String)? get() = definedExternally; set(value) = definedExternally
    var getAutoHeightDuration: ((height: Int) -> Int)? get() = definedExternally; set(value) = definedExternally
}
