package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@material-ui/core/Breadcrumbs")
private external val breadcrumbsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val breadcrumbsComponentType: ComponentType<MBreadcrumbsProps> = breadcrumbsModule.default

external interface MBreadcrumbsProps: StyledPropsWithCommonAttributes {
    var component: String
    var itemsAfterCollapse: Int
    var itemsBeforeCollapse: Int
    var maxItems: Int
    var separator: ReactElement
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun RBuilder.mBreadcrumbs(
    separator: String = "/",
    maxItems: Int = 8,
    itemsBeforeCollapse: Int = 1,
    itemsAfterCollapse: Int = 1,
    component: String = "nav",
    className: String? = null,
    handler: StyledHandler<MBreadcrumbsProps>? = null
) {
    mBreadcrumbs(separator.asDynamic() as ReactElement, maxItems, itemsBeforeCollapse, itemsAfterCollapse, component, className, handler)
}


fun RBuilder.mBreadcrumbs(
    separator: ReactElement,
    maxItems: Int = 8,
    itemsBeforeCollapse: Int = 1,
    itemsAfterCollapse: Int = 1,
    component: String = "nav",
    className: String? = null,
    handler: StyledHandler<MBreadcrumbsProps>? = null
) {
    createStyled(breadcrumbsComponentType, className, handler) {
        attrs.component = component
        attrs.itemsAfterCollapse = itemsAfterCollapse
        attrs.itemsBeforeCollapse = itemsBeforeCollapse
        attrs.maxItems = maxItems
        attrs.separator = separator
    }
}

