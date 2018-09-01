package com.ccfraser.muirwik.wrapper.list

import com.ccfraser.muirwik.wrapper.*
import kotlinx.css.padding
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.css


@JsModule("@material-ui/core/ListItem")
private external val listItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemComponent: RComponent<MListItemProps, RState> = listItemModule.default

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

/**
 * More user friendly version (don't usually need to add children as the params here do pretty much all that is required)
 *
 * Note: The compact param works slightly differently depending on whether an avatar is used. If an avatar is used,
 * the list item wraps the icon in a mAvatar and then a mListItemAvatar which gives it a slightly vertically compact
 * form.
 *
 * However, we found that if you don't use an avatar, your list item would actually turn out bigger than using an
 * mListItemAvatar... so, if you set compact to be true and do not use an avatar, we override the usual padding (which
 * seems to be 1.5 spacing units) and make it just 1 spacing unit as defined by the theme.
 *
 * Note that compact is is not the same as dense which also makes the font smaller.
 */
fun RBuilder.mListItem(
        primaryText: String,
        secondaryText: String? = null,
        iconName: String? = null,
        selected: Boolean = false,
        key: String? = null,
        href: String? = null,
        divider: Boolean = true,
        compact: Boolean = false,
        useAvatar: Boolean = false,
        onClick: ((Event) -> Unit)? = null,
        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null): ReactElement {
    val e = mListItem(button = true, divider = divider, key = key, selected = selected, href = href,
            onClick = onClick, className = className) {

        if (!useAvatar && compact) {
            css { padding(vertical = 1.spacingUnits) }
        }

        // NOTE: This code is similar to mMenuItem... if you make changes here, look there too...
        // TODO: Refactor similar code
        href?.let { attrs.component = "a" }

        if (iconName != null) {
            if (useAvatar) {
                if (compact) {
                    mListItemAvatar { mAvatar { mIcon(iconName) } }
                } else {
                    mAvatar { mIcon(iconName) }
                }
            } else {
                mListItemIcon(iconName)
            }
        }
        mListItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
    return e
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
        containerProps: RProps? = null,
        dense: Boolean = false,
        disableGutters: Boolean = false,
        divider: Boolean = false,
        href: String? = null,
        onClick: ((Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MListItemProps>? = null) = createStyled(listItemComponent) {
    attrs.button = button
    component?.let { attrs.component = component }
    attrs.containerComponent = containerComponent
    containerProps?.let { attrs.containerProps = containerProps }
    attrs.dense = dense
    attrs.disableGutters = disableGutters
    attrs.divider = divider
    href?.let { attrs.href = href }
    onClick?.let { attrs.onClick = onClick }
    attrs.selected = selected
    key?.let { attrs.key = key }
    attrs.selected = selected

    setStyledPropsAndRunHandler(className, handler)
}
