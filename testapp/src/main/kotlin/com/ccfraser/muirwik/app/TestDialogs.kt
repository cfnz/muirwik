package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestDialogs.ComponentStyles.avatarStyle
import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.dialog.*
import com.ccfraser.muirwik.wrapper.list.mList
import com.ccfraser.muirwik.wrapper.list.mListItem
import com.ccfraser.muirwik.wrapper.list.mListItemAvatar
import com.ccfraser.muirwik.wrapper.list.mListItemText
import com.ccfraser.muirwik.wrapper.transitions.MTransitionProps
import com.ccfraser.muirwik.wrapper.transitions.SlideTransitionDirection
import com.ccfraser.muirwik.wrapper.transitions.mSlide
import kotlinext.js.jsObject
import kotlinx.css.Position
import kotlinx.html.InputType
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css
import kotlin.reflect.KClass


class TestDialogs : RComponent<RProps, TestOptionControls.MyTestState>() {
    private var selectedValue: String = ""
    private var simpleDialogOpen: Boolean = false
    private var alertDialogOpen: Boolean = false
    private var confirmationDialogOpen: Boolean = false
    private var confirmationDialogScrollOpen: Boolean = false
    private var fullScreenDialogOpen: Boolean = false
    private var formDialogOpen: Boolean = false

    private var confirmationDialogValue: String = ""
    private var confirmationDialogSelectedValue: String = ""

    private var alertTransition: KClass<out RComponent<MTransitionProps, RState>>? = null
    private val slowTransitionProps: MTransitionProps = jsObject { timeout = 1000 }
    private var slow = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val avatarStyle by css {
            backgroundColor = Colors.Blue.shade100
            color = Colors.Blue.shade600
        }
    }

    class SlideUpTransitionComponent(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
        override fun RBuilder.render() {
            // This works but is a bit long winded and does not use our function
//            val p2: RProps = EmptyProps();
//            p2.asDynamic().direction = "up"
//
//            val e1 = React.createElement(slideComponent, props)
//            val c1 = React.cloneElement(e1, p2)
//            childList.add(c1)


            // This works, and is a bit nicer than the above, but still does not use our function
//            val addionalProps = EmptyProps()
//            addionalProps.asDynamic().direction = "up"
//            childList.add(React.cloneElement(React.createElement(slideComponent, props), addionalProps))


            // This works but with a warning in the console
//            val e2 = React.cloneElement(mSlide(direction = SlideTransitionDirection.up, handler = {
//                attrs.direction = "left"
//            }), props )
//            childList.add(e2)


            // Yay, this works with no warning... just have to use a different RBuilder or addAsChild = false!!
//            childList.add(cloneElement(RBuilder().mSlide(direction = SlideTransitionDirection.up, handler = {}), props))
            childList.add(cloneElement(mSlide(direction = SlideTransitionDirection.down, addAsChild = false), props))
        }
    }

//    class SlideUpTransitionComponent2(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
//        override fun RBuilder.render() {
//            childList.add(mSlide(props.show, direction = SlideTransitionDirection.down, timeout = SimpleTransitionTimeout(3000), addAsChild = false) {
//                props.children()
//            })
//        }
//    }


    override fun RBuilder.render() {
        div {
            mTypography(variant = MTypographyVariant.subheading) {
                +"Selected Value: $selectedValue"
                br {  }
                mCheckboxInLabel("Slow the transition down a bit", id="t", checked = slow, onChange = { _, value -> setState { slow = value } })
                br {  }
                br {  }
                +"Dialog Types:"
            }
            br {  }
            mButton("Open Simple", onClick = { setState { simpleDialogOpen = true }})
            mButton("Open Alert", onClick = { setState {alertDialogOpen = true; alertTransition = null }})
            mButton("Slide Alert", onClick = { setState {alertDialogOpen = true; alertTransition = SlideUpTransitionComponent::class}})
            mButton("Confirmation", onClick = { setState {
                confirmationDialogValue = confirmationDialogSelectedValue
                confirmationDialogOpen = true
            }})
            mButton("Confirmation Diff Scroll", onClick = { setState {
                confirmationDialogValue = confirmationDialogSelectedValue
                confirmationDialogScrollOpen = true
            }})
            mButton("Full Screen", onClick = { setState {fullScreenDialogOpen = true}})
            mButton("Form", onClick = { setState {formDialogOpen = true}})

            // Need to render whether simpleSnackbarOpen or closed so the close transition will occur!
            simpleDialog(simpleDialogOpen)
            alertDialog(alertDialogOpen)
            confirmationDialog(confirmationDialogOpen)
            confirmationDialog(confirmationDialogScrollOpen, DialogScroll.body)
            fullScreenDialog(fullScreenDialogOpen)
            formDialog(formDialogOpen)
        }
    }

    private fun RBuilder.simpleDialog(open: Boolean) {
        val emails = listOf("username@gmail.com", "user02@gmail.com")
        mDialog(open, onClose = { _, _ -> setState { simpleDialogOpen = false}}, transitionProps = if (slow) slowTransitionProps else null) {
            mDialogTitle("Set backup account")
            div {
                mList {
                    emails.forEach {email ->
                        mListItem(button = true, onClick = { setState {
                            this@TestDialogs.selectedValue = email
                            this@TestDialogs.simpleDialogOpen = false
                        }}) {
                            mListItemAvatar {
                                css(avatarStyle)
                                mAvatar {
                                    mIcon("person")
                                }
                            }
                            mListItemText(primary = email)
                        }
                    }
                    mListItem("add account", iconName = "add", divider = false, useAvatar = true, onClick = { setState {
                        this@TestDialogs.selectedValue = "add account"
                        this@TestDialogs.simpleDialogOpen = false
                    }})
                }
            }
        }
    }

    private fun RBuilder.alertDialog(open: Boolean) {
        fun closeAlertDialog() {
            setState { alertDialogOpen = false }
        }

        mDialog(open, onClose = { _, _ ->  closeAlertDialog() }, transitionComponent = alertTransition, transitionProps = if (slow) slowTransitionProps else null) {
            mDialogTitle("Use Google's location service?")
            mDialogContent {
                mDialogContentText("Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.")
            }
            mDialogActions {
                mButton("Disagree", true, onClick = { closeAlertDialog() })
                mButton("Agree", true, onClick = { closeAlertDialog() })
            }
        }
    }

    private fun RBuilder.confirmationDialog(open: Boolean, scroll: DialogScroll = DialogScroll.paper) {
        val options = arrayOf("None", "Atria", "Callisto", "Dione", "Ganymede", "Hangouts Call", "Luna", "Oberon",
                "Phobos", "Pyxis", "Sedna", "Titania", "Triton", "Umbriel")
//        mDialog(disableBackdropClick = true, disableEscapeKeyDown = true, maxWidth = DialogMaxWidth.xs) {
//        mDialog(disableEscapeKeyDown = true, maxWidth = DialogMaxWidth.xs) {

        mDialog(open, scroll = scroll, disableEscapeKeyDown = true, transitionProps = if (slow) slowTransitionProps else null) {
            mDialogTitle("Phone Ringtone")
            mDialogContent {
                mRadioGroup(value = confirmationDialogValue, onChange = {_, value -> setState { confirmationDialogValue = value }} ) {
                    options.forEach {
                        mRadioInLabel(it, value = it)
                    }
                }
            }
            mDialogActions {
                mButton("Cancel", color = MColor.primary, onClick = { setState {
                    confirmationDialogOpen = false
                    confirmationDialogScrollOpen = false
                }})
                mButton("Ok", color = MColor.primary, onClick = { setState {
                    confirmationDialogSelectedValue = confirmationDialogValue;
                    confirmationDialogOpen = false
                    confirmationDialogScrollOpen = false
                }})
            }
        }
    }

    private fun RBuilder.fullScreenDialog(open: Boolean) {
        fun handleClose() {
            setState { fullScreenDialogOpen = false}
        }
        mDialog(open, fullScreen = true, transitionComponent = SlideUpTransitionComponent::class, transitionProps = if (slow) slowTransitionProps else null, onClose = { _, _ -> handleClose() }) {
            mAppBar {
                css {
                    position = Position.relative
                }
                mToolbar {
                    mIconButton(iconName = "close", color = MColor.inherit, iconColor = MIconColor.inherit, onClick = { handleClose() })
                    mToolbarTitle("Sound")
                    mButton("save", variant = MButtonVariant.text, color = MColor.inherit, onClick = { handleClose() })
                }
            }
            mListItem(primaryText = "Phone ringtone", secondaryText = "Titania", divider = true)
            mListItem(primaryText = "Default notification ringtone", secondaryText = "Tethys")
        }
    }

    private fun RBuilder.formDialog(open: Boolean) {
        fun handleClose() {
            setState { formDialogOpen = false}
        }
        mDialog(open, onClose =  { _, _ -> handleClose() }, transitionProps = if (slow) slowTransitionProps else null) {
            mDialogTitle("Subscribe")
            mDialogContent {
                mDialogContentText("To subscribe to this website, please enter your email address here. We will send updates occationally.")
                mTextField("Email Address", autoFocus = true, margin = MTextFieldMargin.dense, type = InputType.email, fullWidth = true)
            }
            mDialogActions {
                mButton("Cancel", color = MColor.primary, onClick = { handleClose() }, variant = MButtonVariant.text)
                mButton("Subscribe", color = MColor.primary, onClick = { handleClose() }, variant = MButtonVariant.text)
            }
        }
    }
}


fun RBuilder.testDialogs() = child(TestDialogs::class) {}
