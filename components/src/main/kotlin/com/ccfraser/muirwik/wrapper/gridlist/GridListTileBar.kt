package com.ccfraser.muirwik.wrapper.gridlist

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/GridListTileBar")
private external val gridListTileBarModule: dynamic
private val gridListTileBarComponent: RComponent<MGridListTileBarProps, RState> =gridListTileBarModule.default

enum class ActionPosition {
    Left, Right;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class TitlePosition {
    Top, Bottom;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MGridListTileBarProps: StyledProps {
    var actionIcon: ReactElement
    var actionPosition: String
    var subtitle: ReactElement
    var title: ReactElement
    var titlePosition: String
}

/**
 * This mGridListTileBar allows easier to use title and subtitle strings.
 */
fun RBuilder.mGridListTileBar(
        title: String,
        subtitle: String? = null,
        actionIcon: ReactElement? = null,
        titlePosition: TitlePosition = TitlePosition.Bottom,
        actionPosition: ActionPosition = ActionPosition.Right,

        className: String? = null,
        handler: StyledHandler<MGridListTileBarProps>? = null) = createStyled(gridListTileBarComponent) {
    actionIcon?.let { attrs.actionIcon = it }
    attrs.actionPosition = actionPosition.toString()

    @Suppress("UnsafeCastFromDynamic")
    subtitle?.let { attrs.subtitle = it.asDynamic() }

    @Suppress("UnsafeCastFromDynamic")
    attrs.title = title.asDynamic()

    attrs.titlePosition = titlePosition.toString()

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mGridListTileBar(
        title: ReactElement,
        subtitle: ReactElement? = null,
        actionIcon: ReactElement? = null,
        titlePosition: TitlePosition = TitlePosition.Bottom,
        actionPosition: ActionPosition = ActionPosition.Right,

        className: String? = null,
        handler: StyledHandler<MGridListTileBarProps>? = null) = createStyled(gridListTileBarComponent) {
    actionIcon?.let { attrs.actionIcon = it }
    attrs.actionPosition = actionPosition.toString()
    subtitle?.let { attrs.subtitle = it }
    attrs.title = title
    attrs.titlePosition = titlePosition.toString()

    setStyledPropsAndRunHandler(className, handler)
}
