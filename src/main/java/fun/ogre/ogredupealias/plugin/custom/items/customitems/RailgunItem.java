package fun.ogre.ogredupealias.plugin.custom.items.customitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItemInteractionCallback;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.*;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;

public class RailgunItem extends CustomItem {

    public RailgunItem() {
        super("railgun", ItemPresets.RAILGUN);
    }

    @Override
    public CustomItemInteractionCallback getCallback() {
        return (player, item, event) -> {
            Location loc = player.getLocation();
            Location eye = player.getEyeLocation();
            World world = player.getWorld();

            Location end = RaycastUtils.raycast(eye, loc.getDirection(), 100, 0.5, point -> {
                boolean hitBlock = !point.getBlock().isPassable();
                boolean hitEntity = !world.getNearbyEntities(point, 3, 3, 3, entity -> {
                    return entity instanceof LivingEntity le && !le.isDead() && le != player && le.getBoundingBox().contains(point.toVector());
                }).isEmpty();
                return hitBlock || hitEntity;
            });
            Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 10F);
            world.spawnParticle(Particle.REDSTONE, end, 30, 0, 0, 0, 1, dust);

            float dist = (float)loc.distance(end);
            float rad = 0.02F;
            AxisAngle4f angle = new AxisAngle4f(0F, 0F, 0F, 1F);
            Vector3f translation = new Vector3f(-0.05F, -0.2F, 0F);

            BlockDisplay beam = world.spawn(eye, BlockDisplay.class, entity -> {
                SoundPlayer sound = new SoundPlayer(loc, Sound.BLOCK_BEACON_POWER_SELECT, 5.0F, 10.0F);
                Vector3f scale = new Vector3f(rad, rad, 0F);
                Transformation transformation = new Transformation(translation, angle, scale, angle);

                entity.setBrightness(new Display.Brightness(15, 15));
                entity.setViewRange(dist);
                entity.setRotation(eye.getYaw(), eye.getPitch());
                entity.setBlock(Material.DIAMOND_BLOCK.createBlockData());
                entity.setTransformation(transformation);
                sound.playWithin(500);

                Bukkit.getScheduler().runTaskLater(instance, entity::remove, 60);
            });

            Bukkit.getScheduler().runTaskLater(instance, () -> {
                Vector3f scale = new Vector3f(rad, rad, dist);
                Transformation transformation = new Transformation(translation, angle, scale, angle);

                beam.setInterpolationDelay(0);
                beam.setInterpolationDuration((int)(dist / 2));
                beam.setTransformation(transformation);
            }, 5);

            Bukkit.getScheduler().runTaskLater(instance, () -> {
                Vector3f scale = new Vector3f(rad, rad, 0.0F);
                Transformation transformation = new Transformation(translation, angle, scale, angle);

                world.createExplosion(end, 3, false, false, player);
                beam.setInterpolationDelay(0);
                beam.setInterpolationDuration(20);
                beam.setTransformation(transformation);
            }, 40);
        };
    }
}
