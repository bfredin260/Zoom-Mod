package net.bfredin.zoommod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ZoomModClient implements ClientModInitializer {
    public static final MinecraftClient client  = MinecraftClient.getInstance();
    public static boolean USER_OPTION_SMOOTH_CAM;

    public static KeyBinding ZOOM_KEY;
    public static final double ZOOM_MULT = 0.23;

    public static boolean IS_ZOOMING = false;

    @Override
    public void onInitializeClient() {
        ZOOM_KEY = new KeyBinding("key.zoom_mod.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.zoom_mod.zoom");
        USER_OPTION_SMOOTH_CAM = false;

        KeyBindingHelper.registerKeyBinding(ZOOM_KEY);
    }

    public static boolean keybindIsPressed() {
        return ZOOM_KEY.isPressed();
    }

    public static void updateZoomed() {
        // on key down
        if(keybindIsPressed() && !IS_ZOOMING) {
            USER_OPTION_SMOOTH_CAM = client.options.smoothCameraEnabled;
            IS_ZOOMING = true;

            client.options.smoothCameraEnabled = true;
        }

        // on key up
        if(!keybindIsPressed() && IS_ZOOMING) {
            IS_ZOOMING = false;
            client.options.smoothCameraEnabled = USER_OPTION_SMOOTH_CAM;
        }

    }
}
