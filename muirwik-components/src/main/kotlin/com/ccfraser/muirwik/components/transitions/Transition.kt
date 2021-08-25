package com.ccfraser.muirwik.components.transitions

import react.Component
import react.State
import styled.StyledProps
import kotlin.reflect.KClass

/**
 * We reference the show (or 'in' in the Material UI docs) prop in some generic transition situations, for example
 * see TestMenus and TestSnackbar
 */
external interface MTransitionProps : StyledProps {
    @JsName("in")
    var show: Boolean
}

typealias TransitionComponent = KClass<out Component<MTransitionProps, State>>