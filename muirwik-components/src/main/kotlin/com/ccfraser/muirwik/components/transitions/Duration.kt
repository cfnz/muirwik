package com.ccfraser.muirwik.components.transitions


/**
 * Attempt at a type safe way of specifying transition durations for some transition props... almost the same
 * as TransitionTimeout but some props don't have the auto value, so this type doesn't either.
 */
interface TransitionDuration {
    fun value(): dynamic
}

class EnterExitTransitionDuration(private val enterTimeMs: Int, private val exitTimeMs: Int) : TransitionDuration {
    override fun value(): dynamic {
        val value = js("({})")
        value.enter = enterTimeMs
        value.exit = exitTimeMs
        return value
    }
}

class SimpleTransitionDuration(val timeMs: Int) : TransitionDuration {
    override fun value(): dynamic {
        return timeMs
    }
}
