package com.ccfraser.muirwik.wrapper

import kotlinext.js.JsObject
import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import kotlinx.css.px
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import react.*
import styled.StyledElementBuilder
import styled.StyledHandler
import styled.StyledProps
import styled.toStyle
import kotlin.reflect.KClass


/**
 * Just a one liner to replace a repetitive two liner :-)
 */
fun <P : StyledProps> StyledElementBuilder<P>.setStyledPropsAndRunHandler(className: String?, handler: StyledHandler<P>?) {
    className?.let {attrs.className = it}
    if (handler != null) handler()
}

/**
 * Create a child with empty props
 */
fun <P : RProps, S : RState> RBuilder.child(component: RComponent<P, S>, handler: RHandler<P>): ReactElement {
    val props: P = jsObject {}
    return child(component, props, handler)
}

/**
 * This is just a little helper to make the creation of our components shorter, for example
 *
 * ...handler: StyledHandler<MCheckboxProps>? = null) = child(with(StyledElementBuilder<MCheckboxProps>(checkboxComponent)) {
 *      etc
 *      create()
 * })
 *
 * becomes
 *
 * ... handler: StyledHandler<MCheckboxProps>? = null) = createStyled(checkboxComponent) {
 *      etc
 * }
 */
fun <P : StyledProps> RBuilder.createStyled(component: RComponent<P, RState>, addAsChild: Boolean = true, handler: StyledHandler<P>): ReactElement {
    val builder = StyledElementBuilder<P>(component)
    handler(builder)
    return if (addAsChild) child(builder.create()) else builder.create()
}

/**
 * Helper for creating a styled component from a component class (e.g. MyComponent::class)
 */
fun <P : StyledProps> RBuilder.createStyled(componentClass: KClass<out RComponent<P, RState>>, addAsChild: Boolean = true, handler: StyledHandler<P>): ReactElement {
    val builder = StyledElementBuilder<P>(componentClass.js)
    handler(builder)

    val el = if (addAsChild) child(builder.create()) else builder.create()

    // For some reason, we seem to need to add the children here whereas in the method above we don't...
    el.props.children

    return el
}

/**
 * Allows you to easily specify the theme's spacing unit. Usually used in css e.g. css { padding(2.spacingUnits) }
 */
val Int.spacingUnits get() = (currentTheme.spacing.unit * this).px

/**
 * This is only a simple jsObjectToCss converter. So far it can only handle
 * a jsObject that is one layer deep, or two layers if the second layer is a
 * media query (used initially just to convert the currentTheme.mixins.toolbar)
 */
fun CSSBuilder.toolbarJsCssToPartialCss(jsObject: JsObject) {
    // TODO: Pretty rude and crude for now, if it is a height or width, put px on the end of the value
    fun addSuffix(key: String, value: String): String {
        return if (key.contains("height", true) || key.contains("width", true)) {
            value + "px"
        } else {
            value
        }
    }

    val keys = Object.keys(jsObject)
    keys.forEach {
        val value = jsObject.asDynamic()[it]
        if (value != null && js("typeof value === 'object'") as Boolean) {
            if (it.startsWith("@media", true)) {
                val query = it.substring(6).trim()
                media(query) {
                    val keys2 = Object.keys(value)
                    keys2.forEach {
                        val value2 = value[it]
                        put(it, addSuffix(it, value2))
                    }
                }
            } else {
                console.error("Don't know how to handle non query sub-object")
            }
        } else {
            put(it, addSuffix(it, value))
        }
    }
}


//class EmptyProps : RProps
class PropsWithJsStyle(var style: JsObject?) : RProps

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun CSSBuilder.toJsStyle() = toStyle() as JsObject

@Suppress("EnumEntryName")
enum class MAlignment {
    inherit, left, center, right, justify
}

@Suppress("EnumEntryName")
enum class MColor {
    default, inherit, primary, secondary
}

@Suppress("EnumEntryName")
enum class MMargin {
    none, dense, normal
}

// This is moving to Breakpoint
//@Suppress("EnumEntryName")
//enum class MSize {
//    xs, sm, md, lg, xl;
//}

/**
 * Helper function to go from camel case to hyphen case (e.g. DefaultCase -> default-case)... there may be better implementations,
 * but at least it is central :-)
 */
fun String.toHyphenCase(): String {
    // TODO: Found a better implementation with caching, shall get rid of the old one soonish...
    // TODO: Scrap that, hyphenize doesn't handle one word values!
//    return hyphenize()
    var text = ""
    var isFirst = true
    this.forEach {
        if (it in 'A'..'Z') {
            if (!isFirst) text += "-"
            text += it.toLowerCase()
        } else {
            text += it
        }
        isFirst = false
    }
    return text
}

/**
 * Often for text fields we want to get the value that has changed from the event.
 * This makes it easier to do so.
 */
val Event.inputValue: String
    get() = (target as? HTMLInputElement)?.value ?: (target as? HTMLTextAreaElement)?.value ?: ""


/**
 * Sometimes we want to persist the event so that we can use it later (e.g. in a setState). This
 * makes it a little easier to do. (See react docs for more information)
 */
fun Event.persist() {
    asDynamic().persist()
}


/**
 * A simple event... not sure if there is another one in Kotlin, but couldn't find it...
 */
typealias SimpleEvent = () -> Unit
