package an.opensauce.aprilfools;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.Random;

public class ServerListener implements ServerTickEvents.EndTick{

    float count = 0;
    @Override
    public void onEndTick(MinecraftServer server) {
        Random random = new Random();
        List<ServerPlayerEntity> playersList = server.getPlayerManager().getPlayerList();
        ServerPlayerEntity[] players = new ServerPlayerEntity[playersList.size()];
        count++;
        for (int x = 0; x < players.length; x++){

            players[x] = playersList.get(x);
            players[x].setMovementSpeed(random.nextInt(10));
            players[x].experienceLevel = random.nextInt(1242);

            ItemStack handmain = players[x].getMainHandStack();
            ItemStack handoff = players[x].getOffHandStack();
            if(count >= 20) {
                count = 0;
                players[x].setStackInHand(Hand.MAIN_HAND, handoff);
                players[x].setStackInHand(Hand.OFF_HAND, handmain);
                TntEntity E = new TntEntity(EntityType.TNT,players[x].world);
                E.setFuse(20);
                E.addVelocity(players[x].getRandom().nextBetween(-5,5),1,players[x].getRandom().nextBetween(-5,5));
                E.createSpawnPacket();
                players[x].world.spawnEntity(E);
            }
            }


    }
}
