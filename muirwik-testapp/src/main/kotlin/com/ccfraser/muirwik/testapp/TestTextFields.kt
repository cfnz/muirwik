package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.persist
import com.ccfraser.muirwik.components.utils.targetInputValue
import com.ccfraser.muirwik.testapp.TestTextFields.ComponentStyles.textField
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import react.dom.form
import styled.StyleSheet
import styled.css

external interface TestTextFieldsState : State {
    var textValue: String
    var multiLineValue: String
    var age: Int
}

class TestTextFields : RComponent<Props, TestTextFieldsState>() {
    var name: String = "Name via local var 'state'"
    var selectValue: String = "Item 2"

    init {
        state.textValue = ""
        state.multiLineValue = ""
        state.age = 0
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val textField by css {
            marginTop = 1.spacingUnits
            marginLeft = 1.spacingUnits
            marginRight = 1.spacingUnits
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            form {
                attrs.autoComplete = false
                attrs.novalidate = true

                demoPanel("Standard Text Fields") {
                    renderTextFields(FormControlVariant.standard)
                }
                demoPanel("Outlined Text Fields") {
                    renderTextFields(FormControlVariant.outlined)
                }

                demoPanel("Filled Text Fields") {
                    renderTextFields(FormControlVariant.filled)
                }

                demoPanel("Variations") {
                    renderVariations(FormControlVariant.outlined)
                }
            }
            demoPanel("Coloured (When selected)") {
                css { paddingBottom = 3.spacingUnits }

                textField("Secondary Name", name, variant = FormControlVariant.outlined) {
                    attrs.onChange = { event -> handleInputChange(event) }
                    css(textField)
                    attrs.color = FormControlColor.secondary
                }
            }
        }
    }

    private fun RBuilder.renderTextFields(variant: FormControlVariant) {
        textField("Name", name, variant = variant) {
            attrs.onChange = { event -> handleInputChange(event) }
            css(textField)
        }
        textField("Uncontrolled", defaultValue = "foo", variant = variant) {
            css(textField)
        }
        textField("Required", defaultValue = "Hello World", variant = variant) {
            attrs.required = true
            css(textField)
        }
        textField("Error", defaultValue = "Hello World", variant = variant) {
            attrs.error = true
            css(textField)
        }
        textField("Disabled", defaultValue = "Hello World", variant = variant) {
            attrs.disabled = true
            css(textField)
        }
        textField("Read Only", defaultValue = "Hello World", variant = variant) {
            css(textField)
//                attrs.inputProps = object : Props { val readOnly = true } IR Compiler didn't like this way of doing things
            attrs.inputProps = jsObject {
                this.asDynamic().readOnly = true
            }
        }
    }

    private fun RBuilder.renderVariations(variant: FormControlVariant) {
        textField("Email", variant = variant) {
            attrs.type = InputType.email.realValue
            attrs.autoComplete = "email"
            css(textField)
        }
        textField("Password", variant = variant) {
            attrs.type = InputType.password.realValue
            attrs.autoComplete = "current-password"
            css(textField)
        }
        textField("Dense", variant = variant) {
            attrs.margin = FormControlMargin.dense
            css(textField)
            attrs.helperText = "Affects css margins"
//            css {
//                +textField
//                marginTop = 16.px
//            }
        }
        textField("Multiline", state.multiLineValue, variant = variant) {
            attrs.maxRows = 4
            attrs.helperText = "Some helper text"
            attrs.multiline = true
            attrs.onChange = { val v = it.targetInputValue; setState { multiLineValue = v } }
            css(textField)
        }
        textField("Multiline Fixed Rows", state.multiLineValue, variant = variant) {
            attrs.rows = 4
            attrs.onChange = { event -> event.persist(); setState { multiLineValue = event.targetInputValue } }
            css(textField)
        }
        textField("Helper Text", variant = variant, defaultValue = "Default Value", helperText = "Some helper text") {
            css(textField)
        }
        textField("With Placeholder", variant = variant, placeholder = "Placeholder Value") {
            attrs.autoComplete = "current-password"
            css(textField)
        }
        textField("Multiline Placeholder", variant = variant, placeholder = "Placeholder Value", helperText = "With pre shrunk label") {
            attrs.multiline = true
            attrs.inputLabelProps = jsObject {
                this.asDynamic().shrink = true
            }
            css(textField)
//                attrs.inputLabelProps = object : Props { val shrink = true } IR Compiler didn't like this way of doing things
        }
        textField("Number", state.age.toString(), variant = variant) {
            attrs.type = InputType.number.realValue
            attrs.onChange = { val value = it.targetInputValue; setState { age = value.toIntOrNull() ?: 0 } }
            css(textField)
        }
        textField("Search", variant = variant) {
            attrs. type = InputType.search.realValue
            css(textField)
        }
        textField( "Select", value = selectValue, variant = variant) {
            attrs.select = true
            attrs.onChange = { event: Event -> setState { selectValue = event.target.asDynamic().value } }
            css {
                +textField
                width = 200.px
            }
            menuItem("Item 1") {
                attrs.key = "Item 1"
                attrs.value = "Item 1"
            }
            menuItem("Item 2") {
                attrs.key = "Item 2"
                attrs.value = "Item 2"
            }
            menuItem("Item 3") {
                attrs.key = "Item 3"
                attrs.value = "Item 3"
            }
        }

        textField("Adornment", variant = variant) {
            css(textField)
            val adornment = buildElement { inputAdornment("Kg") }
            attrs.inputProps = jsObject { this.asDynamic().startAdornment = adornment }
        }
    }

    private fun handleInputChange(event: Event) {
        val value = event.targetInputValue
        setState { name = value }
    }
}
