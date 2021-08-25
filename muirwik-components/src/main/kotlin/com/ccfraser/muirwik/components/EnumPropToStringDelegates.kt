package com.ccfraser.muirwik.components

import react.Props
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
 * Tried getting it working with reified types... could get it working for non null values (as below)
 * but couldn't get it going with nullable, so reverted to the non reified version which needs to pass
 * in the enumValues, and to keep nullable and non null consistent, we are using the non reified version for both.
class EnumPropToStringR<T> {
    inline operator fun <reified T : Enum<T>> getValue(thisRef: Props, property: KProperty<*>): T {
        return enumValueOf(thisRef.asDynamic()[property.name] as String)
    }

    operator fun setValue(thisRef: Props, property: KProperty<*>, value: T) {
        thisRef.asDynamic()[property.name] = value.toString()
    }
}
*/


/**
 * This makes it easy to have a prop which is converted from an enum to a string (i.e. from a kotlin
 * typed enum to a string enum that Material UI wants).
 * @param enumValues Is the list of enum values that gets converted to strings
 * @param propNameOverride Usually null. If the property name used is not the same as the property name that the
 *                         delegate is attached to, then it can be specified here. Usually this is null which means the
 *                         property name will be used. Examples where this might be used is if the property name
 *                         starts with an uppercase character, but our kotlin convention is to use a lowercase
 *                         character, or perhaps the Material UI prop is a reserved word so we use a different name.
 * @param childProp Usually null. If the property has a child property that we are targeting, then this can be specified here.
 *                  For example if the Material UI prop is origin.horizontal and our property value is originHorizontal
 *                  we would have an propNameOverride set to origin and a childProp value set to horizontal.
 *
 */
class EnumPropToString<T>(private val enumValues: Array<T>,
                          private val propNameOverride: String? = null,
                          private val childProp: String? = null) : ReadWriteProperty<Props, T> {
    override fun getValue(thisRef: Props, property: KProperty<*>): T {
        val valAsString = valueAsString(property, thisRef, propNameOverride, childProp)
        return enumValues.first { it.toString() == valAsString }
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: T) {
        val propName = propNameOverride ?: property.name

        if (childProp == null) {
            thisRef.asDynamic()[propName] = value.toString()
        } else {
            if (thisRef.asDynamic()[propName] == undefined) {
                thisRef.asDynamic()[propName] = js ("({})")
            }
            thisRef.asDynamic()[propName][childProp] = value.toString()
        }
    }
}

/**
 * Same as [EnumPropToString] but allows nullable props
 */
class EnumPropToStringNullable<T>(private val enumValues: Array<T>,
                                  private val propNameOverride: String? = null,
                                  private val childProp: String? = null) : ReadWriteProperty<Props, T?> {
    override fun getValue(thisRef: Props, property: KProperty<*>): T? {
        val valAsString = valueAsString(property, thisRef, propNameOverride, childProp)

        return if (valAsString != null) {
            enumValues.firstOrNull { it.toString() == valAsString }
        } else {
            null
        }
    }

    override fun setValue(thisRef: Props, property: KProperty<*>, value: T?) {
        val propName = propNameOverride ?: property.name

        if (childProp == null) {
            thisRef.asDynamic()[propName] = value?.toString()
        } else {
            if (thisRef.asDynamic()[propName] == undefined) {
                thisRef.asDynamic()[propName] = js ("({})")
            }
            thisRef.asDynamic()[propName][childProp] = value?.toString()
        }
    }
}

private fun valueAsString(property: KProperty<*>, thisRef: Props, propNameOverride: String?, childProp: String?): String? {
    val propName = propNameOverride ?: property.name

    return if (childProp == null) {
        if (thisRef.asDynamic()[propName] is String) {
            thisRef.asDynamic()[propName] as String?
        } else {
            null
        }
    } else {
        if (thisRef.asDynamic()[propName] != undefined) {
            if (thisRef.asDynamic()[propName][childProp] is String) {
                thisRef.asDynamic()[propName][childProp] as String?
            } else {
                null
            }
        } else {
            null
        }
    }
}

// The above replaces the below with two classes...

//class EnumPropToString<T>(private val enumValues: Array<T>,
//                          private val propNameOverride: String? = null,
//                          private val childProp: String? = null) : ReadWriteProperty<Props, T> {
//    override fun getValue(thisRef: Props, property: KProperty<*>): T {
//        val valAsString = thisRef.asDynamic()[property.name] as String
//        return enumValues.first { it.toString() == valAsString }
//    }
//
//    override fun setValue(thisRef: Props, property: KProperty<*>, value: T) {
//        thisRef.asDynamic()[property.name] = value.toString()
//    }
//}

//class EnumPropToStringNullable<T>(private val enumValues: Array<T>,
//                                  private val propNameOverride: String? = null,
//                                  private val childProp: String? = null) : ReadWriteProperty<Props, T?> {
//    override fun getValue(thisRef: Props, property: KProperty<*>): T? {
//        val valAsString = thisRef.asDynamic()[property.name] as String?
//        return if (valAsString != null) {
//            enumValues.firstOrNull { it.toString() == valAsString }
//        } else {
//            null
//        }
//    }
//
//    override fun setValue(thisRef: Props, property: KProperty<*>, value: T?) {
//        thisRef.asDynamic()[property.name] = value?.toString()
//    }
//}
//
//class EnumPropToStringNullableChildProp<T>(private val enumValues: Array<T>, private val propNameOverride: String? = null, private val childProp: String? = null) : ReadWriteProperty<Props, T?> {
//    override fun getValue(thisRef: Props, property: KProperty<*>): T? {
//        val propName = propNameOverride ?: property.name
//
//        val valAsString = if (childProp == null) {
//            thisRef.asDynamic()[propName] as String?
//        } else {
//            if (thisRef.asDynamic()[propName] != undefined) {
//                thisRef.asDynamic()[propName][childProp] as String?
//            } else {
//                null
//            }
//        }
//
//        return if (valAsString != null) {
//            enumValues.firstOrNull { it.toString() == valAsString }
//        } else {
//            null
//        }
//    }
//
//    override fun setValue(thisRef: Props, property: KProperty<*>, value: T?) {
//        val propName = propNameOverride ?: property.name
//
//        if (childProp == null) {
//            thisRef.asDynamic()[propName] = value?.toString()
//        } else {
//            if (thisRef.asDynamic()[propName] == undefined) {
//                thisRef.asDynamic()[propName] = js ("({})")
//            }
//            thisRef.asDynamic()[propName][childProp] = value?.toString()
//        }
//    }
//}