package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MAlertSeverity
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.mAlert
import com.ccfraser.muirwik.components.mSnackbar
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.TestAlert.CustomStyles.margin
import kotlinx.css.margin
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.setState
import styled.StyleSheet
import styled.css

class TestAlert : RComponent<RProps, RState>() {
  private object CustomStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val margin by css {
      margin(1.spacingUnits)
    }
  }

  var open = false

  override fun RBuilder.render() {
    div {
      MAlertSeverity.values().forEach { severity ->
        mAlert("This is a ${severity.name} alert", severity = severity) {
          css(margin)
        }
      }

      mButton("click me", onClick = { setState { open = true } }, variant = MButtonVariant.outlined) {
        css(margin)
      }

      mSnackbar(open = open) {
        mAlert("Snackbar Alert")
      }
    }
  }

}

fun RBuilder.testAlert() = child(TestAlert::class) {}