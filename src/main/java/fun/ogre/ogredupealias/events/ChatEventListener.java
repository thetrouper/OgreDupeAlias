package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.plugin.ChatConstraints;
import fun.ogre.ogredupealias.plugin.custom.gui.CustomGUIs.RankChangeGUI;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        try {
            this.handleChatConstraints(e);
            this.handleChangeTag(e);
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

    private void handleChangeTag(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (RankChangeGUI.choosingTagMap.get(p.getUniqueId()) == null) return;
        if (RankChangeGUI.choosingTagMap.get(p.getUniqueId()) && !e.getMessage().contains("#exit")) {
            e.setCancelled(true);
            RankChangeGUI.chooseTag(e.getPlayer(),e.getMessage());
        } else if (RankChangeGUI.choosingTagMap.get(p.getUniqueId()) && e.getMessage().contains("#exit")) {
            e.setCancelled(true);
            RankChangeGUI.choosingTagMap.put(p.getUniqueId(),false);
            p.sendMessage(Text.ofAll("&7You have exited the tag editor!"));
        }
    }
}
