package imgui.extension.icon;


public class ImIcon {

     /*JNI
        #pragma warning (disable:4244)

        #include <imgui.h>
        #include "jni_common.h"
        #include "jni_binding_struct.h"
        #include "widgets.h"
     */


    public enum IconType {
        Flow,
        Circle,
        Square,
        Grid,
        RoundSquare,
        Diamond
    }

    public static void icon(final IconType type, final float w, final float h, final boolean isFilled, final float r, final float g, final float b, final float a,
                            final float ir, final float ig, final float ib, final float ia) {
        nIcon(w, h, type.ordinal(), isFilled, r, g, b, a, ir, ig, ib, ia);
    }

    private static native void nIcon(float w, float h, int type, boolean isFilled, float r, float g, float b, float a,
                                     float ir, float ig, float ib, float ia); /*
        ax::Widgets::Icon(ImVec2(w, h), (ax::Drawing::IconType)type, isFilled, ImVec4(r, g, b, a), ImVec4(ir, ig, ib, ia));
    */
}
