package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.*
import com.ccfraser.muirwik.testapp.TestGridsAndBreakpointsStyles.paper
import kotlinx.css.*
import kotlinx.css.Color
import react.Props
import react.RBuilder
import react.dom.div
import react.fc
import styled.StyleSheet
import styled.css

// We set this in the functional component as it is used in the style sheets (and seems to get set before style sheets need it).
private lateinit var theme: Theme

private object TestGridsAndBreakpointsStyles : StyleSheet("TestGridsAndBreakpointStyles", isStatic = true) {
    val paper by css {
        padding(LinearDimension(theme.spacing(2)))
        textAlign = TextAlign.center
        color = Color(theme.palette.text.secondary)
    }
}


val testGridsAndBreakpoints = fc<Props> {
    theme = useTheme()

    fun RBuilder.item(xsGridSize: GridSize, smGridSize: GridSize?, text: String) {
        gridItem {
            attrs.xs = xsGridSize
            smGridSize?.let { attrs.sm = it }
            paper {
                css(paper)
                +text
            }
        }
    }

    demoPanel("Basic Grid") {
        gridContainer(GridSpacing.spacing3) {
            item(GridSize.cells12, null, "xs=12")
            item(GridSize.cells6, null, "xs=6")
            item(GridSize.cells6, null, "xs=6")
            item(GridSize.cells3, null, "xs=3")
            item(GridSize.cells3, null, "xs=3")
            item(GridSize.cells3, null, "xs=3")
            item(GridSize.cells3, null, "xs=3")
        }
    }
    demoPanel("Grid with breakpoints") {
        gridContainer(GridSpacing.spacing3) {
            item(GridSize.cells12, null, "xs=12")
            item(GridSize.cells12, GridSize.cells6, "xs=12 sm=6")
            item(GridSize.cells12, GridSize.cells6, "xs=12 sm=6")
            item(GridSize.cells6, GridSize.cells3, "xs=6 sm=3")
            item(GridSize.cells6, GridSize.cells3, "xs=6 sm=3")
            item(GridSize.cells6, GridSize.cells3, "xs=6 sm=3")
            item(GridSize.cells6, GridSize.cells3, "xs=6 sm=3")
        }
    }

    demoPanel("Breakpoints Info") {
        typography("up(sm) ${theme.breakpoints.up(Breakpoint.sm)}")
        typography("up(md) ${theme.breakpoints.up(Breakpoint.md)}")
        typography("up(lg) ${theme.breakpoints.up(Breakpoint.lg)}")
        typography("dn(sm) ${theme.breakpoints.down(Breakpoint.sm)}")
        typography("dn(md) ${theme.breakpoints.down(Breakpoint.md)}")
        typography("dn(lg) ${theme.breakpoints.down(Breakpoint.lg)}")
        typography("between sm and md ${theme.breakpoints.between(Breakpoint.sm, Breakpoint.md)}")
        typography("only sm ${theme.breakpoints.only(Breakpoint.sm)}")
        typography("width sm ${theme.breakpoints.value(Breakpoint.sm)}")
    }

    demoPanel("useMediaQuery") {
        typography("(Resize me)")
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
