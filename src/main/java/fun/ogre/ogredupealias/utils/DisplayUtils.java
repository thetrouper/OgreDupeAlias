package fun.ogre.ogredupealias.utils;

import fun.ogre.ogredupealias.OgreDupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;

public final class DisplayUtils {

    public static void tempBlocks(Block centerBlock, Material desiredMaterial, int tickDuration) {
        // Store the original blocks' states and materials
        Block[][] originalBlocks = new Block[3][3];
        String[][] originalMaterials = new String[3][3];

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Block block = centerBlock.getRelative(dx, 0, dz);
                originalBlocks[dx + 1][dz + 1] = block;
                originalMaterials[dx + 1][dz + 1] = block.getType().name();
            }
        }

        // Modify the blocks to the desired material
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Block block = centerBlock.getRelative(dx, 0, dz);
                block.setType(desiredMaterial);
            }
        }

        // Schedule a task to revert the blocks after the desired number of ticks
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Block block = centerBlock.getRelative(dx, 0, dz);
                    Material originalMaterial = Material.getMaterial(originalMaterials[dx + 1][dz + 1]);
                    if (originalMaterial != null) {
                        block.setType(originalMaterial);
                    }
                    block.setBlockData(originalBlocks[dx + 1][dz + 1].getBlockData());
                }
            }
        }, tickDuration);
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
}
