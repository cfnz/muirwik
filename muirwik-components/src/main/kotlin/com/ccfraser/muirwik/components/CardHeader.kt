package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.Node
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/CardHeader")
private external val cardHeaderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardHeaderComponentType: ComponentType<CardHeaderProps> = cardHeaderModule.default

external interface CardHeaderProps : StyledProps {
    var action: ReactElement
    var avatar: ReactElement
    var component: String
    var disableTypography: Boolean
    var subheader: Node
    var subheaderTypographyProps: Props
    var title: Node
    var titleTypographyProps: Props
}

/**
 * This is a simpler version of a CardHeader allowing the use of strings for titles and subheaders. For more
 * options and control, you can use the simpler version and use attributes of [mCardHeader]
 */
@Suppress("UnsafeCastFromDynamic")
fun RBuilder.cardHeader(
    title: String,
    subHeader: String? = null,
    handler: StyledHandler<CardHeaderProps>? = null
) {
    createStyled(cardHeaderComponentType, handler) {
        attrs.title = title.asDynamic()
        subHeader?.let { attrs.subheader = it.asDynamic() }
    }
}

fun RBuilder.cardHeader(handler: StyledHandler<CardHeaderProps>) {
    createStyled(cardHeaderComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("UnsafeCastFromDynamic")
fun RBuilder.mCardHeader(
    title: String,
    subHeader: String? = null,
    handler: StyledHandler<CardHeaderProps>? = null
) {
    createStyled(cardHeaderComponentType, handler) {
        attrs.title = title.asDynamic()
        subHeader?.let { attrs.subheader = it.asDynamic() }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mCardHeader(handler: StyledHandler<CardHeaderProps>) {
    createStyled(cardHeaderComponentType, handler)
}