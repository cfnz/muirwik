package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler

@JsModule("@mui/material/ListItemText")
private external val listItemTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemTextComponentType: ComponentType<ListItemTextProps> = listItemTextModule.default

external interface ListItemTextProps : ButtonBaseProps {
    var disableTypography: Boolean
    var inset: Boolean
    var primary: ReactNode
    var primaryTypographyProps: TypographyProps
    var secondary: ReactNode
    var secondaryTypographyProps: TypographyProps
}

fun RBuilder.listItemText(primary: ReactNode? = null, secondary: ReactNode? = null, handler: StyledHandler<ListItemTextProps>? = null) {
    createStyled(listItemTextComponentType, handler) {
        primary?.let { attrs.primary = primary }
        secondary?.let { attrs.secondary = secondary }
    }
}

fun RBuilder.listItemText(primary: String, secondary: String? = null, handler: StyledHandler<ListItemTextProps>? = null) {
    createStyled(listItemTextComponentType, handler) {
        attrs.primary = ReactNode(primary)
        secondary?.let { attrs.secondary = ReactNode(it) }
    }
}

fun RBuilder.listItemText(primary: String, secondary: String? = null, className: String, handler: StyledHandler<ListItemTextProps>? = null) {
    createStyled(listItemTextComponentType, className, handler) {
        attrs.primary = ReactNode(primary)
        secondary?.let { attrs.secondary = ReactNode(it) }
    }
}


@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
/**
 * This list item is a little simple to user as you can pass strings for primary and secondary...
 */
fun RBuilder.mListItemText(
    primary: String,
    secondary: String? = null,
    inset: Boolean = false,
    disableTypography: Boolean = false,
    className: String? = null,
    handler: StyledHandler<ListItemTextProps>? = null
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

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
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
    handler: StyledHandler<ListItemTextProps>? = null
) {
    createStyled(listItemTextComponentType, className, handler) {
        attrs.disableTypography = disableTypography
        attrs.inset = inset
        primary?.let { attrs.primary = primary }
        secondary?.let { attrs.secondary = secondary }
    }
}
