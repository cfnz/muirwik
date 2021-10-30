package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.IconFontSize
import com.ccfraser.muirwik.components.fontSize
import com.ccfraser.muirwik.components.icon
import com.ccfraser.muirwik.components.lab.treeItem
import com.ccfraser.muirwik.components.lab.treeView
import com.ccfraser.muirwik.components.svgIcon
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css

private object TestTreeViewStyles : StyleSheet("TestTreeViewStyles", isStatic = true) {
    val root1 by css {
        height = 240.px
        flexGrow = 1.0
        width = 400.px
    }
    val root2 by css {
        height = 216.px
        flexGrow = 1.0
        width = 400.px
    }
    val root3 by css {
        height = 264.px
        flexGrow = 1.0
        width = 400.px
        overflowY = Overflow.auto
        "& .MuiTreeItem-group" {
            marginLeft = 15.px
            paddingLeft = 18.px
            borderLeftStyle = BorderStyle.dashed
            borderWidth = 1.px
        }
        "& .MuiTreeItem-iconContainer" {
            "& .close" {
                opacity = 0.3
            }
        }
    }
}

val testTreeView = fc<Props> {
    demoContainer {
        demoPanel("Basic tree view") {
            treeView(
                defaultCollapseIcon = buildElement { icon("expand_more") },
                defaultExpandIcon = buildElement { icon("chevron_right") },
            ) {
                css(TestTreeViewStyles.root1)
                treeStructure()
            }
        }

        demoPanel("Multi Selection") {
            treeView(
                defaultCollapseIcon = buildElement { icon("expand_more") },
                defaultExpandIcon = buildElement { icon("chevron_right") }
            ) {
                attrs.multiSelect = true
                css(TestTreeViewStyles.root1)
                treeStructure()
            }
        }

        demoPanel("Controlled tree view") {
            val (expanded, setExpanded) = useState<Array<String>>(emptyArray())
            val (selected, setSelected) = useState<Array<String>>(emptyArray())

            treeView(
                selected = selected,
                defaultCollapseIcon = buildElement { icon("expand_more") },
                defaultExpandIcon = buildElement { icon("chevron_right") }
            ) {
                attrs.expanded = expanded
                attrs.onNodeToggle = { _, nodeIds ->  setExpanded(nodeIds) }
                attrs.onNodeSelect = { _, nodeIds -> setSelected(nodeIds) }
                css(TestTreeViewStyles.root2)
                treeStructure()

            }
        }

        demoPanel("Custom icons and border") {
            val minusSquare = "M22.047 22.074v0 0-20.147 0h-20.12v0 20.147 0h20.12zM22.047 24h-20.12q-.803 0-1.365-.562t-.562-1.365v-20.147q0-.776.562-1.351t1.365-.575h20.147q.776 0 1.351.575t.575 1.351v20.147q0 .803-.575 1.365t-1.378.562v0zM17.873 11.023h-11.826q-.375 0-.669.281t-.294.682v0q0 .401.294 .682t.669.281h11.826q.375 0 .669-.281t.294-.682v0q0-.401-.294-.682t-.669-.281z"
            val plusSquare = "M22.047 22.074v0 0-20.147 0h-20.12v0 20.147 0h20.12zM22.047 24h-20.12q-.803 0-1.365-.562t-.562-1.365v-20.147q0-.776.562-1.351t1.365-.575h20.147q.776 0 1.351.575t.575 1.351v20.147q0 .803-.575 1.365t-1.378.562v0zM17.873 12.977h-4.923v4.896q0 .401-.281.682t-.682.281v0q-.375 0-.669-.281t-.294-.682v-4.896h-4.923q-.401 0-.682-.294t-.281-.669v0q0-.401.281-.682t.682-.281h4.923v-4.896q0-.401.294-.682t.669-.281v0q.401 0 .682.281t.281.682v4.896h4.923q.401 0 .682.281t.281.682v0q0 .375-.281.669t-.682.294z"
            val closeSquare = "M17.485 17.512q-.281.281-.682.281t-.696-.268l-4.12-4.147-4.12 4.147q-.294.268-.696.268t-.682-.281-.281-.682.294-.669l4.12-4.147-4.12-4.147q-.294-.268-.294-.669t.281-.682.682-.281.696 .268l4.12 4.147 4.12-4.147q.294-.268.696-.268t.682.281 .281.669-.294.682l-4.12 4.147 4.12 4.147q.294.268 .294.669t-.281.682zM22.047 22.074v0 0-20.147 0h-20.12v0 20.147 0h20.12zM22.047 24h-20.12q-.803 0-1.365-.562t-.562-1.365v-20.147q0-.776.562-1.351t1.365-.575h20.147q.776 0 1.351.575t.575 1.351v20.147q0 .803-.575 1.365t-1.378.562v0z"

            treeView(
                defaultCollapseIcon = buildElement { svgIcon(minusSquare) { attrs.fontSize = IconFontSize.inherit } },
                defaultExpandIcon = buildElement { svgIcon(plusSquare) { attrs.fontSize = IconFontSize.inherit } },
                defaultEndIcon = buildElement {
                    svgIcon(closeSquare) {
                        attrs.fontSize = IconFontSize.inherit
                        attrs.className = "close"
                    }
                }
            ) {
                attrs.defaultExpanded = arrayOf("1")
                css(TestTreeViewStyles.root3)
                treeItem("1", "Main") {
                    treeItem("2", "Hello")
                    treeItem("3", "Subtree with children") {
                        treeItem("6", "Hello")
                        treeItem("7", "Sub-subtree with children") {
                            treeItem("9", "Child 1")
                            treeItem("10", "Child 2")
                            treeItem("11", "Child 3")
                        }
                        treeItem("8", "Hello")
                    }
                    treeItem("4", "World")
                    treeItem("5", "Something something")
                }
            }
        }
    }
}

private fun RBuilder.treeStructure() {
    treeItem("1", "Applications") {
        treeItem("2", "Calender")
        treeItem("3", "Chrome")
        treeItem("4", "Webstorm")
    }
    treeItem("5", "Documents") {
        treeItem("10", "OSS")
        treeItem("6", "Material-UI") {
            treeItem("7", "src") {
                treeItem("8", "index.js")
                treeItem("9", "tree-view.js")
            }
        }
    }
}
