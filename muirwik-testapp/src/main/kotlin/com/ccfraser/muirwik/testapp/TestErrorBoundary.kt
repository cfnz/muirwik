package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.errorBoundary
import com.ccfraser.muirwik.components.mPaper
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.TestErrorBoundary.CustomStyles.errorPaper
import com.ccfraser.muirwik.testapp.TestErrorBoundary.CustomStyles.paper
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css

private class TestErrorComponent : RComponent<Props, State>() {
    override fun RBuilder.render() {
        mTypography("No Error Yet")
        throw Error("Opps")
        @Suppress("UNREACHABLE_CODE")
        mTypography("After the error - We won't get here")
    }

}

class TestErrorBoundary : RComponent<Props, State>() {
    private object CustomStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val paper by css {
            padding(2.spacingUnits)
            marginBottom = 2.spacingUnits
        }
        val errorPaper by css {
            padding(2.spacingUnits)
            marginBottom = 2.spacingUnits
            color = Color.red
        }
    }

    var throwErrorInRender = false

    override fun RBuilder.render() {
        mPaper {
            css(paper)
            mTypography("This is not an error")
        }
        try {
            errorBoundary(buildElement { fallbackComponent("Oh dear, we have a problem caught by an errorBoundary, not by the try/catch") }) {
                mPaper {
                    css(errorPaper)
                    mTypography("This won't render")
                }
                child(TestErrorComponent::class) {}
            }
        } catch (t: Throwable) {
            mTypography("The Error Boundary handles this error, so we won't get here.")
        }
        try {
            errorBoundary(buildElement { fallbackComponent("Oh Dear, we have a problem caught by a try/catch, so this error boundary won't come into play") }) {
                mPaper {
                    css(errorPaper)
                    mTypography("This won't render")
                    throw Error("Opps")
                }
                child(TestErrorComponent::class) {}
            }
        } catch (t: Throwable) {
            mPaper {
                css(errorPaper)
                mTypography("Oh dear, we have an error thrown in the same function as the errorBoundary, so the try/catch will work, errorBoundary won't")
            }
        }
        mPaper {
            css(paper)
            mTypography("...and content still renders after the errors. If no error boundary or catching is done, you would get a blank screen.")
            mButton("Error in render (blank screen)", variant = MButtonVariant.outlined, onClick = { setState { throwErrorInRender = true }})
            mButton("Error in event (see console)", variant = MButtonVariant.outlined, onClick = { throw Error("Oops") }) {
                css {
                    marginLeft = 1.spacingUnits
                }
            }
        }
        if (throwErrorInRender) {
            throw Error("Opps")
        }
    }

    private fun fallbackComponent(text: String) {
        // Note we purposely use a new RBuilder so we don't render into our normal display
        RBuilder().mPaper {
            css(errorPaper)
            mTypography(text)
        }
    }
}
