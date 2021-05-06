package imgui.extension.imnodes;

import imgui.binding.ImGuiStructDestroyable;

public final class ImNodesContext extends ImGuiStructDestroyable {
    public ImNodesContext() {
    }

    public ImNodesContext(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_imnodes.h"

        #define IMNODES_CONTEXT ((ImNodesEditorContext*)STRUCT_PTR)
     */

    @Override
    protected long create() {
        return nCreate();
    }

    @Override
    public void destroy() {
        nDestroy();
    }

    private native long nCreate(); /*
        return (intptr_t)imnodes::EditorContextCreate();
    */

    private native void nDestroy(); /*
        imnodes::EditorContextFree(IMNODES_CONTEXT);
    */
}
