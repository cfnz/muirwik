package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.lab.MTreeViewProps
import com.ccfraser.muirwik.components.lab.mTreeItem
import com.ccfraser.muirwik.components.lab.mTreeView
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinext.js.Object
import kotlinx.css.flexGrow
import kotlinx.css.height
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.css.maxWidth
import kotlinx.css.px
import kotlinx.html.DIV
import react.Props
import react.RBuilder
import react.ReactNode
import react.buildElement
import react.fc
import react.useState
import styled.StyleSheet
import styled.StyledDOMBuilder
import styled.StyledElementBuilder
import styled.css
import styled.styledDiv

private object TestTreeViewStyles : StyleSheet("TestTreeViewStyles", isStatic = true) {
  val root1 by css {
    height = 240.px
    flexGrow = 1.0
    maxWidth = 400.px
  }
  val root2 by css {
    height = 216.px
    flexGrow = 1.0
    maxWidth = 400.px
  }
}

val testTreeView = fc<Props> {
  labNoteComponent()

  titledDiv("Basic tree view") {
    mTreeView(
        defaultCollapseIcon = buildElement { mIcon("expand_more") },
        defaultExpandIcon = buildElement { mIcon("chevron_right") },
    ) {
      css(TestTreeViewStyles.root1)
      treeStructure()
    }
  }

  titledDiv("Multi Selection") {
    mTreeView(
        defaultCollapseIcon = buildElement { mIcon("expand_more") },
        defaultExpandIcon = buildElement { mIcon("chevron_right") },
        multiSelect = true
    ) {
      css(TestTreeViewStyles.root1)
      treeStructure()
    }
  }

  titledDiv("Controlled tree view") {
    val (expanded, setExpanded) = useState<Array<String>>(emptyArray())
    val (selected, setSelected) = useState<Array<String>>(emptyArray())

    val handleToggle = {_: Object, nodeIds: Array<String> ->
      setExpanded(nodeIds)
    }

    val handleSelect = {_: Object, nodeIds: Array<String> ->
      setSelected(nodeIds)
    }

    mTreeView(
        defaultCollapseIcon = buildElement { mIcon("expand_more") },
        defaultExpandIcon = buildElement { mIcon("chevron_right") },
        expanded = expanded,
        selected = selected,
        onNodeToggle = handleToggle,
        onNodeSelect = handleSelect,
    ) {
      css(TestTreeViewStyles.root2)
      treeStructure()
    }
  }
}

private fun StyledElementBuilder<MTreeViewProps>.treeStructure() {
  mTreeItem(nodeId = "1", label = ReactNode("Applications")) {
    mTreeItem(nodeId = "2", label = ReactNode("Calender"))
    mTreeItem(nodeId = "3", label = ReactNode("Chrome"))
    mTreeItem(nodeId = "4", label = ReactNode("Webstorm"))
  }
  mTreeItem(nodeId = "5", label = ReactNode("Documents")) {
    mTreeItem(nodeId = "10", label = ReactNode("OSS"))
    mTreeItem(nodeId = "6", label = ReactNode("Material-UI")) {
      mTreeItem(nodeId = "7", label = ReactNode("src")) {
        mTreeItem(nodeId = "8", label = ReactNode("index.js"))
        mTreeItem(nodeId = "9", label = ReactNode("tree-view.js"))
      }
    }
  }
}

private fun RBuilder.titledDiv(subtitle: String, content: StyledDOMBuilder<DIV>.() -> Unit) {
  styledDiv {
    css {
      marginBottom = 4.spacingUnits
      "& > *" {
        marginTop = 2.spacingUnits
      }
    }

    mTypography(subtitle, MTypographyVariant.h4)
    content()
  }
}