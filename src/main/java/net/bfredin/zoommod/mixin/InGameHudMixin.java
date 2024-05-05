package net.bfredin.zoommod.mixin;

import net.bfredin.zoommod.ZoomModClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class InGameHudMixin {

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void getZoom(CallbackInfoReturnable<Double> callbackInfo) {
        if(ZoomModClient.keybindIsPressed()) {
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov * ZoomModClient.ZOOM_MULT);
        }
        ZoomModClient.updateZoomed();
    }
}
