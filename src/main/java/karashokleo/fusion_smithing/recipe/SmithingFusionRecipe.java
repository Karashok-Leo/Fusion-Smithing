package karashokleo.fusion_smithing.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.stream.Stream;

public class SmithingFusionRecipe implements SmithingRecipe
{
    protected final Identifier id;
    protected final SmithingFusionMode mode;
    protected final Ingredient template;
    protected final Ingredient base;
    protected final Ingredient addition;

    public SmithingFusionRecipe(Identifier id, SmithingFusionMode mode, Ingredient template, Ingredient base, Ingredient addition)
    {
        this.id = id;
        this.mode = mode;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public boolean testTemplate(ItemStack stack)
    {
        return this.template.test(stack);
    }

    @Override
    public boolean testBase(ItemStack stack)
    {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack)
    {
        return this.addition.test(stack);
    }

    @Override
    public boolean matches(Inventory inventory, World world)
    {
        return this.template.test(inventory.getStack(0)) &&
               this.base.test(inventory.getStack(1)) &&
               this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager)
    {
        ItemStack base = inventory.getStack(1).copy();
        NbtCompound additionNbt = inventory.getStack(2).getNbt();
        if (additionNbt != null) this.mode.apply(base, additionNbt);
        return base;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager)
    {
        return Arrays.stream(base.getMatchingStacks()).findFirst().orElse(ItemStack.EMPTY);
    }

    @Override
    public boolean isEmpty()
    {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    @Override
    public Identifier getId()
    {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SmithingFusionRecipeSerializer.SMITHING_FUSION;
    }
}
