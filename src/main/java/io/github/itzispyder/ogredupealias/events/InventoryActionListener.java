package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.custom.CustomTable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryActionListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        final Inventory inv = e.getInventory();
        final Player p = (Player) e.getPlayer();

        try {
            CustomTable table = new CustomTable(inv);
            table.getGrid().forEach(item -> {
                p.sendMessage(item.getType().name());
            });
        }
        catch (Exception ignore) {}
    }
}
