package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.OnEventWithReasonDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/SpeedDial")
private external val speedDialModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val speedDialComponentType: ComponentType<SpeedDialProps> = speedDialModule.default

@Suppress("EnumEntryName")
enum class SpeedDialDirection {
    down, left, right, up
}

@Suppress("EnumEntryName")
enum class SpeedDialOnOpenReason {
    toggle, focus, mouseEnter;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}
@Suppress("EnumEntryName")
enum class SpeedDialOnCloseReason {
    toggle, blur, mouseLeave, escapeKeyDown;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface SpeedDialProps : StyledProps {
    var ariaLabel: String

    @JsName("FabProps")
    var fabProps: FabProps

    var hidden: Boolean
    var icon: ReactNode
    var open: Boolean
    var openIcon: ReactNode
    var TransitionComponent: ElementType<TransitionProps>

    @JsName("TransitionProps")
    var transitionProps: TransitionProps
}
var SpeedDialProps.direction by EnumPropToString(SpeedDialDirection.values())
var SpeedDialProps.onClose by OnEventWithReasonDelegate(SpeedDialOnCloseReason.values())
var SpeedDialProps.onOpen by OnEventWithReasonDelegate(SpeedDialOnOpenReason.values())
var SpeedDialProps.transitionDuration by TransitionDurationDelegate()

fun RBuilder.speedDial(
    ariaLabel: String,
    icon: ReactNode = buildElement { speedDialIcon() },
    openIcon: ReactNode? = null,
    direction: SpeedDialDirection = SpeedDialDirection.up,
    handler: StyledHandler<SpeedDialProps>
) {
    createStyled(speedDialComponentType, handler) {
        attrs.ariaLabel = ariaLabel
        attrs.direction = direction
        attrs.icon = icon
        openIcon?.let { attrs.openIcon = it }
    }
}
