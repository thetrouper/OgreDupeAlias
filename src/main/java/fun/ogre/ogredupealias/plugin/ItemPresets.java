package fun.ogre.ogredupealias.plugin;

import fun.ogre.ogredupealias.data.builder.ItemBuilder;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public abstract class ItemPresets {

    private static final ItemFactory factory = Bukkit.getItemFactory();

    public static ItemStack TROLL_SWORD = factory.createItemStack("wooden_sword{Enchantments:[],HideFlags:127,PublicUniVaultValues:[{\"univault:from\":\"Plugin made by ImproperIssues, visit https://github.com/ItziSpyder/UniVault\"}],Unbreakable:1b,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"- No I\\'m not joking, it\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"   really is real!\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"- Don\\'t believe me? Search it up yourself!\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"   \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://google.com/search?q=what+is+the+penis+joke\"},\"text\":\"https://google.com/search?q=what+is+the+penis+joke\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"(\"},{\"italic\":false,\"color\":\"gold\",\"text\":\"The Penis Joke is Real\"},{\"italic\":false,\"color\":\"gray\",\"text\":\") \"},{\"italic\":false,\"color\":\"dark_gray\",\"text\":\"(real)\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_HELMET = factory.createItemStack("netherite_helmet{Enchantments:[{id:\"minecraft:aqua_affinity\",lvl:1},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:respiration\",lvl:5},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Chill Shades \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_CHESTPLATE = factory.createItemStack("netherite_chestplate{Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Hawiian Shirt \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_LEGGINGS = factory.createItemStack("netherite_leggings{Damage:0,Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:swift_sneak\",lvl:3},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Jorts \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_BOOTS = factory.createItemStack("netherite_boots{Enchantments:[{id:\"minecraft:depth_strider\",lvl:3},{id:\"minecraft:feather_falling\",lvl:4},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:soul_speed\",lvl:3},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Flip Flops \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_SWORD = factory.createItemStack("netherite_sword{Damage:0,Enchantments:[{id:\"minecraft:fire_aspect\",lvl:2},{id:\"minecraft:knockback\",lvl:2},{id:\"minecraft:looting\",lvl:3},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:sharpness\",lvl:6},{id:\"minecraft:smite\",lvl:5},{id:\"minecraft:sweeping\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Pool Noodle \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_AXE = factory.createItemStack("netherite_axe{Enchantments:[{id:\"minecraft:efficiency\",lvl:7},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:sharpness\",lvl:5},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Golf Club \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack SNAD = factory.createItemStack("sand{display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad\"}],\"text\":\"\"}'}}");

    public static ItemStack SNAD_COOKIE = factory.createItemStack("cookie{CustomModelData:1111,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"mmMmmM Extra Crunchy!\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad Cookie\"}],\"text\":\"\"}'}}");

    public static ItemStack SCULK_CATALYST = ItemBuilder.create()
            .material(Material.SCULK_CATALYST)
            .customModelData(1111)
            .build();

    public static ItemStack LEGENDARY_CORE = ItemBuilder.create()
            .material(Material.STRUCTURE_BLOCK)
            .name(Text.color("&cLegendary Core"))
            .lore(Text.color("&8- &7Used for forging"))
            .enchant(Enchantment.LURE,1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack LEGENDARY_INGOT = ItemBuilder.create()
            .material(Material.NETHERITE_INGOT)
            .name(Text.color("&cLegendary Ingot"))
            .lore(Text.color("&8- &7Used for forging"))
            .enchant(Enchantment.LURE,1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
}
