package net.fabricmc.example.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListHud.class)
public class ExampleMixin {

    private static final String MY_NAME = "Zyq_Playz"; 

    @Inject(method = "getPlayerName", at = @At("HEAD"), cancellable = true)
    private void hideServerRankAndShowOwner(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        String playerName = entry.getProfile().getName();

        if (playerName.equalsIgnoreCase(MY_NAME)) {
            MutableText customName = Text.literal("OWNER ").formatted(Formatting.RED)
                    .append(Text.literal(playerName).formatted(Formatting.WHITE));
            
            cir.setReturnValue(customName);
        }
    }
}
