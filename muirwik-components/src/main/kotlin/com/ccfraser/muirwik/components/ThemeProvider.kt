package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createTheme
import com.ccfraser.muirwik.components.styles.invoke
import kotlinx.css.LinearDimension
import react.*


// Material UI 3.3.2 (or a bit earlier) has depreciated some typography enums. We do the following
// so we don't get any warning messages for the default theme.
private val themeOptions: ThemeOptions = js("({typography: {useNextVariants: true}})")
private var defaultTheme: Theme = createTheme(themeOptions)

/**
 * Contexts seem to be global in react. This is the context for supplying the current theme to a component
 * We use our own context rather than the one provided in mMuiThemeProvider because it proved difficult to
 * get the theme from the mMuiThemeProvider including trials using withTheme()... maybe there is a way, but
 * this method uses the context feature from the react wrappers and seems to work well. The only drawback is
 * that the consuming the context is only available in a RBuilder, so in the render. Lots of styles are
 * set outside the render, but using this method, if we want to access properties of the theme, we need to
 * move those styles into the render function.
 */
val themeContext: Context<Theme> = createContext(defaultTheme)

/**
 * Allows you to easily specify the theme's spacing unit. Usually used in css e.g. css { padding(2.spacingUnits) }
 * This is now not ideal as we are setting a global var for the default theme's spacing unit. This means
 * if another theme with different spacing units are used... things might not turn out so well.
 * We do this because currently we have used the Int.spacingUnit outside the render function in our test apps
 */
val Int.spacingUnits get(): LinearDimension {
    return LinearDimension(defaultTheme.spacing(this))
}



class ThemeProvider(props: MuiThemeProviderProps) : RComponent<MuiThemeProviderProps, State>(props) {
    override fun RBuilder.render() {
        @Suppress("DEPRECATION")
        mMuiThemeProvider(props.theme) {
            themeContext.Provider(props.theme) {
                props.children()
            }
        }
    }
}

fun RBuilder.themeProvider(theme: Theme = createTheme(), handler: RHandler<Props>? = null) = child(ThemeProvider::class) {
    attrs.theme = theme
    if (handler != null) handler()
}

/**
 * Provides access to the Material UI useTheme hook.
 */
@JsModule("@mui/material/styles/useTheme")
private external val useThemeDefault: dynamic
@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun useTheme(): Theme = useThemeDefault.default() as Theme
