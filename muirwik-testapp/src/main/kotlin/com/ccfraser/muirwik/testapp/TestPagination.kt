package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.PaginationColor
import com.ccfraser.muirwik.components.PaginationShape
import com.ccfraser.muirwik.components.PaginationSize
import com.ccfraser.muirwik.components.PaginationVariant
import com.ccfraser.muirwik.components.TypographyVariant
import com.ccfraser.muirwik.components.pagination
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.typography
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.html.DIV
import react.Props
import react.RBuilder
import react.fc
import react.useState
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

val testPagination = fc<Props> { _ ->
  labNoteComponent()

  titledDiv("Basic Pagination") {
    pagination(count = 10)
    pagination(count = 10, color = PaginationColor.primary)
    pagination(count = 10, color = PaginationColor.secondary)
    pagination(count = 10, disabled = true)
  }

  titledDiv("Outlined Pagination") {
    pagination(count = 10, variant = PaginationVariant.outlined)
    pagination(count = 10, variant = PaginationVariant.outlined, color = PaginationColor.primary)
    pagination(count = 10, variant = PaginationVariant.outlined, color = PaginationColor.secondary)
    pagination(count = 10, variant = PaginationVariant.outlined, disabled = true)
  }

  titledDiv("Rounded Pagination") {
    pagination(count = 10, shape = PaginationShape.rounded)
    pagination(count = 10, shape = PaginationShape.rounded, variant = PaginationVariant.outlined)
  }

  titledDiv("Pagination Size") {
    pagination(count = 10, size = PaginationSize.small)
    pagination(count = 10)
    pagination(count = 10, size = PaginationSize.large)
  }

  titledDiv("Buttons") {
    pagination(count = 10, showFirstButton = true, showLastButton = true)
    pagination(count = 10, hidePrevButton = true, hideNextButton = true)
  }

  titledDiv("Pagination Ranges") {
    pagination(count = 11, defaultPage = 6, siblingCount = 0)
    pagination(count = 11, defaultPage = 6)
    pagination(count = 11, defaultPage = 6, siblingCount = 0, boundaryCount = 2)
    pagination(count = 11, defaultPage = 6, boundaryCount = 2)
  }

  titledDiv("Controlled Pagination") {
    val (page ,setPage) = useState<Number>(2)
    pagination(count = 10, page = page, onChange = { _ , newValue -> setPage(newValue) })
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

    typography(subtitle, TypographyVariant.h4)
    content()
  }
}
