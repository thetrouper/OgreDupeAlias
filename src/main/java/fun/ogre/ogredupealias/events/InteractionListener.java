package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.plugin.custom.gui.CustomGuis;
import fun.ogre.ogredupealias.plugin.funitems.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            e.getPlayer().openInventory(CustomGuis.RANKS.getInventory());

            this.processTable(e);
            NetSkyBlade.handleNetskyBlade(e);
            Defender.handleDefender(e);
            AdminUtility.handleAdminUtility(e);
            VoidCharm.handleVoidCharm(e);
            SnowChinegun.handleSnowChinegun(e);
            AK47.handleAK47(e);
            PotatoCannon.handlePotatoCannon(e);
            PlayerEventListener.handleEnderPearls(e);
            SPBItems.handleRifle(e);
            LaserPointer.handleLaserPointer(e);
            Pickler.handlePickler(e);
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
