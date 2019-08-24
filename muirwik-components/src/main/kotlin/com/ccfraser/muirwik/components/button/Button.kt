package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Button")
private external val buttonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonComponent: RComponent<MButtonProps, RState> = buttonModule.default


interface MButtonProps : MButtonBaseProps {
    var color: MColor
    var disableFocusRipple: Boolean
    var fullWidth: Boolean
    var href: String
    var size: MButtonSize
    var variant: MButtonVariant
}

private fun MButtonProps.redefineTypedProps() {
    this.asDynamic().color = color.toString()
    this.asDynamic().size = size.toString()
    this.asDynamic().variant = variant.toString()
}

fun RBuilder.mButton(
        caption: String,
        color: MColor = MColor.default,
        variant: MButtonVariant = MButtonVariant.text,
        disabled: Boolean = false,
        onClick: ((Event) -> Unit)? = null,
        size: MButtonSize = MButtonSize.medium,
        hRefOptions: HRefOptions? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MButtonProps>? = null) = createStyled(buttonComponent, addAsChild) {
    attrs.color = color
    attrs.disabled = disabled
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    attrs.size = size
    attrs.variant = variant

    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}

