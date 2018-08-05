package com.ccfraser.muirwik.wrapper.transitions


/**
 * Attempt at a type safe way of specifying transition timeouts for some transition props...
 */
interface TransitionTimeout {
    fun value(): dynamic
}

class AutoTransitionTimeout : TransitionTimeout {
    override fun value(): dynamic {
        return "auto"
    }
}

class EnterExitTransitionTimeout(private val enterTimeMs: Int, private val exitTimeMs: Int) : TransitionTimeout {
    override fun value(): dynamic {
        val value = js("({})")
        value.enter = enterTimeMs
        value.exit = exitTimeMs
        return value
    }
}

class SimpleTransitionTimeout(val timeMs: Int) : TransitionTimeout {
    override fun value(): dynamic {
        return timeMs
    }
}
