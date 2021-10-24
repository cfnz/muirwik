package com.ccfraser.muirwik.components

import react.Props
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class TransitionDurationDelegate : ReadWriteProperty<Props, TransitionDuration> {
    override fun getValue(thisRef: Props, property: KProperty<*>): TransitionDuration {
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: TransitionDuration) {
        thisRef.asDynamic()[property.name] = value.value()
    }
}

class TransitionDurationDelegateNullable : ReadWriteProperty<Props, TransitionDuration?> {
    override fun getValue(thisRef: Props, property: KProperty<*>): TransitionDuration? {
//        val propValue = thisRef.asDynamic()[property.name]
//        return when (propValue) {
//            is Number -> SimpleTransitionDuration(propValue)
////            "auto" -> AutoTransitionDuration(propValue)
//            propValue != undefined && propValue.enter != undefined && propValue.exit != undefined -> EnterExitTransitionDuration(jsValue.enter, jsValue.exit)
//            else -> null
//        }
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: TransitionDuration?) {
        thisRef.asDynamic()[property.name] = value?.value()
    }
}

class TransitionDurationWithAutoDelegate : ReadWriteProperty<Props, TransitionDurationWithAuto> {
    override fun getValue(thisRef: Props, property: KProperty<*>): TransitionDurationWithAuto {
        error("transitionDuration cannot be read from props")
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: TransitionDurationWithAuto) {
        thisRef.asDynamic()[property.name] = value.value()
    }
}
