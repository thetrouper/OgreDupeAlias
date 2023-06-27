package fun.ogre.ogredupealias.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public final class ItemUtils {

    public static String nbtOf(ItemStack item) {
        if (item == null || item.getType().isAir()) return "air{}";
        String name = item.getType().name().toLowerCase();
        if (!item.hasItemMeta() || item.getItemMeta() == null) return name + "{}";
        return name + item.getItemMeta().getAsString();
    }

    public static boolean nbtMatches(ItemStack item1, ItemStack item2) {
        return nbtOf(item1).equals(nbtOf(item2));
    }

    public static boolean isSkullOf(ItemStack item, String name) {
        if (item == null || item.getType().isAir()) return false;
        if (item.getType() != Material.PLAYER_HEAD) return false;
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        OfflinePlayer p = meta.getOwningPlayer();
        if (p == null) return false;
        return p.getName().equalsIgnoreCase(name);
    }

    public static ItemStack skullOf(String owner) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(owner);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack skullOf(UUID owner) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(owner);
        return skullOf(p.getName());
    }

    public static String getDisplay(ItemStack item) {
        String def = StringUtils.capitalizeWords(item.getType().name());
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return def;
        return meta.hasDisplayName() ? meta.getDisplayName() : def;
    }

    public static boolean matchDisplay(ItemStack item, ItemStack item2) {
        return getDisplay(item).equals(getDisplay(item2));
    }
    public static int itemCount(Player p, Material material) {
        int count = 0;
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null && i.getType() == material) {
                count += i.getAmount();
            }
        }
        return count;
    }
}
