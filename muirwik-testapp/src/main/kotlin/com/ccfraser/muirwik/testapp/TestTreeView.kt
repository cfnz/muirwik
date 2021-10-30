package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.TypographyVariant
import com.ccfraser.muirwik.components.lab.TreeViewProps
import com.ccfraser.muirwik.components.lab.treeItem
import com.ccfraser.muirwik.components.lab.treeView
import com.ccfraser.muirwik.components.icon
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.typography
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
    treeView(
        defaultCollapseIcon = buildElement { icon("expand_more") },
        defaultExpandIcon = buildElement { icon("chevron_right") },
    ) {
      css(TestTreeViewStyles.root1)
      treeStructure()
    }
  }

  titledDiv("Multi Selection") {
    treeView(
        defaultCollapseIcon = buildElement { icon("expand_more") },
        defaultExpandIcon = buildElement { icon("chevron_right") },
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

    treeView(
        defaultCollapseIcon = buildElement { icon("expand_more") },
        defaultExpandIcon = buildElement { icon("chevron_right") },
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

private fun StyledElementBuilder<TreeViewProps>.treeStructure() {
  treeItem(nodeId = "1", label = ReactNode("Applications")) {
    treeItem(nodeId = "2", label = ReactNode("Calender"))
    treeItem(nodeId = "3", label = ReactNode("Chrome"))
    treeItem(nodeId = "4", label = ReactNode("Webstorm"))
  }
  treeItem(nodeId = "5", label = ReactNode("Documents")) {
    treeItem(nodeId = "10", label = ReactNode("OSS"))
    treeItem(nodeId = "6", label = ReactNode("Material-UI")) {
      treeItem(nodeId = "7", label = ReactNode("src")) {
        treeItem(nodeId = "8", label = ReactNode("index.js"))
        treeItem(nodeId = "9", label = ReactNode("tree-view.js"))
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

    typography(subtitle, TypographyVariant.h4)
    content()
  }
}