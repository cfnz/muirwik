package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.components.utils.spreadProps
import com.ccfraser.muirwik.testapp.TestDialogs.ComponentStyles.avatarStyle
import kotlinx.css.Position
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.position
import kotlinx.html.InputType
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css

class TestDialogs : RComponent<Props, State>() {
    private var selectedValue = ""
    private var simpleDialogOpen = false
    private var alertDialogOpen = false
    private var confirmationDialogOpen = false
    private var confirmationDialogScrollOpen = false
    private var fullScreenDialogOpen = false
    private var formDialogOpen = false
    private var draggableDialogOpen = false
    private var useAlertSlideTransition = false

    private var confirmationDialogValue: String = ""
    private var confirmationDialogSelectedValue: String = ""

    private val slideTransitionComponent = forwardRef { props: PropsWithRef<Any>, ref -> slide(direction = SlideTransitionDirection.up) { attrs.ref = ref; spreadProps(props)} }
//        forwardRef { props: Props, ref -> childList.add(cloneElement(buildElement { mSlide(direction = SlideTransitionDirection.down) { attrs.ref = ref} }, props)) }
//        forwardRef { props: Props, ref -> child(cloneElement(buildElement { mSlide(direction = SlideTransitionDirection.down) { attrs.ref = ref} }, props)) }
//        forwardRef { props: Props, ref -> child(buildElement { mSlide(direction = SlideTransitionDirection.down) { attrs.ref = ref; spreadProps(props)} }) }


    private val slowTransitionProps: TransitionProps = js("({timeout: 1000})")
    private var slow = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val avatarStyle by css {
            backgroundColor = Colors.Blue.shade100
            color = Colors.Blue.shade600
        }
    }

    override fun RBuilder.render() {
        div {
            checkboxWithLabel("Slow the transition down a bit", checked = slow) { attrs.onChange = { _, value -> setState { slow = value } } }
            br {  }
            br {  }
            typography("Dialog Types:", variant = TypographyVariant.subtitle1)
            button("Open Simple") { attrs.onClick = { setState { simpleDialogOpen = true }}}
            button("Open Alert") { attrs.onClick = { setState { alertDialogOpen = true; useAlertSlideTransition = false }}}
            button("Slide Alert") { attrs.onClick = { setState {alertDialogOpen = true; useAlertSlideTransition = true }}}
            button("Confirmation (with dividers on)") {
                attrs.onClick = {
                    setState {
                        confirmationDialogValue = confirmationDialogSelectedValue
                        confirmationDialogOpen = true
                    }
                }
            }
            button("Confirmation (Body Scroll)") {
                attrs.onClick = {
                    setState {
                        confirmationDialogValue = confirmationDialogSelectedValue
                        confirmationDialogScrollOpen = true
                    }
                }
            }
            button("Full Screen") { attrs.onClick = { setState {fullScreenDialogOpen = true}}}
            button("Form") { attrs.onClick = { setState {formDialogOpen = true}}}
            button("Draggable") { attrs.onClick = { setState {draggableDialogOpen = true}}}

            br {  }
            br {  }
            typography("Selected Value: $selectedValue", variant = TypographyVariant.subtitle1)

            // Need to render whether simpleSnackbarOpen or closed so the close transition will occur!
            simpleDialog(simpleDialogOpen)
            alertDialog(alertDialogOpen)
            confirmationDialog(confirmationDialogOpen)
            confirmationDialog(confirmationDialogScrollOpen, DialogScroll.body)
            fullScreenDialog(fullScreenDialogOpen)
            formDialog(formDialogOpen)
            draggableDialog(draggableDialogOpen)
        }
    }

    private fun RBuilder.simpleDialog(open: Boolean) {
        val emails = listOf("username@gmail.com", "user02@gmail.com")
        dialog(open) {
            attrs.onClose = { _, _ -> setState { simpleDialogOpen = false} }
            attrs.transitionProps = if (slow) slowTransitionProps else null
            dialogTitle("Set backup account")
            div {
                list {
                    emails.forEach {email ->
                        listItemButton {
                            attrs.onClick = { setState {
                                this@TestDialogs.selectedValue = email
                                this@TestDialogs.simpleDialogOpen = false
                            }}
                            listItemAvatar {
                                avatar {
                                    css(avatarStyle)
                                    icon("person")
                                }
                            }
                            listItemText(email)
                        }
                    }
                    listItemButtonWithIcon("add", "add account", useAvatar = true) {
                        attrs.onClick = {
                            setState {
                                this@TestDialogs.selectedValue = "add account"
                                this@TestDialogs.simpleDialogOpen = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.alertDialog(open: Boolean) {
        fun closeAlertDialog() {
            setState { alertDialogOpen = false }
        }

        dialog(open) {
            attrs.onClose = { _, _ ->  closeAlertDialog() }
            attrs.transitionComponent = if (useAlertSlideTransition) slideTransitionComponent else undefined
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.keepMounted = true

            dialogTitle("Use Google's location service?")
            dialogContent {
                dialogContentText("Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.")
            }
            dialogActions {
                button("Disagree") {
                    attrs.onClick = { closeAlertDialog() }
                }
                button("Agree") {
                    attrs.onClick = { closeAlertDialog() }
                }
            }
        }
    }

    private fun RBuilder.confirmationDialog(open: Boolean, scroll: DialogScroll = DialogScroll.paper) {
        val options = arrayOf("None", "Atria", "Callisto", "Dione", "Ganymede", "Hangouts Call", "Luna", "Oberon",
                "Phobos", "Pyxis", "Sedna", "Titania", "Triton", "Umbriel")
//        mDialog(disableBackdropClick = true, disableEscapeKeyDown = true, maxWidth = DialogMaxWidth.xs) {
//        mDialog(disableEscapeKeyDown = true, maxWidth = DialogMaxWidth.xs) {

        dialog(open) {
            attrs.scroll = scroll
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.disableEscapeKeyDown = true
            dialogTitle("Phone Ringtone")
            // We will show the dividers on one of the dialogs (i.e. the one with paper scroll
            dialogContent(scroll == DialogScroll.paper) {
                radioGroup(confirmationDialogValue) {
                    attrs.onChange = {_, value -> setState { confirmationDialogValue = value }}
                    options.forEach {
                        radioWithLabel(it, value = it)
                    }
                }
            }
            dialogActions {
                button("Cancel") {
                    attrs.onClick = { setState {
                        confirmationDialogOpen = false
                        confirmationDialogScrollOpen = false
                    } }
                }
                button("Ok") {
                    attrs.onClick = { setState {
                        confirmationDialogSelectedValue = confirmationDialogValue
                        confirmationDialogOpen = false
                        confirmationDialogScrollOpen = false
                    } }
                }
            }
        }
    }

    private fun RBuilder.fullScreenDialog(open: Boolean) {
        fun handleClose() {
            setState { fullScreenDialogOpen = false}
        }
        dialog(open) {
            attrs.fullScreen = true
            attrs.transitionComponent = slideTransitionComponent
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.onClose = { _, _ -> handleClose() }

            appBar {
                css {
                    position = Position.relative
                }
                toolbar {
                    iconButton(iconName = "close", IconButtonColor.inherit) {
                        attrs.onClick = { handleClose() }
                    }
                    toolbarTitle("Sound")
                    button("save", ButtonColor.inherit, ButtonVariant.text) {
                        attrs.onClick = { handleClose() }
                    }
                }
            }
            listItemText("Phone ringtone", "Titania") {
                divider()
            }
            listItemText("Default notification ringtone", "Tethys")
        }
    }

    private fun RBuilder.formDialog(open: Boolean) {
        fun handleClose() {
            setState { formDialogOpen = false}
        }
        dialog(open) {
            attrs.onClose =  { _, _ -> handleClose() }
            attrs.transitionProps = if (slow) slowTransitionProps else null
            dialogTitle("Subscribe")
            dialogContent {
                dialogContentText("To subscribe to this website, please enter your email address here. We will send updates occationally.")
                textField("Email Address") {
                    attrs.autoFocus = true
                    attrs.margin = FormControlMargin.dense
                    attrs.type = InputType.email.realValue
                    attrs.fullWidth = true
                }
            }
            dialogActions {
                button("Cancel", variant = ButtonVariant.text) {
                    attrs.onClick = { handleClose() }
                }
                button("Subscribe", variant = ButtonVariant.text) {
                    attrs.onClick = { handleClose() }
                }
            }
        }
    }

    private fun RBuilder.draggableDialog(open: Boolean) {
        snackbar("Sorry, not implemented yet...", open) {
            attrs.autoHideDuration = 3000
            attrs.onClose = { _, _ -> setState {draggableDialogOpen = false}}
//        fun handleClose() {
//            setState { draggableDialogOpen = false}
//        }
//
//        createElement("Draggable", props, null)
//
//        mDialog(open, onClose =  { _, _ -> handleClose() }, transitionProps = if (slow) slowTransitionProps else null) {
//            mDialogTitle("Subscribe")
//            mDialogContent {
//                mDialogContentText("To subscribe to this website, please enter your email address here. We will send updates occationally.")
//                mTextField("Email Address", autoFocus = true, margin = MTextFieldMargin.dense, type = InputType.email, fullWidth = true)
//            }
//            mDialogActions {
//                mButton("Cancel", color = MColor.primary, onClick = { handleClose() }, variant = MButtonVariant.text)
//                mButton("Subscribe", color = MColor.primary, onClick = { handleClose() }, variant = MButtonVariant.text)
//            }
//        }
        }
    }
}
