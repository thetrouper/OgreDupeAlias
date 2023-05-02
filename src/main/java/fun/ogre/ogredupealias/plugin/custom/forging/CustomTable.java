package fun.ogre.ogredupealias.plugin.custom.forging;

import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.plugin.RecipientList;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CustomTable {

    public static final RecipientList recipeSpies = new RecipientList();
    private final Inventory inv;

    public CustomTable(Inventory inv) {
        this.inv = inv;
    }

    public void clearGrid() {
        getGrid().stream().filter(Objects::nonNull).forEach(item -> item.setAmount(item.getAmount() - 1));
    }

    public List<ItemStack> getGrid(boolean shapeless) {
        List<ItemStack> list = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 4; i++) list.add(inv.getItem(i + (x * 9)));
        }
        if (shapeless) {
            return list.stream().filter(Objects::nonNull).sorted(Comparator.comparing(ItemStack::getTranslationKey)).toList();
        }
        return list;
    }

    public List<ItemStack> getGrid() {
        return getGrid(false);
    }

    public CraftingKey getGridKey() {
        return getGridKey(false);
    }

    public CraftingKey getGridKey(boolean shapeless) {
        return new CraftingKey(getGrid(shapeless));
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
        ItemStack resultSlotItem = inv.getItem(this.getResultSlot());
        if (resultSlotItem != null && !resultSlotItem.getType().isAir()) return false;

        ItemStack result = CraftingKeys.getResult(this.getGridKey(false));
        if (result.getType().isAir()) {
            result = CraftingKeys.getResult(this.getGridKey(true));
            if (result.getType().isAir()) return false;
        }

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

        if (inv == null) return;
        if (inv.getType() == InventoryType.PLAYER) return;

        if (table.isUnresponsiveSlot(slot)) {
            e.setCancelled(true);
            return;
        }

        if (slot == table.getCraftingSlot()) {
            e.setCancelled(true);
            ServerUtils.dmEachPlayer(recipeSpies::isRecipient, Text.builder(
                    "\n&b&lSHAPED: &3" + table.getGridKey(false)
                    + "\n&b&lSHAPELESS: &3" + table.getGridKey(true)
                    + "\n "
            ).prefix().color().build());
            if (table.attemptCraft()) p.playSound(p.getLocation(), Sound.BLOCK_SMITHING_TABLE_USE,1,1);
            else p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
        }
    }

    public static void onInventoryClose(InventoryCloseEvent e) {
        final Inventory inv = e.getInventory();
        final Player p = (Player) e.getPlayer();
        final CustomTable table = new CustomTable(inv);

        List<ItemStack> items = table.getGrid();
        items.add(table.getResult());
        items.stream().filter(Objects::nonNull).forEach(item -> {
            p.getWorld().dropItem(p.getLocation(),item);
        });
    }
}
