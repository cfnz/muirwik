package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.PropsWithClassName
import react.RBuilder
import styled.StyledHandler
import styled.css

public fun RBuilder.demoPanel(heading: String, handler: StyledHandler<out PropsWithClassName>) {
    typography(heading) {
        css {
            paddingTop = 2.spacingUnits
        }
    }

    paper(variant = PaperVariant.outlined) {
        css {
            padding(2.spacingUnits)
        }
        handler(this)
    }
}

// The gist is we put demoPanels into the demoContainer
public fun RBuilder.demoContainer(handler: StyledHandler<out PropsWithClassName>) {
    box {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.flexStart
        }

        handler(this)
    }
}