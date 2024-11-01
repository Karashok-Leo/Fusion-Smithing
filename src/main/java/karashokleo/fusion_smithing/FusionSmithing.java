package karashokleo.fusion_smithing;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import karashokleo.fusion_smithing.config.FSConfig;
import karashokleo.fusion_smithing.item.FusionSmithingTemplateItem;
import karashokleo.fusion_smithing.recipe.SmithingFusionRecipeSerializer;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.tinyconfig.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FusionSmithing implements ModInitializer
{
    public static final String MOD_ID = "fusion-smithing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ConfigManager<FSConfig> config = new ConfigManager<>
            (MOD_ID, new FSConfig())
            .builder()
            .setDirectory("./")
            .sanitize(true)
            .build();

    @Override
    public void onInitialize()
    {
        config.refresh();
        FusionSmithingTemplateItem.register();
        SmithingFusionRecipeSerializer.register();
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) ->
        {
            if (id.toString().equals(config.value.injected_loot_table))
                tableBuilder.pool(
                        LootPool
                                .builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .with(ItemEntry.builder(FusionSmithingTemplateItem.FUSION_SMITHING_TEMPLATE))
                );
        });
    }

    public static Identifier id(String path)
    {
        return new Identifier(MOD_ID, path);
    }

    public static String createTranslationKey(String type, String path)
    {
        return type + '.' + MOD_ID + '.' + path;
    }
}