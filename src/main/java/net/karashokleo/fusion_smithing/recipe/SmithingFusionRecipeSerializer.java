package net.karashokleo.fusion_smithing.recipe;

import com.google.gson.JsonObject;
import net.karashokleo.fusion_smithing.FusionSmithing;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class SmithingFusionRecipeSerializer implements RecipeSerializer<SmithingFusionRecipe>
{
    public static RecipeSerializer<SmithingFusionRecipe> SMITHING_FUSION;

    public static void register()
    {
        SMITHING_FUSION = Registry.register(Registries.RECIPE_SERIALIZER, FusionSmithing.id("smithing_fusion"), new SmithingFusionRecipeSerializer());
    }

    @Override
    public SmithingFusionRecipe read(Identifier id, JsonObject json)
    {
        Ingredient template = Ingredient.fromJson(JsonHelper.getElement(json, "template"));
        Ingredient base = Ingredient.fromJson(JsonHelper.getElement(json, "base"));
        Ingredient addition = Ingredient.fromJson(JsonHelper.getElement(json, "addition"));
        return new SmithingFusionRecipe(id, template, base, addition);
    }

    @Override
    public SmithingFusionRecipe read(Identifier id, PacketByteBuf buf)
    {
        Ingredient template = Ingredient.fromPacket(buf);
        Ingredient base = Ingredient.fromPacket(buf);
        Ingredient addition = Ingredient.fromPacket(buf);
        return new SmithingFusionRecipe(id, template, base, addition);
    }

    @Override
    public void write(PacketByteBuf buf, SmithingFusionRecipe recipe)
    {
        recipe.template.write(buf);
        recipe.base.write(buf);
        recipe.addition.write(buf);
    }
}
