package fun.ogre.ogredupealias.plugin.custom.items.customitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItemInteractionCallback;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.List;

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
                List<LivingEntity> entities = world.getNearbyEntities(point, 3, 3, 3, entity -> {
                    return entity instanceof LivingEntity le && !le.isDead() && le != player && le.getBoundingBox().expand(1).contains(point.toVector());
                }).stream().map(e -> (LivingEntity)e).toList();

                if (!entities.isEmpty()) {
                    entities.get(0).damage(10, player);
                    entities.get(0).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, true));
                    entities.get(0).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 255, true));
                }

                boolean hitBlock = !point.getBlock().isPassable();
                boolean hitEntity = !entities.isEmpty();
                return hitBlock || hitEntity;
            });
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(2, 255, 255), 100F);
            world.spawnParticle(Particle.REDSTONE, end, 100, 0.5, 0.5, 0.5, 1, dust);

            this.beam(loc, end, eye, world, player);
            this.flash(loc, end, eye, world, player);
        };
    }

    private void flash(Location loc, Location end, Location eye, World world, Player player) {
        float dist = (float)loc.distance(end);
        float diameter = 3F;
        float radius = diameter / 2F;
        AxisAngle4f angle = new AxisAngle4f(0F, 0F, 0F, 1F);
        Vector3f translation = new Vector3f(0F, 0F, 0F);

        BlockDisplay beam = world.spawn(eye, BlockDisplay.class, entity -> {
            Vector3f scale = new Vector3f(0F, 0F, 10000.0F);
            Transformation transformation = new Transformation(translation, angle, scale, angle);

            entity.setBrightness(new Display.Brightness(15, 15));
            entity.setViewRange(dist);
            entity.setRotation(eye.getYaw(), eye.getPitch());
            entity.setBlock(Material.WHITE_STAINED_GLASS.createBlockData());
            entity.setTransformation(transformation);

            Bukkit.getScheduler().runTaskLater(instance, entity::remove, 10);
        });

        Bukkit.getScheduler().runTaskLater(instance, () -> {
            Vector3f scale = new Vector3f(diameter, diameter, 10000.0F);
            Vector3f trans = new Vector3f(-radius);
            Transformation transformation = new Transformation(trans, angle, scale, angle);

            beam.setInterpolationDelay(0);
            beam.setInterpolationDuration(5);
            beam.setTransformation(transformation);
        }, 5);
    }

    private void beam(Location loc, Location end, Location eye, World world, Player player) {
        float dist = (float)loc.distance(end);
        float rad = 0.05F;
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
            entity.setGlowing(true);
            entity.setGlowColorOverride(Color.fromRGB(2, 255, 255));
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

            this.explosion(end, player);
            beam.setInterpolationDelay(0);
            beam.setInterpolationDuration(20);
            beam.setTransformation(transformation);
        }, 40);
    }

    private void explosion(Location loc, Entity source) {
        World world = loc.getWorld();
        AxisAngle4f angle = new AxisAngle4f(0F, 0F, 0F, 1F);
        Vector3f translate = new Vector3f(-0.5F, -0.5F, -0.5F);
        Vector3f scale = new Vector3f(0F, 0F, 0F);
        Transformation transformation = new Transformation(translate, angle, scale, angle);

        BlockDisplay ball = world.spawn(loc, BlockDisplay.class, entity -> {
            entity.setTransformation(transformation);
            entity.setBlock(Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
            entity.setViewRange(10000F);
            Bukkit.getScheduler().runTaskLater(instance, entity::remove, 30);
        });

        Bukkit.getScheduler().runTaskLater(instance, task -> {
            float diameter = 10F;
            float radius = diameter / 2.0F;
            Vector3f enlarge = new Vector3f(diameter, diameter, diameter);
            Vector3f enlargeTransition = new Vector3f(-radius, -radius, -radius);
            Transformation rescale = new Transformation(enlargeTransition, angle, enlarge, angle);

            ball.setInterpolationDelay(0);
            ball.setInterpolationDuration(5);
            ball.setTransformation(rescale);

            world.spawnParticle(Particle.EXPLOSION_LARGE, loc, 1, 2, 2, 2, 0);
            world.createExplosion(loc, 5, false, false, source);
        }, 5);

        Bukkit.getScheduler().runTaskLater(instance, task -> {
            ball.setInterpolationDelay(0);
            ball.setInterpolationDuration(5);
            ball.setTransformation(transformation);

            world.spawnParticle(Particle.EXPLOSION_LARGE, loc, 1, 2, 2, 2, 0);
            world.createExplosion(loc, 5, false, false, source);
        }, 15);
    }
}
