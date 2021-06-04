package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.input.mInputAdornment
import com.ccfraser.muirwik.components.menu.mMenuItem
import com.ccfraser.muirwik.testapp.TestTextFields.ComponentStyles.textField
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.styledForm

external interface TestTextFieldsState : RState {
    var textValue: String
    var multiLineValue: String
    var age: Int
}

class TestTextFields : RComponent<RProps, TestTextFieldsState>() {
    var name: String = "Name via local var 'state'"
    var selectValue: String = "Item 2"

    init {
        state.textValue = ""
        state.multiLineValue = ""
        state.age = 0
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val textField by css {
            marginLeft = 1.spacingUnits
            marginRight = 1.spacingUnits
        }
    }

    override fun RBuilder.render() {
        styledForm {
            css {
                display = Display.flex
                flexWrap = FlexWrap.wrap
            }
            attrs.autoComplete = false
            attrs.novalidate = true

            mTypography("Standard Text Fields", variant = MTypographyVariant.h4)
            renderTextFields(MFormControlVariant.standard)

            mTypography("Outlined Text Fields", variant = MTypographyVariant.h4)
            renderTextFields(MFormControlVariant.outlined)

            mTypography("Filled Text Fields", variant = MTypographyVariant.h4)
            renderTextFields(MFormControlVariant.filled)
        }

        mTypography("Coloured (When selected)", variant = MTypographyVariant.h4)
        styledDiv {
            css { paddingBottom = 3.spacingUnits }

            mTextField(label = "Secondary Name", value = name, variant = MFormControlVariant.outlined, onChange = { event -> handleInputChange(event) }) {
                css(textField)
                attrs.color = MTextFieldColor.secondary
            }
        }
    }

    private fun RBuilder.renderTextFields(variant: MFormControlVariant) {
        styledDiv {
            css { paddingBottom = 3.spacingUnits }

            mTextField(label = "Name", value = name, variant = variant, onChange = { event -> handleInputChange(event) }) {
                css(textField)
            }
            mTextField(label = "Uncontrolled", defaultValue = "foo", variant = variant) {
                css(textField)
            }
            mTextField(label = "Required", required = true, defaultValue = "Hello World", variant = variant) {
                css(textField)
            }
            mTextField(label = "Error", error = true, defaultValue = "Hello World", variant = variant) {
                css(textField)
            }
            mTextField(label = "Disabled", disabled = true, defaultValue = "Hello World", variant = variant) {
                css(textField)
            }
            mTextField(label = "Email", type = InputType.email, autoComplete = "email", variant = variant) {
                css(textField)
            }
            mTextField(label = "Password", type = InputType.password, autoComplete = "current-password", variant = variant) {
                css(textField)
            }
            mTextField(label = "Read Only", defaultValue = "Hello World", variant = variant) {
                css(textField)
                attrs.inputProps = object : RProps { val readOnly = true }
            }
            mTextField(label = "Dense", margin = MFormControlMargin.dense, variant = variant) {
                css {
                    +textField
                    marginTop = 16.px
                }
            }
            mTextFieldMultiLine(label = "Multiline", rowsMax = 4, value = state.multiLineValue, helperText = "Some helper text", variant = variant,
                    onChange = { val v = it.targetInputValue; setState { multiLineValue = v } }) {
                css(textField)
            }
            mTextFieldMultiLine(label = "Multiline Fixed Rows", rows = 4, value = state.multiLineValue, variant = variant,
                    onChange = { event -> event.persist(); setState { multiLineValue = event.targetInputValue } }) {
                css(textField)
            }
            mTextField(label = "Helper Text", defaultValue = "Default Value", helperText = "Some helper text", variant = variant) {
                css(textField)
            }
            mTextField(label = "With Placeholder", placeholder = "Placeholder Value", autoComplete = "current-password", variant = variant) {
                css(textField)
            }
            mTextFieldMultiLine(label = "Multiline Placeholder", placeholder = "Placeholder Value", variant = variant) {
                css(textField)
            }
            mTextField(label = "Number", type = InputType.number, value = state.age.toString(),
                    onChange = { val value = it.targetInputValue; setState { age = value.toIntOrNull() ?: 0 } },
                    helperText = "Number with pre shrunk label", variant = variant) {
                css(textField)
                attrs.inputLabelProps = object : RProps { val shrink = true }
            }
            mTextField(label = "Search", type = InputType.search, variant = variant) {
                css(textField)
            }
            mTextFieldSelect(label = "Select", value = selectValue, variant = variant,
                    onChange = { event: Event -> setState { selectValue = event.target.asDynamic().value } }) {
                css {
                    +textField
                    width = 200.px
                }
                mMenuItem {
                    +"Item 1"
                    attrs.asDynamic().key = "Item 1"
                    attrs.asDynamic().value = "Item 1"
                }
                mMenuItem {
                    +"Item 2"
                    attrs.asDynamic().key = "Item 2"
                    attrs.asDynamic().value = "Item 2"
                }
                mMenuItem {
                    +"Item 3"
                    attrs.asDynamic().key = "Item 3"
                    attrs.asDynamic().value = "Item 3"
                }
            }

            mTextField(label = "Adornment", variant = variant) {
                css(textField)
                val adornment = mInputAdornment { +"Kg" }
                attrs.inputProps = jsObject { this.asDynamic().startAdornment = adornment }
            }
        }
    }

    private fun handleInputChange(event: Event) {
        val value = event.targetInputValue
        setState { name = value }
    }
}


fun RBuilder.testTextFields() = child(TestTextFields::class) {}
