package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.MIconContainerProps
import com.ccfraser.muirwik.components.MRatingSize
import com.ccfraser.muirwik.components.mRating
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
                mTypography("Controlled", component = "legend")
                mRating("simple-controlled", value, onChange = { _, newValue -> value = newValue })
            }
            styledDiv {
                css(margin)
                mTypography("Read Only", component = "legend")
                mRating("read-only", value, readOnly = true)

            }
            styledDiv {
                css(margin)
                mTypography("Disabled", component = "legend")
                mRating("disabled", value, disabled = true)
            }
            styledDiv {
                css(margin)
                mTypography("Pristine", component = "legend")
                mRating("pristine")
            }
        }
        demoPanel("Customized Rating") {
            var value: Number? by useState(2)
            styledDiv {
                css(margin)
                mTypography("Custom empty icon", component = "legend")
                mRating("customized-empty", value, precision = 0.5, onChange = { _, newValue -> value = newValue }) {
                    attrs.emptyIcon = buildElement { mIcon("star_border", fontSize = MIconFontSize.inherit) }
                }
            }
            styledDiv {
                css(margin)
                mTypography("Custom icon and color", component = "legend")
                mRating("customized-empty", value, precision = 0.5, onChange = { _, newValue -> value = newValue },
                    icon = buildElement { mIcon("favorite", fontSize = MIconFontSize.inherit) },
                    emptyIcon =  buildElement { mIcon("favorite_border", fontSize = MIconFontSize.inherit) }
                ) {
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
                mTypography("Custom icon set", component = "legend")
                mRating("customized-icons", value, onChange = { _, newValue -> value = newValue }) {
                    attrs.iconContainerComponent = iconContainer
                }

            }
            styledDiv {
                css(margin)
                mTypography("10 Starts", component = "legend")
                mRating("customized-10", null, max = 10)
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
                mRating("hover-feedback", value, precision = 0.5) {
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
                mRating("size-s", null, size = MRatingSize.small)
                mRating("size-m", null, size = MRatingSize.medium)
                mRating("size-l", null, size = MRatingSize.large)
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

private val iconContainer = fc<MIconContainerProps> { props ->
    span {
        when (props.value) {
            1 -> mIcon("sentiment_very_dissatisfied", className = props.className)
            2 -> mIcon("sentiment_dissatisfied", className = props.className)
            3 -> mIcon("sentiment_satisfied", className = props.className)
            4 -> mIcon("sentiment_satisfied_alt", className = props.className)
            else -> mIcon("sentiment_very_satisfied", className = props.className)
        }
    }
}

fun RBuilder.testRatings() {
    child(testRatings) {}
}