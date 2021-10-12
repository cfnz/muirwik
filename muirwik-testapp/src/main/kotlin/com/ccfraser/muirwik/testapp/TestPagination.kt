package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.lab.pagination.MPaginationColor
import com.ccfraser.muirwik.components.lab.pagination.MPaginationShape
import com.ccfraser.muirwik.components.lab.pagination.MPaginationSize
import com.ccfraser.muirwik.components.lab.pagination.MPaginationVariant
import com.ccfraser.muirwik.components.lab.pagination.mPagination
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
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
    mPagination(count = 10)
    mPagination(count = 10, color = MPaginationColor.primary)
    mPagination(count = 10, color = MPaginationColor.secondary)
    mPagination(count = 10, disabled = true)
  }

  titledDiv("Outlined Pagination") {
    mPagination(count = 10, variant = MPaginationVariant.outlined)
    mPagination(count = 10, variant = MPaginationVariant.outlined, color = MPaginationColor.primary)
    mPagination(count = 10, variant = MPaginationVariant.outlined, color = MPaginationColor.secondary)
    mPagination(count = 10, variant = MPaginationVariant.outlined, disabled = true)
  }

  titledDiv("Rounded Pagination") {
    mPagination(count = 10, shape = MPaginationShape.rounded)
    mPagination(count = 10, shape = MPaginationShape.rounded, variant = MPaginationVariant.outlined)
  }

  titledDiv("Pagination Size") {
    mPagination(count = 10, size = MPaginationSize.small)
    mPagination(count = 10)
    mPagination(count = 10, size = MPaginationSize.large)
  }

  titledDiv("Buttons") {
    mPagination(count = 10, showFirstButton = true, showLastButton = true)
    mPagination(count = 10, hidePrevButton = true, hideNextButton = true)
  }

  titledDiv("Pagination Ranges") {
    mPagination(count = 11, defaultPage = 6, siblingCount = 0)
    mPagination(count = 11, defaultPage = 6)
    mPagination(count = 11, defaultPage = 6, siblingCount = 0, boundaryCount = 2)
    mPagination(count = 11, defaultPage = 6, boundaryCount = 2)
  }

  titledDiv("Controlled Pagination") {
    val (page ,setPage) = useState<Number>(2)
    mPagination(count = 10, page = page, onChange = { _ , newValue -> setPage(newValue) })
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
