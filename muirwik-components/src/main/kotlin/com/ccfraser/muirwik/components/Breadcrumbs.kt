package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Breadcrumbs")
private external val breadcrumbsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val breadcrumbsComponentType: ComponentType<MBreadcrumbsProps> = breadcrumbsModule.default

external interface MBreadcrumbsProps: StyledPropsWithCommonAttributes {
    var component: ElementType
    var expandText: String
    var itemsAfterCollapse: Int
    var itemsBeforeCollapse: Int
    var maxItems: Int
    var separator: ReactNode
}

fun RBuilder.breadcrumbs(
    separator: String,
    maxItems: Int = 8,
    handler: StyledHandler<MBreadcrumbsProps>
) {
    createStyled(breadcrumbsComponentType, handler) {
        attrs.maxItems = maxItems
        attrs.separator = ReactNode(separator)
    }
}

fun RBuilder.breadcrumbs(handler: StyledHandler<MBreadcrumbsProps>) {
    createStyled(breadcrumbsComponentType, handler)
}



@Deprecated("Use the simpler 'non m' version.")
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
    @Suppress("DEPRECATION")
    mBreadcrumbs(separator.asDynamic() as ReactElement, maxItems, itemsBeforeCollapse, itemsAfterCollapse, component, className, handler)
}

@Deprecated("Use the simpler 'non m' version.")
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

