package imgui;

import imgui.type.ImInt;

public final class ImNodes {

    public enum ColorStyle {
        NodeBackground,
        NodeBackgroundHovered,
        NodeBackgroundSelected,
        NodeOutline,
        TitleBar,
        TitleBarHovered,
        TitleBarSelected,
        Link,
        LinkHovered,
        LinkSelected,
        Pin,
        PinHovered,
        BoxSelector,
        BoxSelectorOutline,
        GridBackground,
        GridLine,
    }

    public enum StyleVar {
        GridSpacing,
        NodeCornerRounding,
        NodePaddingHorizontal,
        NodePaddingVertical,
        NodeBorderThickness,
        LinkThickness,
        LinkLineSegmentsPerLength,
        LinkHoverDistance,
        PinCircleRadius,
        PinQuadSideLength,
        PinTriangleSideLength,
        PinLineThickness,
        PinHoverRadius,
        PinOffset
    }

    public enum PinShape {
        Circle,
        CircleFilled,
        Triangle,
        TriangleFilled,
        Quad,
        QuadFilled
    }

    private ImNodes() { }

    /*JNI
        #include <stdint.h>
        #include <imgui.h>
        #include <imnodes.h>
        #include "jni_common.h"
        #include "jni_binding_struct.h"
     */

    // STYLES

    public static native void styleColorsDark(); /*
        imnodes::StyleColorsDark();
    */
    public static native void styleColorsClassic(); /*
        imnodes::StyleColorsClassic();
    */
    public static native void styleColorsLight(); /*
        imnodes::StyleColorsLight();
    */

    public static void pushColorStyle(final ColorStyle colorStyle, final int color) {
        nPushColorStyle(colorStyle.ordinal(), color);
    }

    private static native void nPushColorStyle(int colorStyle, int color); /*
        imnodes::PushColorStyle((imnodes::ColorStyle)colorStyle, color);
    */

    public static native void popColorStyle(); /*
        imnodes::PopColorStyle();
    */

    public static void pushStyleVar(final StyleVar styleVar, final float value) {
        nPushStyleVar(styleVar.ordinal(), value);
    }

    private static native void nPushStyleVar(int styleVar, float value); /*
        imnodes::PushStyleVar((imnodes::StyleVar)styleVar, value);
    */

    public static native void popStyleVar(); /*
        imnodes::PopStyleVar();
    */


    // END STYLES


    public static native void initialize(); /*
        imnodes::Initialize();
    */

    public static native void shutdown(); /*
        imnodes::Shutdown();
    */

    public static native void beginNodeEditor(); /*
        imnodes::BeginNodeEditor();
    */

    public static native void endNodeEditor(); /*
        imnodes::EndNodeEditor();
    */

    public static native void beginNode(int node); /*
        imnodes::BeginNode(node);
    */

    public static native void endNode(); /*
        imnodes::EndNode();
    */

    public static native void link(int id, int source, int target); /*
        imnodes::Link(id, source, target);
    */

    public static native void beginNodeTitleBar(); /*
        imnodes::BeginNodeTitleBar();
    */

    public static native void endNodeTitleBar(); /*
        imnodes::EndNodeTitleBar();
    */

    public static native void beginStaticAttribute(int id); /*
        imnodes::BeginStaticAttribute(id);
    */

    public static native void endStaticAttribute(); /*
        imnodes::EndStaticAttribute();
    */

    public static void beginInputAttribute(final int id) {
        nBeginInputAttribute(id, PinShape.CircleFilled.ordinal());
    }

    public static void beginInputAttribute(final int id, final PinShape shape) {
        nBeginInputAttribute(id, shape.ordinal());
    }

    private static native void nBeginInputAttribute(int id, int pinShape); /*
        imnodes::BeginInputAttribute(id, (imnodes::PinShape)pinShape);
    */

    public static native void endInputAttribute(); /*
        imnodes::EndInputAttribute();
    */

    public static void beginOutputAttribute(final int id) {
        nBeginOutputAttribute(id, PinShape.CircleFilled.ordinal());
    }

    public static void beginOutputAttribute(final int id, final PinShape pinShape) {
        nBeginOutputAttribute(id, pinShape.ordinal());
    }

    private static native void nBeginOutputAttribute(int id, int pinShape); /*
        imnodes::BeginOutputAttribute(id, (imnodes::PinShape)pinShape);
    */

    public static native void endOutputAttribute(); /*
        imnodes::EndOutputAttribute();
    */

    public static native boolean isEditorHovered(); /*
        return imnodes::IsEditorHovered();
    */

    public static native int getHoveredNode(); /*
        int i;
        return imnodes::IsNodeHovered(&i) ? i : -1;
    */

    public static native int getHoveredLink(); /*
        int i;
        return imnodes::IsLinkHovered(&i) ? i : -1;
    */

    public static native int getHoveredPin(); /*
        int i;
        return imnodes::IsPinHovered(&i) ? i : -1;
    */

    public static boolean isLinkCreated(final ImInt source, final ImInt target) {
        return nIsLinkCrated(source.getData(), target.getData());
    }

    private static native boolean nIsLinkCrated(int[] source, int[] target); /*
        return imnodes::IsLinkCreated(&source[0], &target[0]);
    */

    public static native void setNodeDraggable(int node, boolean isDraggable); /*
        imnodes::SetNodeDraggable(node, isDraggable);
    */

    public static native void setNodeScreenSpacePos(int node, float x, float y); /*
        imnodes::SetNodeScreenSpacePos(node, ImVec2(x, y));
    */

    public static native void setNodeEditorSpacePos(int node, float x, float y); /*
        imnodes::SetNodeEditorSpacePos(node, ImVec2(x, y));
    */

    public static native void setNodeGridSpacePos(int node, float x, float y); /*
        imnodes::SetNodeGridSpacePos(node, ImVec2(x, y));
    */

    public static native void getNodeScreenSpacePos(int node, ImVec2 vec2); /*
        ImVec2 result = imnodes::GetNodeScreenSpacePos(node);
        Jni::ImVec2Cpy(env, &result, vec2);
    */

    public static native void getNodeEditorSpacePos(int node, ImVec2 vec2); /*
        ImVec2 result = imnodes::GetNodeScreenSpacePos(node);
        Jni::ImVec2Cpy(env, &result, vec2);
    */

    public static native void getNodeGridSpacePos(int node, ImVec2 vec2); /*
        ImVec2 result = imnodes::GetNodeScreenSpacePos(node);
        Jni::ImVec2Cpy(env, &result, vec2);
    */

    public static native void editorResetPanning(float x, float y); /*
        imnodes::EditorContextResetPanning(ImVec2(x, y));
    */

    public static native void editorMoveToNode(int node); /*
        imnodes::EditorContextMoveToNode(node);
    */

}
