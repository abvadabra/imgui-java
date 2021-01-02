package imgui;

import imgui.type.ImInt;

public final class ImNodeEditor {

    private ImNodeEditor() { }

    public enum PinKind {
        Input,
        Output
    }

    public enum StyleColor {
        Bg,
        Grid,
        NodeBg,
        NodeBorder,
        HovNodeBorder,
        SelNodeBorder,
        NodeSelRect,
        NodeSelRectBorder,
        HovLinkBorder,
        SelLinkBorder,
        LinkSelRect,
        LinkSelRectBorder,
        PinRect,
        PinRectBorder,
        Flow,
        FlowMarker,
        GroupBg,
        GroupBorder
    }

    public enum StyleVar {
        NodePadding,
        NodeRounding,
        NodeBorderWidth,
        HoveredNodeBorderWidth,
        SelectedNodeBorderWidth,
        PinRounding,
        PinBorderWidth,
        LinkStrength,
        SourceDirection,
        TargetDirection,
        ScrollDuration,
        FlowMarkerDistance,
        FlowSpeed,
        FlowDuration,
        PivotAlignment,
        PivotSize,
        PivotScale,
        PinCorners,
        PinRadius,
        PinArrowSize,
        PinArrowWidth,
        GroupRounding,
        GroupBorderWidth,
    }

    /*JNI
        #include <imgui.h>
        #include <imgui_node_editor.h>
        #include "jni_common.h"
        #include "jni_binding_struct.h"
        namespace ed = ax::NodeEditor;
     */

    // CORE

    public static native long createEditor(); /*
        return (jlong)ed::CreateEditor();
    */

    public static native void destroyEditor(long editor); /*
        ed::DestroyEditor((ed::EditorContext*)editor);
    */

    public static native void setCurrentEditor(long editor); /*
        ed::SetCurrentEditor((ed::EditorContext*)editor);
    */

    public static native void begin(String title); /*
        ed::Begin(title);
    */

    public static native void beginNode(int id); /*
        ed::BeginNode(id);
    */

    public static void beginPin(final int id, final PinKind kind) {
        nBeginPin(id, kind.ordinal());
    }

    private static native void nBeginPin(int pin, int pinKind); /*
        ed::BeginPin(pin, (ed::PinKind)pinKind);
    */

    public static native void endPin(); /*
        ed::EndPin();
    */

    public static native void endNode(); /*
        ed::EndNode();
    */

    public static native void end(); /*
        ed::End();
    */

    // CORE END

    // STYLING

    public static void pushStyleColor(final StyleColor color, final float r, final float g, final float b, final float a) {
        nPushStyleColor(color.ordinal(), r, g, b, a);
    }

    private static native void nPushStyleColor(int color, float r, float g, float b, float a); /*
        ed::PushStyleColor((ed::StyleColor)color, ImVec4(r, g, b, a));
    */

    public static native void popStyleColor(int count); /*
        ed::PopStyleColor(count);
    */

    public static void pushStyleColor(final StyleVar var, final float v) {
        nPushStyleVar(var.ordinal(), v);
    }

    public static void pushStyleColor(final StyleVar var, final float x, final float y) {
        nPushStyleVar(var.ordinal(), x, y);
    }

    public static void pushStyleColor(final StyleVar var, final float r, final float g, final float b, final float a) {
        nPushStyleVar(var.ordinal(), r, g, b, a);
    }

    private static native void nPushStyleVar(int var, float v); /*
        ed::PushStyleVar((ed::StyleVar)var, v);
    */

    private static native void nPushStyleVar(int var, float x, float y); /*
        ed::PushStyleVar((ed::StyleVar)var, ImVec2(x, y));
    */

    private static native void nPushStyleVar(int var, float r, float g, float b, float a); /*
        ed::PushStyleVar((ed::StyleVar)var, ImVec4(r, g, b, a));
    */

    public static native void popStyleVar(int count); /*
        ed::PopStyleVar(count);
    */

    // STYLING END

    // ADDITIONAL LOGIC

    public static native boolean showNodeContextMenu(int nodeId); /*
        return ed::ShowNodeContextMenu((ed::NodeId*)nodeId);
    */

    public static native boolean showPinContextMenu(int pinId); /*
        return ed::ShowPinContextMenu((ed::PinId*)pinId);
    */

    public static native boolean showLinkContextMenu(int linkId); /*
        return ed::ShowLinkContextMenu((ed::LinkId*)linkId);
    */

    public static native boolean showBackgroundContextMenu(); /*
        return ed::ShowBackgroundContextMenu();
    */


    public static native void setNodePosition(int node, float x, float y); /*
        ed::SetNodePosition(node, ImVec2(x, y));
    */

    public static void link(final int id, final int startPinId, final int endPinId) {
        link(id, startPinId, endPinId, 1F, 1F, 1F, 1F, 1F);
    }

    public static native void link(int id, int startPinId, int endPinId, float r, float g, float b, float a,
                                   float thickness); /*
        ed::Link(id, startPinId, endPinId, ImVec4(r, g, b, a), thickness);
    */

    public static boolean beginCreate() {
        return beginCreate(1F, 1F, 1F, 1F, 1F);
    }

    public static native boolean beginCreate(float r, float g, float b, float a, float thickness); /*
        return ed::BeginCreate(ImVec4(r, g, b, a), thickness);
    */

    public static boolean queryNewLink(final ImInt startId, final ImInt endId) {
        return nQueryNewLink(startId.getData(), endId.getData(), 1F, 1F, 1F, 1F, 1F);
    }

    public static boolean queryNewLink(final ImInt startId, final ImInt endId, final float r, final float g, final float b, final float a, final float thickness) {
        return nQueryNewLink(startId.getData(), endId.getData(), r, g, b, a, thickness);
    }

    private static native boolean nQueryNewLink(int[] startId, int[] endId, float r, float g, float b, float a, float thickness); /*
        return ed::QueryNewLink((ed::PinId*)&startId[0], (ed::PinId*)&endId[0], ImVec4(r, g, b, a), thickness);
    */

    public static boolean acceptNewItem() {
        return acceptNewItem(1F, 1F, 1F, 1F, 1F);
    }

    public static native boolean acceptNewItem(float r, float g, float b, float a, float thickness); /*
        return ed::AcceptNewItem(ImVec4(r, g, b, a), thickness);
    */

    public static void rejectNewItem() {
        rejectNewItem(1F, 1F, 1F, 1F, 1F);
    }

    public static native void rejectNewItem(float r, float g, float b, float a, float thickness); /*
        ed::RejectNewItem(ImVec4(r, g, b, a), thickness);
    */

    public static native void endCreate(); /*
        ed::EndCreate();
    */

    public static native boolean beginDelete(); /*
        return ed::BeginDelete();
    */

    public static boolean queryDeletedLink(final ImInt linkId, final ImInt startId, final ImInt endId) {
        return nQueryDeletedLink(linkId.getData(), startId.getData(), endId.getData());
    }

    private static native boolean nQueryDeletedLink(int[] linkId, int[] startId, int[] endId); /*
        return ed::QueryDeletedLink((ed::LinkId*)&linkId[0], (ed::PinId*)&startId[0], (ed::PinId*)&endId[0]);
    */

    public static boolean queryDeletedNode(final ImInt nodeId) {
        return nQueryDeletedNode(nodeId.getData());
    }

    private static native boolean nQueryDeletedNode(int[] nodeId); /*
        return ed::QueryDeletedNode((ed::NodeId*)&nodeId[0]);
    */

    public static native void endDelete(); /*
        ed::EndDelete();
    */

    public static native void navigateToContent(float duration); /*
        ed::NavigateToContent(duration);
    */


    public static native void getNodePosition(int node, ImVec2 dst); /*
        ImVec2 result = ed::GetNodePosition(node);
        Jni::ImVec2Cpy(env, &result, dst);
    */

    public static native void centerNodeOnScreen(int node); /*
        ed::CenterNodeOnScreen(node);
    */

    public static native boolean hasSelectionChanged(); /*
        return ed::HasSelectionChanged();
    */

    // ADDITIONAL LOGIC END

}
