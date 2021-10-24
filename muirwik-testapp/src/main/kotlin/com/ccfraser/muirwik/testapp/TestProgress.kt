package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.buttonProgress
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.buttonSuccess
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.fabProgress
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.wrapper
import kotlinx.browser.window
import kotlinx.css.*
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.math.min
import kotlin.random.Random


class TestProgress : RComponent<Props, State>() {
    private var loading = false
    private var success = false
    private var timerId: Int? = null
    private var determinateTimerId: Int? = null
    private var circularDeterminateValue = 0
    private var linearValue = 0
    private var linearBuffer = 0

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val wrapper by css {
            margin(1.spacingUnits)
            position = Position.relative
        }
        val buttonSuccess by css {
            backgroundColor = Colors.Green.shade500
            hover {
                backgroundColor = Colors.Green.shade700
            }
        }
        val fabProgress by css {
            color = Colors.Green.shade500
            position = Position.absolute
            top = -(10.px)
            left = -(10.px)
            zIndex = 1
        }
        val buttonProgress by css {
            color = Colors.Green.shade500
            position = Position.absolute
            top = 50.pct
            left = 50.pct
            marginTop = -12.px
            marginLeft = -12.px
        }
    }

    override fun componentWillUnmount() {
        timerId?.let { window.clearInterval(it) }
        determinateTimerId?.let { window.clearInterval(it) }
        timerId = null
        determinateTimerId = null
    }

    override fun componentDidMount() {
        determinateTimerId = window.setInterval({
            setState { if (circularDeterminateValue >= 100) circularDeterminateValue = 0 else circularDeterminateValue++ }

            // We shall just use the value of the circularDeterminateValue so we don't update the linear too often
            if (circularDeterminateValue % 20 == 0) {
                if (linearValue >= 100) {
                    setState { linearValue = 0 }
                } else {
                    val diff = Random.nextDouble() * 10
                    val diff2 = Random.nextDouble() * 10

                    setState {
                        linearValue = min(100, linearValue + diff.toInt())
                        linearBuffer = min(100, linearValue + diff.toInt() + diff2.toInt())
                    }
                }
            }
        }, 20)
    }


    fun onClick() {
        if (!loading) {
            setState {
                success = false
                loading = true
            }
            timerId = window.setTimeout( {
                setState {
                    success = true
                    loading = false
                }}, 2000)
        }
    }

    override fun RBuilder.render() {
        typography("Circular Indeterminate")
        div{
            circularProgress { css { margin(2.spacingUnits) }}
            circularProgress(size = 50.px) { css { margin(2.spacingUnits) }}
            circularProgress(color = CircularProgressColor.secondary) { css { margin(2.spacingUnits) }}
            circularProgress {
                attrs.thickness = 7.0
                css {
                    margin(2.spacingUnits)
                    color = Colors.Purple.shade500
                }
            }
        }
        br { }
        typography("Interactive Integration")
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
            }
            styledDiv {
                css(wrapper)
                fab(if (success) "done" else "save") {
                    attrs.onClick = { onClick() }
                    if (success) {
                        css(buttonSuccess)
                    }
                    if (loading) {
                        circularProgress(size = 68.px) { css(fabProgress) }
                    }
                }
            }
            styledDiv {
                css(wrapper)
                button("Accept terms", variant = ButtonVariant.contained) {
                    attrs.disabled = loading
                    attrs.onClick = { onClick() }
                    if (success) {
                        css(buttonSuccess)
                    }
                }
                if (loading) {
                    circularProgress(size = 24.px) { css(buttonProgress) }
                }
            }
        }
        br { }
        typography("Circular Determinate")
        div {
            circularProgress(circularDeterminateValue.toDouble(), variant = CircularProgressVariant.determinate) { css { margin(2.spacingUnits) }}
            circularProgress(circularDeterminateValue.toDouble(), variant = CircularProgressVariant.determinate, size = 50.px) { css { margin(2.spacingUnits) }}
            circularProgress(circularDeterminateValue.toDouble(), variant = CircularProgressVariant.determinate, color = CircularProgressColor.secondary) { css { margin(2.spacingUnits) }}
            circularProgress(circularDeterminateValue.toDouble(), variant = CircularProgressVariant.determinate) {
                attrs.thickness = 7.0
                css {
                    margin(2.spacingUnits)
                    color = Colors.Purple.shade500
                }
            }
        }
        br { }
        typography("Linear Indeterminate")
        linearProgress()
        br { }
        typography("Linear Determinate")
        linearProgress(color = LinearProgressColor.secondary, variant = LinearProgressVariant.determinate, value = linearValue.toDouble())
        br { }
        typography("Linear Buffer")
        linearProgress(variant = LinearProgressVariant.buffer, value = linearValue.toDouble()) {
            attrs.valueBuffer = linearBuffer.toDouble()
        }
    }
}
