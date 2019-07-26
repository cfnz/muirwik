package com.ccfraser.muirwik.components.styles

/**
 * ts2kt types with tweaks from material-ui/styles/spacing
 */
external interface Spacing {
    @nativeInvoke
    operator fun invoke(): Number
    @nativeInvoke
    operator fun invoke(value1: Number): Number
    @nativeInvoke
    operator fun invoke(value1: Number, value2: Number): String
    @nativeInvoke
    operator fun invoke(value1: Number, value2: Number, value3: Number): String
    @nativeInvoke
    operator fun invoke(value1: Number, value2: Number, value3: Number, value4: Number): String
}
