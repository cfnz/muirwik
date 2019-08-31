package com.ccfraser.muirwik.components

import react.RProps

/**
 * This is not an exhaustive list of global element attributes, but more of the regularly used
 * ones. Note to get to the others, you can still go attrs.asDynamic.blabla...
 *
 * Info from https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
 */

enum class AutoCapitalize {
    off, none, on, sentences, words, characters
}

enum class TextDir {
    ltr, rtl, auto
}

enum class DropZone {
    copy, move, link
}

interface ReactHtmlElementAttributes : RProps {
    var accessKey: String
    var autoCapitalize: AutoCapitalize
//    var class: String
    var contentEditable: Boolean
    var dir: TextDir
    var draggable: Boolean
    var dropzone: DropZone
    var exportParts: String
    var hidden: Boolean
    var id: String
    var inputMode: String
    var `is`: String
    var itemId: String
    var itemProp: String
    var itemRef: String
    var itemScope: String
    var itemType: String
    var lang: String
    var part: String
    var slot: String
    var spellcheck: Boolean
    var style: String
    var tabIndex: Int
    var title: String
    var translate: Boolean
}

