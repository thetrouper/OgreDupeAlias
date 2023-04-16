package io.github.itzispyder.ogredupealias.plugin.custom;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomTable {

    private final Inventory inv;

    public CustomTable(Inventory inv) {
        this.inv = inv;
    }

    public void clearGrid() {
        getGrid().forEach(item -> item.setAmount(0));
    }

    public List<ItemStack> getGrid() {
        List<ItemStack> list = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 4; i++) list.add(inv.getItem(i + (x * 9)));
        }
        return list;
    }

    public ItemStack getResult() {
        return inv.getItem(16);
    }
}
