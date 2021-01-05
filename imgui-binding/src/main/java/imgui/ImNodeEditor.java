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
        #include "widgets.h"
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

    public static native long getDoubleClickedNode(); /*
        return (jlong)(uintptr_t)ed::GetDoubleClickedNode();
    */

    public static native long getDoubleClickedPin(); /*
        return (jlong)(uintptr_t)ed::GetDoubleClickedPin();
    */

    public static native long getDoubleClickedLink(); /*
        return (jlong)(uintptr_t)ed::GetDoubleClickedLink();
    */

    public static native float getCurrentZoom(); /*
        return ed::GetCurrentZoom();
    */

    public static native void pinRect(float x, float y, float w, float h); /*
        ed::PinRect(ImVec2(x, y), ImVec2(w, h));
    */

    public static native void pinPivotRect(float x, float y, float w, float h); /*
        ed::PinPivotRect(ImVec2(x, y), ImVec2(w, h));
    */

    public static native void pinPivotSize(float w, float h); /*
        ed::PinPivotSize(ImVec2(w, h));
    */

    public static native void pinPivotScale(float w, float h); /*
        ed::PinPivotScale(ImVec2(w, h));
    */

    public static native void pinPivotAlignment(float x, float y); /*
        ed::PinPivotAlignment(ImVec2(x, y));
    */

    public static boolean showNodeContextMenu(ImInt nodeId){
        return nShowNodeContextMenu(nodeId.getData());
    }

    public static boolean showPinContextMenu(ImInt pinId){
        return nShowPinContextMenu(pinId.getData());
    }

    public static boolean showLinkContextMenu(ImInt linkId){
        return nShowLinkContextMenu(linkId.getData());
    }

    public static native long getNodeWithContextMenu(); /*
        ed::NodeId id;
        return ed::ShowNodeContextMenu(&id) ? (jlong)(uintptr_t)id : -1;
    */

    public static native long getPinWithContextMenu(); /*
        ed::PinId id;
        return ed::ShowPinContextMenu(&id) ? (jlong)(uintptr_t)id : -1;
    */

    public static native long getLinkWithContextMenu(); /*
        ed::LinkId id;
        return ed::ShowLinkContextMenu(&id) ? (jlong)(uintptr_t)id : -1;
    */

    private static native boolean nShowNodeContextMenu(int[] nodeId); /*
        return ed::ShowNodeContextMenu((ed::NodeId*)&nodeId[0]);
    */

    private static native boolean nShowPinContextMenu(int[] pinId); /*
        return ed::ShowPinContextMenu((ed::PinId*)&pinId[0]);
    */

    private static native boolean nShowLinkContextMenu(int[] linkId); /*
        return ed::ShowLinkContextMenu((ed::LinkId*)&linkId[0]);
    */

    public static native boolean showBackgroundContextMenu(); /*
        return ed::ShowBackgroundContextMenu();
    */

    public static native void suspend(); /*
        ed::Suspend();
    */

    public static native void resume(); /*
        ed::Resume();
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

    // UTILITIES
    // TODO: MOVE OUT OF THIS CLASS

    public enum IconType {
        Flow,
        Circle,
        Square,
        Grid,
        RoundSquare,
        Diamond
    }

    public static void icon(IconType type, float w, float h, boolean isFilled, float r, float g, float b, float a,
                                     float ir, float ig, float ib, float ia) {
        nIcon(w, h, type.ordinal(), isFilled, r, g, b, a, ir, ig, ib, ia);
    }

    private static native void nIcon(float w, float h, int type, boolean isFilled, float r, float g, float b, float a,
                                   float ir, float ig, float ib, float ia); /*
        ax::Widgets::Icon(ImVec2(w, h), (ax::Drawing::IconType)type, isFilled, ImVec4(r, g, b, a), ImVec4(ir, ig, ib, ia));
    */

    // UTILITIES END

}
