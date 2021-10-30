package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.RatingsStyles.margin
import kotlinx.css.*
import react.*
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv


private object RatingsStyles : StyleSheet("RatingStyles", isStatic = true) {
    val margin by css {
        margin(vertical = 1.spacingUnits)
    }
}

public val testRatings = fc<Props> { _ ->
    demoContainer {
        demoPanel("Simple Rating") {
            var value: Number? by useState(2)
            styledDiv {
                css(margin)
                typography("Controlled") { attrs.component = "legend" }
                rating("simple-controlled", value) {
                    attrs.onChange = { _, newValue -> value = newValue }
                }
            }
            styledDiv {
                css(margin)
                typography("Read Only") { attrs.component = "legend" }
                rating("read-only", value) { attrs.readOnly = true }

            }
            styledDiv {
                css(margin)
                typography("Disabled") { attrs.component = "legend" }
                rating("disabled", value,) { attrs.disabled = true }
            }
            styledDiv {
                css(margin)
                typography("Pristine") { attrs.component = "legend" }
                rating("pristine")
            }
        }
        demoPanel("Customized Rating") {
            var value: Number? by useState(2)
            styledDiv {
                css(margin)
                typography("Custom empty icon") { attrs.component = "legend" }
                rating("customized-empty", value) {
                    attrs.precision = 0.5
                    attrs.onChange = { _, newValue -> value = newValue }
                    attrs.emptyIcon = buildElement { icon("star_border") { attrs.fontSize = IconFontSize.inherit} }
                }
            }
            styledDiv {
                css(margin)
                typography("Custom icon and color") { attrs.component = "legend" }
                rating("customized-empty", value) {
                    attrs.precision = 0.5
                    attrs.onChange = { _, newValue -> value = newValue }
                    attrs.icon = buildElement { icon("favorite") { attrs.fontSize = IconFontSize.inherit} }
                    attrs.emptyIcon =  buildElement { icon("favorite_border") { attrs.fontSize = IconFontSize.inherit} }
                    css {
                        ".MuiRating-iconFilled" {
                            color = Color("#ff6d75")
                        }
                        ".MuiRating-iconHover" {
                            color = Color("#ff3d47")
                        }
                    }
                }
            }
            styledDiv {
                css(margin)
                typography("Custom icon set") { attrs.component = "legend" }
                rating("customized-icons", value) {
                    attrs.onChange = { _, newValue -> value = newValue }
                    attrs.iconContainerComponent = iconContainer
                }
            }
            styledDiv {
                css(margin)
                typography("10 Starts") { attrs.component = "legend" }
                rating("customized-10", null, max = 10)
            }
        }
        demoPanel("Hover Feedback") {
            var value: Number? by useState(2)
            styledDiv {
                css {
                    +RatingsStyles.margin
                    display = Display.flex
                    alignItems = Align.center
                }
                var hover: Number by useState(-1)
                rating("hover-feedback", value) {
                    attrs.precision = 0.5
                    attrs.onChange = { _, newValue -> value = newValue }
                    attrs.onChangeActive = { _, hoverValue -> hover = hoverValue }
                }
                styledDiv {
                    css { marginLeft = 2.spacingUnits }
                    value?.let { +labelForValue(if (hover != -1) hover else it) }
                }
            }
        }
        demoPanel("Sizes") {
            styledDiv {
                css {
                    +RatingsStyles.margin
                    display = Display.flex
                    flexDirection = FlexDirection.column
                }
                rating("size-s", null) { attrs.size = RatingSize.small }
                rating("size-m", null) { attrs.size = RatingSize.medium }
                rating("size-l", null) { attrs.size = RatingSize.large }
            }
        }
    }
}

private fun labelForValue(value: Number) = when(value) {
    0.5 -> "Useless"
    1 -> "Useless+"
    1.5 -> "Poor"
    2 -> "Poor+"
    2.5 -> "Ok"
    3 -> "Ok+"
    3.5 -> "Good"
    4 -> "Good+"
    4.5 -> "Excellent"
    5 -> "Excellent+"
    else -> "Unknown"
}

private val iconContainer = fc<IconContainerProps> { props ->
    span {
        when (props.value) {
            1 -> icon("sentiment_very_dissatisfied") { attrs.className = props.className }
            2 -> icon("sentiment_dissatisfied") { attrs.className = props.className }
            3 -> icon("sentiment_satisfied") { attrs.className = props.className }
            4 -> icon("sentiment_satisfied_alt") { attrs.className = props.className }
            else -> icon("sentiment_very_satisfied") { attrs.className = props.className }
        }
    }
}
