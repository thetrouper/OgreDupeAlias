package fun.ogre.ogredupealias.plugin.custom.forging;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class CraftingKeys {

    private static final Map<String,ItemStack> REGISTERED_KEYS = new HashMap<>();

    public static void initRecipes() {
        // my custom
        register(ItemPresets.LASER_POINTER, "[polished_blackstone_button{}, lime_concrete{}, lime_stained_glass{}, lime_concrete{}, diamond{}, lime_concrete{}, stick{}, lime_concrete{}, air{}]");
        register(ItemPresets.DEFENDER, "[iron_nugget{}, iron_nugget{}, iron_nugget{}, iron_nugget{}, prismarine_shard{}, iron_nugget{}, iron_nugget{}, iron_nugget{}, iron_nugget{}]");
        register(ItemPresets.ADMIN_UTILITY, "[air{}, air{}, barrier{}, air{}, blaze_rod{}, air{}, blaze_rod{}, air{}, air{}]");
        register(ItemPresets.NETSKY_BLADE, "[end_crystal{}, ender_pearl{}]");
        register(ItemPresets.VOID_CHARM, "[black_stained_glass{}, black_stained_glass{}, black_stained_glass{}, black_stained_glass{}, barrier{}, black_stained_glass{}, black_stained_glass{}, black_stained_glass{}, black_stained_glass{}]");
        //register(ItemPresets.TROLL_SWORD,"[dirt{}]");
        register(ItemPresets.SNAD,"[sand{}]");
        register(ItemPresets.SNAD_COOKIE,"[air{}, sugar{}, air{}, sand{display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad\"}],\"text\":\"\"}'}}, cocoa_beans{}, sand{display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad\"}],\"text\":\"\"}'}}, air{}, sugar{}, air{}]");
        // legendary gear
        register(ItemPresets.LEGENDARY_CORE,"[netherite_scrap{}, trident{}, netherite_scrap{}, enchanted_golden_apple{}, sculk_catalyst{CustomModelData:1111}, enchanted_golden_apple{}, netherite_scrap{}, dragon_head{}, netherite_scrap{}]");
        register(ItemPresets.LEGENDARY_INGOT,"[totem_of_undying{}, netherite_ingot{}, totem_of_undying{}, netherite_ingot{}, structure_block{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Core\"}],\"text\":\"\"}'}}, netherite_ingot{}, totem_of_undying{}, netherite_ingot{}, totem_of_undying{}]");
        register(ItemPresets.LEGENDARY_HELMET,"[netherite_helmet{}, netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}]");
        register(ItemPresets.LEGENDARY_CHESTPLATE,"[netherite_chestplate{}, netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}]");
        register(ItemPresets.LEGENDARY_LEGGINGS,"[netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}, netherite_leggings{}]");
        register(ItemPresets.LEGENDARY_BOOTS,"[netherite_boots{}, netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}]");
        register(ItemPresets.LEGENDARY_SWORD,"[netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}, netherite_sword{}]");
        register(ItemPresets.LEGENDARY_AXE,"[netherite_axe{}, netherite_ingot{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Ingot\"}],\"text\":\"\"}'}}]");
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
