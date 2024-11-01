package karashokleo.fusion_smithing.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.function.BiConsumer;

public enum SmithingFusionMode
{
    OVERWRITE(ItemStack::setNbt),
    MERGE((base, addition) -> base.getOrCreateNbt().copyFrom(addition)),
    ;

    private final BiConsumer<ItemStack, NbtCompound> handler;

    SmithingFusionMode(BiConsumer<ItemStack, NbtCompound> handler)
    {
        this.handler = handler;
    }

    public void apply(ItemStack base, NbtCompound addition)
    {
        handler.accept(base, addition);
    }
}
