package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.Config;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage((p.hasPlayedBefore() ? Config.Player.joinMessage() : Config.Player.firstJoinMessage()).replaceAll("%player%",p.getDisplayName()));
        Config.Player.onJoin().forEach(p::chat);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        e.setQuitMessage(Config.Player.quitMessage().replaceAll("%player%",p.getDisplayName()));
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        try {
            this.handleFood(e);
        }
        catch (Exception ignore) {}
    }

    private void handleEnderPearls(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();
        Action a = e.getAction();
        switch (a) {
            case RIGHT_CLICK_BLOCK, RIGHT_CLICK_AIR -> {
                if (item.getType() == Material.ENDER_PEARL) {
                    if (e.isCancelled()) return;
                    p.setCooldown(Material.ENDER_PEARL, 30 * 20);
                }
            }
        }
    }

    private void handleFood(PlayerItemConsumeEvent e) {
        final ItemStack item = e.getItem();
        final Player p = e.getPlayer();

        if (ItemUtils.nbtMatches(item,ItemPresets.SNAD_COOKIE)) {
            p.setHealth(0);
        }
        else if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            if (e.isCancelled()) return;
            p.setCooldown(Material.ENCHANTED_GOLDEN_APPLE, 30 * 20);
        }

    }
}
