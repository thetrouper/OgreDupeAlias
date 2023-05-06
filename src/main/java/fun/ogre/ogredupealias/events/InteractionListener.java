package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
        }
        catch (Exception ignore) {}
    }

    private void processTable(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();

        if (PlacedStructures.isCustomTable(b)) {
            e.setCancelled(true);
            p.openInventory(InventoryPresets.createCustomTable());
        }
    }
}
