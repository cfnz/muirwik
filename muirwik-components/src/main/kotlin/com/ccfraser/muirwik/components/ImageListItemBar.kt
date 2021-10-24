package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageListItemBar")
private external val imageListItemBarModule: dynamic
private val imageListItemBarComponentType: ComponentType<ImageListItemBarProps> = imageListItemBarModule.default

@Suppress("EnumEntryName")
enum class ActionPosition {
    left, right
}

@Suppress("EnumEntryName")
enum class ImageListItemBarPosition {
    below, bottom, top
}

external interface ImageListItemBarProps: StyledProps {
    var actionIcon: ReactNode
    var subtitle: ReactNode
    var title: ReactNode
}
var ImageListItemBarProps.actionPosition by EnumPropToString(ActionPosition.values())
var ImageListItemBarProps.position by EnumPropToString(ImageListItemBarPosition.values())


/**
 * This mImageListTileBar allows easier to use title and subtitle strings.
 */
fun RBuilder.imageListItemBar(
    title: String,
    subtitle: String? = null,
    handler: StyledHandler<ImageListItemBarProps>? = null
) {
    createStyled(imageListItemBarComponentType, handler) {
        subtitle?.let { attrs.subtitle = ReactNode(it) }
        attrs.title = ReactNode(title)
    }
}

/**
 * This mImageListTileBar allows easier to use title and subtitle strings.
 */
fun RBuilder.mImageListItemBar(
    title: String,
    subtitle: String? = null,
    actionIcon: ReactNode? = null,
    position: ImageListItemBarPosition = ImageListItemBarPosition.bottom,
    actionPosition: ActionPosition = ActionPosition.right,
    className: String? = null,
    handler: StyledHandler<ImageListItemBarProps>? = null
) {
    createStyled(imageListItemBarComponentType, className, handler) {
        actionIcon?.let { attrs.actionIcon = it }
        attrs.actionPosition = actionPosition

        @Suppress("UnsafeCastFromDynamic")
        subtitle?.let { attrs.subtitle = it.asDynamic() }

        @Suppress("UnsafeCastFromDynamic")
        attrs.title = title.asDynamic()

        attrs.position = position
    }
}

fun RBuilder.mImageListItemBar(
    title: ReactNode,
    subtitle: ReactNode? = null,
    actionIcon: ReactNode? = null,
    position: ImageListItemBarPosition = ImageListItemBarPosition.bottom,
    actionPosition: ActionPosition = ActionPosition.right,
    className: String? = null,
    handler: StyledHandler<ImageListItemBarProps>? = null
) {
    createStyled(imageListItemBarComponentType, className, handler) {
        actionIcon?.let { attrs.actionIcon = it }
        attrs.actionPosition = actionPosition
        subtitle?.let { attrs.subtitle = it }
        attrs.title = title
        attrs.position = position
    }
}
