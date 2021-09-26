package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.MTypographyProps
import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/ListItemText")
private external val listItemTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemTextComponentType: ComponentType<MListItemTextProps> = listItemTextModule.default

external interface MListItemTextProps : MButtonBaseProps {
    var disableTypography: Boolean
    var inset: Boolean
    var primary: ReactElement
    var primaryTypographyProps: MTypographyProps
    var secondary: ReactElement
    var secondaryTypographyProps: MTypographyProps
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
    handler: StyledHandler<MListItemTextProps>? = null
) {
    @Suppress("UnsafeCastFromDynamic")
    val primaryAsElement: ReactElement = primary.asDynamic()

    @Suppress("UnsafeCastFromDynamic")
    val secondaryAsElement: ReactElement? = secondary?.asDynamic()

    mListItemText(
        primaryAsElement,
        secondaryAsElement,
        inset,
        disableTypography,
        className,
        handler)
}

/**
 * List item that takes react elements as primary and secondary items. Often you can use the version that takes
 * strings instead.
 */
fun RBuilder.mListItemText(
    primary: ReactElement? = null,
    secondary: ReactElement? = null,
    inset: Boolean = false,
    disableTypography: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MListItemTextProps>? = null
) {
    createStyled(listItemTextComponentType, className, handler) {
        attrs.disableTypography = disableTypography
        attrs.inset = inset
        primary?.let { attrs.primary = primary }
        secondary?.let { attrs.secondary = secondary }
    }
}
