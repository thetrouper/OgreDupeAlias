package fun.ogre.ogredupealias.utils;

import fun.ogre.ogredupealias.OgreDupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        if (p.getBedSpawnLocation() == null) {
            p.teleport(p.getWorld().getSpawnLocation());
            return;
        }
        if (hasTag(p,"TWjester") && (hasTag(p,"TWblue"))) {
            ServerUtils.forEachPlayerRun(players -> {
                return hasTag(players,"TWplayer") && hasTag(players, "TWblue");
            }, players -> {
                players.getInventory().addItem(new ItemStack(Material.ARROW,1));
                players.sendMessage(Text.color("&9Game> &7Your jester has fallen! You have been given an arrow."));
            });
        }
        if (hasTag(p,"TWjester") && (hasTag(p,"TWred"))) {
            ServerUtils.forEachPlayerRun(players -> {
                return hasTag(players,"TWplayer") && hasTag(players, "TWred");
            }, players -> {
                players.getInventory().addItem(new ItemStack(Material.ARROW,1));
                players.sendMessage(Text.color("&9Game> &7Your jester has fallen! You have been given an arrow."));
            });
        }
        p.teleport(p.getBedSpawnLocation());
        Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance, () -> {
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        }, 10);
    }
}
