package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.createTheme
import com.ccfraser.muirwik.components.utils.targetValue
import kotlinx.css.Display
import kotlinx.css.display
import react.*
import styled.css
import styled.styledDiv

class TestLocalization : RComponent<Props, State>() {
    var locale = "enUS"

    override fun RBuilder.render() {
        val theme: Theme = createTheme(null, Locales.findByKey(locale))

        styledDiv {
            css {
                display = Display.flex
            }

            themeProvider(theme) {
                textField("Locale Key", locale, variant = FormControlVariant.outlined) {
                    attrs.select = true
                    attrs.onChange = { setState { locale = it.targetValue as String } }
                    Locales.keys().forEach {
                        menuItem(it, value = it)
                    }
                }

                tablePagination(1, 2000, 10, { _, _ ->  }) {
                    attrs.component = "span"
                }
            }
        }
    }
}
