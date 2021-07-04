package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.lab.alert.MAlertSeverity
import com.ccfraser.muirwik.components.lab.alert.MAlertVariant
import com.ccfraser.muirwik.components.lab.alert.mAlert
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.testapp.TestLabAlert.CustomStyles.margin
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.paddingBottom
import kotlinx.css.px
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestLabAlert : RComponent<RProps, RState>() {
    private object CustomStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(1.spacingUnits)
        }
    }

    var open = false
    var snackbarAlertText = ""

    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(16.px)
            }

            labNoteComponent()

            mGridContainer(MGridSpacing.spacing2) {
                val breakpoints = MGridBreakpoints(MGridSize.cells6)
                        .up(Breakpoint.lg, MGridSize.cells4)
                        .down(Breakpoint.sm, MGridSize.cells12)

                MAlertVariant.values().reversed().forEach { variant ->
                    mGridItem(breakpoints) {
                        mTypography("${variant.name.replaceFirstChar { it.titlecase() }} Alerts")
                        MAlertSeverity.values().forEach { severity ->
                            val an = if (severity.name[0] in listOf('a', 'e', 'i', 'o', 'u')) "an" else "a"
                            mAlert("This is $an ${severity.name} alert", variant, severity) {
                                css(margin)
                            }
                        }
                    }
                }
            }

            mTypography("This is an alert with a title")
            mAlert("Alert Title", "This is an error alert", severity = MAlertSeverity.error) {
                css(margin)
            }

            mTypography("This is an alert with a close (that does not do anything)")
            mAlert("Alert Title", "This is a success alert", onClose = { showSnackbarAlert("You hit close!") }) {
                css(margin)
            }

            mTypography("This is an alert with an action")
            mAlert("Alert Title", "This is a warning alert", severity = MAlertSeverity.warning, onClose = {}) {
                css(margin)
                attrs.action = mButton("Undo", addAsChild = false,
                        onClick = { showSnackbarAlert("You hit Undo!") })
            }
        }

        mButton("click me", onClick = { setState { open = true; snackbarAlertText = "Ths is a Snackbar Alert" } }, variant = MButtonVariant.outlined) {
            css(margin)
        }

        mSnackbar(open, autoHideDuration = 2500,
                onClose = { _, _: MSnackbarOnCloseReason -> setState { open = false } }) {
            mAlert(snackbarAlertText, severity = MAlertSeverity.info)
        }
    }

    private fun showSnackbarAlert(text: String) {
        setState {
            snackbarAlertText = text
            open = true
        }
    }
}

fun RBuilder.testLabAlert() = child(TestLabAlert::class) {}