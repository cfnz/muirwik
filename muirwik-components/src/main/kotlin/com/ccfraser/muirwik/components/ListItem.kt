package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import react.ComponentType
import react.Props
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/ListItem")
private external val listItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemComponentType: ComponentType<ListItemProps> = listItemModule.default

/**
 * This is for the vertical alignment of list items, for example, if you have an avatar and a long list item
 * (three lines or more for example), then you might want the avatar aligned to the the top of the list item
 * rather than being vertically centered - in this case, use flexStart.
 */
@Suppress("EnumEntryName")
enum class ListItemAlignItems {
    center, flexStart;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface ListItemProps : StyledPropsWithCommonAttributes {
    var component: ElementType
    var components: dynamic
    var componentsProps: Props?
    var dense: Boolean
    var disableGutters: Boolean
    var disablePadding: Boolean
    var divider: Boolean
    var secondaryAction: ReactNode
}
var ListItemProps.alignItems by EnumPropToString(ListItemAlignItems.values())

fun RBuilder.listItem(handler: StyledHandler<ListItemProps>) {
    createStyled(listItemComponentType, handler)
}
