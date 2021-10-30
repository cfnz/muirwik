package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.*

private object TestPaginationStyles : StyleSheet("TestPaginationStyles", isStatic = true) {
    val panel by css {
        "& > *" {
            not(":first-child") {
                marginTop = 2.spacingUnits
            }
        }
    }
}

val testPagination = fc<Props> {
    labNoteComponent()

    demoContainer {
        demoPanel("Basic Pagination") {
            css(TestPaginationStyles.panel)
            pagination(count = 10)
            pagination(count = 10, color = PaginationItemColor.primary)
            pagination(count = 10, color = PaginationItemColor.secondary)
            pagination(count = 10) { attrs.disabled = true }
        }

        demoPanel("Outlined Pagination") {
            css(TestPaginationStyles.panel)
            pagination(count = 10, variant = PaginationItemVariant.outlined)
            pagination(count = 10, variant = PaginationItemVariant.outlined, color = PaginationItemColor.primary)
            pagination(count = 10, variant = PaginationItemVariant.outlined, color = PaginationItemColor.secondary)
            pagination(count = 10, variant = PaginationItemVariant.outlined) { attrs.disabled = true }
        }

        demoPanel("Rounded Pagination") {
            css(TestPaginationStyles.panel)
            pagination(count = 10, shape = PaginationItemShape.rounded)
            pagination(count = 10, shape = PaginationItemShape.rounded, variant = PaginationItemVariant.outlined)
        }

        demoPanel("Pagination Size") {
            css(TestPaginationStyles.panel)
            pagination(count = 10, size = PaginationItemSize.small)
            pagination(count = 10)
            pagination(count = 10, size = PaginationItemSize.large)
        }

        demoPanel("Buttons") {
            css(TestPaginationStyles.panel)
            pagination(count = 10) {
                attrs.showFirstButton = true
                attrs.showLastButton = true
            }
            pagination(count = 10) {
                attrs.hidePrevButton = true
                attrs.hideNextButton = true
            }
        }

        demoPanel("Pagination Ranges") {
            css(TestPaginationStyles.panel)
            pagination(count = 11, defaultPage = 6) { attrs.siblingCount = 0 }
            pagination(count = 11, defaultPage = 6)
            pagination(count = 11, defaultPage = 6) { attrs.siblingCount = 0; attrs.boundaryCount = 2}
            pagination(count = 11, defaultPage = 6) { attrs.boundaryCount = 2 }
        }

        demoPanel("Controlled Pagination") {
            css(TestPaginationStyles.panel)
            val (page, setPage) = useState(2)
            pagination(count = 10, page = page) { attrs.onChange = { _, newValue -> setPage(newValue) } }
        }
    }
}
