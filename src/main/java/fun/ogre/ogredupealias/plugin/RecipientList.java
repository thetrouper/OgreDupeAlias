package fun.ogre.ogredupealias.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

public class RecipientList {

    private final Set<UUID> recipients;

    public RecipientList() {
        this.recipients = new HashSet<>();
    }

    public List<UUID> getRecipients() {
        return new ArrayList<>(recipients);
    }

    public void addRecipient(Player p) {
        recipients.add(p.getUniqueId());
    }

    public void removeRecipient(Player p) {
        recipients.remove(p.getUniqueId());
    }

    public boolean isRecipient(Player p) {
        return recipients.contains(p.getUniqueId());
    }

    public void addRecipient(UUID p) {
        recipients.add(p);
    }

    public void removeRecipient(UUID p) {
        recipients.remove(p);
    }

    public boolean isRecipient(UUID p) {
        return recipients.contains(p);
    }

    public void forEachRecipient(Consumer<Player> consumer) {
        recipients.forEach(uuid -> {
            Player p = Bukkit.getPlayer(uuid);
            if (p == null) return;
            if (!p.isOnline()) return;
            consumer.accept(p);
        });
    }
}
