package com.ccfraser.muirwik.components.styles

/**
 * ts2kt types with tweaks from material-ui/styles/spacing
 */
external interface Spacing

@Suppress("NOTHING_TO_INLINE")
inline operator fun Spacing.invoke(): Number {
    return asDynamic()() as Number
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun Spacing.invoke(value1: Number): Number {
    return asDynamic()(value1) as Number
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun Spacing.invoke(value1: Number, value2: Number): String {
    return asDynamic()(value1, value2) as String
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun Spacing.invoke(value1: Number, value2: Number, value3: Number): String {
    return asDynamic()(value1, value2, value3) as String
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun Spacing.invoke(value1: Number, value2: Number, value3: Number, value4: Number): String {
    return asDynamic()(value1, value2, value3, value4) as String
}
