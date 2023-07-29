package fun.ogre.ogredupealias.plugin.custom.items.customitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItemInteractionCallback;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import fun.ogre.ogredupealias.utils.raytracers.BlockDisplayRaytracer;
import fun.ogre.ogredupealias.utils.raytracers.CustomDisplayRaytracer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

import java.util.List;

public class LazerGunItem extends CustomItem {

    public static final Material LAZER_COLOR = Material.LIME_CONCRETE;

    public LazerGunItem() {
        super("lazergun", ItemPresets.LAZER_GUN);
    }

    @Override
    public CustomItemInteractionCallback getCallback() {
        return (player, item, event) -> {
            Action action = event.getAction();

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                SoundPlayer shootSound = new SoundPlayer(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 10);
                shootSound.playWithin(10);
                trace(0, 10, player, player.getEyeLocation().add(0, -0.2, 0), player.getLocation().getDirection(), 64, 10);
            }
        };
    }

    public void trace(int attempts, int maxAttempts, LivingEntity shooter, Location start, Vector direction, double distance, double damage) {
        CustomDisplayRaytracer.Point result = CustomDisplayRaytracer.trace(start, direction, distance, 0.05, point -> {
            List<Entity> nearby = point.getNearbyEntities(shooter, 5, true, 0.1, e -> e instanceof LivingEntity le && !le.isDead());
            if (!nearby.isEmpty() && nearby.get(0) instanceof LivingEntity entity) {
                entity.damage(damage, shooter);
            }
            return (!nearby.isEmpty() || CustomDisplayRaytracer.HIT_BLOCK.test(point)) && point.getTraveledDist() > 0.3;
        });
        BlockDisplayRaytracer.trace(LAZER_COLOR, start, result.getLoc(), 10);
        SoundPlayer shootSound = new SoundPlayer(result.getLoc(), Sound.BLOCK_BEACON_ACTIVATE, 1, 10);
        shootSound.playWithin(10);

        if (CustomDisplayRaytracer.HIT_BLOCK.test(result)) {
            BlockDisplayRaytracer.outline(LAZER_COLOR, result.getLoc(), 10);
            if (attempts < maxAttempts) {
                trace(attempts + 1, maxAttempts, shooter, result.getLoc(), reflect(result.getLoc(), direction), distance, damage);
            }
        }
    }

    public Vector reflect(Location pointOfContact, Vector incoming) {
        Vector negate = incoming.clone().multiply(-1).normalize();
        Vector contact = getContactingVector(pointOfContact.getBlock(), pointOfContact).multiply(-1);
        Vector reflect = contact.subtract(negate).normalize();
        return reflect;
    }

    public Vector getContactingVector(Block block, Location pointOfContact) {
        Vector center = block.getLocation().add(0.5, 0.5, 0.5).toVector();
        Vector incoming = center.subtract(pointOfContact.toVector()).normalize();
        double x = incoming.dot(new Vector(1, 0, 0));
        double y = incoming.dot(new Vector(0, 1, 0));
        double z = incoming.dot(new Vector(0, 0, 1));
        double ax = Math.abs(x);
        double ay = Math.abs(y);
        double az = Math.abs(z);

        if (ax > ay && ax > az) {
            return new Vector(x, 0, 0).normalize();
        }
        else if (ay > ax && ay > az) {
            return new Vector(0, y, 0).normalize();
        }
        else {
            return new Vector(0, 0, z).normalize();
        }
    }
}
