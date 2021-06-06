package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.Locales
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextFieldSelect
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.menu.mMenuItem
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.createMuiTheme
import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import kotlinx.css.Display
import kotlinx.css.display
import react.*
import styled.css
import styled.styledDiv

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestLocalization : RComponent<RProps, RState>() {
    var locale = "enUS"

    override fun RBuilder.render() {
        val theme: Theme = createMuiTheme(null, Locales.findByKey(locale))

        styledDiv {
            css {
                display = Display.flex
            }

            mThemeProvider(theme) {
                mTextFieldSelect("Locale Key", locale, variant = MFormControlVariant.outlined, onChange = { setState { locale = it.targetValue as String } }) {
                    Locales.keys().forEach {
                        mMenuItem(it, value = it)
                    }
                }

                mTablePagination {
                    attrs.count = 2000
                    attrs.rowsPerPage = 10
                    attrs.page = 1
                    attrs.component = "span"
                    attrs.onChangePage = {_, _ ->  }
                }
            }
        }
    }
}


fun RBuilder.testLocalization() = child(TestLocalization::class) {}

