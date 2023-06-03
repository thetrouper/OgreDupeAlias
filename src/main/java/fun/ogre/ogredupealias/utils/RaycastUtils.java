package fun.ogre.ogredupealias.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;

public class RaycastUtils {

    public static Location raycast(Location start, Location end, Predicate<Location> hitCondition) {
        return raycast(start, end, 0.5, hitCondition);
    }

    public static Location raycast(Location start, Location end, double frequency, Predicate<Location> hitCondition) {
        return raycast(start, end.toVector().subtract(start.toVector()).normalize(), start.distance(end), frequency, hitCondition);
    }

    public static Location raycast(Location start, Vector rotation, double distance, Predicate<Location> hitCondition) {
        return raycast(start, rotation, distance, 0.5, hitCondition);
    }

    public static Location raycast(Location start, Vector rotation, double distance, double frequency, Predicate<Location> hitCondition) {
        for (double i = 0.0; i <= distance; i += frequency) {
            Location point = start.clone().add(rotation.clone().multiply(i));
            if (hitCondition.test(point)) {
                return point;
            }
        }
        return start.clone().add(rotation.clone().multiply(distance));
    }

    public static Location raycast(Location start, Vector rotation, double distance, double frequency, long tickInterval, Predicate<Location> hitCondition) {
        AtomicReference<Location> result = new AtomicReference<>();
        final double[] i = { 0.0 };
        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            if ((i[0] += frequency) <= distance && result.get() == null) {
                Location point = start.clone().add(rotation.clone().multiply(i[0]));
                if (hitCondition.test(point)) {
                    result.set(point);
                }
            }
            else if (result.get() != null) {
                result.set(start.clone().add(rotation.clone().multiply(distance)));
            }
        }, 0, tickInterval);
        return result.get() != null ? result.get() : start.clone().add(rotation.clone().multiply(distance));
    }
}
