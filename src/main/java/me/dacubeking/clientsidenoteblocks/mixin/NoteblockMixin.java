package me.dacubeking.clientsidenoteblocks.mixin;

import me.dacubeking.clientsidenoteblocks.mixininterfaces.NoteblockInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NoteBlock.class)
public abstract class NoteblockMixin extends Block implements NoteblockInterface {
    public NoteblockMixin(Properties settings) {
        super(settings);
    }

    @Shadow
    @Nullable
    protected abstract Identifier getCustomSoundId(Level world, BlockPos pos);


    @Override
    public Identifier clientSideNoteblocks$getCustomSoundPublic(Level world, BlockPos pos) {
        return getCustomSoundId(world, pos);
    }
}
