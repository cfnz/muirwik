package com.ccfraser.muirwik.wrapper.transitions

import styled.StyledProps

/**
 * We reference thw show (or in) prop in some generic transition situations, for example see TestMenus and TestSnackbar
 */
external interface MTransitionProps : StyledProps {
    @JsName("in")
    var show: Boolean

    var timeout: dynamic
}
