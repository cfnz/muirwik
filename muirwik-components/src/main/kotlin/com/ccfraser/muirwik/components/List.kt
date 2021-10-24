package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.buildElement
import styled.StyledHandler


@JsModule("@mui/material/List")
private external val listModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val listComponentType: ComponentType<ListProps> = listModule.default

external interface ListProps : ButtonBaseProps {
    var dense: Boolean
    var disablePadding: Boolean
    var subheader: ReactElement
}

fun RBuilder.list(handler: StyledHandler<ListProps>) {
    createStyled(listComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mList(
    dense: Boolean = false,
    disablePadding: Boolean = false,
    subheader: ReactElement? = null,
    component: String = "ul",
    className: String? = null,
    handler: StyledHandler<ListProps>? = null
) {
    createStyled(listComponentType, className, handler) {
        attrs.component = component
        attrs.dense = dense
        attrs.disablePadding = disablePadding
        subheader?.let { attrs.subheader = subheader }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
fun RBuilder.mList(
    subheader: String,
    addHeadDivider: Boolean = false,
    dense: Boolean = false,
    disablePadding: Boolean = false,
    component: String = "ul",
    className: String? = null,
    handler: StyledHandler<ListProps>? = null
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
