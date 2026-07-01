package me.dacubeking.clientsidenoteblocks.mixininterfaces;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public interface ClientLevelInterface {


    void clientSideNoteblocks$bypassedPlaySound(@Nullable Player except, double x, double y, double z, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, long seed);
}
