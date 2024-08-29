package com.infernalstudios.infernalexp.mixin;

import com.infernalstudios.infernalexp.module.BlockModule;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(BlockEntityType.Builder.class)
public class BlockEntityTypeMixin {
    @SuppressWarnings("all")
    @ModifyArg(method = "of", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"),
            index = 0, remap = false)
    private static <E> E[] registerCampfires(E[] elements) {
        List<E> blocks = new ArrayList<>(Arrays.stream(elements).toList());
        if (blocks.contains(Blocks.CAMPFIRE) && blocks.contains(Blocks.SOUL_CAMPFIRE))
            blocks.add((E) BlockModule.GLOWLIGHT_CAMPFIRE.get());
        return (E[]) blocks.toArray();
    }
}
