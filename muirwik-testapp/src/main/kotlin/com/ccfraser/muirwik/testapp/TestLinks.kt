package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.HRefOptions
import com.ccfraser.muirwik.testapp.TestLinks.CustomTabStyles.linkMargin
import kotlinx.css.margin
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.StyleSheet
import styled.css


class TestLinks : RComponent<Props, State>() {
    private object CustomTabStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val linkMargin by css {
            margin(1.spacingUnits)
        }
    }
    override fun RBuilder.render() {
        typography {
            mLink("Link Target Blank", hRefOptions = HRefOptions("#")) { css(linkMargin)}
            mLink("color = \"inherit\"", hRefOptions = HRefOptions("#", false)) {
                css(linkMargin)
                attrs.color = TypographyColor.inherit
            }
            mLink("variant = \"body2\"", hRefOptions = HRefOptions("#", false)) {
                css(linkMargin)
                attrs.variant = TypographyVariant.body2
            }
        }
    }
}
