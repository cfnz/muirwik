package com.ccfraser.muirwik.components.transitions

import styled.StyledProps

/**
 * We reference the show (or 'in' in the Material UI docs) prop in some generic transition situations, for example
 * see TestMenus and TestSnackbar
 */
external interface MTransitionProps : StyledProps {
    @JsName("in")
    var show: Boolean

    var timeout: dynamic
}
