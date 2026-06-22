package me.dacubeking.clientsidenoteblocks.mixininterfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public interface NoteblockInterface {
    Identifier clientSideNoteblocks$getCustomSoundPublic(Level world, BlockPos pos);
}
