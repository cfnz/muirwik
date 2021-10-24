package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.ControlColor
import com.ccfraser.muirwik.components.utils.HRefOptions
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.image
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.imageBackdrop
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.imageButton
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.imageMarked
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.imageSrc
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.imageTitle
import com.ccfraser.muirwik.testapp.TestButtons.ComplexComponentStyles.root
import com.ccfraser.muirwik.testapp.TestButtons.ComponentStyles.buttonMargin
import kotlinext.js.js
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import react.*
import react.dom.attrs
import react.dom.br
import react.dom.div
import react.dom.label
import styled.*
import react.dom.button as stdButton


class TestButtons : RComponent<Props, State>() {
    private var loading = false

    private fun handleMouseMove() {
        console.log("Another Move")
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val buttonMargin by css {
            margin(1.spacingUnits)
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            buttonSet("Standard (Text) buttons")
            buttonSet("Outlined buttons", ButtonVariant.outlined)
            buttonSet("Contained buttons", ButtonVariant.contained)
            groupedButtons()
            splitButtons()
            iconButtons()
            fabButtons()
            loadingButtons()
            buttonSizes()
            playaAroundWithButtons()
        }
        br { }
        moreComplexButton()
    }

    // Since we are creating 3 sets of buttons almost the same, we will put them into a function
    private fun RBuilder.buttonSet(heading: String, variant: ButtonVariant = ButtonVariant.text) {
        demoPanel(heading) {
            css {
                child("button") { margin(1.spacingUnits) }
            }
            button("Default", variant = variant)
            button("Primary", ButtonColor.primary, variant = variant)
            button("Secondary", ButtonColor.secondary, variant = variant)
            button("Disabled", variant = variant) {
                attrs.disabled = true
            }
            button("Link", hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"), variant = variant) {
                css(buttonMargin)
            }
            styledInput {
                css { display = Display.none }
                attrs {
                    accept = "image/*"
                    id = "button-file"
                    multiple = true
                    type = InputType.file
                }
            }
            label {
                attrs["htmlFor"] = "button-file" // This worked whereas (for some reason) attrs.hmlFor did not
                button("Upload", variant = variant) {
                    css(buttonMargin)
                    attrs.component = "span"
                }
            }
        }
    }

    private fun RBuilder.groupedButtons() {
        demoPanel("Grouped buttons") {
            buttonGroup {
                button("One")
                button("Two")
                button("Three")
            }
            br { }
            br { }
            buttonGroup(ButtonColor.primary, ButtonGroupVariant.contained) {
                button("One", ButtonColor.primary)
                button("Two", ButtonColor.primary)
                button("Three", ButtonColor.primary)
            }
            br { }
            br { }
            buttonGroup(orientation = ButtonGroupOrientation.vertical) {
                button("One")
                button("Two")
                button("Three")
            }
        }
    }

    private fun RBuilder.splitButtons() {
        demoPanel("Split buttons") {
            buttonGroup(ButtonColor.primary, ButtonGroupVariant.contained) {
                button("One")
                button {
                    attrs.variant = ButtonVariant.contained
                    attrs.size = ButtonSize.small
                    icon("arrow_drop_down")
                }
            }
        }
    }

    private fun RBuilder.iconButtons() {
        demoPanel("Icon buttons") {
            iconButton("send") { attrs.onClick = { window.alert("I was clicked") } }
            iconButton("star")
            iconButton("delete", IconButtonColor.primary)
            iconButton("delete", color = IconButtonColor.secondary)
        }
        demoPanel("Buttons with icons") {
            css {
                child("button") { +buttonMargin }
            }
            button("Delete", ButtonColor.secondary, ButtonVariant.contained) {
                attrs.startIcon = buildElement { icon("delete") { attrs.fontSize = IconFontSize.small } }
            }
            button("Send", ButtonColor.primary, ButtonVariant.contained) {
                attrs.endIcon = buildElement { icon("send") { attrs.fontSize = IconFontSize.small } }
            }
            button("Upload", variant = ButtonVariant.contained) {
                attrs.startIcon = buildElement { icon("cloud_upload") { attrs.fontSize = IconFontSize.small } }
            }
            button("Svg Icon", variant = ButtonVariant.contained) {
                attrs.startIcon = buildElement { svgIcon("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z") }
            }
        }
    }

    private fun RBuilder.fabButtons() {
        demoPanel("FABs") {
            css {
                child("button") { +buttonMargin }
            }
            fab("add", FabColor.primary)
            fab("edit-icon", FabColor.secondary)
            fab("navigation-icon") {
                attrs.disabled = true
            }
            fab("navigation-icon", "Extended", color = FabColor.secondary)
            fab("add", FabColor.secondary)
        }
    }

    private fun RBuilder.buttonSizes() {
        demoPanel("Sizes") {
            buttons(ButtonVariant.text)
            buttons(ButtonVariant.outlined)
            buttons(ButtonVariant.contained)
            div {
                fab("add", FabColor.secondary, size = ButtonSize.small) { css(buttonMargin) }
                fab("add", FabColor.secondary, size = ButtonSize.medium) { css(buttonMargin) }
                fab("add", FabColor.secondary, size = ButtonSize.large) { css(buttonMargin) }
            }
            div {
                fab("navigation", "Extended", size = ButtonSize.small) { css(buttonMargin) }
                fab("navigation", "Extended", size = ButtonSize.medium) { css(buttonMargin) }
                fab("navigation", "Extended", size = ButtonSize.large) { css(buttonMargin) }
            }
            div {
                iconButton("delete", size = MIconButtonSize.small) { css(buttonMargin) }
                iconButton("delete", size = MIconButtonSize.medium) { css(buttonMargin) }
                iconButton {
                    css(buttonMargin)
                    icon("delete") { attrs.fontSize = IconFontSize.small }
                }
            }
        }
    }

    private fun RBuilder.buttons(variant: ButtonVariant) {
        styledDiv {
            css {
                child("button") { +buttonMargin }
            }
            button("Small", variant = variant, size = ButtonSize.small)
            button("Medium", variant = variant, size = ButtonSize.medium)
            button("Large", variant = variant, size = ButtonSize.large)
        }
    }

    private fun RBuilder.loadingButtons() {
        demoPanel("Loading Buttons") {
            switchWithLabel("Loading", loading, ControlColor.primary) {
                attrs.onChange = { _, value -> setState { loading = value } }
                css {
                    paddingBottom = 1.spacingUnits
                }
            }
            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    children("button") {
                        margin(horizontal = 1.spacingUnits)
                    }
                }
                loadingButton("Disabled", loading, variant = ButtonVariant.outlined) { attrs.disabled = true }
                loadingButton("Fetch data", loading, variant = ButtonVariant.outlined) {
                    attrs.loadingIndicator = ReactNode("Loading...")
                }
                loadingButton("Send", loading, variant = ButtonVariant.contained) {
                    attrs.loadingPosition = LoadingPosition.end
                    attrs.endIcon = buildElement { icon("send") }
                }
                loadingButton("Save", loading, ButtonColor.secondary, ButtonVariant.contained) {
                    attrs.loadingPosition = LoadingPosition.start
                    attrs.startIcon = buildElement { icon("save") }
                }
            }
        }
    }

    private fun RBuilder.playaAroundWithButtons() {
        demoPanel("Some play around buttons (Check the console for event notifications...)") {
            stdButton {
                +"Normal React Button"
                attrs.onClickFunction = { console.log("Hello, I am clicked") }
                attrs.onMouseMoveFunction = { console.log("Move") }
            }
            br { }
            br { }
            button("Clk, Dbl Clk and Move") {
                css(buttonMargin)
                attrs.onClick = { console.log("Yay, I clicked") }
                attrs.onDoubleClick = { console.log("A Double Click?") }
                attrs.onMouseMove = { handleMouseMove() }
            }
            button("Contained", variant = ButtonVariant.contained) { css(buttonMargin) }
            button("Primary", ButtonColor.primary, variant = ButtonVariant.contained) { css(buttonMargin) }
            button("Secondary with HRef", color = ButtonColor.secondary, hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"),
                variant = ButtonVariant.contained) { css(buttonMargin) }
            button("Styled Button") {
                css {
                    background = "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)"
                    borderRadius = 3.px
                    borderStyle = BorderStyle.none
                    color = Color.white
                    height = 48.px
                    padding(0.px, 30.px)
                    boxShadow += BoxShadow(false, 0.px, 3.px, 5.px, 2.px, rgba(255, 105, 135, 0.3))
                    margin(1.spacingUnits)
                }
            }
            button("Icon", ButtonColor.primary, variant = ButtonVariant.contained) {
                css(buttonMargin)
                icon("send", IconColor.inherit) {
                    css { marginLeft = 1.em }
                }
            }
        }
    }

    private object ComplexComponentStyles : StyleSheet("ComplexComponentStyles", isStatic = true) {
        val root by css {
            display = Display.flex
            flexWrap = FlexWrap.wrap
            minWidth = 300.px
            maxWidth = 800.px
//            width = 100.pct
        }
        val image by css {
            position = Position.relative
            height = 200.px
            hover {
                zIndex = 1
                children {
                    opacity = 0.75
                    border(4.px, BorderStyle.solid, Color.white)
                    transition += Transition("opacity", 195.ms, Timing.materialStandard, 0.ms)
                }
                child(".${ComplexComponentStyles.name}-${ComplexComponentStyles::imageTitle.name}") {
                    border(4.px, BorderStyle.solid, Color.white)
                }
            }
        }
        val imageButton by css {
            position = Position.absolute
            left = 0.px
            right = 0.px
            top = 0.px
            bottom = 0.px
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.center
            // The below uses a theme property, so we need the theme context, so do it in the render
            // color = Color(theme.palette.common.white)
        }
        val imageSrc by css {
            position = Position.absolute
            left = 0.px
            right = 0.px
            top = 0.px
            bottom = 0.px
            backgroundSize = "cover"
            backgroundPosition = "center 40%"
        }
        val imageBackdrop by css {
            position = Position.absolute
            left = 0.px
            right = 0.px
            top = 0.px
            bottom = 0.px
            // The below uses a theme property, so we need the theme context, so do it in the render
            // backgroundColor = Color(theme.palette.common.black)
            opacity = 0.4
            transition += Transition("opacity", 195.ms, Timing.materialStandard, 0.ms)
        }
        val imageTitle by css {
            position = Position.relative
            padding(2.spacingUnits, 4.spacingUnits, 1.spacingUnits + 6.px)
        }
        val imageMarked by css {
            height = 3.px
            width = 18.px
            position = Position.absolute
            bottom = -2.px
            left = 50.pct - 9.px
        }
    }

    private fun RBuilder.moreComplexButton() {
        data class ImageInfo(val imageName: String, val title: String, val width: String)
        val imageInfos = listOf(
            ImageInfo("breakfast.jpg", "Breakfast", "40%"),
            ImageInfo("burgers.jpg", "Burgers", "30%"),
            ImageInfo("camera.jpg", "Camera", "30%")
        )

        themeContext.Consumer { theme ->
            demoPanel("More complex button...") {
                css(root)
                imageInfos.forEach {
                    button(it.title) {
                        css(image)
                        attrs.focusRipple = true
                        attrs.key = it.title
                        attrs.asDynamic().style = js { width = it.width }
                        styledSpan {
                            css {
                                +imageSrc
                                backgroundImage = Image("url(/images/grid-list/${it.imageName})")
                            }
                        }
                        styledSpan {
                            css {
                                +imageBackdrop
                                backgroundColor = Color(theme.palette.common.black)
                            }
                        }
                        styledSpan {
                            css {
                                +imageButton
                                color = Color(theme.palette.common.white)
                            }
                            typography(it.title, TypographyVariant.subtitle1, TypographyColor.inherit) {
                                css(imageTitle)
                                styledSpan {
                                    css {
                                        +imageMarked
                                        backgroundColor = Color(theme.palette.common.white)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
