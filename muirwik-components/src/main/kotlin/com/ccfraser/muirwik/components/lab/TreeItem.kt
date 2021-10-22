package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.ElementType
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler

@JsModule("@material-ui/lab/TreeItem")
private external val treeItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val treeItemComponentType: ComponentType<MTreeItemProps> = treeItemModule.default

external interface MTreeItemProps : StyledPropsWithCommonAttributes {
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

fun RBuilder.mTreeItem(
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
    handler: StyledHandler<MTreeItemProps>? = null
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