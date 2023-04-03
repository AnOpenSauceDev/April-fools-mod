package an.opensauce.aprilfools.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.encryption.NetworkEncryptionUtils;
import net.minecraft.network.message.LastSeenMessagesCollector;
import net.minecraft.network.message.MessageBody;
import net.minecraft.network.message.MessageChain;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Instant;

@Mixin(value = ClientPlayNetworkHandler.class, priority = 500)
public abstract class DrunkenChat {



    @Shadow
    private LastSeenMessagesCollector lastSeenMessagesCollector;

    @Shadow
    private MessageChain.Packer messagePacker;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void sendChatMessage(String content) {
        String tmp = content;
        content = StringUtils.swapCase(tmp = new StringBuilder(tmp).reverse().toString());
        System.out.println(content);
        Instant instant = Instant.now();
        long l = NetworkEncryptionUtils.SecureRandomUtil.nextLong();
        LastSeenMessagesCollector.LastSeenMessages lastSeenMessages = lastSeenMessagesCollector.collect();
        MessageSignatureData messageSignatureData = messagePacker.pack(new MessageBody(content, instant, l, lastSeenMessages.lastSeen()));
        sendPacket(new ChatMessageC2SPacket(content, instant, l, messageSignatureData, lastSeenMessages.update()));
    }



    @Shadow
    public abstract void sendPacket(Packet<?> packet);

}
