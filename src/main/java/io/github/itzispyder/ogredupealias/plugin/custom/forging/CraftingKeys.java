package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class CraftingKeys {

    private static final Map<String,ItemStack> REGISTERED_KEYS = new HashMap<>();

    public static void initRecipes() {
        register(ItemPresets.LEGENDARY_CORE,"[netherite_scrap{}, trident{}, netherite_scrap{}, enchanted_golden_apple{}, sculk_catalyst{CustomModelData:1111}, enchanted_golden_apple{}, netherite_scrap{}, dragon_head{}, netherite_scrap{}]");
        register(ItemPresets.LEGENDARY_INGOT,"[totem_of_undying{}, netherite_ingot{}, totem_of_undying{}, netherite_ingot{}, structure_block{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Core\"}],\"text\":\"\"}'}}, netherite_ingot{}, totem_of_undying{}, netherite_ingot{}, totem_of_undying{}]");
        register(ItemPresets.TROLL_SWORD,"[dirt{}]");
    }

    public static void register(ItemStack result, CraftingKey key) {
        REGISTERED_KEYS.put(key.getKey(),result);
    }

    public static void register(ItemStack result, String key) {
        REGISTERED_KEYS.put(key,result);
    }

    public static ItemStack getResult(CraftingKey key) {
        ItemStack result = REGISTERED_KEYS.get(key.getKey());
        return result != null ? result : new ItemStack(Material.AIR);
    }
}
