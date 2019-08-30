package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonBaseProps
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/ListItem")
private external val listItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemComponent: RComponent<MListItemProps, RState> = listItemModule.default

/**
 * This is for the vertical alignment of list items, for example, if you have an avatar and a long list item
 * (three lines or more for example), then you might want the avatar aligned to the the top of the list item
 * rather than being vertically centered - in this case, use flexStart.
 */
@Suppress("EnumEntryName")
enum class MListItemAlignItems {
    flexStart, center;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

interface MListItemProps : MButtonBaseProps {
    var button: Boolean

    @JsName("ContainerComponent")
    var containerComponent: String

    @JsName("ContainerProps")
    var containerProps: RProps?

    var dense: Boolean
    var disableGutters: Boolean
    var divider: Boolean
    var href: String?
    var selected: Boolean

    // TODO: should this have a value?
}
var MListItemProps.alignItems by EnumPropToString(MListItemAlignItems.values())


/**
 * More user friendly version (don't usually need to add children as the params here do pretty much all that is required)
 * For item with icon or avatar, use [mListItemWithIcon] or [mListItemWithAvatar]
 */
fun RBuilder.mListItem(
        primaryText: String,
        secondaryText: String? = null,
        selected: Boolean = false,
        key: String? = null,
        alignItems: MListItemAlignItems = MListItemAlignItems.center,
        divider: Boolean = true,
        hRefOptions: HRefOptions? = null,
        onClick: ((Event) -> Unit)? = null,
        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null): ReactElement {
    return mListItem(button = true, selected = selected, key = key, alignItems = alignItems, divider = divider,
            hRefOptions = hRefOptions, onClick = onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }
        mListItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

/**
 * More user friendly version with icon (don't usually need to add children as the params here do pretty much all that is required)
 */
fun RBuilder.mListItemWithIcon(
        iconName: String,
        primaryText: String,
        secondaryText: String? = null,
        selected: Boolean = false,
        key: String? = null,
        alignItems: MListItemAlignItems = MListItemAlignItems.center,
        divider: Boolean = true,
        useAvatar: Boolean = false,
        hRefOptions: HRefOptions? = null,
        onClick: ((Event) -> Unit)? = null,
        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null): ReactElement {
    return mListItem(button = true, selected = selected, key = key, alignItems = alignItems, divider = divider,
            hRefOptions = hRefOptions, onClick = onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }

        if (useAvatar) {
            mListItemAvatar { mAvatar { mIcon(iconName) } }
        } else {
            mListItemIcon(iconName)
        }
        mListItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

/**
 * More user friendly version with avatar (don't usually need to add children as the params here do pretty much all that is required)
 * Note, for icon with avatar use [mListItemWithIcon]
 */
fun RBuilder.mListItemWithAvatar(
        avatarSrc: String,
        primaryText: String,
        secondaryText: String? = null,
        selected: Boolean = false,
        key: String? = null,
        alignItems: MListItemAlignItems = MListItemAlignItems.center,
        divider: Boolean = true,
        hRefOptions: HRefOptions? = null,
        onClick: ((Event) -> Unit)? = null,
        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null): ReactElement {
    return mListItem(button = true, selected = selected, key = key, alignItems = alignItems, divider = divider,
            hRefOptions = hRefOptions, onClick =  onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }
        mListItemAvatar { mAvatar(avatarSrc) }
        mListItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

/**
 * Full version, but you will need to use other components as children, e.g. mListItemText
 */
fun RBuilder.mListItem(
        button: Boolean = false,
        component: String? = null,
        containerComponent: String = "li",
        selected: Boolean = false,
        key: String? = null,
        alignItems: MListItemAlignItems = MListItemAlignItems.center,
        containerProps: RProps? = null,
        dense: Boolean = false,
        disableGutters: Boolean = false,
        divider: Boolean = false,
        hRefOptions: HRefOptions? = null,
        onClick: ((Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null) = createStyled(listItemComponent) {
    attrs.alignItems = alignItems
    attrs.button = button
    component?.let { attrs.component = component }
    attrs.containerComponent = containerComponent
    containerProps?.let { attrs.containerProps = containerProps }
    attrs.dense = dense
    attrs.disableGutters = disableGutters
    attrs.divider = divider
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    attrs.selected = selected
    key?.let { attrs.key = key }
    attrs.selected = selected

    setStyledPropsAndRunHandler(className, handler)
}
