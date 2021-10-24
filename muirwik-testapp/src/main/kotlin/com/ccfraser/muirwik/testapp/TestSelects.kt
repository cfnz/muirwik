package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.targetValue
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
import styled.StyleSheet
import styled.StyledElementBuilder
import styled.css
import styled.styledDiv

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
            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "age-simple")

                // Method 1, using input props
                val inputProps: Props = jsObject { }
                inputProps.asDynamic().name = "age"
                inputProps.asDynamic().id = "age-simple"
                select(age) {
                    attrs.name = "age"
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    attrs.inputProps = inputProps
                    menuItem("None", value = "")
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
            }

            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "age-helper")

                // Method 2, using an input element
                select(age) {
                    attrs.input = buildElement { input { attrs.name = "age"; attrs.id = "age-helper"} }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    menuItem("None", value = "")
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
                formHelperText("Some important helper text")
            }
            formControl {
                css(formControl)
                select(age) {
                    attrs.displayEmpty = true
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    css(selectEmpty)
                    menuItem("None", value = "")
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
                formHelperText("Without Label")
            }
            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "age-label-placeholder") { attrs.shrink = true }

                select(age) {
                    attrs.displayEmpty = true
                    attrs.input = buildElement { input { attrs.name = "age"; attrs.id = "age-label-placeholder"} }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    menuItem("None", value = "")
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
                formHelperText("Label + placeholder")
            }
            formControl {
                css(formControl)
                attrs.disabled = true
                inputLabel("Name", htmlFor = "name-disabled")
                select(name) {
                    attrs.input = buildElement { input { attrs.name = "name"; attrs.id = "name-disabled" } }
                    attrs.onChange = { event, _ -> handleNameChange(event) }
                    menuItem("None", value = "")
                    menuItem("Hai", value = "hai")
                    menuItem("Oliver", value = "oliver")
                    menuItem("Kevin", value = "kevin")
                }
                formHelperText("Disabled")
            }
            formControl {
                css(formControl)
                attrs.error = true
                inputLabel("Name", htmlFor = "name-error")
                select(name) {
                    attrs.name = "name"
                    attrs.input = buildElement { input { attrs.id = "name-error" } }
                    attrs.onChange = { event, _ -> handleNameChange(event) }
                    attrs.renderValue = { value: Any -> buildElement { span { +"⚠  - $value" } } }
                    menuItem("None", value = "")
                    menuItem("Hai", value = "hai")
                    menuItem("Oliver", value = "oliver")
                    menuItem("Kevin", value = "kevin")
                }
                formHelperText("Error")
            }
            formControl {
                css(formControl)
                inputLabel("Name", htmlFor = "name-readonly")
                select(name) {
                    attrs.name = "name"
                    attrs.input = buildElement { input { attrs.id = "name-readonly"; attrs.readOnly = true} }
                    attrs.onChange = { event, _ -> handleNameChange(event) }
                    menuItem("None", value = "")
                    menuItem("Hai", value = "hai")
                    menuItem("Oliver", value = "oliver")
                    menuItem("Kevin", value = "kevin")
                }
                formHelperText("Read Only")
            }
            formControl {
                css(formControl)
                select(name) {
                    attrs.name = "name"
                    attrs.displayEmpty = true
                    attrs.onChange = { event, _ -> handleNameChange(event) }
                    css(selectEmpty)
                    menuItem("Placeholder", value = "") { attrs.disabled = true }
                    menuItem("Hai", value = "hai")
                    menuItem("Oliver", value = "oliver")
                    menuItem("Kevin", value = "kevin")
                }
                formHelperText("Placeholder")
            }
            formControl {
                css(formControl)
                attrs.required = true
                inputLabel("Name", htmlFor = "name-required")
                select(name) {
                    attrs.name = "name"
                    attrs.displayEmpty = true
                    attrs.onChange = { event, _ -> handleNameChange(event) }
                    attrs.input = buildElement { input { attrs.id = "name-required"} }
                    menuItem("None", value = "")
                    menuItem("Hai", value = "hai")
                    menuItem("Oliver", value = "oliver")
                    menuItem("Kevin", value = "kevin")
                }
                formHelperText("Required")
            }
        }
    }

    private fun RBuilder.nativeSelects() {
        demoPanel("Native Selects") {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "native-age-simple")
                select(age) {
                    attrs.name = "age"
                    attrs.native = true
                    attrs.input = buildElement { input { attrs.name = "age"; attrs.id = "native-age-simple" } }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
            }

            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "native-age-helper")
                select(age) {
                    attrs.native = true
                    attrs.input = buildElement { input { attrs.name = "age"; attrs.id = "native-age-helper" } }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                formHelperText("Some important helper text")
            }
            formControl {
                css(formControl)
                select(age) {
                    attrs.native = true
                    attrs.displayEmpty = true
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    css(selectEmpty)
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                formHelperText("Without Label")
            }
            formControl {
                css(formControl)
                inputLabel("Age", htmlFor = "native-age-label-placeholder") { attrs.shrink = true }

                select(age) {
                    attrs.native = true
                    attrs.displayEmpty = true
                    attrs.input = buildElement { input { attrs.name = "age"; attrs.id = "native-age-label-placeholder"} }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                formHelperText("Label + placeholder")
            }
        }
    }

    private fun RBuilder.multiSelects() {
        themeContext.Consumer {theme ->
            fun addMenuItems(builder: StyledElementBuilder<SelectProps>, useCheckBoxes: Boolean) {
                names.forEach {
                    builder.menuItem(key = it, value = it) {
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
                                checkbox((selectedNames as? Array<String>)?.contains(it) ?: false)
                                listItemText(it)
                            }
                        }
                    }
                }
            }

            demoPanel("Multi Selects") {
                css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
                formControl {
                    css(formControl)
                    inputLabel("Name", "select-multiple")
                    select(selectedNames) {
                        attrs.multiple = true
                        attrs.input = buildElement { input { attrs.id = "select-multiple" } }
                        attrs.onChange = { event, _ -> handleMultipleChange(event) }
                        addMenuItems(this, false)
                    }
                }
                formControl {
                    css(formControl)
                    inputLabel("Checkbox", "select-multiple-checkbox")
                    select(selectedNames) {
                        attrs.multiple = true
                        attrs.input = buildElement { input { attrs.id = "select-multiple-checkbox" } }
                        attrs.onChange = { event, _ -> handleMultipleChange(event) }

                        @Suppress("UNCHECKED_CAST")
                        attrs.renderValue = { value -> buildElement { span { +(value as Array<String>).joinToString(", ") }}}
                        addMenuItems(this, true)
                    }
                }
                formControl {
                    css(formControl)
                    inputLabel("Chip", "select-multiple-chip")
                    select(selectedNames) {
                        attrs.multiple = true
                        attrs.input = buildElement { input { attrs.id = "select-multiple-chip" } }
                        attrs.onChange = { event, _ -> handleMultipleChange(event) }
                        attrs.renderValue = { value: Any -> buildElement {
                            styledDiv {
                                css(chips)
                                @Suppress("UNCHECKED_CAST")
                                (value as Array<String>).forEach {
                                    chip(it, key = it) {
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
            formControl {
                css(formControl)
                inputLabel("Standard")
                select(age, variant = FormControlVariant.standard) {
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    menuItem("None") { attrs.value = "" }
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
                formHelperText("Some important helper text")
            }
            formControl(variant = FormControlVariant.filled) {
                css(formControl)
                inputLabel("Filled", variant = FormControlVariant.filled)
                select(age) {
                    attrs.input = buildElement { filledInput { attrs.id = "test" } }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    menuItem("None", value = "")
                    menuItem("Ten", value = "10")
                    menuItem("Twenty", value = "20")
                    menuItem("Thirty", value = "30")
                }
            }
            formControl(variant = FormControlVariant.outlined) {
                css(formControl)
                inputLabel("Outlined", htmlFor = "outlined", variant = FormControlVariant.outlined) {
//                    Need to get into storing ref element of label so we can get its width...
//                    ... seems pretty low level stuff just to put an outlined control on a form...
//                    See material-ui demo for more info.
//                    ref { refElement = it } // findDOMNode(it) }
                }
                select(age) {
                    attrs.native = true
                    attrs.input = buildElement { outlinedInput { attrs.name = "outline"; attrs.id = "outlined" } }
                    attrs.onChange = { event, _ -> handleAgeChange(event) }
                    option { attrs.value = "None"; +"" }
                    option { attrs.value = "10"; +"Ten" }
                    option { attrs.value = "20"; +"Twenty" }
                    option { attrs.value = "30"; +"Thirty" }
                }
                formHelperText("Native Select WIP... ")
            }
        }
    }
}
