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

    @JsName("ContentComponent")
    var contentComponent: ElementType

    @JsName("ContentProps")
    var contentProps: ElementType

    var disabled: Boolean
    var endIcon: ReactElement
    var expandIcon: ReactElement
    var icon: ReactElement
    var label: ReactNode
    var nodeId: String

    @JsName("TransitionComponent")
    var transitionComponent: ElementType

    @JsName("TransitionProps")
    var transitionProps: Object
}

fun RBuilder.treeItem(
    nodeId: String,
    label: String? = null,
    handler: StyledHandler<TreeItemProps>? = null
) {
    createStyled(treeItemComponentType, handler) {
        label?.let { attrs.label = ReactNode(it) }
        attrs.nodeId = nodeId
    }
}

fun RBuilder.treeItem(
    nodeId: String,
    label: ReactNode? = null,
    handler: StyledHandler<TreeItemProps>? = null
) {
    createStyled(treeItemComponentType, handler) {
        label?.let { attrs.label = it }
        attrs.nodeId = nodeId
    }
}
