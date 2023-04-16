package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.data.PlacedStructures;
import io.github.itzispyder.ogredupealias.plugin.InventoryPresets;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Action action = e.getAction();

        try {
            final Block b = e.getClickedBlock();
            final ItemStack item = e.getItem();

            if (PlacedStructures.isCustomTable(b)) {
                e.setCancelled(true);
                p.openInventory(InventoryPresets.createCustomTable());
                return;
            }
        }
        catch (Exception ignore) {}
    }
}
