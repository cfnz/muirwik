package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.testapp.TestAlert.CustomStyles.margin
import kotlinx.css.margin
import kotlinx.css.marginTop
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestAlert : RComponent<Props, State>() {
    private object CustomStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(1.spacingUnits)
        }
    }

    var open = false
    var snackbarAlertText = ""

    override fun RBuilder.render() {
        styledDiv {
            gridContainer(GridSpacing.spacing2) {
                val breakpoints = GridBreakpoints(GridSize.cells6)
                    .up(Breakpoint.lg, GridSize.cells4)
                    .down(Breakpoint.sm, GridSize.cells12)

                AlertVariant.values().reversed().forEach { variant ->
                    gridItem(breakpoints) {
                        demoPanel("${variant.name.replaceFirstChar { it.titlecase() }} Alerts") {
                            AlertSeverity.values().forEach { severity ->
                                val an = if (severity.name[0] in listOf('a', 'e', 'i', 'o', 'u')) "an" else "a"
                                alert("This is $an ${severity.name} alert", severity, variant) {
                                    css(margin)
                                }
                            }
                        }
                    }
                }
            }

            demoPanel("This is an alert with a title") {
                alert("Alert Title", "This is an error alert", severity = AlertSeverity.error)
            }

            demoPanel("This is an alert with a close (that does not do anything)") {
                alert("Alert Title", "This is a success alert") { attrs.onClose = { showSnackbarAlert("You hit close!") } }
            }

            demoPanel("This is an alert with an action") {
                alert("Alert Title", "This is a warning alert", AlertSeverity.warning) {
                    attrs.onClose = {}
                    attrs.action = buildElement {
                        button("Undo") {attrs.onClick = { showSnackbarAlert("You hit Undo!") } }
                    }
                }
            }
        }

        button("click me", variant = ButtonVariant.outlined) {
            attrs.onClick = { setState { open = true; snackbarAlertText = "Ths is a Snackbar Alert" } }
            css {
                marginTop = 2.spacingUnits
            }
        }

        snackbar(open) {
            attrs.autoHideDuration = 2500
            attrs.onClose = { _, _: SnackbarOnCloseReason -> setState { open = false } }
            alert(snackbarAlertText, AlertSeverity.info)
        }
    }

    private fun showSnackbarAlert(text: String) {
        setState {
            snackbarAlertText = text
            open = true
        }
    }
}
