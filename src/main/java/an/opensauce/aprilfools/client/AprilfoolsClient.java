package an.opensauce.aprilfools.client;

import an.opensauce.aprilfools.mixin.GameRenderMixin;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;

import static net.minecraft.client.render.GameRenderer.SUPER_SECRET_SETTING_COUNT;

@Environment(EnvType.CLIENT)
public class AprilfoolsClient implements ClientModInitializer{
    private int superSecretSettingIndex;
    private boolean postProcessorEnabled;
    private PostEffectProcessor postProcessor;
    private MinecraftClient MCclient;
    private ResourceManager resourceManager;

    @Override
    public void onInitializeClient() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PostEffectProcessor processor = client.gameRenderer.getPostProcessor();
            postProcessor = processor;
            MCclient = client;

            resourceManager = client.getResourceManager();
        });

    }
}