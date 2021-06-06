package com.ccfraser.muirwik.components.transitions

import react.RProps
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class TransitionDurationDelegate : ReadWriteProperty<RProps, TransitionDuration> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): TransitionDuration {
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: TransitionDuration) {
        thisRef.asDynamic()[property.name] = value.value()
    }
}

class TransitionDurationDelegateNullable : ReadWriteProperty<RProps, TransitionDuration?> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): TransitionDuration? {
//        val propValue = thisRef.asDynamic()[property.name]
//        return when (propValue) {
//            is Number -> SimpleTransitionDuration(propValue)
////            "auto" -> AutoTransitionDuration(propValue)
//            propValue != undefined && propValue.enter != undefined && propValue.exit != undefined -> EnterExitTransitionDuration(jsValue.enter, jsValue.exit)
//            else -> null
//        }
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: TransitionDuration?) {
        thisRef.asDynamic()[property.name] = value?.value()
    }
}

class TransitionDurationWithAutoDelegate : ReadWriteProperty<RProps, TransitionDurationWithAuto> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): TransitionDurationWithAuto {
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: TransitionDurationWithAuto) {
        thisRef.asDynamic()[property.name] = value.value()
    }
}

class TransitionComponentDelegate : ReadWriteProperty<RProps, TransitionComponent?> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): TransitionComponent? {
        error("transitionComponent cannot be read from props")
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: TransitionComponent?) {
        // The actual prop name is a component so has an uppercase first letter whereas for kotlin
        // we have kept the convention that props start with lowercase...
        val propName = property.name.first().uppercaseChar() + property.name.substring(1)

        // This does not seem to like null but works with undefined
        thisRef.asDynamic()[propName] = value?.js ?: undefined
    }
}

