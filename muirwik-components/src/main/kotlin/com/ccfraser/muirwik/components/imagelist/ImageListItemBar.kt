package com.ccfraser.muirwik.components.imagelist

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/ImageListItemBar")
private external val imageListItemBarModule: dynamic
private val imageListItemBarComponentType: ComponentType<MImageListItemBarProps> = imageListItemBarModule.default

@Suppress("EnumEntryName")
enum class MActionPosition {
    left, right
}

@Suppress("EnumEntryName")
enum class MTitlePosition {
    top, bottom
}

external interface MImageListItemBarProps: StyledProps {
    var actionIcon: ReactElement
    var subtitle: ReactElement
    var title: ReactElement
}
var MImageListItemBarProps.actionPosition by EnumPropToString(MActionPosition.values())
var MImageListItemBarProps.titlePosition by EnumPropToString(MTitlePosition.values())


/**
 * This mImageListTileBar allows easier to use title and subtitle strings.
 */
fun RBuilder.mImageListItemBar(
    title: String,
    subtitle: String? = null,
    actionIcon: ReactElement? = null,
    titlePosition: MTitlePosition = MTitlePosition.bottom,
    actionPosition: MActionPosition = MActionPosition.right,
    className: String? = null,
    handler: StyledHandler<MImageListItemBarProps>? = null
) {
    createStyled(imageListItemBarComponentType, className, handler) {
        actionIcon?.let { attrs.actionIcon = it }
        attrs.actionPosition = actionPosition

        @Suppress("UnsafeCastFromDynamic")
        subtitle?.let { attrs.subtitle = it.asDynamic() }

        @Suppress("UnsafeCastFromDynamic")
        attrs.title = title.asDynamic()

        attrs.titlePosition = titlePosition
    }
}

fun RBuilder.mImageListItemBar(
    title: ReactElement,
    subtitle: ReactElement? = null,
    actionIcon: ReactElement? = null,
    titlePosition: MTitlePosition = MTitlePosition.bottom,
    actionPosition: MActionPosition = MActionPosition.right,
    className: String? = null,
    handler: StyledHandler<MImageListItemBarProps>? = null
) {
    createStyled(imageListItemBarComponentType, className, handler) {
        actionIcon?.let { attrs.actionIcon = it }
        attrs.actionPosition = actionPosition
        subtitle?.let { attrs.subtitle = it }
        attrs.title = title
        attrs.titlePosition = titlePosition
    }
}
