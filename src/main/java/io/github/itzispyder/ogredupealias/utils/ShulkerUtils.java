package io.github.itzispyder.ogredupealias.utils;

import org.bukkit.Bukkit;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ShulkerUtils {

    private static final Map<UUID,ItemStack> lastOpenedBox = new HashMap<>();

    public static Inventory getOf(ItemStack item, ShulkerBox box) {
        final String display = ItemUtils.getDisplay(item);
        final Inventory inv = Bukkit.createInventory(null, box.getInventory().getSize(), Text.color("&7Viewing&r " + display));

        inv.setContents(box.getInventory().getContents());
        return inv;
    }

    public static void onShulkerInteraction(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();
        final Action a = e.getAction();

        if (!e.isCancelled() && a == Action.RIGHT_CLICK_BLOCK) return;
        if (item == null || item.getType().isAir()) return;
        if (a != Action.RIGHT_CLICK_BLOCK && a != Action.RIGHT_CLICK_AIR) return;
        if (!item.getType().name().contains("SHULKER_BOX")) return;

        final BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        final ShulkerBox box = (ShulkerBox) meta.getBlockState();

        p.openInventory(getOf(item, box));
        lastOpenedBox.put(p.getUniqueId(),item);
    }

    public static void onInventoryClick(InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        final Player p = (Player) e.getWhoClicked();

        final ItemStack item = lastOpenedBox.get(p.getUniqueId());
        if (item == null || !item.getType().name().contains("SHULKER_BOX")) return;
        final BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        final ShulkerBox box = (ShulkerBox) meta.getBlockState();

        box.getInventory().setContents(inv.getContents());
        box.update();
        meta.setBlockState(box);
        item.setItemMeta(meta);
    }

    public static void onInventoryClose(InventoryCloseEvent e) {
        final Inventory inv = e.getInventory();
        final Player p = (Player) e.getPlayer();

        final ItemStack item = lastOpenedBox.get(p.getUniqueId());
        if (item == null || !item.getType().name().contains("SHULKER_BOX")) return;
        final BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        final ShulkerBox box = (ShulkerBox) meta.getBlockState();

        box.getInventory().setContents(inv.getContents());
        box.update();
        meta.setBlockState(box);
        item.setItemMeta(meta);
        lastOpenedBox.remove(p.getUniqueId());
    }
}
