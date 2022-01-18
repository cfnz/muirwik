package com.ccfraser.muirwik.components

import kotlinext.js.jso
import react.*

typealias ErrorBoundaryErrorEvent = (error: Throwable, info: ErrorInfo) -> Unit

external interface ErrorBoundaryProps : PropsWithChildren {
    /**
     * Content you want to display on an error (note, we don't want to add this as a child, i.e. don't use the normal RBuilder
     * in a render (e.g. use a different (or new) RBuilder or for material components, set addAsChild = false if available
     */
    var fallbackContent: ReactElement

    /**
     * If you want to log the error, you can also get notified by using this event
     */
    var onError: ErrorBoundaryErrorEvent?
}

external interface ErrorBoundaryState : State {
    var hasError: Boolean
}

class ErrorBoundary(props: ErrorBoundaryProps) : RComponent<ErrorBoundaryProps, ErrorBoundaryState>(props) {

    override fun RBuilder.render() {
        if (state.hasError) {
            child(props.fallbackContent)
        } else {
            props.children()
        }
    }

    override fun componentDidCatch(error: Throwable, info: ErrorInfo) {
        props.onError?.let { it(error, info) }
    }

    companion object : RStatics<ErrorBoundaryProps, ErrorBoundaryState, ErrorBoundary, Nothing>(ErrorBoundary::class) {
        init {
            getDerivedStateFromError = {
                val result: ErrorBoundaryState = jso()
                result.hasError = true
                result
            }
        }
    }
}

fun RBuilder.errorBoundary(fallbackContent: ReactElement, onError: ErrorBoundaryErrorEvent? = null, handler: RHandler<Props>) = child(ErrorBoundary::class) {
    attrs.fallbackContent = fallbackContent
    attrs.onError = onError
    handler()
}

