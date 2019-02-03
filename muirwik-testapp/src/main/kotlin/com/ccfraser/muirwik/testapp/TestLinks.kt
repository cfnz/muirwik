package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestLinks.CustomTabStyles.linkMargin
import kotlinx.css.margin
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyleSheet
import styled.css


class TestLinks : RComponent<RProps, RState>() {
    private object CustomTabStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val linkMargin by css {
            margin(1.spacingUnits)
        }
    }
    override fun RBuilder.render() {
        mTypography {
            mLink("Link Target Blank", href = "#", targetBlank = true) { css(linkMargin)}
            mLink("color = \"inherit\"", href = "#", color = MTypographyColor.inherit) { css(linkMargin)}
            mLink("variant = \"body2\"", href = "#", variant = MTypographyVariant.body2) { css(linkMargin)}
        }
    }
}

fun RBuilder.testLinks() = child(TestLinks::class) {}
