package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomTable {

    private final Inventory inv;

    public CustomTable(Inventory inv) {
        this.inv = inv;
    }

    public void clearGrid() {
        getGrid().stream().filter(Objects::nonNull).forEach(item -> item.setAmount(item.getAmount() - 1));
    }

    public List<ItemStack> getGrid() {
        List<ItemStack> list = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 4; i++) list.add(inv.getItem(i + (x * 9)));
        }
        return list;
    }

    public CraftingKey getGridKey() {
        return new CraftingKey(getGrid());
    }

    public ItemStack getResult() {
        return inv.getItem(16);
    }

    public int getResultSlot() {
        return 16;
    }

    public int getCraftingSlot() {
        return 14;
    }

    public boolean attemptCraft() {
        if (CraftingKey.getResult(this.getGridKey()).getType().isAir()) return false;
        ItemStack resultSlotItem = inv.getItem(this.getResultSlot());
        if (resultSlotItem != null && !resultSlotItem.getType().isAir()) return false;
        ItemStack result = CraftingKey.getResult(this.getGridKey());
        this.clearGrid();
        inv.setItem(this.getResultSlot(),result);
        return true;
    }

    public List<Integer> getGridSlots() {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 4; i++) list.add(i + (x * 9));
        }
        return list;
    }

    public boolean isUnresponsiveSlot(int slot) {
        return slot != getCraftingSlot() && slot != getResultSlot() && !getGridSlots().contains(slot);
    }

    public static void onInventoryAction(InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        final Player p = (Player) e.getWhoClicked();
        final int slot = e.getSlot();
        final CustomTable table = new CustomTable(inv);

        if (table.isUnresponsiveSlot(slot)) {
            e.setCancelled(true);
            return;
        }

        if (slot == table.getCraftingSlot()) {
            e.setCancelled(true);
            if (table.attemptCraft()) p.playSound(p.getLocation(), Sound.BLOCK_SMITHING_TABLE_USE,1,1);
            else p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
        }
    }

    public static void onInventoryClose(InventoryCloseEvent e) {
        final Inventory inv = e.getInventory();
        final Player p = (Player) e.getPlayer();
        final CustomTable table = new CustomTable(inv);

        table.getGrid().stream().filter(Objects::nonNull).forEach(item -> {
            p.getWorld().dropItem(p.getLocation(),item);
        });
    }
}
