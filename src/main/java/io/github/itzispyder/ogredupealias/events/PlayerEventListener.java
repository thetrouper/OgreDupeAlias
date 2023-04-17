package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.data.Config;
import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import io.github.itzispyder.ogredupealias.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

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
        final ItemStack item = e.getItem();
        final Player p = e.getPlayer();

        if (ItemUtils.nbtMatches(item,ItemPresets.SNAD_COOKIE)) p.setHealth(0);
    }
}
