package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.plugin.ChatConstraints;
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
        catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    private void handleChatConstraints(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();
        final ChatConstraints cc = new ChatConstraints(p,msg);

        if (!cc.passAllChecks()) e.setCancelled(true);
    }
}
