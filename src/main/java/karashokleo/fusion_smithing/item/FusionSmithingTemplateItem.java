package karashokleo.fusion_smithing.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import karashokleo.fusion_smithing.FusionSmithing;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class FusionSmithingTemplateItem
{
    // Texts
    private static final Text FS_TITLE_TEXT = Text.translatable(FusionSmithing.createTranslationKey("text", "title")).formatted(Formatting.GRAY);
    private static final Text FS_APPLIES_TO_TEXT = Text.translatable(FusionSmithing.createTranslationKey("text", "applies_to")).formatted(Formatting.BLUE);
    private static final Text FS_INGREDIENTS_TEXT = Text.translatable(FusionSmithing.createTranslationKey("text", "ingredients")).formatted(Formatting.BLUE);
    private static final Text FS_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(FusionSmithing.createTranslationKey("text", "base_slot_description"));
    private static final Text FS_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(FusionSmithing.createTranslationKey("text", "additions_slot_description"));

    // Textures - Vanilla Copy
    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = new Identifier("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = new Identifier("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = new Identifier("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = new Identifier("item/empty_slot_hoe");

    // Template Item
    public static final SmithingTemplateItem FUSION_SMITHING_TEMPLATE = createTemplate();

    public static void register()
    {
        Registry.register(Registries.ITEM, FusionSmithing.id("fusion_smithing_template"), FUSION_SMITHING_TEMPLATE);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(FUSION_SMITHING_TEMPLATE));
    }

    public static SmithingTemplateItem createTemplate()
    {
        return new SmithingTemplateItem(
                FS_APPLIES_TO_TEXT,
                FS_INGREDIENTS_TEXT,
                FS_TITLE_TEXT,
                FS_BASE_SLOT_DESCRIPTION_TEXT,
                FS_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getUpgradeEmptyBaseSlotTextures(),
                getUpgradeEmptyBaseSlotTextures()
        );
    }

    public static List<Identifier> getUpgradeEmptyBaseSlotTextures()
    {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE
        );
    }
}
