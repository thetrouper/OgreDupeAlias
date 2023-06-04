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
