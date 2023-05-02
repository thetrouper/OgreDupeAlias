package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.ChatConstraints;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        try {
            this.handleChatConstraints(e);
        }
        catch (Exception ignore) {}
    }

    private void handleChatConstraints(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();
        final ChatConstraints cc = new ChatConstraints(p,msg);

        if (!cc.passAllChecks()) e.setCancelled(true);
    }
}
