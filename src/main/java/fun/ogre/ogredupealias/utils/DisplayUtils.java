package fun.ogre.ogredupealias.utils;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.data.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;

public final class DisplayUtils {

    public static void tempBlocks(Block centerBlock, Material desiredMaterial, int radius, int tickDuration) {
        Location loc = centerBlock.getLocation();
        List<BlockStorage> blocks = new ArrayList<>();
        forEachBlockIn(loc.clone().add(radius,radius,radius), loc.clone().subtract(radius,radius,radius), (point) -> {
            Block b = point.getBlock();
            if (!b.getType().isAir() && loc.distance(point) <= radius) {
                blocks.add(new BlockStorage(b));
                b.setType(desiredMaterial);
            }
        });
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            blocks.forEach(BlockStorage::restore);
        }, tickDuration);
    }

    public static void forEachBlockIn(Location start, Location end, Consumer<Location> action) {
        int[] min = {
                Math.min(start.getBlockX(), end.getBlockX()),
                Math.min(start.getBlockY(), end.getBlockY()),
                Math.min(start.getBlockZ(), end.getBlockZ())
        };
        int[] max = {
                Math.max(start.getBlockX(), end.getBlockX()),
                Math.max(start.getBlockY(), end.getBlockY()),
                Math.max(start.getBlockZ(), end.getBlockZ())
        };

        for (int a = min[0]; a <= max[0]; a ++) {
            for (int b = min[1]; b <= max[1]; b ++) {
                for (int c = min[2]; c <= max[2]; c ++) {
                    Location loc = new Location(start.getWorld(), a, b, c);
                    action.accept(loc);
                }
            }
        }
    }

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
    public static void outLineBlock(Block block, Predicate<Location> hitCondition) {
        Location loc = block.getLocation();
        Location b1 = loc.clone().add(0,0,0);
        Location b2 = loc.clone().add(1,0,0);
        Location b3 = loc.clone().add(0,0,1);
        Location b4 = loc.clone().add(1,0,1);
        Location t1 = loc.clone().add(0,1,0);
        Location t2 = loc.clone().add(1,1,0);
        Location t3 = loc.clone().add(0,1,1);
        Location t4 = loc.clone().add(1,1,1);

        RaycastUtils.raycast(b1,b2,0.2, hitCondition);
        RaycastUtils.raycast(b1,b3,0.2, hitCondition);
        RaycastUtils.raycast(b2,b4,0.2, hitCondition);
        RaycastUtils.raycast(b3,b4,0.2, hitCondition);
        RaycastUtils.raycast(b1,t1,0.2, hitCondition);
        RaycastUtils.raycast(b2,t2,0.2, hitCondition);
        RaycastUtils.raycast(b3,t3,0.2, hitCondition);
        RaycastUtils.raycast(b4,t4,0.2, hitCondition);
        RaycastUtils.raycast(t1,t2,0.2, hitCondition);
        RaycastUtils.raycast(t1,t3,0.2, hitCondition);
        RaycastUtils.raycast(t3,t4,0.2, hitCondition);
        RaycastUtils.raycast(t2,t4,0.2, hitCondition);
    }
}
