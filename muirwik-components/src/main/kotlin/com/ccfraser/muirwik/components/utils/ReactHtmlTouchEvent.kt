package com.ccfraser.muirwik.components.utils

import org.w3c.dom.Element
import org.w3c.dom.events.UIEvent
import org.w3c.dom.events.UIEventInit


/**
 * Exposes the JavaScript [TouchEvent](https://developer.mozilla.org/en/docs/Web/API/TouchEvent) to Kotlin
 */
open external class TouchEvent(type: String, eventInitDict: TouchEventInit = definedExternally) : UIEvent {
    open val altKey: Boolean
    open val changedTouches: Array<Touch>
    open val ctrlKey: Boolean
    open val metaKey: Boolean
    open val shiftKey: Boolean
    open val targetTouchesRead: Array<Touch>
    open val touches: Array<Touch>
    open val rotation: Double
    open val scale: Double
}

external interface TouchEventInit : UIEventInit {
    var altKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var changedTouches: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
    var ctrlKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var metaKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var shiftKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var targetTouchesRead: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
    var touches: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
    var rotation: Double?
        get() = definedExternally
        set(value) = definedExternally
    var scale: Double?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Touch {
    var identifier: Long
    var screenX: Int
    var screenY: Int
    var clientX: Long
    var clientY: Long
    var pageX: Double
    var pageY: Double
    var target: Element
}

// Can't use @kotlin.internal.InlineOnly, but for want, just accepting event info, we shouldn't need it,
// so the above should be fine.
//
//@kotlin.internal.InlineOnly
//public inline fun TouchEventInit(deltaX: Double? = 0.0, deltaY: Double? = 0.0, deltaZ: Double? = 0.0, deltaMode: Int? = 0, screenX: Int? = 0, screenY: Int? = 0, clientX: Int? = 0, clientY: Int? = 0, button: Short? = 0, buttons: Short? = 0, relatedTarget: EventTarget? = null, ctrlKey: Boolean? = false, shiftKey: Boolean? = false, altKey: Boolean? = false, metaKey: Boolean? = false, modifierAltGraph: Boolean? = false, modifierCapsLock: Boolean? = false, modifierFn: Boolean? = false, modifierFnLock: Boolean? = false, modifierHyper: Boolean? = false, modifierNumLock: Boolean? = false, modifierScrollLock: Boolean? = false, modifierSuper: Boolean? = false, modifierSymbol: Boolean? = false, modifierSymbolLock: Boolean? = false, view: Window? = null, detail: Int? = 0, bubbles: Boolean? = false, cancelable: Boolean? = false, composed: Boolean? = false): TouchEventInit {
//    val o = js("({})")
//
//    o["deltaX"] = deltaX
//    o["deltaY"] = deltaY
//    o["deltaZ"] = deltaZ
//    o["deltaMode"] = deltaMode
//    o["screenX"] = screenX
//    o["screenY"] = screenY
//    o["clientX"] = clientX
//    o["clientY"] = clientY
//    o["button"] = button
//    o["buttons"] = buttons
//    o["relatedTarget"] = relatedTarget
//    o["ctrlKey"] = ctrlKey
//    o["shiftKey"] = shiftKey
//    o["altKey"] = altKey
//    o["metaKey"] = metaKey
//    o["modifierAltGraph"] = modifierAltGraph
//    o["modifierCapsLock"] = modifierCapsLock
//    o["modifierFn"] = modifierFn
//    o["modifierFnLock"] = modifierFnLock
//    o["modifierHyper"] = modifierHyper
//    o["modifierNumLock"] = modifierNumLock
//    o["modifierScrollLock"] = modifierScrollLock
//    o["modifierSuper"] = modifierSuper
//    o["modifierSymbol"] = modifierSymbol
//    o["modifierSymbolLock"] = modifierSymbolLock
//    o["view"] = view
//    o["detail"] = detail
//    o["bubbles"] = bubbles
//    o["cancelable"] = cancelable
//    o["composed"] = composed
//
//    return o
//}
