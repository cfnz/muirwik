package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
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
private val cardHeaderComponentType: ComponentType<MCardHeaderProps> = cardHeaderModule.default

external interface MCardHeaderProps : StyledProps {
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
 * options and control, you can use the full version of [mCardHeader]
 */
@Suppress("UnsafeCastFromDynamic")
fun RBuilder.mCardHeader(
    title: String,
    subHeader: String? = null,
    avatar: ReactElement? = null,
    action: ReactElement? = null,
    className: String? = null,
    handler: StyledHandler<MCardHeaderProps>? = null
) {
    val titleNode: Node? = title.asDynamic()
    val subHeaderNode: Node? = subHeader?.asDynamic()
    return mCardHeader(titleNode, subHeaderNode, avatar, action, className = className, handler = handler)
}

/**
 * The full version of mCardHeader.
 */
fun RBuilder.mCardHeader(
    title: Node? = null,
    subHeader: Node? = null,
    avatar: ReactElement? = null,
    action: ReactElement? = null,
    className: String? = null,
    handler: StyledHandler<MCardHeaderProps>? = null
) {
    createStyled(cardHeaderComponentType, className, handler) {
        action?.let { attrs.action = it }
        avatar?.let { attrs.avatar = it }
        subHeader?.let { attrs.subheader = it }
        title?.let { attrs.title = it }
    }
}