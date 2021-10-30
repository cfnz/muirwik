package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/lab/TreeItem")
private external val treeItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val treeItemComponentType: ComponentType<TreeItemProps> = treeItemModule.default

external interface TreeItemProps : StyledProps {
  var collapseIcon: ReactElement
  var endIcon: ReactElement
  var expandIcon: ReactElement
  var icon: ReactElement
  var label: ReactNode
  var nodeId: String
  var onIconClick: Any
  var onLabelClick: Any
  @JsName("TransitionComponent")
  var transitionComponent: ElementType
  @JsName("TransitionProps")
  var transitionProps: Object
}

fun RBuilder.treeItem(
    collapseIcon: ReactElement? = null,
    endIcon: ReactElement? = null,
    expandIcon: ReactElement? = null,
    icon: ReactElement? = null,
    label: ReactNode? = null,
    nodeId: String? = null,
    onIconClick: Any? = null,
    onLabelClick: Any? = null,
    transitionComponent: ElementType? = null,
    transitionProps: Object? = null,
    className: String? = null,
    handler: StyledHandler<TreeItemProps>? = null
) {
  createStyled(treeItemComponentType, className, handler) {
    collapseIcon?.let { attrs.collapseIcon = it }
    endIcon?.let { attrs.endIcon = it }
    expandIcon?.let { attrs.expandIcon = it }
    icon?.let { attrs.icon = it }
    label?.let { attrs.label = it }
    nodeId?.let { attrs.nodeId = it }
    onIconClick?.let { attrs.onIconClick = it }
    onLabelClick?.let { attrs.onLabelClick = it }
    transitionComponent?.let { attrs.transitionComponent = it }
    transitionProps?.let { attrs.transitionProps = it }
  }
}