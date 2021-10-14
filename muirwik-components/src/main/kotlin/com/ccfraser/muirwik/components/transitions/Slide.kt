package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.HTMLElement
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Slide")
private external val slideModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val slideComponentType: ComponentType<MSlideProps> = slideModule.default

@Suppress("EnumEntryName")
enum class SlideTransitionDirection {
    left, right, up, down
}

external interface MSlideProps : MTransitionProps {
    var container: HTMLElement
}
var MSlideProps.direction by EnumPropToString(SlideTransitionDirection.values())
var MSlideProps.timeout by TransitionDurationDelegate()

fun RBuilder.mSlide(
    show: Boolean = false,
    direction: SlideTransitionDirection = SlideTransitionDirection.down,
    timeout: TransitionDuration? = null,
    container: HTMLElement? = null,
    className: String? = null,
    handler: StyledHandler<MSlideProps>? = null
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

