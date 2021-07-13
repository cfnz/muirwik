package com.ccfraser.muirwik.components.gridlist

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridListTileBar")
private external val gridListTileBarModule: dynamic
private val gridListTileBarComponentType: ComponentType<MGridListTileBarProps> = gridListTileBarModule.default

@Suppress("EnumEntryName")
enum class MActionPosition {
    left, right
}

@Suppress("EnumEntryName")
enum class MTitlePosition {
    top, bottom
}

external interface MGridListTileBarProps: StyledProps {
    var actionIcon: ReactElement
    var subtitle: ReactElement
    var title: ReactElement
}
var MGridListTileBarProps.actionPosition by EnumPropToString(MActionPosition.values())
var MGridListTileBarProps.titlePosition by EnumPropToString(MTitlePosition.values())


/**
 * This mGridListTileBar allows easier to use title and subtitle strings.
 */
fun RBuilder.mGridListTileBar(
        title: String,
        subtitle: String? = null,
        actionIcon: ReactElement? = null,
        titlePosition: MTitlePosition = MTitlePosition.bottom,
        actionPosition: MActionPosition = MActionPosition.right,

        className: String? = null,
        handler: StyledHandler<MGridListTileBarProps>? = null) = createStyled(gridListTileBarComponentType) {
    actionIcon?.let { attrs.actionIcon = it }
    attrs.actionPosition = actionPosition

    @Suppress("UnsafeCastFromDynamic")
    subtitle?.let { attrs.subtitle = it.asDynamic() }

    @Suppress("UnsafeCastFromDynamic")
    attrs.title = title.asDynamic()

    attrs.titlePosition = titlePosition

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mGridListTileBar(
        title: ReactElement,
        subtitle: ReactElement? = null,
        actionIcon: ReactElement? = null,
        titlePosition: MTitlePosition = MTitlePosition.bottom,
        actionPosition: MActionPosition = MActionPosition.right,

        className: String? = null,
        handler: StyledHandler<MGridListTileBarProps>? = null) = createStyled(gridListTileBarComponentType) {
    actionIcon?.let { attrs.actionIcon = it }
    attrs.actionPosition = actionPosition
    subtitle?.let { attrs.subtitle = it }
    attrs.title = title
    attrs.titlePosition = titlePosition

    setStyledPropsAndRunHandler(className, handler)
}
