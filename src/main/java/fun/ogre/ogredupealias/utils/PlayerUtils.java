package fun.ogre.ogredupealias.utils;

import fun.ogre.ogredupealias.OgreDupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtils {
    public static List<String> getTags(Player player) {
        List<String> tags = new ArrayList<>();
        tags.addAll(player.getScoreboardTags());
        return tags;
    }

    public static boolean hasTag(Player p, String tag) {
        return p.getScoreboardTags().contains(tag);
    }
    public static void addTag(Player p, String tag) {
        p.getScoreboardTags().add(tag);
    }
    public static void removeTag(Player p, String tag) {
        p.getScoreboardTags().remove(tag);
    }
    public static boolean hasSameTag(Player p1, Player p2, String tag) {
        return hasTag(p1, tag) == hasTag(p2, tag);
    }
    public static void safeKill(Player p) {
        if (p.getBedSpawnLocation() == null) return;
        p.teleport(p.getBedSpawnLocation());
        Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance, () -> {
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        }, 10);
    }
}
