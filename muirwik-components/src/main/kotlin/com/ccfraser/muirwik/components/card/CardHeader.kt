package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import org.w3c.dom.Node
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/CardHeader")
private external val cardHeaderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardHeaderComponent : RComponent<MCardHeaderProps, RState> = cardHeaderModule.default

interface MCardHeaderProps : StyledProps {
    var action: ReactElement
    var avatar: ReactElement
    var title: Node
    var subheader: Node
}

@Suppress("UnsafeCastFromDynamic")
fun RBuilder.mCardHeader(title: String,
                         subHeader: String? = null,
                         avatar: ReactElement? = null,
                         action: ReactElement? = null,
                         className: String? = null,
                         handler: StyledHandler<MCardHeaderProps>? = null): ReactElement {

    val titleNode: Node? = title.asDynamic()
    val subHeaderNode: Node? = subHeader?.asDynamic()
    return mCardHeader(titleNode, subHeaderNode, avatar, action, className, handler)
}

fun RBuilder.mCardHeader(title: Node? = null,
                         subHeader: Node? = null,
                         avatar: ReactElement? = null,
                         action: ReactElement? = null,
                         className: String? = null,
                         handler: StyledHandler<MCardHeaderProps>? = null) = createStyled(cardHeaderComponent) {
    action?.let { attrs.action = it }
    avatar?.let { attrs.avatar = it }
    title?.let { attrs.title = it }
    subHeader?.let { attrs.subheader = it }

    setStyledPropsAndRunHandler(className, handler)
}
