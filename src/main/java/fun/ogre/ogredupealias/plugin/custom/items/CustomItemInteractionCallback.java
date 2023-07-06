package fun.ogre.ogredupealias.plugin.custom.items;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface CustomItemInteractionCallback {

    void handleInteraction(Player player, ItemStack item, PlayerInteractEvent event);
}
