package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.*

public fun RBuilder.demoPanel(heading: String, handler: StyledHandler<out PropsWithClassName>) {
    mTypography(heading) {
        css {
            paddingTop = 2.spacingUnits
        }
    }

    mPaper(variant = MPaperVariant.outlined) {
        css {
            padding(2.spacingUnits)
        }
        handler(this)
    }
}

// The gist is we put demoPanels into the demoContainer
public fun RBuilder.demoContainer(handler: StyledHandler<out PropsWithClassName>) {
    mBox {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.flexStart
        }

        handler(this)
    }
}