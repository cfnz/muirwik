package com.ccfraser.muirwik.components

import kotlinext.js.Object
import kotlinext.js.getOwnPropertyNames
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import react.*
import styled.StyledElementBuilder
import styled.StyledHandler
import styled.StyledProps
import styled.toStyle
import kotlin.js.Json
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
 * Spreads the props passed in to the props of the component.
 *
 * Used in situations like this jsx: <TextField {...params} label="Combo box" />
 *
 * Applied like:
 *     val props = propsFromSomewhere
 *     mTextField("Combo Box", variant = MFormControlVariant.outlined) {
 *         spreadProps(props)
 *     }
 *
 * Note: if the props contains and prop used in the constructor, the constructor version will get overwritten,
 * i.e. in this example, if the props includes the variant prop, it will overwrite the constructor param.
 */
fun RElementBuilder<RProps>.spreadProps(props: RProps) {
    val json: Json = props.asDynamic()

    json.getOwnPropertyNames().forEach {
        attrs.asDynamic()[it] = json[it]
    }
}
//
//fun RProps.spreadProps(nprops: RProps) {
//    val json: Json = nprops.asDynamic()
//
//    json.getOwnPropertyNames().forEach {
//        attrs.asDynamic()[it] = json[it]
//    }
//}

/**
 * This is only a simple jsObjectToCss converter. So far it can only handle
 * a jsObject that is one layer deep, or two layers if the second layer is a
 * media query (used initially just to convert the currentTheme.mixins.toolbar)
 */
fun CSSBuilder.toolbarJsCssToPartialCss(jsObject: Object) {
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
        if (value != null && jsTypeOf(value) == "object") {
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
@JsExport
class PropsWithJsStyle(var style: Object?) : RProps

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun CSSBuilder.toJsStyle() = toStyle() as Object

@Suppress("EnumEntryName")
enum class MAlignment {
    inherit, left, center, right, justify
}

@Suppress("EnumEntryName")
enum class MColor {
    default, inherit, primary, secondary
}

@Suppress("EnumEntryName")
enum class MOptionColor {
    primary, secondary, default
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
 * This makes it easier to do so (it also works for all input fields).
 */
val Event.targetInputValue: String
    get() = (target as? HTMLInputElement)?.value ?: (target as? HTMLTextAreaElement)?.value ?: ""

/**
 * If the input is a checkbox, and it is checked, then return true otherwise return false.
 */
val Event.targetChecked: Boolean
    get() = (target as? HTMLInputElement)?.checked ?: false

val Event.targetValue: Any
    get() = target.asDynamic().value as Any

/**
 * Sometimes we want to persist the event so that we can use it later (e.g. in a setState). This
 * makes it a little easier to do. (See react docs for more information)
 */
fun Event.persist() {
    asDynamic().persist()
}

/**
 * Just wrapping some options related to href's to make them a bit easier to use and less parameters to ignore
 * when not using them. Note that, same as the [setHRefTargetNoOpener] params, if targetBlank is true, the
 * value of target will be ignored.
 */
data class HRefOptions(val href: String, val targetBlank: Boolean = true, val target: String? = "")

/**
 * See the other [setHRefTargetNoOpener] for more information.
 */
fun setHRefTargetNoOpener(attrs: RProps, hRefOptions: HRefOptions) {
    setHRefTargetNoOpener(attrs, hRefOptions.href, hRefOptions.targetBlank, hRefOptions.target)
}

/**
 * This is a convenience function (which might end up somewhere else) that handles setting of the href, target and rel
 * properties of various components. These components often don't have the target and rel properties as it is expected in
 * Material-UI to just pass them to the root element.
 *
 * When the targetBlank parameter is true it overrides anything in target with "_blank" setting the rel prop to "nopener"
 * as recommended in https://material-ui.com/style/links/. When targetBlank is true target will not be used.
 *
 * This function only takes effect if href is not null.
 */
fun setHRefTargetNoOpener(attrs: RProps, href: String?, targetBlank: Boolean, target: String?) {
    href?.let {
        attrs.asDynamic().href = it

        target?.let { attrs.asDynamic().target = it }
        // We have not got a prop for target, so we will let a parent element sort it.

        if (targetBlank) {
            attrs.asDynamic().target = "_blank"
            attrs.asDynamic().rel = "noopener"
        }
    }
}

/**
 * A simple event... not sure if there is another one in Kotlin, but couldn't find it...
 */
typealias SimpleEvent = () -> Unit

/**
 * Type type often used on the Component prop which should be a String or a Component... since we don't have
 * union types, we will use Any for now.
 */
typealias ElementType = Any