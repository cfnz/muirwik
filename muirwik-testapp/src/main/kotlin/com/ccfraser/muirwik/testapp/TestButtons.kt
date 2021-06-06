package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.*
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
import react.dom.*
import styled.*


class TestButtons : RComponent<RProps, RState>() {

    private fun handleMouseMove() {
        console.log("Another Move")
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val buttonMargin by css {
            margin(1.spacingUnits)
        }
    }

    // Since we are creating 3 sets of buttons almost the same, we will put them into a function
    private fun RBuilder.buttonSet(heading: String, variant: MButtonVariant = MButtonVariant.text, margin: Boolean = false) {
        styledDiv {
            mTypography(heading)
            mButton("Default", variant = variant) {
                if (margin) css(buttonMargin)
            }
            mButton("Primary", MColor.primary, variant = variant) {
                if (margin) css(buttonMargin)
            }
            mButton("Secondary", MColor.secondary, variant = variant) {
                if (margin) css(buttonMargin)
            }
            mButton("Disabled", disabled = true, variant = variant) {
                if (margin) css(buttonMargin)
            }
            mButton("Link", hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"), variant = variant) {
                if (margin) css(buttonMargin)
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
                mButton("Upload", variant = variant) {
                    if (margin) css(buttonMargin)
                    attrs.component = "span"
                }
            }
        }
    }

    override fun RBuilder.render() {
        buttonSet("Standard (Text) buttons")
        br { }
        buttonSet("Outlined buttons", MButtonVariant.outlined, true)
        br { }
        buttonSet("Contained buttons", MButtonVariant.contained, true)
        br { }
        styledDiv {
            mTypography("Grouped buttons")
            mButtonGroup {
                mButton("One")
                mButton("Two")
                mButton("Three")
            }
            br { }
            br { }
            mButtonGroup(MColor.primary, MButtonGroupVariant.contained) {
                mButton("One", MColor.primary)
                mButton("Two", MColor.primary)
                mButton("Three", MColor.primary)
            }
            br { }
            br { }
            mButtonGroup(orientation = MButtonGroupOrientation.vertical) {
                mButton("One")
                mButton("Two")
                mButton("Three")
            }
        }
        br { }
        styledDiv {
            mTypography("Split buttons")
            mButtonGroup(MColor.primary, MButtonGroupVariant.contained) {
                mButton("One")
                mButton("", variant = MButtonVariant.contained, size = MButtonSize.small) {
                    mIcon("arrow_drop_down")
                }
            }
        }
        br { }
        styledDiv {
            mTypography("Icon buttons")
            mIconButton("send", onClick = { window.alert("I was clicked")})
            mIconButton("star")
            mIconButton("delete", MColor.primary)
            mIconButton("delete", color = MColor.secondary)
        }
        styledDiv {
            mTypography("Buttons with icons")
            mButton("Delete", MColor.secondary, MButtonVariant.contained) {
                css(buttonMargin)
                attrs.startIcon = mIcon("delete", fontSize = MIconFontSize.small, addAsChild = false)
            }
            mButton("Send", MColor.primary, MButtonVariant.contained) {
                css(buttonMargin)
                attrs.endIcon = mIcon("send", fontSize = MIconFontSize.small, addAsChild = false)
            }
            mButton("Upload", variant = MButtonVariant.contained) {
                css(buttonMargin)
                attrs.startIcon = mIcon("cloud_upload", fontSize = MIconFontSize.small, addAsChild = false)
            }
            mButton("Svg Icon", variant = MButtonVariant.contained) {
                css(buttonMargin)
                attrs.startIcon = mSvgIcon("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z", addAsChild = false)
            }
        }
        br { }
        styledDiv {
            mTypography("FABs")
            mFab("add", MColor.primary) { css(buttonMargin) }
            mFab("edit-icon", MColor.secondary) { css(buttonMargin) }
            mFab("navigation-icon", disabled = true) { css(buttonMargin) }
            mFab("navigation-icon", "Extended", color = MColor.secondary) { css(buttonMargin) }
            mFab("add", MColor.secondary) { css(buttonMargin) }
        }
        br { }
        styledDiv {
            mTypography("Sizes")
            div {
                mButton("Small", size = MButtonSize.small) { css(buttonMargin) }
                mButton("Medium", size = MButtonSize.medium) { css(buttonMargin) }
                mButton("Large", size = MButtonSize.large) { css(buttonMargin) }
            }
            div {
                mButton("Small", MColor.primary, size = MButtonSize.small, variant = MButtonVariant.outlined) { css(buttonMargin) }
                mButton("Medium", MColor.primary, size = MButtonSize.medium, variant = MButtonVariant.outlined) { css(buttonMargin) }
                mButton("Large", MColor.primary, size = MButtonSize.large, variant = MButtonVariant.outlined) { css(buttonMargin) }
            }
            div {
                mButton("Small", MColor.primary, size = MButtonSize.small, variant = MButtonVariant.contained) { css(buttonMargin) }
                mButton("Medium", MColor.primary, size = MButtonSize.medium, variant = MButtonVariant.contained) { css(buttonMargin) }
                mButton("Large", MColor.primary, size = MButtonSize.large, variant = MButtonVariant.contained) { css(buttonMargin) }
            }
            div {
                mFab("add", MColor.secondary, size = MButtonSize.small) { css(buttonMargin) }
                mFab("add", MColor.secondary, size = MButtonSize.medium) { css(buttonMargin) }
                mFab("add", MColor.secondary, size = MButtonSize.large) { css(buttonMargin) }
            }
            div {
                mFab("navigation", "Extended", MColor.primary, size = MButtonSize.small) { css(buttonMargin) }
                mFab("navigation", "Extended", MColor.primary, size = MButtonSize.medium) { css(buttonMargin) }
                mFab("navigation", "Extended", MColor.primary, size = MButtonSize.large) { css(buttonMargin) }
            }
            div {
                mIconButton("delete", size = MIconButtonSize.small) { css(buttonMargin) }
                mIconButton("delete", size = MIconButtonSize.medium) { css(buttonMargin) }
                mIconButton {
                    css(buttonMargin)
                    mIcon("delete", fontSize = MIconFontSize.large)
                }
            }
        }
        br { }
        styledDiv {
            mTypography("Some play around buttons (Check the console for event notifications...)")
            button {
                +"Normal React Button"
                attrs.onClickFunction = { console.log("Hello, I am clicked") }
                attrs.onMouseMoveFunction = { console.log("Move") }
            }
            br { }
            br { }
            mButton("Clk, Dbl Clk and Move", onClick = { console.log("Yay, I clicked") }) {
                css(buttonMargin)
                attrs.onDoubleClick = { console.log("A Double Click?") }
                attrs.onMouseMove = { handleMouseMove() }
            }
            mButton("Contained", variant = MButtonVariant.contained) { css(buttonMargin) }
            mButton("Primary", MColor.primary, variant = MButtonVariant.contained) { css(buttonMargin) }
            mButton("Secondary with HRef", color = MColor.secondary, hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"),
                    variant = MButtonVariant.contained) { css(buttonMargin) }
            mButton("Styled Button") {
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
            mButton("Icon", MColor.primary, variant = MButtonVariant.contained) {
                css(buttonMargin)
                mIcon("send", MIconColor.inherit) {
                    css { marginLeft = 1.em }
                }
            }
        }
        br { }
        mTypography("More complex button...")
        moreComplexButton()
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
            styledDiv {
                css(root)
                imageInfos.forEach {
                    mButton(it.title) {
                        css(image)
                        attrs.focusRipple = true
                        attrs.key = it.title
//                    attrs.asDynamic().focusVisibleClassname = "${ComplexComponentStyles.name}-${ComplexComponentStyles::focusVisible.name}"
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
                            mTypography(color = MTypographyColor.inherit, variant = MTypographyVariant.subtitle1) {
                                css(imageTitle)
                                +it.title
                                //                        attrs.component = "span"
                                styledSpan {
                                    css {
                                        +imageMarked
                                        backgroundColor = Color(theme.palette.common.white)
//                                        val themejs = theme
//                                        put("transition", js("themejs.transitions.create('opacity')"))
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


fun RBuilder.testButtons() = child(TestButtons::class) {}
