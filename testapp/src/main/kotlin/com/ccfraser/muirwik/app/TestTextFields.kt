package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestTextFields.ComponentStyles.textField
import com.ccfraser.muirwik.wrapper.*
import kotlinext.js.js
import kotlinx.css.Display
import kotlinx.css.FlexWrap
import kotlinx.css.px
import kotlinx.html.InputType
import kotlinx.html.style
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import react.*
import react.dom.form
import styled.StyleSheet
import styled.css
import styled.styledForm

@JsModule("@material-ui/core/styles")
@JsName("withStyles")
private external val withStyles: dynamic


class TestTextFields : RComponent<RProps, TestTextFields.MyTestState>() {
    var name: String = "Name via local var 'state'"
    var selectValue: String = "Item 2"
//    var multiline: String = "My multiline text"

    init {
        state = MyTestState("", "", 0)
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val textField by css {
            marginLeft = 16.px
            marginRight = 16.px
            width = 200.px
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
            mTextField(label = "Name", value = name, onChange = {event -> handleInputChange(event)}) {
                css(textField)
            }
            mTextField(label = "Uncontrolled", defaultValue = "foo") {
                css(textField)
            }
            mTextField(label = "Required", required = true, defaultValue = "Hello World") {
                css(textField)
            }
            mTextField(label = "Error", error = true, defaultValue = "Hello World") {
                css(textField)
            }
            mTextField(label = "Password", type = InputType.password, autoComplete = "current-password") {
                css(textField)
            }
            mTextFieldMultiLine(label = "Multiline", rowsMax = 4, value = state.multiLineValue,
                    onChange = {val v = it.inputValue; setState { multiLineValue = v }}) {
                css(textField)
            }
            mTextFieldMultiLine(label = "Multiline Fixed Rows", rows = 4, value = state.multiLineValue,
                    onChange = {event -> event.persist(); setState { multiLineValue = event.inputValue }}) {
                css(textField)
            }
            mTextField(label = "Helper", defaultValue = "Default Value", helperText = "Some important helper text", autoComplete = "current-password") {
                css(textField)
            }
            mTextField(label = "With Placeholder", placeholder = "Placeholder Value", autoComplete = "current-password") {
                css(textField)
            }
            mTextFieldMultiLine(label = "With Placeholder Multiline", placeholder = "Placeholder Value") {
                css(textField)
            }
            mTextField(label = "Number", type = InputType.number, inputLabelProps = object : RProps { val shrink = true },
                    value = state.age.toString(), onChange = { val value = it.inputValue; setState { age = value.toIntOrNull() ?: 0 }},
                    helperText = "Note as well as a number, this also has a pre shrunk label") {
                css(textField)
            }
            mTextField(label = "Search", type = InputType.search) {
                css(textField)
            }
            mTextFieldSelect(label = "Select", value = selectValue, onChange = { event: Event -> setState { selectValue = event.target.asDynamic().value }}) {
                css(textField)
                mMenuItem { +"Item 1"
                    attrs.asDynamic().key = "Item 1"
                    attrs.asDynamic().value = "Item 1"
                    }
                mMenuItem { +"Item 2"
                    attrs.asDynamic().key = "Item 2"
                    attrs.asDynamic().value = "Item 2"
                }
                mMenuItem { +"Item 3"
                    attrs.asDynamic().key = "Item 3"
                    attrs.asDynamic().value = "Item 3"
                }
            }

            val adornment = mInputAdornment { +"Kg" }
            mTextField(label = "Adornment", inputProps = object : RProps { var startAdornment = adornment }) {
                css(textField)
            }
        }
    }

    private fun handleInputChange(event: Event) {
        val target = event.target
        val value = if (target.asDynamic().type == "checkbox") target.asDynamic().checked else target.asDynamic().value
//        val name = target.asDynamic().name
        setState {name = value }
    }

    class MyTestState(var textValue: String, var multiLineValue: String, var age: Int) : RState
}


fun RBuilder.testTextFields() = child(TestTextFields::class) {}
