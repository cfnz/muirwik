package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestErrorBoundary.CustomStyles.errorPaper
import com.ccfraser.muirwik.testapp.TestErrorBoundary.CustomStyles.paper
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css

private class TestErrorComponent : RComponent<Props, State>() {
    override fun RBuilder.render() {
        typography("No Error Yet")
        throw Error("Opps")
        @Suppress("UNREACHABLE_CODE")
        typography("After the error - We won't get here")
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
        paper {
            css(paper)
            typography("This is not an error")
        }
        try {
            errorBoundary(buildElement { fallbackComponent("Oh dear, we have a problem caught by an errorBoundary, not by the try/catch") }) {
                paper {
                    css(errorPaper)
                    typography("This won't render")
                }
                child(TestErrorComponent::class) {}
            }
        } catch (t: Throwable) {
            typography("The Error Boundary handles this error, so we won't get here.")
        }
        try {
            errorBoundary(buildElement { fallbackComponent("Oh Dear, we have a problem caught by a try/catch, so this error boundary won't come into play") }) {
                paper {
                    css(errorPaper)
                    typography("This won't render")
                    throw Error("Opps")
                }
                child(TestErrorComponent::class) {}
            }
        } catch (t: Throwable) {
            paper {
                css(errorPaper)
                typography("Oh dear, we have an error thrown in the same function as the errorBoundary, so the try/catch will work, errorBoundary won't")
            }
        }
        paper {
            css(paper)
            typography("...and content still renders after the errors. If no error boundary or catching is done, you would get a blank screen.")
            button("Error in render (blank screen)", variant = ButtonVariant.outlined) {
                attrs.onClick = { setState { throwErrorInRender = true }}
            }
            button("Error in event (see console)", variant = ButtonVariant.outlined) {
                attrs.onClick = { throw Error("Oops") }
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
        RBuilder().paper {
            css(errorPaper)
            typography(text)
        }
    }
}
