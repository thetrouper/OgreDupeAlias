package io.github.itzispyder.ogredupealias.plugin;

import io.github.itzispyder.ogredupealias.data.builder.ItemBuilder;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryPresets {

    public static Inventory createCustomTable() {
        final Inventory inv = Bukkit.createInventory(null,27, Text.color("&eForging Table"));
        ItemStack x = ItemBuilder.create()
                .material(Material.CYAN_STAINED_GLASS_PANE)
                .name(" ")
                .build();
        ItemStack craft = ItemBuilder.create()
                .material(Material.CRAFTING_TABLE)
                .name(Text.color("&aCraft Contents"))
                .build();
        ItemStack a = new ItemStack(Material.AIR);

        inv.setContents(new ItemStack[]{
                x,a,a,a,x,x,x,x,x,
                x,a,a,a,x,craft,x,a,x,
                x,a,a,a,x,x,x,x,x,
        });

        return inv;
    }
}
