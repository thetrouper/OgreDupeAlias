package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.custom.forging.CustomTable;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryActionListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        final String title = e.getView().getTitle();

        try {
            if (inv == null) return;
            if (inv.getType() == InventoryType.PLAYER) return;

            if (title.equals(Text.color("&eForging Table"))) CustomTable.onInventoryAction(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        final String title = e.getView().getTitle();

        try {
            if (title.equals(Text.color("&eForging Table"))) CustomTable.onInventoryClose(e);
        }
        catch (Exception ignore) {}
    }
}
