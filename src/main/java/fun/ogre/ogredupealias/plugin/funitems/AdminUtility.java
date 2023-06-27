package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.events.EntityDamageListener;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class AdminUtility implements Listener {
    public static final Map<UUID, UUID> playerDragMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        playerDragMap.forEach((draggerID, draggedID) -> {
            Player dragger = Bukkit.getPlayer(draggerID);
            Player dragged = Bukkit.getPlayer(draggedID);
            if (dragged == null || dragger == null) return;
            Location start = dragger.getEyeLocation();
            Vector rot = dragger.getLocation().getDirection().normalize();
            Location destinsation = RaycastUtils.raycast(start, rot, 5, 1, point -> {
                return false;
            });
            dragged.teleport(destinsation);
        });
    }
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getOffHandItem();
        if (!p.isOp()) return;
        if (ItemUtils.matchDisplay(ItemPresets.ADMIN_UTILITY, stack) && !p.isSneaking()) {
            e.setCancelled(true);
            p.getWorld().spawn(p.getLocation(), Fireball.class, fireball -> {
                fireball.setDirection(p.getLocation().getDirection());
                fireball.setVelocity(p.getLocation().getDirection());
                fireball.setShooter(p);
            });
        } else if (ItemUtils.matchDisplay(ItemPresets.ADMIN_UTILITY, stack)) {
            e.setCancelled(true);
            Vector rot = p.getLocation().getDirection();
            Location loc = p.getEyeLocation();
            World w = p.getWorld();
            Particle.DustOptions dust = new Particle.DustOptions(Color.RED, 10.0F);
            Location target = RaycastUtils.raycast(loc, rot, 60, 1, (point) -> {
                return !point.getBlock().isPassable();
            });
            Location skyPoint = RaycastUtils.raycast(target,new Location(target.getWorld(),target.getX(),target.getY() + 50,target.getZ()), (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
                return false;
            });
            AtomicReference<Integer> i = new AtomicReference<>(0);
            DisplayUtils.ring(skyPoint,30, (point) -> {
                point.getWorld().spawn(point,Fireball.class,fireball -> {
                    Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance, () -> {
                        Vector vec = target.toVector().subtract(fireball.getLocation().toVector()).normalize();
                        double distance = target.distance(point);
                        fireball.setDirection(vec);
                        fireball.setVelocity(vec.multiply(5));
                        fireball.setShooter(p);
                    }, i.get() * 5);
                    i.set(i.get() + 1);
                });
            }, (point, angle) -> {
                return angle % 40 == 0;
            });
        }
    }
    public static void handleAdminUtility(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (!p.isOp()) return;
        if (ItemUtils.matchDisplay(stack, ItemPresets.ADMIN_UTILITY)) {
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    if (p.isSneaking()) {
                        Location start = p.getEyeLocation();
                        Vector rot = p.getLocation().getDirection().normalize();
                        Location center = p.getLocation();
                        List<LivingEntity> Hit = new ArrayList<>();
                        DisplayUtils.ring(center, 10, (point) -> {
                            RaycastUtils.raycast(center, point.toVector().subtract(center.toVector()).normalize(), 30, 0.3, 1, (location, distance) -> {
                                for (int i = 0; i < 20; i++) {
                                    World w = point.getWorld();
                                    if (w != null) {
                                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(location, 0.5,0.5,0.5, entity -> {
                                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                                        }));
                                        SoundPlayer hissSound = new SoundPlayer(start, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);

                                        targets.forEach(target -> {
                                            if (target instanceof LivingEntity living) {
                                                living.damage(2, p);
                                                living.setFireTicks(120);
                                                hissSound.playWithin(10);
                                            }
                                        });
                                        w.spawnParticle(Particle.FLAME, location, 1, 0,0,0, 0);
                                        w.spawnParticle(Particle.SMOKE_NORMAL, location, 1, 0,0,0, 0.1);
                                        return !targets.isEmpty() || !location.getBlock().isPassable();
                                    }
                                }
                                return false;
                            }, result -> {
                                result.getWorld().spawnParticle(Particle.LAVA, result, 20, 0.5, 0.5, 0.5, 0);
                            });
                        }, (point, angle) -> {
                            return angle % 9 == 0;
                        });
                    } else {
                        Location start = p.getEyeLocation();
                        Vector rot = p.getLocation().getDirection().normalize();
                        RaycastUtils.raycast(start, rot, 60, 0.5, 1, (point, distance) -> {
                            for (int i = 0; i < 40; i++) {
                                World w = point.getWorld();
                                if (w == null) return false;
                                List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                                    return entity instanceof LivingEntity living && !living.isDead() && living != p;
                                }));
                                SoundPlayer hissSound = new SoundPlayer(start, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);

                                targets.forEach(target -> {
                                    if (target instanceof LivingEntity living) {
                                        living.damage(2, p);
                                        living.setFireTicks(120);
                                        hissSound.playWithin(10);
                                    }
                                });
                                w.spawnParticle(Particle.FLAME, point, 1, 0,0,0, 0);
                                w.spawnParticle(Particle.SMOKE_NORMAL, point, 1, 0,0,0, 0.1);
                                return !targets.isEmpty() || !point.getBlock().isPassable();
                            }
                            return false;
                        }, result -> {
                            result.getWorld().spawnParticle(Particle.LAVA, result, 20, 0.5, 0.5, 0.5, 0);
                        });
                    }
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    if (p.isSneaking()) {
                        Location start = p.getEyeLocation();
                        Vector rot = p.getLocation().getDirection().normalize();
                        RaycastUtils.raycast(start, rot, 60, 0.5, (point) -> {
                            World w = point.getWorld();
                            if (w == null) return false;
                            List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                                return entity instanceof LivingEntity living && !living.isDead() && living != p;
                            }));
                            // Create stuff here
                            SoundPlayer zap = new SoundPlayer(start, Sound.ENTITY_BEE_HURT, 0.3F, 1);
                            Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 1);
                            // Check for hits
                            targets.forEach(target -> {
                                if (target instanceof LivingEntity living) {
                                    // Player hit here
                                    AtomicReference<Boolean> dead = new AtomicReference<>(false);
                                    Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance, () -> {
                                        if (living != null && !living.isDead() && !dead.get() && living.getHealth() > 0.0F) {
                                            living.damage(1, p);
                                            living.setNoDamageTicks(EntityDamageListener.NO_DAMAGE_TICKS);
                                            living.setMaximumNoDamageTicks(EntityDamageListener.MAX_NO_DAMAGE_TICKS);
                                        } else if (living != null && !dead.get()) {
                                            dead.set(true);
                                            living.setNoDamageTicks(EntityDamageListener.DEFAULT_NO_DAMAGE_TICKS);
                                            living.setMaximumNoDamageTicks(EntityDamageListener.DEFAULT_MAX_NO_DAMAGE_TICKS);
                                        }
                                    },0,1);

                                }
                            });
                            // Every raytrace
                            zap.playWithin(10);
                            w.spawnParticle(Particle.END_ROD, point, 1, 0,0,0, 0);
                            w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
                            return !targets.isEmpty();
                        });
                    } else {
                        Player hashed = Bukkit.getPlayer(playerDragMap.get(p.getUniqueId()));
                        if (hashed == null) {
                            Location start = p.getEyeLocation();
                            Vector rot = p.getLocation().getDirection().normalize();
                            RaycastUtils.raycast(start, rot, 30,1, point -> {
                                World w = point.getWorld();
                                List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 1,1,1, entity -> {
                                    return entity instanceof Player player && !player.isDead() && player != p;
                                }));
                                if (targets.isEmpty()) return false;
                                Player target = ((Player) targets.get(0));
                                if (target.getName().equals("obvWolf") || target.getName().equals("ImproperIssues")) {
                                    p.sendMessage(Text.ofAll("§cYou are not allowed to drag: §a" + target.getName()));
                                    return true;
                                }
                                target.setAllowFlight(true);
                                target.setGlowing(true);
                                target.sendMessage(Text.ofAll("§3You are now being dragged by:§e " + p.getName()));
                                p.sendMessage(Text.ofAll("§3You are now dragging:§e " + target.getName()));
                                playerDragMap.put(p.getUniqueId(), targets.get(0).getUniqueId());
                                return true;
                            });
                        }
                        else {
                            hashed.setAllowFlight(false);
                            hashed.setGlowing(false);
                            hashed.sendMessage(Text.ofAll("§3You are no longer being dragged by:§e " + p.getName()));
                            p.sendMessage(Text.ofAll("§3You are no longer dragging:§e " + hashed.getName()));
                            playerDragMap.remove(p.getUniqueId());
                        }
                    }
                }
            }
        }
    }
}
