package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/lab/TreeView")
private external val treeViewModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val treeViewComponentType: ComponentType<TreeViewProps> = treeViewModule.default

// Seems to work fine with props being Array<String> rather then String for single select
external interface TreeViewProps : StyledProps {
    var defaultCollapseIcon: ReactElement
    var defaultEndIcon: ReactElement
    var defaultExpanded: Array<String>
    var defaultExpandIcon: ReactElement
    var defaultParentIcon: ReactElement
    var defaultSelected: Array<String>
    var disableItemsFocusable: Boolean
    var disableSelection: Boolean
    var expanded: Array<String>
    var multiSelect: Boolean
    var onNodeToggle: (event: Event, nodeIds: Array<String>) -> Unit
    var onNodeFocus: (event: Event, value: Array<String>) -> Unit
    var onNodeSelect: (event: Event, value: Array<String>) -> Unit
    var selected: Array<String>
}

fun RBuilder.treeView(
    selected: Array<String>? = null,
    defaultCollapseIcon: ReactElement? = null,
    defaultEndIcon: ReactElement? = null,
    defaultExpandIcon: ReactElement? = null,
    defaultParentIcon: ReactElement? = null,
    handler: StyledHandler<TreeViewProps>? = null
) {
    createStyled(treeViewComponentType, handler) {
        defaultCollapseIcon?.let { attrs.defaultCollapseIcon = it }
        defaultEndIcon?.let { attrs.defaultEndIcon = it }
        defaultExpandIcon?.let { attrs.defaultExpandIcon = it }
        defaultParentIcon?.let { attrs.defaultParentIcon = it }
        selected?.let { attrs.selected = it }
    }
}
