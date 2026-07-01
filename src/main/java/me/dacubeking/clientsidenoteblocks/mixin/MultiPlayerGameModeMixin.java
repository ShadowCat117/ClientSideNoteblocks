package me.dacubeking.clientsidenoteblocks.mixin;

import me.dacubeking.clientsidenoteblocks.client.ClientSideNoteblocksClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.world.level.block.NoteBlock.INSTRUMENT;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {

    @Final
    @Shadow
    private Minecraft minecraft;

    @Shadow
    @Final
    private ClientPacketListener connection;

    @Redirect(method = "continueDestroyBlock",  at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/sounds/SoundEngine$PlayResult;"))
    public SoundEngine.PlayResult cancelBlockBreakSound(SoundManager instance, SoundInstance sound, BlockPos pos, Direction direction) {
        Level world = this.minecraft.level;
        LocalPlayer player = this.minecraft.player;
        if (!ClientSideNoteblocksClient.isEnabled()
                || world == null || player == null
                || player.isCreative() || player.isSpectator()
                || world.getBlockState(pos).getBlock() != Blocks.NOTE_BLOCK
                || (world.getBlockState(pos).getValue(INSTRUMENT).worksAboveNoteBlock() || !world.getBlockState(pos.above()).isAir())) {
            this.minecraft.getSoundManager().play(sound);
        } else if (ClientSideNoteblocksClient.isDebug()) {
            ClientSideNoteblocksClient.LOGGER.info("Cancelled block break sound");
        }
        return null;
    }
}
