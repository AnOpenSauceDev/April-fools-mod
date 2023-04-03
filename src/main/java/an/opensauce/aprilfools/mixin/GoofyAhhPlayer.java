package an.opensauce.aprilfools.mixin;

import an.opensauce.aprilfools.Utils;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class GoofyAhhPlayer {

    @Shadow public abstract void sendMessage(Text message, boolean overlay);

    @Inject(method = "onDeath",at = @At("HEAD"))
    public void cringe(DamageSource damageSource, CallbackInfo ci){
        Random rand = new Random();
        sendMessage(Text.of(Utils.DeathQuotes[rand.nextInt(Utils.DeathQuotes.length)]),false);
    }
}
