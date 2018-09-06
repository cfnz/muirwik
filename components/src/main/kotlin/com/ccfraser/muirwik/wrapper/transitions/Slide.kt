package com.ccfraser.muirwik.wrapper.transitions

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Slide")
private external val slideModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val slideComponent: RComponent<MSlideProps, RState> = slideModule.default

@Suppress("EnumEntryName")
enum class SlideTransitionDirection {
    left, right, up, down
}

external interface MSlideProps : MTransitionProps {
    var direction: String
}

fun RBuilder.mSlide(
        show: Boolean = false,
        direction: SlideTransitionDirection = SlideTransitionDirection.down,
        timeout: TransitionTimeout? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MSlideProps>? = null) = createStyled(slideComponent, addAsChild) {
    attrs.direction = direction.toString()
    attrs.show = show
    timeout?.let { attrs.timeout = timeout.value() }

    // Seem to need these two for the thing that is sliding to disappear rather than scroll of the page.
    attrs.asDynamic().mountOnEnter = true
    attrs.asDynamic().unmountOnExit = true

    setStyledPropsAndRunHandler(className, handler)
}

