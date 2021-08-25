package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.*
import com.ccfraser.muirwik.testapp.TestGridsAndBreakpointsStyles.paper
import com.ccfraser.muirwik.testapp.TestGridsAndBreakpointsStyles.root
import kotlinx.css.*
import kotlinx.css.Color
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv

// We set this in the functional component as it is used in the style sheets (and seems to get set before style sheets need it).
private lateinit var theme: Theme

private object TestGridsAndBreakpointsStyles : StyleSheet("TestGridsAndBreakpointStyles", isStatic = true) {
    val root by css {
        flexGrow = 1.0
        margin(2.spacingUnits)
    }

    val paper by css {
        padding(theme.spacing(2).px)
        textAlign = TextAlign.center
        color = Color(theme.palette.text.secondary)
    }
}


val testGridsAndBreakpoints = fc<Props> {
    theme = useTheme()

    fun RBuilder.item(xsGridSize: MGridSize, smGridSize: MGridSize?, text: String) {
        mGridItem(xs = xsGridSize) {
            smGridSize?.let { attrs.sm = it }
            mPaper {
                css(paper)
                +text
            }
        }
    }

    styledDiv {
        css(root)
        mTypography("Basic Grid", MTypographyVariant.h4)
        mGridContainer(MGridSpacing.spacing3) {
            item(MGridSize.cells12, null, "xs=12")
            item(MGridSize.cells6, null, "xs=6")
            item(MGridSize.cells6, null, "xs=6")
            item(MGridSize.cells3, null, "xs=3")
            item(MGridSize.cells3, null, "xs=3")
            item(MGridSize.cells3, null, "xs=3")
            item(MGridSize.cells3, null, "xs=3")
        }
    }
    styledDiv {
        css(root)
        mTypography("Grid with breakpoints", MTypographyVariant.h4)
        mGridContainer(MGridSpacing.spacing3) {
            item(MGridSize.cells12, null, "xs=12")
            item(MGridSize.cells12, MGridSize.cells6, "xs=12 sm=6")
            item(MGridSize.cells12, MGridSize.cells6, "xs=12 sm=6")
            item(MGridSize.cells6, MGridSize.cells3, "xs=6 sm=3")
            item(MGridSize.cells6, MGridSize.cells3, "xs=6 sm=3")
            item(MGridSize.cells6, MGridSize.cells3, "xs=6 sm=3")
            item(MGridSize.cells6, MGridSize.cells3, "xs=6 sm=3")
        }
    }

    styledDiv {
        css(root)
        mTypography("Breakpoints Info", MTypographyVariant.h4)
        mTypography("up(sm) ${theme.breakpoints.up(Breakpoint.sm)}")
        mTypography("up(md) ${theme.breakpoints.up(Breakpoint.md)}")
        mTypography("up(lg) ${theme.breakpoints.up(Breakpoint.lg)}")
        mTypography("dn(sm) ${theme.breakpoints.down(Breakpoint.sm)}")
        mTypography("dn(md) ${theme.breakpoints.down(Breakpoint.md)}")
        mTypography("dn(lg) ${theme.breakpoints.down(Breakpoint.lg)}")
        mTypography("between sm and md ${theme.breakpoints.between(Breakpoint.sm, Breakpoint.md)}")
        mTypography("only sm ${theme.breakpoints.only(Breakpoint.sm)}")
        mTypography("width sm ${theme.breakpoints.width(Breakpoint.sm)}")
    }

    styledDiv {
        css(root)
        mTypography("useMediaQuery", MTypographyVariant.h4)
        mTypography("(Resize me)")
        val matchesSm = useMediaQuery(theme.breakpoints.up(Breakpoint.sm))
        val matchesMd = useMediaQuery(theme.breakpoints.up(Breakpoint.md))
        val matchesLg = useMediaQuery(theme.breakpoints.up(Breakpoint.lg))

        // You can also pass useMediaQuery a function that takes a theme argument... eg.
        val matchesXl = useMediaQuery({ theme -> theme.breakpoints.up(Breakpoint.xl) })
        div { +"theme.breakpoints.up(Breakpoint.sm) matches: $matchesSm ${if (matchesSm) "(Show on Small and bigger)" else ""}" }
        div { +"theme.breakpoints.up(Breakpoint.md) matches: $matchesMd ${if (matchesMd) "(Show on Medium and bigger)" else ""}" }
        div { +"theme.breakpoints.up(Breakpoint.lg) matches: $matchesLg ${if (matchesLg) "(Show on Large and bigger)" else ""}" }
        div { +"theme.breakpoints.up(Breakpoint.xl) matches: $matchesXl ${if (matchesXl) "(Show on XL and bigger)" else ""}" }
    }
}
