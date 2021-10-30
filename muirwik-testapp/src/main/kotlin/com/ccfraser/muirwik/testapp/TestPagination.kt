package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
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

val testPagination = fc<Props> {
    labNoteComponent()

    titledDiv("Basic Pagination") {
        pagination(count = 10)
        pagination(count = 10, color = PaginationItemColor.primary)
        pagination(count = 10, color = PaginationItemColor.secondary)
        pagination(count = 10) { attrs.disabled = true }
    }

    titledDiv("Outlined Pagination") {
        pagination(count = 10, variant = PaginationItemVariant.outlined)
        pagination(count = 10, variant = PaginationItemVariant.outlined, color = PaginationItemColor.primary)
        pagination(count = 10, variant = PaginationItemVariant.outlined, color = PaginationItemColor.secondary)
        pagination(count = 10, variant = PaginationItemVariant.outlined) { attrs.disabled = true }
    }

    titledDiv("Rounded Pagination") {
        pagination(count = 10, shape = PaginationItemShape.rounded)
        pagination(count = 10, shape = PaginationItemShape.rounded, variant = PaginationItemVariant.outlined)
    }

    titledDiv("Pagination Size") {
        pagination(count = 10, size = PaginationItemSize.small)
        pagination(count = 10)
        pagination(count = 10, size = PaginationItemSize.large)
    }

    titledDiv("Buttons") {
        pagination(count = 10) {
            attrs.showFirstButton = true
            attrs.showLastButton = true
        }
        pagination(count = 10) {
            attrs.hidePrevButton = true
            attrs.hideNextButton = true
        }
    }

    titledDiv("Pagination Ranges") {
        pagination(count = 11, defaultPage = 6) { attrs.siblingCount = 0 }
        pagination(count = 11, defaultPage = 6)
        pagination(count = 11, defaultPage = 6) { attrs.siblingCount = 0; attrs.boundaryCount = 2}
        pagination(count = 11, defaultPage = 6) { attrs.boundaryCount = 2 }
    }

    titledDiv("Controlled Pagination") {
        val (page, setPage) = useState(2)
        pagination(count = 10, page = page) { attrs.onChange = { _, newValue -> setPage(newValue) } }
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
