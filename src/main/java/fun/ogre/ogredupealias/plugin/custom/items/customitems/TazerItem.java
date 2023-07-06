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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.function.Consumer;
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
            Location target = RaycastUtils.raycast(loc, vec, 64.0, point -> !point.getBlock().isPassable());

            zap(loc, vec, target, entity -> entity == player);
        };
    }

    public static void zap(Location start, Vector startingDirection, Location end, Predicate<Entity> excluding) {
        Predicate<Entity> filter = (e) -> !excluding.test(e) && !e.isDead() && e instanceof LivingEntity;
        int maxSections = 10;
        int delta = 5;
        double maxDist = start.distance(end);
        double sectionDist = maxDist / maxSections;
        Location prevLoc = start.clone();
        Vector vec = startingDirection;

        Consumer<Entity> onHit = (entity) -> {
            ((LivingEntity) entity).damage(6, entity); // let the entity kill themselves for max trolldge
            entity.setFireTicks(100);
            SoundPlayer hit = new SoundPlayer(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.5F);
            hit.playWithin(20);
        };
        Predicate<Location> hitCondition = (point) -> {
            Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 0.7F);
            point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
            point.getWorld().getNearbyEntities(point, 2, 2, 2).stream().filter(filter).forEach(onHit);
            return false;
        };

        for (int i = 0; i < maxSections - 1; i ++) {
            prevLoc = RaycastUtils.raycast(prevLoc, vec, sectionDist, 0.2, hitCondition);
            vec = randomizeVector(vec, delta);
            SoundPlayer zap = new SoundPlayer(prevLoc, Sound.ENTITY_BEE_HURT, 1, 10);
            zap.playWithin(20);
        }
        prevLoc = RaycastUtils.raycast(prevLoc, end, 0.3, hitCondition);
        prevLoc.getWorld().getNearbyEntities(prevLoc, 2, 2, 2).stream().filter(filter).forEach(onHit);
    }

    private static Vector randomizeVector(Vector vec, int delta) {
        return vec.clone().add(new Vector(Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta))).normalize();
    }
}
