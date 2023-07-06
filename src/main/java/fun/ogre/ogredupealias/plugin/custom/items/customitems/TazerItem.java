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
            Predicate<Entity> filter = (e) -> e != player && !e.isDead() && e instanceof LivingEntity;
            Location target = RaycastUtils.raycast(loc, vec, 64.0, point -> !point.getBlock().isPassable());
            int maxSections = 10;
            int delta = 5;
            double maxDist = loc.distance(target);
            double sectionDist = maxDist / maxSections;
            Location prevLoc = loc.clone();

            Predicate<Location> hitCondition = (point) -> {
                Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 0.7F);
                point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                return false;
            };
            Consumer<Entity> onHit = (entity) -> {
                ((LivingEntity) entity).damage(6, entity); // let the entity kill themselves for max trolldge
                entity.setFireTicks(100);
                SoundPlayer hit = new SoundPlayer(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.5F);
                hit.playWithin(20);
            };

            for (int i = 0; i < maxSections - 1; i ++) {
                prevLoc = RaycastUtils.raycast(prevLoc, vec, sectionDist, 0.2, hitCondition);
                vec = randomizeVector(vec, delta);
                SoundPlayer zap = new SoundPlayer(prevLoc, Sound.ENTITY_BEE_HURT, 1, 10);
                zap.playWithin(20);
                prevLoc.getWorld().getNearbyEntities(prevLoc, 2, 2, 2).stream().filter(filter).forEach(onHit);
            }
            prevLoc = RaycastUtils.raycast(prevLoc, target, 0.2, hitCondition);
            prevLoc.getWorld().getNearbyEntities(prevLoc, 2, 2, 2).stream().filter(filter).forEach(onHit);
        };
    }

    private Vector randomizeVector(Vector vec, int delta) {
        return vec.clone().add(new Vector(Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta), Randomizer.rand(-delta, delta))).normalize();
    }
}
