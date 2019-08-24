package com.ccfraser.muirwik.components

import org.w3c.dom.DragEvent
import org.w3c.dom.clipboard.ClipboardEvent
import org.w3c.dom.events.*
import react.RProps

/**
 * This is non exhaustive typed list of HTML Element events (which is a mixture of events inherited
 * from Element, and implemented from GlobalEventHandlers and TouchEventHandlers.
 *
 * Info from https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement.
 *
 * Since most of the Material UI components pass on any props to the underlying element, this makes it easier
 * to set them if required.
 */
interface ReactHtmlElementEvents : RProps {
    var onAbort: ((Event) -> Unit)?
    var onBlur: ((FocusEvent) -> Unit)?
    var onCanPlay: ((Event) -> Unit)?
    var onCanPlayThrough: ((Event) -> Unit)?

    // onChange is often overridden and with different parameters, so we will just not define it here.
    //var onChange: ((Event) -> Unit)?

    var onClick: ((MouseEvent) -> Unit)?
    var onContextMenu: ((Event) -> Unit)?
    var onCopy: ((ClipboardEvent) -> Unit)?
    var onCut: ((ClipboardEvent) -> Unit)?
    var onDoubleClick: ((MouseEvent) -> Unit)?
    var onDrag: ((DragEvent) -> Unit)?
    var onDragEnd: ((DragEvent) -> Unit)?
    var onDragEnter: ((DragEvent) -> Unit)?
    var onDragExit: ((DragEvent) -> Unit)?
    var onDragLeave: ((DragEvent) -> Unit)?
    var onDragOver: ((DragEvent) -> Unit)?
    var onDragStart: ((DragEvent) -> Unit)?
    var onDrop: ((DragEvent) -> Unit)?
    var onDurationChange: ((Event) -> Unit)?
    var onEmptied: ((Event) -> Unit)?
    var onEnded: ((Event) -> Unit)?
    var onError: ((Event) -> Unit)?
    var onFocus: ((FocusEvent) -> Unit)?
    var onInput: ((InputEvent) -> Unit)?
    var onKeyDown: ((KeyboardEvent) -> Unit)?
    var onKeyPress: ((KeyboardEvent) -> Unit)?
    var onKeyUp: ((KeyboardEvent) -> Unit)?
    var onLoad: ((Event) -> Unit)?
    var onLoadedData: ((Event) -> Unit)?
    var onLoadedMetadata: ((Event) -> Unit)?
    var onLoadStart: ((Event) -> Unit)?
    var onMouseDown: ((MouseEvent) -> Unit)?
    var onMouseEnter: ((MouseEvent) -> Unit)?
    var onMouseLeave: ((MouseEvent) -> Unit)?
    var onMouseMove: ((MouseEvent) -> Unit)?
    var onMouseOut: ((MouseEvent) -> Unit)?
    var onMouseOver: ((MouseEvent) -> Unit)?
    var onMouseUp: ((MouseEvent) -> Unit)?
    var onPaste: ((ClipboardEvent) -> Unit)?
    var onPause: ((Event) -> Unit)?
    var onPlay: ((Event) -> Unit)?
    var onPlaying: ((Event) -> Unit)?
    var onProgress: ((Event) -> Unit)?
    var onRateChange: ((Event) -> Unit)?
    var onScroll: ((UIEvent) -> Unit)?
    var onSeeked: ((Event) -> Unit)?
    var onSeeking: ((Event) -> Unit)?
    var onSelect: ((UIEvent) -> Unit)?
    var onStalled: ((Event) -> Unit)?
    var onSubmit: ((FocusEvent) -> Unit)?
    var onSuspend: ((Event) -> Unit)?
    var onTouchCancel: ((TouchEvent) -> Unit)?
    var onTouchEnd: ((TouchEvent) -> Unit)?
    var onTouchMove: ((TouchEvent) -> Unit)?
    var onTouchStart: ((TouchEvent) -> Unit)?
    var onTimeUpdate: ((Event) -> Unit)?
    var onVolumeChange: ((Event) -> Unit)?
    var onWaiting: ((Event) -> Unit)?
    var onWheel: ((WheelEvent) -> Unit)?
}
