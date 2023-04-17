package io.github.itzispyder.ogredupealias.plugin;

import io.github.itzispyder.ogredupealias.data.builder.ItemBuilder;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public abstract class ItemPresets {

    public static final ItemFactory ITEM_FACTORY = Bukkit.getItemFactory();

    public static ItemStack TROLL_SWORD = ITEM_FACTORY.createItemStack("wooden_sword{Enchantments:[],HideFlags:127,PublicUniVaultValues:[{\"univault:from\":\"Plugin made by ImproperIssues, visit https://github.com/ItziSpyder/UniVault\"}],Unbreakable:1b,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"- No I\\'m not joking, it\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"   really is real!\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"- Don\\'t believe me? Search it up yourself!\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"   \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://google.com/search?q=what+is+the+penis+joke\"},\"text\":\"https://google.com/search?q=what+is+the+penis+joke\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"(\"},{\"italic\":false,\"color\":\"gold\",\"text\":\"The Penis Joke is Real\"},{\"italic\":false,\"color\":\"gray\",\"text\":\") \"},{\"italic\":false,\"color\":\"dark_gray\",\"text\":\"(real)\"}],\"text\":\"\"}'}}");

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
