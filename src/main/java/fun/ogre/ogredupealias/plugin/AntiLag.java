package fun.ogre.ogredupealias.plugin;

import fun.ogre.ogredupealias.OgreDupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AntiLag {
    public void checkForLag(Player p) {
        final Location origPos = p.getLocation();
        Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance, () -> {
            Location secondaryPos = p.getLocation();
            double distance = origPos.distance(secondaryPos);

        }, 20);
    }
}
