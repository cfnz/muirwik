package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.jsObject
import react.ComponentType
import react.Props
import react.RBuilder
import react.createElement
import styled.StyledHandler

@JsModule("@mui/material/SvgIcon")
private external val svgIconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val svgIconComponentType: ComponentType<SvgIconProps> = svgIconModule.default

@Suppress("EnumEntryName")
enum class SvgShapeRendering {
    auto, optimizeSpeed, crispEdges, geometricPrecision
}

external interface SvgIconProps : IconProps {
    var htmlColor: String?
    var titleAccess: String?
    var viewBox: String?
}
var SvgIconProps.shapeRendering by EnumPropToStringNullable(SvgShapeRendering.values())

fun RBuilder.svgIcon(
    svgPath: String,
    color: IconColor = IconColor.inherit,
    htmlColor: String? = null,
    handler: StyledHandler<SvgIconProps>? = null
) {
    createStyled(svgIconComponentType, handler) {
        attrs.color = color
        htmlColor?.let { attrs.htmlColor = it }

        val props: Props =  jsObject {  }
        props.asDynamic().d = svgPath

        childList.add(createElement("path", props))
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mSvgIcon(
    svgPath: String,
    color: IconColor = IconColor.inherit,
    htmlColor: String? = null,
    fontSize: IconFontSize = IconFontSize.medium,
    className: String? = null,
    handler: StyledHandler<SvgIconProps>? = null
) {
    createStyled(svgIconComponentType, className, handler) {
        attrs.color = color
        htmlColor?.let { attrs.htmlColor = it }
        attrs.fontSize = fontSize

        val props: Props =  jsObject {  }
        props.asDynamic().d = svgPath

        childList.add(createElement("path", props))
    }
}


