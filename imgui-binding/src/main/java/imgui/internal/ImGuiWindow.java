package imgui.internal;

import imgui.binding.ImGuiStruct;

public final class ImGuiWindow extends ImGuiStruct {

    public ImGuiWindow(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include <stdint.h>
        #include <imgui.h>
        #include <imgui_internal.h>
        #include "jni_common.h"
        #include "jni_binding_struct.h"

        #define IMGUI_WINDOW ((ImGuiWindow*)STRUCT_PTR)
     */

    public native int getID(); /*
        return IMGUI_WINDOW->ID;
    */

    public native String getName(); /*
        return env->NewStringUTF(IMGUI_WINDOW->Name);
    */



}
