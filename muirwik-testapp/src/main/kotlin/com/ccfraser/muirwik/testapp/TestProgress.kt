package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.buttonProgress
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.buttonSuccess
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.fabProgress
import com.ccfraser.muirwik.testapp.TestProgress.ComponentStyles.wrapper
import kotlinx.css.*
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.browser.window
import kotlin.js.Math
import kotlin.math.min


class TestProgress : RComponent<RProps, RState>() {
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
            top = -6.px
            left = -6.px
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
                    val diff = Math.random() * 10
                    val diff2 = Math.random() * 10

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
        mTypography("Circular Indeterminate")
        div{
            mCircularProgress { css { margin(2.spacingUnits) }}
            mCircularProgress(size = 50.px) { css { margin(2.spacingUnits) }}
            mCircularProgress(color = MCircularProgressColor.secondary) { css { margin(2.spacingUnits) }}
            mCircularProgress(thickness = 7.0) {
                css {
                    margin(2.spacingUnits)
                    color = Colors.Purple.shade500
                }
            }
        }
        br { }
        mTypography("Interactive Integration")
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
            }
            styledDiv {
                css(wrapper)
                mButtonFab(if (success) "done" else "save", primary = true, onClick = { onClick() }) {
                    if (success) {
                        css(buttonSuccess)
                    }
                    if (loading) {
                        mCircularProgress(size = 68.px) { css(fabProgress) }
                    }
                }
            }
            styledDiv {
                css(wrapper)
                mButton("Accept terms", true, disabled = loading, variant = MButtonVariant.contained, onClick = { onClick() }) {
                    if (success) {
                        css(buttonSuccess)
                    }
                }
                if (loading) {
                    mCircularProgress(size = 24.px) { css(buttonProgress) }
                }
            }
        }
        br { }
        mTypography("Circular Determinate")
        div {
            mCircularProgress(variant = MCircularProgressVariant.determinate, value = circularDeterminateValue.toDouble()) { css { margin(2.spacingUnits) }}
            mCircularProgress(variant = MCircularProgressVariant.determinate, value = circularDeterminateValue.toDouble(), size = 50.px) { css { margin(2.spacingUnits) }}
            mCircularProgress(variant = MCircularProgressVariant.determinate, value = circularDeterminateValue.toDouble(), color = MCircularProgressColor.secondary) { css { margin(2.spacingUnits) }}
            mCircularProgress(variant = MCircularProgressVariant.determinate, value = circularDeterminateValue.toDouble(), thickness = 7.0) {
                css {
                    margin(2.spacingUnits)
                    color = Colors.Purple.shade500
                }
            }
        }
        br { }
        mTypography("Linear Indeterminate")
        mLinearProgress()
        br { }
        mTypography("Linear Determinate")
        mLinearProgress(color = MLinearProgressColor.secondary, variant = MLinearProgressVariant.determinate, value = linearValue.toDouble())
        br { }
        mTypography("Linear Buffer")
        mLinearProgress(variant = MLinearProgressVariant.buffer, value = linearValue.toDouble(), valueBuffer = linearBuffer.toDouble())
    }
}

fun RBuilder.testProgress() = child(TestProgress::class) {}
