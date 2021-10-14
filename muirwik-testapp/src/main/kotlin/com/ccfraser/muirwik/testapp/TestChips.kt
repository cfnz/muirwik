package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestChips.CustomStyles.margin
import kotlinx.browser.window
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


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
                mChip("Basic Chip") {
                    css(margin)
                }
                mChip(
                    "Clickable Chip",
                    avatar = buildElement { mAvatar { +"MB" } },
                    onClick = { handleClick() }
                ) {
                    css(margin)
                }
                mChip(
                    "Deletable Chip",
                    avatar = buildElement { mAvatar(src = "/images/cards/contemplative-reptile.jpg") },
                    onDelete = { handleDelete() }
                ) {
                    css(margin)
                }
                mChip(
                    "Clickable Deletable Chip",
                    avatar = buildElement { mAvatar { buildElement { mIcon("face") }}},
                    onClick = { handleClick() },
                    onDelete = { handleDelete() }
                ) {
                    css(margin)
                }
                mChip(
                    "Custom delete icon Chip".asDynamic(),
                    onClick = { handleClick() },
                    onDelete = { handleDelete() }
                ) {
                    css(margin)
                    attrs.deleteIcon = buildElement { mIcon("done") }
                }
                mChip("Primary Color Chip", color = MChipColor.primary, onClick = { handleClick() }, onDelete = { handleDelete() }) {
                    css(margin)
                }
                mChip("Secondary Color Chip", color = MChipColor.secondary, onClick = { handleClick() }, onDelete = { handleDelete() }) {
                    css(margin)
                }
                mChip("Primary Outline Chip", color = MChipColor.primary, variant = MChipVariant.outlined, onClick = { handleClick() }, onDelete = { handleDelete() }) {
                    css(margin)
                }
            }
            demoPanel("Small Chips") {
                mChip(
                    "Primary Color Chip",
                    color = MChipColor.primary,
                    size = MChipSize.small,
                    onClick = { handleClick() },
                    onDelete = { handleDelete() }) {
                    css(margin)
                }
                mChip(
                    "Secondary Color Chip",
                    color = MChipColor.secondary,
                    size = MChipSize.small,
                    onClick = { handleClick() },
                    onDelete = { handleDelete() }) {
                    css(margin)
                }
                mChip(
                    "Primary Outline Chip",
                    color = MChipColor.primary,
                    size = MChipSize.small,
                    variant = MChipVariant.outlined,
                    onClick = { handleClick() },
                    onDelete = { handleDelete() }) {
                    css(margin)
                }
            }
            demoPanel("Chip Array Example") {
                css { display = Display.flex; justifyContent = JustifyContent.center; flexWrap = FlexWrap.wrap; padding(2.spacingUnits) }

                chipData.forEach { entry ->
                    mChip(
                        entry.value,
                        key = entry.key,
                        avatar = if (entry.value == "React" || entry.value == "Kotlin") buildElement { mAvatar { buildElement { mIcon("tag_faces")}}} else null,
                        onDelete = {
                            if (entry.value == "React" || entry.value == "Kotlin") {
                                window.alert("Why would you want to delete ${entry.value}? :-)")
                            } else {
                                setState { chipData.remove(entry.key) }
                            }
                        }
                    ) {
                        css(margin)
                    }
                }
            }
        }
    }
}
