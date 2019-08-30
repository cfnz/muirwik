package com.ccfraser.muirwik.components

import react.RProps
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * This makes it easy to use a prop which is a child of a "real" prop. For example if we have
 * an anchorPosition object in Material UI which has a child left and top props, we can use
 * this two of these Delegates to easily access them... e.g. anchorPositionLeft and anchorPositionTop for example.
 * @param propName The name of the "real" react prop which has children.
 * @param childPropName The name of the child prop.
 *
 */
class ChildPropDelegate<T>(private val propName: String, private val childPropName: String) : ReadWriteProperty<RProps, T> {
    override fun getValue(thisRef: RProps, property: KProperty<*>): T {
        return thisRef.asDynamic()[propName][childPropName] as T
    }

    override fun setValue(thisRef: RProps, property: KProperty<*>, value: T) {
        if (thisRef.asDynamic()[propName] == undefined) {
            thisRef.asDynamic()[propName] = js ("({})")
        }
        thisRef.asDynamic()[propName][childPropName] = value
    }
}

/**
 * Same as [ChildPropDelegate] but allows nullable props
 */
class ChildPropDelegateNullable<T>(val propName: String, val childPropName: String) {
    inline operator fun <reified T> getValue(thisRef: RProps, property: KProperty<*>): T? {

        return when {
            thisRef.asDynamic()[propName] == undefined -> null
            thisRef.asDynamic()[propName][childPropName] == undefined -> null
            thisRef.asDynamic()[propName][childPropName] !is T -> null
            else -> thisRef.asDynamic()[propName][childPropName] as T
        }
    }

    operator fun setValue(thisRef: RProps, property: KProperty<*>, value: T?) {
        if (thisRef.asDynamic()[propName] == undefined) {
            thisRef.asDynamic()[propName] = js ("({})")
        }
        thisRef.asDynamic()[propName][childPropName] = value
    }
}
