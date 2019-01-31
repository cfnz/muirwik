package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.form.mFormHelperText
import com.ccfraser.muirwik.components.input.mInput
import com.ccfraser.muirwik.components.input.mInputLabel
import com.ccfraser.muirwik.components.list.mListItemText
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

class TestSelects : RComponent<RProps, RState>() {
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
        simpleSelects()
        nativeSelects()
        multiSelects()
    }

    private fun RBuilder.simpleSelects() {
        mTypography("Simple Selects", MTypographyVariant.h4)
        styledForm {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "age-simple")

                // Method 1, using input props
                val inputProps: RProps = jsObject { }
                inputProps.asDynamic().name = "age"
                inputProps.asDynamic().id = "age-simple"
                mSelect(age, name = "age", inputProps = inputProps, onChange = { event, _ -> handleAgeChange(event) }) {
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
                mSelect(age, input = mInput(name = "age", id = "age-helper", addAsChild = false), onChange = { event, _ -> handleAgeChange(event) }) {
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

                mSelect(age, displayEmpty = true, input = mInput(name = "age", id = "age-label-placeholder", addAsChild = false),
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
                mSelect(name, input = mInput(name = "name", id = "name-disabled", addAsChild = false),
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
                mSelect(name, name = "name", input = mInput(id = "name-error", addAsChild = false),
                        onChange = { event, _ -> handleNameChange(event) },
                        renderValue = { value: Any -> span { +"⚠  - ${value}" } }) {
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
                mSelect(name, name = "name", input = mInput(id = "name-readonly", readOnly = true, addAsChild = false),
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
                mSelect(name, name = "name", input = mInput(id = "name-required", addAsChild = false),
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
        mTypography("Native Selects", MTypographyVariant.h4)
        styledForm {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Age", htmlFor = "native-age-simple")
                mSelect(age, name = "age", native = true, input = mInput(name = "age", id = "native-age-simple", addAsChild = false),
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
                mSelect(age, native = true, input = mInput(name = "age", id = "native-age-helper", addAsChild = false), onChange = { event, _ -> handleAgeChange(event) }) {
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

                mSelect(age, native = true, displayEmpty = true, input = mInput(name = "age", id = "native-age-label-placeholder", addAsChild = false),
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
        fun addMenuItems(builder: StyledElementBuilder<MSelectProps>, useCheckBoxes: Boolean) {
            names.forEach {
                builder.mMenuItem(key = it, value = it) {
                    css {
                        fontWeight = when ((selectedNames as? Array<String>)?.contains(it) ?: false) {
                            true -> FontWeight(currentTheme.typography.fontWeightMedium.toString())
                            else -> FontWeight(currentTheme.typography.fontWeightRegular.toString())
                        }
                    }
                    when (useCheckBoxes) {
                        false -> +it
                        else -> {
                            mCheckbox((selectedNames as? Array<String>)?.contains(it) ?: false)
                            mListItemText(it)
                        }
                    }
                }
            }
        }

        mTypography("Multi Selects", MTypographyVariant.h4)
        styledForm {
            css { display = Display.flex; flexWrap = FlexWrap.wrap; paddingBottom = 4.spacingUnits }
            mFormControl {
                css(formControl)
                mInputLabel("Name", htmlFor = "select-multiple")
                mSelect(selectedNames, multiple = true, input = mInput(id = "select-multiple", addAsChild = false),
                        onChange = { event, _ -> handleMultipleChange(event) }) {
                    addMenuItems(this, false)
                }
            }
            mFormControl {
                css(formControl)
                mInputLabel("Checkbox", htmlFor = "select-multiple-checkbox")
                mSelect(selectedNames, multiple = true, input = mInput(id = "select-multiple-checkbox", addAsChild = false),
                        renderValue = { value: Any ->
                            span {+(value as Array<String>).joinToString(", ")}
                        },
                        onChange = { event, _ -> handleMultipleChange(event) }) {
                    addMenuItems(this, true)
                }
            }
            mFormControl {
                css(formControl)
                mInputLabel("Chip", htmlFor = "select-multiple-chip")
                mSelect(selectedNames, multiple = true, input = mInput(id = "select-multiple-chip", addAsChild = false),
                        renderValue = { value: Any ->
                            styledDiv {
                                css(chips)
                                (value as Array<String>).forEach {
                                    mChip(it, key = it) {
                                        css(chip)
                                    }
                                }
                            }
                        },
                        onChange = { event, _ -> handleMultipleChange(event) }) {
                    addMenuItems(this, false)
                }
            }
        }
    }


/*

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};
            renderValue={selected => selected.join(', ')}
            MenuProps={MenuProps}
          >
            {names.map(name => (
              <MenuItem key={name} value={name}>
                <Checkbox checked={this.state.name.indexOf(name) > -1} />
                <ListItemText primary={name} />
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        <FormControl className={classes.formControl}>
          <InputLabel htmlFor="select-multiple-chip">Chip</InputLabel>
          <Select
            multiple
            value={this.state.name}
            onChange={this.handleChange}
            input={<Input id="select-multiple-chip" />}
            renderValue={selected => (
              <div className={classes.chips}>
                {selected.map(value => (
                  <Chip key={value} label={value} className={classes.chip} />
                ))}
              </div>
            )}
            MenuProps={MenuProps}
          >
            {names.map(name => (
              <MenuItem
                key={name}
                value={name}
                style={{
                  fontWeight:
                    this.state.name.indexOf(name) === -1
                      ? theme.typography.fontWeightRegular
                      : theme.typography.fontWeightMedium,
                }}
              >
                {name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </div>
    );
  }
}
 */
}

fun RBuilder.testSelects() = child(TestSelects::class) {}
