package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.PropsWithJsStyle
import com.ccfraser.muirwik.components.utils.spreadProps
import com.ccfraser.muirwik.components.utils.toJsStyle
import com.ccfraser.muirwik.testapp.TestSnackbar.ComponentStyles.fabMoveDown
import com.ccfraser.muirwik.testapp.TestSnackbar.ComponentStyles.fabMoveUp
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.css.properties.translate3d
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestSnackbar : RComponent<Props, State>() {
    var simpleSnackbarOpen: Boolean = false
    var simpleSnackbarWithTextOpen: Boolean = false
    var positionedOpen: Boolean = false
    var transition1SnackbarOpen: Boolean = false
    var transition2SnackbarOpen: Boolean = false
    var delaySnackbarOpen: Boolean = false
    var fabMoveOptionOpen: Boolean = false
    var fabMoveSnackbarOpen: Boolean = false
    var hAnchor: SnackbarAnchorHorizontal = SnackbarAnchorHorizontal.center
    var vAnchor: SnackbarAnchorVertical = SnackbarAnchorVertical.bottom

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val fabMoveUp by css {
            position = Position.absolute
            bottom = 2.spacingUnits
            right = 2.spacingUnits
            transform.translate3d(0.px, -60.px, 0.px)
            transition("transform", 195.ms, Timing.easeInOut, 0.ms)
        }
        val fabMoveDown by css {
            position = Position.absolute
            bottom = 2.spacingUnits
            right = 2.spacingUnits
            transform.translate3d(0.px, 0.px, 0.px)
            transition(::transform, 225.ms, Timing.easeInOut)
        }
    }

    private fun handleClose(reason: SnackbarOnCloseReason) {
        println("Close Reason: $reason, ordinal value ${reason.ordinal}")
        closeAll()
    }

    private fun closeAll() {
        setState {
            simpleSnackbarOpen = false
            simpleSnackbarWithTextOpen = false
            positionedOpen = false
            transition1SnackbarOpen = false
            transition2SnackbarOpen = false
            delaySnackbarOpen = false
            fabMoveOptionOpen = false
            fabMoveSnackbarOpen = false
        }
    }

    private val slideTransitionComponent = forwardRef { props: Props, ref -> slide(direction = SlideTransitionDirection.up) { attrs.ref = ref; spreadProps(props)} }
    private val fadeTransitionComponent = forwardRef { props: Props, ref -> fade {
        attrs.timeout = SimpleTransitionDuration(1000)
        attrs.ref = ref
        spreadProps(props)}
    }

    override fun RBuilder.render() {
        div {
            button("Simple Snackbar") { attrs.onClick = { setState { simpleSnackbarOpen = true } } }
            button("Simple Snackbar With Text") { attrs.onClick = { setState { simpleSnackbarWithTextOpen = true } } }
            button("Top Left") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.top; hAnchor = SnackbarAnchorHorizontal.left } } }
            button("Top Center") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.top; hAnchor = SnackbarAnchorHorizontal.center } } }
            button("Top Right") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.top; hAnchor = SnackbarAnchorHorizontal.right } } }
            button("Bottom Right") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.bottom; hAnchor = SnackbarAnchorHorizontal.right } } }
            button("Bottom Center") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.bottom; hAnchor = SnackbarAnchorHorizontal.center } } }
            button("Bottom Left") { attrs.onClick = { setState { positionedOpen = true; vAnchor = SnackbarAnchorVertical.bottom; hAnchor = SnackbarAnchorHorizontal.left } } }
            button("Transition1") { attrs.onClick = { setState { transition1SnackbarOpen = true } } }
            button("Transition2") { attrs.onClick = { setState { transition2SnackbarOpen = true } } }
            button("Duration") { attrs.onClick = { setState { delaySnackbarOpen = true } } }
            button("Fab Move") { attrs.onClick = { setState { fabMoveOptionOpen = true } } }

            snackbar("Note archived", simpleSnackbarOpen) {
                attrs.autoHideDuration = 4000
                attrs.onClose = { _, reason: SnackbarOnCloseReason -> handleClose(reason) }
                attrs.action = buildAction()
            }

            // Using attrs instead of params
            snackbar("Note archived as text", simpleSnackbarWithTextOpen) {
                attrs.onClose = { _, reason: SnackbarOnCloseReason -> handleClose(reason) }
                attrs.autoHideDuration = 4000
                attrs.action = buildAction()
            }

            // Wrapping Params for comparison
            snackbar("Positioned", positionedOpen) {
                attrs.anchorOriginHorizontal = hAnchor
                attrs.anchorOriginVertical = vAnchor
                attrs.autoHideDuration = 4000
                attrs.onClose = { _, reason: SnackbarOnCloseReason -> handleClose(reason) }
                attrs.action = buildAction()
            }

            snackbar("Transitioned by Sliding...", transition1SnackbarOpen) {
                attrs.transitionComponent = slideTransitionComponent
//                attrs.transitionComponent = SlideTransitionComponent::class
                attrs.action = buildAction()
            }

            snackbar("Transitioned by Fade...", open = transition2SnackbarOpen) {
//                attrs.transitionComponent = FadeTransition::class
                attrs.transitionComponent = fadeTransitionComponent
                attrs.action = buildAction()
            }

            snackbar("Custom Transition Delays...", delaySnackbarOpen) {
                attrs.onClose = { _, reason: SnackbarOnCloseReason -> handleClose(reason)}
                attrs.transitionDuration = EnterExitTransitionDuration(500, 2000)
            }

            if (fabMoveOptionOpen) {
                val contentProps = PropsWithJsStyle(CssBuilder().apply { width = 360.px }.toJsStyle())

                themeContext.Consumer { theme ->
                    styledDiv {
                        css {
                            width = 360.px
                            height = 360.px
                            position = Position.relative
                            backgroundColor = Color(theme.palette.background.paper)
                            overflow = Overflow.hidden
                        }
                        button("Show Snackbar") { attrs.onClick = { setState { fabMoveSnackbarOpen = true } } }
                        button("Hide Fab Move Div") { attrs.onClick = { setState { fabMoveOptionOpen = false } } }
                        fab("add") {
                            css(if (fabMoveSnackbarOpen) fabMoveUp else fabMoveDown)
                        }
                        snackbar("Just Testing", fabMoveSnackbarOpen) {
                            attrs.onClose = { _, _ -> setState { fabMoveSnackbarOpen = false }}
                            attrs.contentProps = contentProps
                            css { position = Position.absolute }
                        }
                    }
                }
            }
        }
    }

    private fun buildAction() = buildElement {
        div {
            button("UNDO", ButtonColor.secondary, ButtonVariant.text) { attrs.size = ButtonSize.small; attrs.onClick = { closeAll() } }
            iconButton("close", IconButtonColor.inherit) { attrs.onClick = { closeAll() } }
        }
    }
}
