package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.form.mFormHelperText
import com.ccfraser.muirwik.components.input.mFilledInput
import com.ccfraser.muirwik.components.input.mInput
import com.ccfraser.muirwik.components.input.mInputLabel
import com.ccfraser.muirwik.components.input.mOutlinedInput
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.menu.mMenuItem
import com.ccfraser.muirwik.testapp.TestSelects.ComponentStyles.chip
import com.ccfraser.muirwik.testapp.TestSelects.ComponentStyles.chips
import com.ccfraser.muirwik.testapp.TestSelects.ComponentStyles.formControl
import com.ccfraser.muirwik.testapp.TestSelects.ComponentStyles.selectEmpty
import kotlinext.js.jsObject
import kotlinx.css.*
import org.w3c.dom.events.Event
import react.*
import react.dom.option
import react.dom.span
import styled.*

class TestSelects : RComponent<Props, State>() {
    private var age: Any = 10
    private var name: Any = ""

    private var names = arrayListOf(
            "Oliver Hansen",
            "Van Henry",
            "April Tucker",
            "Ralph Hubbard",
            "Omar Alexander",
            "Carlos Abbott",
            "Miriam Wagner",
            "Bradley Wilkerson",
            "Virginia Andrews",
            "Kelly Snyder"
    )

    private var selectedNames: Any = arrayOf<String>()

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val formControl by css {
            margin(1.spacingUnits)
            minWidth = 120.px
            maxWidth = 300.px
        }
        val selectEmpty by css {
            marginTop = 2.spacingUnits
        }
        val chips by css {
            display = Display.flex
            flexWrap = FlexWrap.wrap
        }

        val chip by css {
            margin(1.spacingUnits / 4)
        }
    }

    private fun handleAgeChange(event: Event) {
        val value = event.targetValue
        setState { age = value }
    }

    private fun handleNameChange(event: Event) {
        val value = event.targetValue
        setState { name = value }
    }

    private fun handleMultipleChange(event: Event) {
        val value = event.targetValue
        setState { selectedNames = value }
    }

    override fun RBuilder.render() {
        demoContainer {
            simpleSelects()
            nativeSelects()
            multiSelects()
            selectVariants()
        }
    }

    private fun RBuilder.simpleSelects() {
        demoPanel("Simple Selects") {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "age-simple")

                // Method 1, using input props
                val inputProps: Props = jsObject { }
                inputProps.asDynamic().name = "age"
                inputProps.asDynamic().id = "age-simple"
                mSelect(age, name = "age", onChange = { event, _ -> handleAgeChange(event) }) {
                    attrs.inputProps = inputProps
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
            }

            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "age-helper")

                // Method 2, using an input element
                mSelect(age, input = buildElement { mInput(name = "age", id = "age-helper") }, onChange = { event, _ -> handleAgeChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
                mFormHelperText("Some important helper text")
            }
            mFormControl {
                css(formControl)
                mSelect(age, displayEmpty = true, onChange = { event, _ -> handleAgeChange(event) }) {
                    css(selectEmpty)
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
                mFormHelperText("Without Label")
            }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "age-label-placeholder", shrink = true)

                mSelect(age, displayEmpty = true, input = buildElement { mInput(name = "age", id = "age-label-placeholder") },
                        onChange = { event, _ -> handleAgeChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
                mFormHelperText("Label + placeholder")
            }
            mFormControl(disabled = true) {
                css(formControl)
                mInputLabel("Name", htmlFor = "name-disabled")
                mSelect(name, input = buildElement { mInput(name = "name", id = "name-disabled") },
                        onChange = { event, _ -> handleNameChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Hai", value = "hai")
                    mMenuItem("Oliver", value = "oliver")
                    mMenuItem("Kevin", value = "kevin")
                }
                mFormHelperText("Disabled")
            }
            mFormControl(error = true) {
                css(formControl)
                mInputLabel("Name", htmlFor = "name-error")
                mSelect(name, name = "name", input = buildElement { mInput(id = "name-error") },
                        onChange = { event, _ -> handleNameChange(event) }) {
                    attrs.renderValue = { value: Any -> buildElement { span { +"⚠  - ${value}" } } }
                    mMenuItem("None", value = "")
                    mMenuItem("Hai", value = "hai")
                    mMenuItem("Oliver", value = "oliver")
                    mMenuItem("Kevin", value = "kevin")
                }
                mFormHelperText("Error")
            }
            mFormControl {
                css(formControl)
                mInputLabel("Name", htmlFor = "name-readonly")
                mSelect(name, name = "name", input = buildElement { mInput(id = "name-readonly", readOnly = true) },
                        onChange = { event, _ -> handleNameChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Hai", value = "hai")
                    mMenuItem("Oliver", value = "oliver")
                    mMenuItem("Kevin", value = "kevin")
                }
                mFormHelperText("Read Only")
            }
            mFormControl {
                css(formControl)
                mSelect(name, name = "name", displayEmpty = true, onChange = { event, _ -> handleNameChange(event) }) {
                    css(selectEmpty)
                    mMenuItem("Placeholder", value = "", disabled = true)
                    mMenuItem("Hai", value = "hai")
                    mMenuItem("Oliver", value = "oliver")
                    mMenuItem("Kevin", value = "kevin")
                }
                mFormHelperText("Placeholder")
            }
            mFormControl(required = true) {
                css(formControl)
                mInputLabel("Name", htmlFor = "name-required")
                mSelect(name, name = "name", input = buildElement { mInput(id = "name-required") },
                        onChange = { event, _ -> handleNameChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Hai", value = "hai")
                    mMenuItem("Oliver", value = "oliver")
                    mMenuItem("Kevin", value = "kevin")
                }
                mFormHelperText("Required")
            }
        }
    }

    private fun RBuilder.nativeSelects() {
        demoPanel("Native Selects") {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "native-age-simple")
                mSelect(age, name = "age", native = true, input = buildElement { mInput(name = "age", id = "native-age-simple") },
                        onChange = { event, _ -> handleAgeChange(event) }) {
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
            }

            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "native-age-helper")
                mSelect(age, native = true, input = buildElement { mInput(name = "age", id = "native-age-helper") }, onChange = { event, _ -> handleAgeChange(event) }) {
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                mFormHelperText("Some important helper text")
            }
            mFormControl {
                css(formControl)
                mSelect(age, native = true, displayEmpty = true, onChange = { event, _ -> handleAgeChange(event) }) {
                    css(selectEmpty)
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                mFormHelperText("Without Label")
            }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "native-age-label-placeholder", shrink = true)

                mSelect(age, native = true, displayEmpty = true, input = buildElement { mInput(name = "age", id = "native-age-label-placeholder") },
                        onChange = { event, _ -> handleAgeChange(event) }) {
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                mFormHelperText("Label + placeholder")
            }
        }
    }

    private fun RBuilder.multiSelects() {
        themeContext.Consumer {theme ->
            fun addMenuItems(builder: StyledElementBuilder<MSelectProps>, useCheckBoxes: Boolean) {
                names.forEach {
                    builder.mMenuItem(key = it, value = it) {
                        css {
                            @Suppress("UNCHECKED_CAST")
                            fontWeight = when ((selectedNames as? Array<String>)?.contains(it) ?: false) {
                                true -> FontWeight(theme.typography.fontWeightMedium.toString())
                                else -> FontWeight(theme.typography.fontWeightRegular.toString())
                            }
                        }
                        when (useCheckBoxes) {
                            false -> +it
                            else -> {
                                @Suppress("UNCHECKED_CAST")
                                mCheckbox((selectedNames as? Array<String>)?.contains(it) ?: false)
                                mListItemText(it)
                            }
                        }
                    }
                }
            }

            demoPanel("Multi Selects") {
                css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
                mFormControl {
                    css(formControl)
                    mInputLabel("Name", htmlFor = "select-multiple")
                    mSelect(selectedNames, multiple = true, input = buildElement { mInput(id = "select-multiple") },
                            onChange = { event, _ -> handleMultipleChange(event) }) {
                        addMenuItems(this, false)
                    }
                }
                mFormControl {
                    css(formControl)
                    mInputLabel("Checkbox", htmlFor = "select-multiple-checkbox")
                    mSelect(selectedNames, multiple = true, input = buildElement { mInput(id = "select-multiple-checkbox") },
                            onChange = { event, _ -> handleMultipleChange(event) }) {
                        @Suppress("UNCHECKED_CAST")
                        attrs.renderValue = { value -> buildElement { span { +(value as Array<String>).joinToString(", ") }}}
                        addMenuItems(this, true)
                    }
                }
                mFormControl {
                    css(formControl)
                    mInputLabel("Chip", htmlFor = "select-multiple-chip")
                    mSelect(selectedNames, multiple = true, input = buildElement { mInput(id = "select-multiple-chip") },
                            onChange = { event, _ -> handleMultipleChange(event) }) {
                        attrs.renderValue = { value: Any -> buildElement {
                            styledDiv {
                                css(chips)
                                @Suppress("UNCHECKED_CAST")
                                (value as Array<String>).forEach {
                                    mChip(it, key = it) {
                                        css(chip)
                                    }
                                }
                            }
                        } }
                        addMenuItems(this, false)
                    }
                }
            }
        }
    }

    private fun RBuilder.selectVariants() {
        demoPanel("Select Variants") {
//            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Standard")
                mSelect(age, variant = MFormControlVariant.standard, onChange = { event, _ -> handleAgeChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
                mFormHelperText("Some important helper text")
            }
            mFormControl(variant = MFormControlVariant.filled) {
                css(formControl)
                mInputLabel("Filled", variant = MFormControlVariant.filled)
                mSelect(age, input = buildElement { mFilledInput(id = "test") }, onChange = { event, _ -> handleAgeChange(event) }) {
                    mMenuItem("None", value = "")
                    mMenuItem("Ten", value = "10")
                    mMenuItem("Twenty", value = "20")
                    mMenuItem("Thirty", value = "30")
                }
            }
            mFormControl(variant = MFormControlVariant.outlined) {
                css(formControl)
                mInputLabel("Outlined", htmlFor = "outlined", variant = MFormControlVariant.outlined) {
//                    Need to get into storing ref element of label so we can get its width...
//                    ... seems pretty low level stuff just to put an outlined control on a form...
//                    See material-ui demo for more info.
//                    ref { refElement = it } // findDOMNode(it) }
                }
                mSelect(age, native = true, input = buildElement { mOutlinedInput(name = "outline", id = "outlined",
//                            labelWidth = refElement?.asDynamic().offsetWidth),
                            labelWidth = 60) },
                        onChange = { event, _ -> handleAgeChange(event) }) {
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                mFormHelperText("WIP... hard coded width :-o")
            }
        }
    }
}
