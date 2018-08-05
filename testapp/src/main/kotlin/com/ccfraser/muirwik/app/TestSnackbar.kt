package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestSnackbar.ComponentStyles.fabMoveDown
import com.ccfraser.muirwik.app.TestSnackbar.ComponentStyles.fabMoveUp
import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.transitions.*
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.css.properties.translate3d
import kotlinx.html.style
import react.*
import react.dom.div
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestSnackbar : RComponent<RProps, RState>() {
    var simpleSnackbarOpen: Boolean = false
    var simpleSnackbarWithTextOpen: Boolean = false
    var positionedOpen: Boolean = false
    var transition1SnackbarOpen: Boolean = false
    var transition2SnackbarOpen: Boolean = false
    var fabMoveOptionOpen: Boolean = false
    var fabMoveSnackbarOpen: Boolean = false
    var hAnchor: SnackbarHorizAnchor = SnackbarHorizAnchor.Center
    var vAnchor: SnackbarVertAnchor = SnackbarVertAnchor.Bottom
    val altBuilder = RBuilder()

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val fabMoveUp by css {
            position = Position.absolute
            bottom = 2.spacingUnits
            right = 2.spacingUnits
            transform.translate3d(0.px, -46.px, 0.px)
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

    fun handleClose() {
        setState {
            simpleSnackbarOpen = false
            simpleSnackbarWithTextOpen = false
            positionedOpen = false
            transition1SnackbarOpen = false
            transition2SnackbarOpen = false
            fabMoveOptionOpen = false
            fabMoveSnackbarOpen = false
        }
    }

    // The props that come in from the snackbar already include some transition values and the details of the children... so we need
    // to include some of them and override others
    class SlideTransitionComponent(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
        override fun RBuilder.render() {
            mSlide(props.show, direction = SlideTransitionDirection.Left, timeout = SimpleTransitionTimeout(2000)) {
                props.children()
            }
        }
    }

    // Old way of doing it... don't need to clone it as above
    class SlideTransitionComponent2(props: RProps) : RComponent<RProps, RState>(props) {
        override fun RBuilder.render() {
            val e = mSlide(props.asDynamic().`in`, direction = SlideTransitionDirection.Left, timeout = SimpleTransitionTimeout(4000), addAsChild = false)
            childList.add(cloneElement(e, e.props, props.children))
        }
    }

    class FadeTransition(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
        override fun RBuilder.render() {
            mFade(props.show, timeout = SimpleTransitionTimeout(1500)) {
                props.children()
            }
        }
    }

    override fun RBuilder.render() {
        div {
            mButton("Simple Snackbar", onClick = { setState { simpleSnackbarOpen = true }})
            mButton("Simple Snackbar with Text", onClick = { setState { simpleSnackbarWithTextOpen = true }})
            mButton("Top Left", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Top; hAnchor = SnackbarHorizAnchor.Left }})
            mButton("Top Center", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Top; hAnchor = SnackbarHorizAnchor.Center }})
            mButton("Top Right", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Top; hAnchor = SnackbarHorizAnchor.Right }})
            mButton("Bottom Right", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Bottom; hAnchor = SnackbarHorizAnchor.Right }})
            mButton("Bottom Center", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Bottom; hAnchor = SnackbarHorizAnchor.Center }})
            mButton("Bottom Left", onClick = { setState { positionedOpen = true; vAnchor = SnackbarVertAnchor.Bottom; hAnchor = SnackbarHorizAnchor.Left }})
            mButton("Transition1", onClick = { setState { transition1SnackbarOpen = true }})
            mButton("Transition2", onClick = { setState { transition2SnackbarOpen = true }})
            mButton("Fab Move", onClick = { setState { fabMoveOptionOpen = true }})

            mSnackbar(altBuilder.span { +"Note archived" }, open = simpleSnackbarOpen, horizAnchor = SnackbarHorizAnchor.Left,
                    autoHideDuration = 4000,
                    onClose = { handleClose() },
                    action = altBuilder.div {
                        mButton("UNDO", color = MColor.Secondary, variant = MButtonVariant.Flat, size = MButtonSize.Small, onClick = { handleClose() })
                        mIconButton("close", onClick = { handleClose() } , color = MColor.Inherit)
                    }
            )

            mSnackbar("Note archived as text", open = simpleSnackbarWithTextOpen, horizAnchor = SnackbarHorizAnchor.Left,
                    autoHideDuration = 4000,
                    onClose = { handleClose() },
                    action = altBuilder.div {
                        mButton("UNDO", color = MColor.Secondary, variant = MButtonVariant.Flat, size = MButtonSize.Small, onClick = { handleClose() })
                        mIconButton("close", onClick = { handleClose() } , color = MColor.Inherit)
                    }
            )

            mSnackbar(message = "Positioned", open = positionedOpen, horizAnchor = hAnchor, vertAnchor = vAnchor,
                    autoHideDuration = 4000,
                    onClose = { handleClose() },
                    action = altBuilder.div {
                        mButton("UNDO", color = MColor.Secondary, variant = MButtonVariant.Flat, size = MButtonSize.Small, onClick = { handleClose() })
                        mIconButton("close", onClick = { handleClose() } , color = MColor.Inherit)
                    }
            )

            mSnackbar("Transitioned by Sliding...", altBuilder.div {
                mButton("UNDO", color = MColor.Secondary, variant = MButtonVariant.Flat, size = MButtonSize.Small, onClick = { handleClose() })
                mIconButton("close", onClick = { handleClose() } , color = MColor.Inherit)
            },
                    open = transition1SnackbarOpen,
                    transitionComponent = SlideTransitionComponent::class
            )

            mSnackbar("Transitioned by Fade...", altBuilder.div {
                mButton("UNDO", color = MColor.Secondary, variant = MButtonVariant.Flat, size = MButtonSize.Small, onClick = { handleClose() })
                mIconButton("close", onClick = { handleClose() } , color = MColor.Inherit)
            },
                    open = transition2SnackbarOpen,
                    transitionComponent = FadeTransition::class
            ) {
//                attrs.transitionComponent = ::transitionLeft
            }

            if (fabMoveOptionOpen) {
                val contentProps = PropsWithJsStyle(CSSBuilder().apply { width = 360.px }.toJsStyle())
                styledDiv {
                    css {
                        width = 360.px
                        height = 360.px
                        position = Position.relative
                        backgroundColor = Color(currentTheme.palette.background.paper)
                        overflow = Overflow.hidden
                    }
                    mButton("Show Snackbar", onClick = { setState { fabMoveSnackbarOpen = true }})
                    mButton("Hide Fab Move Div", onClick = { setState { fabMoveOptionOpen = false }})
                    mButtonFab("add") {
                        css(if (fabMoveSnackbarOpen) fabMoveUp else fabMoveDown)
                    }
                    mSnackbar("Just Testing", open = fabMoveSnackbarOpen, onClose = { setState { fabMoveSnackbarOpen = false }}, contentProps = contentProps) {
                        css { position = Position.absolute }
                    }
                }
            }
        }
    }
}


fun RBuilder.testSnackbar() = child(TestSnackbar::class) {}

