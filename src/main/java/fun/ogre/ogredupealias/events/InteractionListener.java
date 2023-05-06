package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.utils.Cooldown;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InteractionListener implements Listener {

    private static final Cooldown<UUID> aAppleCooldown = new Cooldown<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
            this.handleEApples(e);
        }
        catch (Exception ignore) {}
    }

    private void handleEApples(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();
        final Action a = e.getAction();

        if (!a.name().contains("RIGHT_CLICK")) return;
        if (item == null) return;
        if (item.getType() != Material.ENCHANTED_GOLDEN_APPLE) return;
        if (aAppleCooldown.isOnCooldown(p.getUniqueId())) {
            e.setCancelled(true);
            p.sendMessage(Text.ofAll("&cEnchanted Golden Apples are on cooldown!"));
            return;
        }

        aAppleCooldown.setCooldown(p.getUniqueId(), 30 * 1000);
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
