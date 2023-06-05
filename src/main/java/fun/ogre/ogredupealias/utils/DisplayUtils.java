package fun.ogre.ogredupealias.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;

public final class DisplayUtils {

    public static void ring(Location center, double radius, Consumer<Location> onPoint, BiPredicate<Location, Integer> condition) {
        for (int i = 0; i <= 360; i ++) {
            Location point = center.clone().add(radius * Math.sin(i), 0, radius * Math.cos(i));
            if (condition.test(point, i)) {
                onPoint.accept(point);
            }
        }
    }

    public static void sphere(Location center, double radius, Consumer<Location> onPoint, BiPredicate<Location, Double> condition) {
        double radiusSquared = radius * radius;

        int minX = (int) Math.floor(center.getX() - radius);
        int minY = (int) Math.floor(center.getY() - radius);
        int minZ = (int) Math.floor(center.getZ() - radius);
        int maxX = (int) Math.ceil(center.getX() + radius);
        int maxY = (int) Math.ceil(center.getY() + radius);
        int maxZ = (int) Math.ceil(center.getZ() + radius);

        for (int x = minX; x <= maxX; x++) {
            double xSquared = (x - center.getX()) * (x - center.getX());
            for (int y = minY; y <= maxY; y++) {
                double ySquared = (y - center.getY()) * (y - center.getY());
                for (int z = minZ; z <= maxZ; z++) {
                    double zSquared = (z - center.getZ()) * (z - center.getZ());

                    if (xSquared + ySquared + zSquared <= radiusSquared) {
                        Location point = new Location(center.getWorld(), x, y, z);
                        double distance = center.distance(point);

                        if (condition.test(point, distance)) {
                            onPoint.accept(point);
                        }
                    }
                }
            }
        }
    }

    public static void wave(Location center, double radius, double frequency, long interval, Consumer<Location> onPoint) {
        AtomicReference<Double> currentRadius = new AtomicReference<>(0.0);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            if (currentRadius.get() <= radius) {
                ring(center, currentRadius.get(), onPoint, (point, angle) -> true);
                currentRadius.set(currentRadius.get() + frequency);
            }
        }, 0, interval);
    }
}
