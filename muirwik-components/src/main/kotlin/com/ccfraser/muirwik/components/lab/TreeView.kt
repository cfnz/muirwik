package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/lab/TreeView")
private external val treeViewModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val treeViewComponentType: ComponentType<TreeViewProps> = treeViewModule.default

external interface TreeViewProps : StyledProps {
  var defaultCollapseIcon: ReactElement
  var defaultEndIcon: ReactElement
  var defaultExpanded: Array<String>
  var defaultExpandIcon: ReactElement
  var defaultParentIcon: ReactElement
  var defaultSelected: Array<String>
  var disableSelection: Boolean
  var expanded: Array<String>
  var multiSelect: Boolean
  var onNodeSelect: (event: Object, value: dynamic/*Array<String> | String*/) -> Unit
  var onNodeToggle: (event: Object, nodeIds: Array<String>) -> Unit
  var selected: dynamic /*Array<String> | String*/
}

fun RBuilder.treeView(
    defaultCollapseIcon: ReactElement? = null,
    defaultEndIcon: ReactElement? = null,
    defaultExpanded: Array<String> = emptyArray(),
    defaultExpandIcon: ReactElement? = null,
    defaultParentIcon: ReactElement? = null,
    defaultSelected: Array<String> = emptyArray(),
    disableSelection: Boolean = false,
    expanded: Array<String>? = null,
    multiSelect: Boolean = false,
    onNodeSelect: ((event: Object, value: dynamic/*Array<String> | String*/) -> Unit)? = null,
    onNodeToggle: ((event: Object, nodeIds: Array<String>) -> Unit)? = null,
    selected: Any? /*Array<String> | String*/ = null                                            ,
    className: String? = null,
    handler: StyledHandler<TreeViewProps>? = null
) {
  createStyled(treeViewComponentType, className, handler) {
    defaultCollapseIcon?.let { attrs.defaultCollapseIcon = it }
    defaultEndIcon?.let { attrs.defaultEndIcon = it }
    attrs.defaultExpanded = defaultExpanded
    defaultExpandIcon?.let { attrs.defaultExpandIcon = it }
    defaultParentIcon?.let { attrs.defaultParentIcon = it }
    attrs.defaultSelected = defaultSelected
    attrs.disableSelection = disableSelection
    expanded?.let { attrs.expanded = it }
    attrs.multiSelect = multiSelect
    onNodeSelect?.let { attrs.onNodeSelect = it }
    onNodeToggle?.let { attrs.onNodeToggle = it }
    selected?.let { attrs.selected = it }
  }
}