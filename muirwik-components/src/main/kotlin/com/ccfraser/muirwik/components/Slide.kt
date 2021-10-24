package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.HTMLElement
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Slide")
private external val slideModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val slideComponentType: ComponentType<SlideProps> = slideModule.default

@Suppress("EnumEntryName")
enum class SlideTransitionDirection {
    left, right, up, down
}

external interface SlideProps : TransitionProps {
    var container: HTMLElement
}
var SlideProps.direction by EnumPropToString(SlideTransitionDirection.values())
var SlideProps.timeout by TransitionDurationDelegate()

fun RBuilder.slide(
    show: Boolean = false,
    direction: SlideTransitionDirection = SlideTransitionDirection.down,
    handler: StyledHandler<SlideProps>
) {
    createStyled(slideComponentType, handler) {
        attrs.direction = direction
        attrs.show = show

        // Seem to need these two for the thing that is sliding to disappear rather than scroll of the page.
        attrs.asDynamic().mountOnEnter = true
        attrs.asDynamic().unmountOnExit = true
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mSlide(
    show: Boolean = false,
    direction: SlideTransitionDirection = SlideTransitionDirection.down,
    timeout: TransitionDuration? = null,
    container: HTMLElement? = null,
    className: String? = null,
    handler: StyledHandler<SlideProps>? = null
) {
    createStyled(slideComponentType, className, handler) {
        attrs.direction = direction
        attrs.show = show
        timeout?.let { attrs.timeout = it }
        container?.let { attrs.container = container }

        // Seem to need these two for the thing that is sliding to disappear rather than scroll of the page.
        attrs.asDynamic().mountOnEnter = true
        attrs.asDynamic().unmountOnExit = true
    }
}

