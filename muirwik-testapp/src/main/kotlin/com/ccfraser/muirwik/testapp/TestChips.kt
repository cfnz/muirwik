package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestChips.CustomStyles.margin
import kotlinx.browser.window
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css


class TestChips : RComponent<Props, State>() {
    val chipData = hashMapOf(
            0 to "Angular",
            1 to "jQuery",
            2 to "Polymer",
            3 to "React",
            4 to "Vue.js",
            5 to "Kotlin"
    )

    private object CustomStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(1.spacingUnits)
        }
    }

    fun handleClick() {
        window.alert("You clicked the Chip.")
    }

    fun handleDelete() {
        window.alert("You clicked the Delete icon.")
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Standard Chips") {
                css { display = Display.flex; justifyContent = JustifyContent.center; flexWrap = FlexWrap.wrap }
                chip("Basic Chip") {
                    css(margin)
                }
                chip("Clickable Chip", avatar = buildElement { avatar { +"MB" } }) {
                    attrs.onClick = { handleClick() }
                    css(margin)
                }
                chip("Deletable Chip", avatar = buildElement { avatar(src = "/images/cards/contemplative-reptile.jpg") }) {
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Clickable Deletable Chip", avatar = buildElement { avatar { buildElement { icon("face") }}}) {
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Custom delete icon Chip") {
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    attrs.deleteIcon = buildElement { icon("done") }
                    css(margin)
                }
                chip("Primary Color Chip", color = ChipColor.primary) {
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Secondary Color Chip", color = ChipColor.secondary) {
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Primary Outline Chip", color = ChipColor.primary) {
                    attrs.variant = ChipVariant.outlined
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
            }
            demoPanel("Small Chips") {
                chip("Primary Color Chip", color = ChipColor.primary) {
                    attrs.size = ChipSize.small
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Secondary Color Chip", color = ChipColor.secondary) {
                    attrs.size = ChipSize.small
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
                chip("Primary Outline Chip", color = ChipColor.primary) {
                    attrs.size = ChipSize.small
                    attrs.variant = ChipVariant.outlined
                    attrs.onClick = { handleClick() }
                    attrs.onDelete = { handleDelete() }
                    css(margin)
                }
            }
            demoPanel("Chip Array Example") {
                css { display = Display.flex; justifyContent = JustifyContent.center; flexWrap = FlexWrap.wrap; padding(2.spacingUnits) }

                chipData.forEach { entry ->
                    chip(entry.value,
                        key = entry.key,
                        avatar = if (entry.value == "React" || entry.value == "Kotlin") buildElement { avatar { buildElement { icon("tag_faces")}}} else null
                    ) {
                        attrs.onDelete = {
                            if (entry.value == "React" || entry.value == "Kotlin") {
                                window.alert("Why would you want to delete ${entry.value}? :-)")
                            } else {
                                setState { chipData.remove(entry.key) }
                            }
                        }
                        css(margin)
                    }
                }
            }
        }
    }
}
