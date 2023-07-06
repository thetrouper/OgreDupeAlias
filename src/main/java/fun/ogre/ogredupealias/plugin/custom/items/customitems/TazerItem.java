package fun.ogre.ogredupealias.plugin.custom.items.customitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItemInteractionCallback;
import fun.ogre.ogredupealias.utils.Randomizer;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

public class TazerItem extends CustomItem {

    public TazerItem() {
        super(ItemPresets.TAZER);
    }

    @Override
    public CustomItemInteractionCallback getCallback() {
        return (player, item, event) -> {
            if (!event.getAction().name().contains("RIGHT_CLICK")) return;

            Location loc = player.getEyeLocation();
            Vector vec = player.getLocation().getDirection().normalize();

            Location target = RaycastUtils.raycast(loc, vec, 64.0, point -> {
                boolean hitBlock = !point.getBlock().isPassable();
                boolean hitEntity = point.getWorld().getNearbyEntities(point, 2, 2, 2).stream().anyMatch(e -> e != player && !e.isDead() && e instanceof LivingEntity);
                return hitBlock || hitEntity;
            });
            int maxSections = 10;
            int delta = 5;
            double maxDist = loc.distance(target);
            double sectionDist = maxDist / maxSections;
            Location prevLoc = loc.clone();

            Predicate<Location> hitCondition = (point) -> {
                Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 0.7F);
                point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                point.getWorld().getNearbyEntities(point, 2, 2, 2).stream()
                        .filter(e -> e != player && !e.isDead() && e instanceof LivingEntity)
                        .forEach(entity -> {
                            ((LivingEntity) entity).damage(6, player);
                            entity.setFireTicks(100);
                            SoundPlayer zap = new SoundPlayer(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.5F);
                            zap.playWithin(20);
                        });
                return false;
            };

            for (int i = 0; i < maxSections - 1; i ++) {
                prevLoc = RaycastUtils.raycast(prevLoc, vec, sectionDist, 0.2, hitCondition);
                vec = randomizeVector(vec, delta);
                SoundPlayer zap = new SoundPlayer(prevLoc, Sound.ENTITY_BEE_HURT, 1, 10);
                zap.playWithin(20);
            }
            RaycastUtils.raycast(prevLoc, target, 0.2, hitCondition);
        };
    }

    private Vector randomizeVector(Vector vec, int delta) {
        return vec.clone().add(new Vector(Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta))).normalize();
    }
}
