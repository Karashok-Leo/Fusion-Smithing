package net.karashokleo.fusion_smithing.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public record SmithingFusionRecipeProvider(
        Identifier id,
        RecipeSerializer<?> type,
        Ingredient template,
        Ingredient base,
        Ingredient addition
)
        implements RecipeJsonProvider
{
    @Override
    public void serialize(JsonObject json)
    {
        json.add("template", this.template.toJson());
        json.add("base", this.base.toJson());
        json.add("addition", this.addition.toJson());
    }

    @Override
    public Identifier getRecipeId()
    {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return this.type;
    }

    @Override
    @Nullable
    public JsonObject toAdvancementJson()
    {
        return null;
    }

    @Override
    @Nullable
    public Identifier getAdvancementId()
    {
        return null;
    }
}
