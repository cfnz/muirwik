package com.ccfraser.muirwik.components

import org.w3c.dom.events.Event
import react.RProps
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * There are a few places in Material UI that has onClose props that return a reason. The reason is a string
 * but we usually make a type safe class to wrap it. This delegate makes it easy to declare such extension
 * properties.
 */
class OnClosePropWithReasonDelegate<T>(private val enumValues: Array<T>) : ReadWriteProperty<RProps, ((Event, T) -> Unit)?> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): ((Event, T) -> Unit)? {
        error("Can't read value of ${thisRef}.${property.name}")
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: ((Event, T) -> Unit)?) {
        if (value == null) {
            thisRef.asDynamic()[property.name] = null
        } else {
            thisRef.asDynamic()[property.name] = {
                event: Event, s: String -> value(event, enumValues.first { it.toString() == s })
            }
        }
    }
}
