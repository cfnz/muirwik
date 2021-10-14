package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.*
import com.ccfraser.muirwik.components.dialog.*
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.SlideTransitionDirection
import com.ccfraser.muirwik.components.transitions.mSlide
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
import styled.styledDiv
import kotlin.reflect.KClass


class TestDialogs : RComponent<Props, State>() {
    private var selectedValue: String = ""
    private var simpleDialogOpen: Boolean = false
    private var alertDialogOpen: Boolean = false
    private var confirmationDialogOpen: Boolean = false
    private var confirmationDialogScrollOpen: Boolean = false
    private var fullScreenDialogOpen: Boolean = false
    private var formDialogOpen: Boolean = false
    private var draggableDialogOpen: Boolean = false

    private var confirmationDialogValue: String = ""
    private var confirmationDialogSelectedValue: String = ""

//    private var alertTransition: KClass<out RComponent<MTransitionProps, State>>? = null
    private var alertTransition: ComponentType<MSliderProps>? = null
    private val slowTransitionProps: MTransitionProps = js("({timeout: 1000})")
    private var slow = false

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val avatarStyle by css {
            backgroundColor = Colors.Blue.shade100
            color = Colors.Blue.shade600
        }
    }

    class SlideUpTransitionComponent(props: MTransitionProps) : RComponent<MTransitionProps, State>(props) {
        override fun RBuilder.render() {
            
            // This works but is a bit long winded and does not use our function
//            val p2: Props = EmptyProps();
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
            childList.add(cloneElement(buildElement { mSlide(direction = SlideTransitionDirection.down) }, props))
        }
    }

//    class SlideUpTransitionComponent2(props: MTransitionProps) : RComponent<MTransitionProps, State>(props) {
//        override fun RBuilder.render() {
//            childList.add(mSlide(props.show, direction = SlideTransitionDirection.down, timeout = SimpleTransitionTimeout(3000), addAsChild = false) {
//                props.children()
//            })
//        }
//    }


    override fun RBuilder.render() {
        div {
            mTypography(variant = MTypographyVariant.subtitle1) {
                +"Selected Value: $selectedValue"
                br {  }
                mCheckboxWithLabel("Slow the transition down a bit", checked = slow, onChange = { _, value -> setState { slow = value } })
                br {  }
                br {  }
                +"Dialog Types:"
            }
            br {  }
            mButton("Open Simple") { attrs.onClick = { setState { simpleDialogOpen = true }}}
            mButton("Open Alert") { attrs.onClick = { setState {alertDialogOpen = true; alertTransition = null }}}
//            mButton("Slide Alert") { attrs.onClick = { setState {alertDialogOpen = true; alertTransition = SlideUpTransitionComponent::class}}}
            mButton("Slide Alert") {
                attrs.onClick = {
                    setState {
                        alertDialogOpen = true;
                        alertTransition = forwardRef<MSliderProps> { props, ref ->
                            childList.add(cloneElement(buildElement {
                                mSlide(direction = SlideTransitionDirection.down) {
                                    attrs.ref = ref
                                }
                            }, props))
                        }
                    }
                }
            }

            mButton("Confirmation (with dividers on)") {
                attrs.onClick = {
                    setState {
                        confirmationDialogValue = confirmationDialogSelectedValue
                        confirmationDialogOpen = true
                    }
                }
            }
            mButton("Confirmation (Body Scroll)") {
                attrs.onClick = {
                    setState {
                        confirmationDialogValue = confirmationDialogSelectedValue
                        confirmationDialogScrollOpen = true
                    }
                }
            }
            mButton("Full Screen") { attrs.onClick = { setState {fullScreenDialogOpen = true}}}
            mButton("Form") { attrs.onClick = { setState {formDialogOpen = true}}}
            mButton("Draggable") { attrs.onClick = { setState {draggableDialogOpen = true}}}

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
        mDialog(open) {
            attrs.onClose = { _, _ -> setState { simpleDialogOpen = false} }
            attrs.transitionProps = if (slow) slowTransitionProps else null
            mDialogTitle("Set backup account")
            div {
                mList {
                    emails.forEach {email ->
                        mListItem(button = true, onClick = { setState {
                            this@TestDialogs.selectedValue = email
                            this@TestDialogs.simpleDialogOpen = false
                        }}) {
                            mListItemAvatar {
                                mAvatar {
                                    css(avatarStyle)
                                    mIcon("person")
                                }
                            }
                            mListItemText(primary = email)
                        }
                    }
                    mListItemWithIcon("add", "add account", divider = false, useAvatar = true, onClick = { setState {
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

        mDialog(open) {
            attrs.onClose = { _, _ ->  closeAlertDialog() }
//            attrs.transitionComponent = alertTransition
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.keepMounted = true

            mDialogTitle("Use Google's location service?")
            mDialogContent {
                mDialogContentText("Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.")
            }
            mDialogActions {
                mButton("Disagree") {
                    attrs.onClick = { closeAlertDialog() }
                }
                mButton("Agree") {
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

        mDialog(open) {
            attrs.scroll = scroll
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.disableEscapeKeyDown = true
            mDialogTitle("Phone Ringtone")
            // We will show the dividers on one of the dialogs (i.e. the one with paper scroll
            mDialogContent(scroll == DialogScroll.paper) {
                mRadioGroup(value = confirmationDialogValue, onChange = {_, value -> setState { confirmationDialogValue = value }} ) {
                    options.forEach {
                        mRadioWithLabel(it, value = it)
                    }
                }
            }
            mDialogActions {
                mButton("Cancel") {
                    attrs.onClick = { setState {
                        confirmationDialogOpen = false
                        confirmationDialogScrollOpen = false
                    } }
                }
                mButton("Ok") {
                    attrs.onClick = { setState {
                        confirmationDialogSelectedValue = confirmationDialogValue;
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
        mDialog(open) {
            attrs.fullScreen = true
            attrs.transitionComponent = SlideUpTransitionComponent::class
            attrs.transitionProps = if (slow) slowTransitionProps else null
            attrs.onClose = { _, _ -> handleClose() }

            mAppBar {
                css {
                    position = Position.relative
                }
                mToolbar {
                    mIconButton(iconName = "close", MIconButtonColor.inherit) {
                        attrs.onClick = { handleClose() }
                    }
                    mToolbarTitle("Sound")
                    mButton("save", MButtonColor.inherit, MButtonVariant.text) {
                        attrs.onClick = { handleClose() }
                    }
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
        mDialog(open) {
            attrs.onClose =  { _, _ -> handleClose() }
            attrs.transitionProps = if (slow) slowTransitionProps else null
            mDialogTitle("Subscribe")
            mDialogContent {
                mDialogContentText("To subscribe to this website, please enter your email address here. We will send updates occationally.")
                mTextField("Email Address", autoFocus = true, margin = MFormControlMargin.dense, type = InputType.email, fullWidth = true)
            }
            mDialogActions {
                mButton("Cancel", variant = MButtonVariant.text) {
                    attrs.onClick = { handleClose() }
                }
                mButton("Subscribe", variant = MButtonVariant.text) {
                    attrs.onClick = { handleClose() }
                }
            }
        }
    }

    private fun RBuilder.draggableDialog(open: Boolean) {
        mSnackbar("Sorry, not implemented yet...", open, autoHideDuration = 3000,
                onClose = { _, _ -> setState {draggableDialogOpen = false}})
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
