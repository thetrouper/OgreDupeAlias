package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Defender {

    public static void handleDefender(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (ItemUtils.matchDisplay(stack, ItemPresets.DEFENDER)) {
            e.setCancelled(true);
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getLocation().getDirection().normalize();
                    RaycastUtils.raycast(start, rot, 60, 0.5, (point) -> {
                        World w = point.getWorld();
                        if (w == null) return false;
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5, 0.5, 0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        // Create stuff here
                        SoundPlayer hit = new SoundPlayer(start, Sound.BLOCK_STONE_HIT, 0.3F, 2);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.RED, 0.1F);
                        // Check for hits
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                // Player hit here
                                living.damage(3, p);
                                hit.playWithin(10);
                            }
                        });
                        // Every raytrace
                        w.spawnParticle(Particle.CRIT, point, 3, 0, 0, 0, 0);
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                        return !targets.isEmpty();
                    });
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getLocation().getDirection().normalize();
                    RaycastUtils.raycast(start, rot, 12, 0.5, (point) -> {
                        World w = point.getWorld();
                        if (w == null) return false;
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5, 0.5, 0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        // Create stuff here
                        SoundPlayer shoot = new SoundPlayer(start, Sound.BLOCK_NOTE_BLOCK_HAT, 0.3F, 2);
                        SoundPlayer zap = new SoundPlayer(start, Sound.ENTITY_BEE_HURT, 0.3F, 2);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 1);
                        // Check for hits
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity entity) {
                                // Player hit here
                                Location stunLoc = entity.getLocation();
                                int[] counter = {0};
                                Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                                    if (counter[0] > 60) return;
                                    if (entity == null) return;
                                    target.teleport(stunLoc);
                                    counter[0]++;
                                },0,1);
                                entity.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(120,1));
                                entity.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(600,60));
                                entity.damage(1, p);
                                zap.playWithin(15);
                            }
                        });
                        // Every raytrace
                        shoot.playWithin(15);
                        w.spawnParticle(Particle.SCRAPE, point, 1, 0, 0, 0, 0);
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                        return !targets.isEmpty();
                    });
                }
            }
        }
    }
}
