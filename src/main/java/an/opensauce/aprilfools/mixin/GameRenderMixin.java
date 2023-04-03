package an.opensauce.aprilfools.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRenderMixin {

    @Shadow abstract void loadPostProcessor(Identifier id);

    @Shadow private int superSecretSettingIndex;

    @Shadow private @Nullable PostEffectProcessor postProcessor;

    int count = 0;

    @Inject(method = "render", at = @At("HEAD"))
    public void tick(float tickDelta, long startTime, boolean tick, CallbackInfo ci){
        count++;
       // System.out.println(count);
        if (count >= 200){
            //System.out.println("Bazinga");
            count = 0;
            if(MinecraftClient.getInstance().player != null/* && superSecretSettingIndex != 24*/){
                loadPostProcessor(new Identifier("shaders/post/wobble.json"));
            }
        }
    }

}
