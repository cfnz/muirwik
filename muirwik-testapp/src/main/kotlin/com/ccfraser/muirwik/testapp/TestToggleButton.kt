package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MGridAlignItems
import com.ccfraser.muirwik.components.MGridDirection
import com.ccfraser.muirwik.components.MGridSpacing
import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.direction
import com.ccfraser.muirwik.components.lab.MToggleButtonGroupOrientation
import com.ccfraser.muirwik.components.lab.MToggleButtonGroupSize
import com.ccfraser.muirwik.components.lab.mToggleButton
import com.ccfraser.muirwik.components.lab.mToggleButtonGroup
import com.ccfraser.muirwik.components.mGridContainer
import com.ccfraser.muirwik.components.mGridItem
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.html.DIV
import react.Props
import react.RBuilder
import react.StateSetter
import react.fc
import react.useState
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

val testToggleButton = fc<Props> {

  exclusiveSelectionExample()

  multipleSelectionExample()

  sizesExample()

  verticalExample()
}

private fun RBuilder.verticalExample() {
  titledDiv("Vertical Buttons") {
    val (alignment, setAlignment) = useState<Any>("list")

    mToggleButtonGroup(
        value = alignment,
        orientation = MToggleButtonGroupOrientation.vertical,
        exclusive = true,
        onChange = { _, value -> setAlignment(value) },
    ) {
      mToggleButton(value = "list") {
        mIcon("view_list")
      }
      mToggleButton(value = "module") {
        mIcon("view_module")
      }
      mToggleButton(value = "quilt") {
        mIcon("view_quilt")
      }
    }
  }
}

private fun RBuilder.sizesExample() {
  titledDiv("Sizes") {
    val (alignment, setAlignment) = useState<Any>("left")

    mGridContainer(MGridSpacing.spacing2, alignItems = MGridAlignItems.flexStart) {
      attrs.direction = MGridDirection.column

      MToggleButtonGroupSize.values().forEach { size ->
        mGridItem {
          textAlignmentButtonGroup(alignment, setAlignment, size)
        }
      }
    }
  }
}

private fun RBuilder.textAlignmentButtonGroup(
    alignment: Any,
    setAlignment: StateSetter<Any>,
    size: MToggleButtonGroupSize = MToggleButtonGroupSize.medium
) {
  mToggleButtonGroup(
      value = alignment,
      size = size,
      exclusive = true,
      onChange = { _, value -> setAlignment(value) },
  ) {
    mToggleButton(value = "left") {
      mIcon("format_align_left")
    }
    mToggleButton(value = "center") {
      mIcon("format_align_center")
    }
    mToggleButton(value = "right") {
      mIcon("format_align_right")
    }
    mToggleButton(value = "justify") {
      mIcon("format_align_justify")
    }
  }
}

private fun RBuilder.multipleSelectionExample() {
  titledDiv("Multiple Selection") {
    val (formats, setFormats) = useState<Any> { arrayOf("bold", "italic") }

    mToggleButtonGroup(
        value = formats,
        onChange = { _, value -> setFormats(value) }
    ) {
      mToggleButton(value = "bold") {
        mIcon("format_bold")
      }
      mToggleButton(value = "italic") {
        mIcon("format_italic")
      }
      mToggleButton(value = "underlined") {
        mIcon("format_underlined")
      }
      mToggleButton(value = "color", disabled = true) {
        mIcon("format_color_fill")
        mIcon("arrow_drop_down")
      }
    }
  }
}

private fun RBuilder.exclusiveSelectionExample() {
  titledDiv("Exclusive Selection") {
    val (alignment, setAlignment) = useState<Any>("left")

    textAlignmentButtonGroup(alignment, setAlignment)
  }
}

private fun RBuilder.titledDiv(subtitle: String, content: StyledDOMBuilder<DIV>.() -> Unit) {
  styledDiv {
    css {
      marginBottom = 4.spacingUnits
      "& > *" {
        marginTop = 2.spacingUnits
      }
    }

    mTypography(subtitle, MTypographyVariant.h4)
    content()
  }
}