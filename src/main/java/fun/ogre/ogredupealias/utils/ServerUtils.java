package fun.ogre.ogredupealias.utils;

import fun.ogre.ogredupealias.OgreDupeAlias;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ServerUtils {

    public static List<Player> getPlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public static List<Player> getStaff() {
        return getPlayers().stream().filter(Player -> Player.hasPermission("oda.chat.seeflags")).toList();
    }

    public static void forEachPlayer(Consumer<Player> consumer) {
        getPlayers().forEach(consumer);
    }

    public static void forEachStaff(Consumer<Player> consumer) {
        getStaff().forEach(consumer);
    }

    public static void dmEachPlayer(Predicate<Player> condition, String dm) {
        forEachPlayer(p -> {
            if (condition.test(p)) p.sendMessage(dm);
        });
    }

    public static void dmEachPlayer(String dm) {
        forEachPlayer(p -> p.sendMessage(dm));
    }

    public static void forEachSpecified(Iterable<Player> players, Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    public static void forEachSpecified(Consumer<Player> consumer, Player... players) {
        Arrays.stream(players).forEach(consumer);
    }

    public static void sendActionBar(Player p, String msg) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
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
    public static boolean hasBlockBelow(Player player, Material material) {
        for (int y = player.getLocation().getBlockY() - 1; y >= player.getLocation().getBlockY() - 12; y--) {
            if (player.getWorld().getBlockAt(player.getLocation().getBlockX(), y, player.getLocation().getBlockZ()).getType() == material) {
                return true;
            }
        }
        return false;
    }
}

