package com.ccfraser.muirwik.components

import styled.StyledProps

/**
 * We reference the show (or 'in' in the Material UI docs) prop in some generic transition situations, for example
 * see TestMenus and TestSnackbar
 */
external interface TransitionProps : StyledProps {
    @JsName("in")
    var show: Boolean
}

//typealias TransitionComponent = KClass<out Component<TransitionProps, State>>