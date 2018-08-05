package com.ccfraser.muirwik.wrapper.list

import com.ccfraser.muirwik.wrapper.MButtonBaseProps
import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import react.dom.span
import styled.StyledHandler


@JsModule("@material-ui/core/ListItemText")
private external val listItemTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemTextComponent: RComponent<MListItemTextProps, RState> = listItemTextModule.default

interface MListItemTextProps : MButtonBaseProps {
    var disableTypography: Boolean
    var inset: Boolean
    var primary: ReactElement
    var secondary: ReactElement
}

fun RBuilder.mListItemText(
        primary: ReactElement? = null,
        secondary: ReactElement? = null,
        inset: Boolean = false,
        disableTypography: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MListItemTextProps>? = null) = createStyled(listItemTextComponent) {
    attrs.disableTypography = disableTypography
    attrs.inset = inset
    primary?.let { attrs.primary = primary }
    secondary?.let { attrs.secondary = secondary }

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * This list item is a little simple to user as you can pass strings for primary and secondary...
 */
fun RBuilder.mListItemText(
        primary: String,
        secondary: String? = null,
        inset: Boolean = false,
        disableTypography: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MListItemTextProps>? = null): ReactElement {

    @Suppress("UnsafeCastFromDynamic")
    val primaryAsElement: ReactElement = primary.asDynamic()

    @Suppress("UnsafeCastFromDynamic")
    val secondaryAsElement: ReactElement? = secondary?.asDynamic()

    return mListItemText(
            primaryAsElement,
            secondaryAsElement,
            inset,
            disableTypography,
            className,
            handler)
}
