package io.github.itzispyder.ogredupealias.plugin;

import io.github.itzispyder.ogredupealias.data.builder.ItemBuilder;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public abstract class ItemPresets {

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
