package imgui.internal;

import imgui.type.ImInt;

public final class ImGui extends imgui.ImGui {
    private static final ImGuiDockNode DOCK_NODE = new ImGuiDockNode(0);
    private static final ImGuiWindow WINDOW = new ImGuiWindow(0);

    /*JNI
        #include <stdint.h>
        #include <imgui.h>
        #include <imgui_internal.h>
     */

    public static ImGuiWindow getCurrentWindow() {
        final long ptr = nGetCurrentWindow();
        if (ptr == 0L) {
            return null;
        } else {
           WINDOW.ptr = ptr;
           return WINDOW;
        }
    }

    public static native long nGetCurrentWindow(); /*
        return (jlong)(uintptr_t) ImGui::GetCurrentWindow();
    */

    public static void focusWindow(final ImGuiWindow window) {
        nFocusWindow(window.ptr);
    }

    public static native void nFocusWindow(long ptr); /*
        ImGui::FocusWindow((ImGuiWindow*)ptr);
    */

    public static native void nBringWindowToFocusFront(long ptr); /*
        ImGui::BringWindowToFocusFront((ImGuiWindow*)ptr);
    */

    public static native void nBringWindowToDisplayFront(long ptr); /*
        ImGui::BringWindowToDisplayFront((ImGuiWindow*)ptr);
    */

    public static native int getItemID(); /*
        return (jint)ImGui::GetItemID();
    */

    public static native int getActiveID(); /*
        return (jint)ImGui::GetActiveID();
    */

    public static native int getFocusID(); /*
        return (jint)ImGui::GetFocusID();
    */

    public static void setActiveID(final int id, final ImGuiWindow window) {
        nSetActiveID(id, window.ptr);
    }

    public static native void nSetActiveID(int id, long windowPtr); /*
        ImGui::SetActiveID((ImGuiID)id, (ImGuiWindow*)windowPtr);
    */

    public static void setFocusID(final int id, final ImGuiWindow window) {
        nSetFocusID(id, window.ptr);
    }

    public static native void nSetFocusID(int id, long windowPtr); /*
        ImGui::SetFocusID((ImGuiID)id, (ImGuiWindow*)windowPtr);
    */

    public static native void clearActiveID(); /*
        ImGui::ClearActiveID();
    */

    public static native int getHoveredID(); /*
        return (jint)ImGui::GetHoveredID();
    */

    public static native void setHoveredID(int id); /*
        ImGui::SetHoveredID((ImGuiID)id);
    */



    // Basic Helpers for widget code

    public static native void itemSize(float width, float height, float textBaselineY); /*
        ImGui::ItemSize(ImVec2(width, height), textBaselineY);
    */

    public static native void itemSize(float x, float y, float mx, float my, float textBaselineY); /*
        ImGui::ItemSize(ImRect(x, y, mx, my), textBaselineY);
    */

    public static native boolean itemAdd(float x, float y, float mx, float my, int id); /*
        return ImGui::ItemAdd(ImRect(x, y, mx, my), (ImGuiID)id);
    */

    public static native boolean itemHoverable(float x, float y, float mx, float my, int id); /*
        return ImGui::ItemHoverable(ImRect(x, y, mx, my), (ImGuiID)id);
    */

    public static native void pushItemFlag(int imGuiItemFlags, boolean enabled); /*
        ImGui::PushItemFlag(imGuiItemFlags, enabled);
    */

    public static native void popItemFlag(); /*
        ImGui::PopItemFlag();
    */

    // Docking - Builder function needs to be generally called before the node is used/submitted.
    // - The DockBuilderXXX functions are designed to _eventually_ become a public API, but it is too early to expose it and guarantee stability.
    // - Do not hold on ImGuiDockNode* pointers! They may be invalidated by any split/merge/remove operation and every frame.
    // - To create a DockSpace() node, make sure to set the ImGuiDockNodeFlags_DockSpace flag when calling DockBuilderAddNode().
    //   You can create dockspace nodes (attached to a window) _or_ floating nodes (carry its own window) with this API.
    // - DockBuilderSplitNode() create 2 child nodes within 1 node. The initial node becomes a parent node.
    // - If you intend to split the node immediately after creation using DockBuilderSplitNode(), make sure
    //   to call DockBuilderSetNodeSize() beforehand. If you don't, the resulting split sizes may not be reliable.
    // - Call DockBuilderFinish() after you are done.

    public static native void dockBuilderDockWindow(String windowName, int nodeId); /*
        ImGui::DockBuilderDockWindow(windowName, nodeId);
    */

    public static ImGuiDockNode dockBuilderGetNode(final int nodeId) {
        final long ptr = nDockBuilderGetNode(nodeId);
        if (ptr == 0) {
            return null;
        } else {
            DOCK_NODE.ptr = ptr;
            return DOCK_NODE;
        }
    }

    private static native long nDockBuilderGetNode(int nodeId); /*
        return (intptr_t)ImGui::DockBuilderGetNode(nodeId);
    */

    public static ImGuiDockNode dockBuilderGetCentralNode(final int nodeId) {
        final long ptr = nDockBuilderGetCentralNode(nodeId);
        if (ptr == 0) {
            return null;
        } else {
            DOCK_NODE.ptr = ptr;
            return DOCK_NODE;
        }
    }

    private static native long nDockBuilderGetCentralNode(int nodeId); /*
        return (intptr_t)ImGui::DockBuilderGetCentralNode(nodeId);
    */

    public static native int dockBuilderAddNode(); /*
        return ImGui::DockBuilderAddNode();
    */

    public static native int dockBuilderAddNode(int nodeId); /*
        return ImGui::DockBuilderAddNode(nodeId);
    */

    public static native int dockBuilderAddNode(int nodeId, int flags); /*
        return ImGui::DockBuilderAddNode(nodeId, flags);
    */

    /**
     * Remove node and all its child, undock all windows.
     */
    public static native void dockBuilderRemoveNode(int nodeId); /*
        ImGui::DockBuilderRemoveNode(nodeId);
    */

    public static native void dockBuilderRemoveNodeDockedWindows(int nodeId); /*
        ImGui::DockBuilderRemoveNodeDockedWindows(nodeId);
    */

    public static native void dockBuilderRemoveNodeDockedWindows(int nodeId, boolean clearSettingsRefs); /*
        ImGui::DockBuilderRemoveNodeDockedWindows(nodeId, clearSettingsRefs);
    */

    /**
     * Remove all split/hierarchy. All remaining docked windows will be re-docked to the remaining root node (node_id).
     */
    public static native void dockBuilderRemoveNodeChildNodes(int nodeId); /*
        ImGui::DockBuilderRemoveNodeChildNodes(nodeId);
    */

    public static native void dockBuilderSetNodePos(int nodeId, float posX, float posY); /*
        ImGui::DockBuilderSetNodePos(nodeId, ImVec2(posX, posY));
    */

    public static native void dockBuilderSetNodeSize(int nodeId, float sizeX, float sizeY); /*
        ImGui::DockBuilderSetNodeSize(nodeId, ImVec2(sizeX, sizeY));
    */

    /**
     * Create 2 child nodes in this parent node.
     */
    public static int dockBuilderSplitNode(int nodeId, int splitDir, float sizeRatioForNodeAtDir, ImInt outIdAtDir, ImInt outIdAtOppositeDir) {
        if (outIdAtDir == null && outIdAtOppositeDir != null) {
            return nDockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, 0, outIdAtOppositeDir.getData());
        } else if (outIdAtDir != null && outIdAtOppositeDir == null) {
            return nDockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, outIdAtDir.getData());
        } else if (outIdAtDir != null) {
            return nDockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, outIdAtDir.getData(), outIdAtOppositeDir.getData());
        } else {
            return nDockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir);
        }
    }

    private static native int nDockBuilderSplitNode(int nodeId, int splitDir, float sizeRatioForNodeAtDir, int[] outIdAtDir, int[] outIdAtOppositeDir); /*
        return ImGui::DockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, (ImGuiID*)&outIdAtDir[0], (ImGuiID*)&outIdAtOppositeDir[0]);
    */

    private static native int nDockBuilderSplitNode(int nodeId, int splitDir, float sizeRatioForNodeAtDir); /*
        return ImGui::DockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, NULL, NULL);
    */

    private static native int nDockBuilderSplitNode(int nodeId, int splitDir, float sizeRatioForNodeAtDir, int[] outIdAtDir); /*
        return ImGui::DockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, (ImGuiID*)&outIdAtDir[0], NULL);
    */

    private static native int nDockBuilderSplitNode(int nodeId, int splitDir, float sizeRatioForNodeAtDir, int o, int[] outIdAtOppositeDir); /*
        return ImGui::DockBuilderSplitNode(nodeId, splitDir, sizeRatioForNodeAtDir, NULL, (ImGuiID*)&outIdAtOppositeDir[0]);
    */

    // TODO DockBuilderCopyDockSpace, DockBuilderCopyNode

    public static native void dockBuilderCopyWindowSettings(String srcName, String dstName); /*
        ImGui::DockBuilderCopyWindowSettings(srcName, dstName);
    */

    public static native void dockBuilderFinish(int nodeId); /*
        ImGui::DockBuilderFinish(nodeId);
    */
}
