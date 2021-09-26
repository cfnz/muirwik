package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mDivider
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.buildElement
import styled.StyledHandler


@JsModule("@mui/material/List")
private external val listModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listComponentType: ComponentType<MListProps> = listModule.default

external interface MListProps : MButtonBaseProps {
    var dense: Boolean
    var disablePadding: Boolean
    var subheader: ReactElement
}

fun RBuilder.mList(
    dense: Boolean = false,
    disablePadding: Boolean = false,
    subheader: ReactElement? = null,
    component: String = "ul",
    className: String? = null,
    handler: StyledHandler<MListProps>? = null
) {
    createStyled(listComponentType, className, handler) {
        attrs.component = component
        attrs.dense = dense
        attrs.disablePadding = disablePadding
        subheader?.let { attrs.subheader = subheader }
    }
}

fun RBuilder.mList(
    subheader: String,
    addHeadDivider: Boolean = false,
    dense: Boolean = false,
    disablePadding: Boolean = false,
    component: String = "ul",
    className: String? = null,
    handler: StyledHandler<MListProps>? = null
) {
    createStyled(listComponentType, className, handler) {
        attrs.component = component
        attrs.dense = dense
        attrs.disablePadding = disablePadding

        @Suppress("UnsafeCastFromDynamic")
        attrs.subheader = buildElement { mListSubheader(subheader) }

        if (addHeadDivider) {
            childList.add( buildElement { mDivider() })
        }
    }
}
