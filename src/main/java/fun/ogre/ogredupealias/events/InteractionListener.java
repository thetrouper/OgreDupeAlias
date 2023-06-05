package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.RecipientList;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class InteractionListener implements Listener {

    public static final RecipientList forceFieldProtected = new RecipientList();
    public static final Map<UUID, UUID> playerDragMap = new HashMap<>();
    private static final Cooldown<UUID> netskySwordCooldown = new Cooldown<>();
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
    public void forceFieldCheck(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        World w = e.getFrom().getWorld();
        if (forceFieldProtected.isRecipient(p)) {
            Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 0.5F);
            DisplayUtils.ring(p.getEyeLocation(), 4, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
                List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,10,0.5, entity -> {
                    return entity instanceof LivingEntity living && !living.isDead() && living != p;
                }));
                targets.forEach(target -> {
                    if (target instanceof LivingEntity living) {
                        SoundPlayer blockSound = new SoundPlayer(target.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
                        Vector direction = target.getLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize();
                        double strength = 2.0;
                        double verticalMultiplier = 0.5;
                        living.setVelocity(direction.multiply(strength).setY(verticalMultiplier));
                        blockSound.playWithin(10);
                    }
                });
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,0.5,0),3.968, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,1,0),3.872, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,1.5,0),3.708, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,2,0),3.464, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,2.5,0),3.122, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,3,0),2.645, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,3.5,0),1.936, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,4,0),0.9, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-0.5,0),3.968, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-1,0),3.872, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-1.5,0),3.708, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-2,0),3.464, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-2.5,0),3.122, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-3,0),2.645, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-3.5,0),1.936, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
            DisplayUtils.ring(p.getEyeLocation().add(0,-4,0),0.9, (point) -> {
                w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
            }, (point, angle) -> {
                return angle % 9 == 0;
            });
        }
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
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
            this.handleNetskyBlade(e);
            this.handleDefender(e);
            this.handleAdminUtility(e);
            this.handleVoidCharm(e);
            this.handleLaserPointer(e);
        }
        catch (Exception ignore) {}
    }
    private void handleLaserPointer(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        e.setCancelled(true);
        if (ItemUtils.matchDisplay(stack, ItemPresets.DEFENDER)) {
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    // Beam here
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK ->  {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getEyeLocation().getDirection();
                    RaycastUtils.raycast(start,rot,60,1, (point) -> {
                        return !point.getBlock().isPassable();
                    });
                }
            }
        }
    }
    private void processTable(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();

        if (PlacedStructures.isCustomTable(b)) {
            e.setCancelled(true);
            p.openInventory(InventoryPresets.createCustomTable());
        }
    }
    private void handleDefender(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (ItemUtils.matchDisplay(stack, ItemPresets.DEFENDER)) {
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
                                entity.addPotionEffect(PotionEffectType.SLOW.createEffect(240,9));
                                entity.addPotionEffect(PotionEffectType.JUMP.createEffect(240,128));
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
    private void handleVoidCharm(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (ItemUtils.matchDisplay(stack, ItemPresets.VOID_CHARM)) {
            e.setCancelled(true);
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    //test
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getLocation().getDirection().normalize();
                    SoundPlayer throwSound = new SoundPlayer(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1,0.4F);
                    throwSound.playWithin(10);
                    RaycastUtils.raycast(start, rot, 60, 0.5, (point) -> {
                        World w = point.getWorld();
                        if (w == null) return false;
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        // Create stuff here
                        Particle.DustOptions dust = new Particle.DustOptions(Color.BLACK, 1);
                        SoundPlayer bellSound = new SoundPlayer(p.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1,0.4F);

                        // Check for hits

                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                // Player hit here
                                Location targetLoc = target.getLocation();
                                Location originalLoc = target.getLocation().add(0,0.1,0);
                                bellSound.playWithin(10);
                                int[] counter = {0};
                                Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                                    if (counter[0] > 60) return;
                                    target.teleport(targetLoc.subtract(0, 0.05, 0));
                                    w.spawnParticle(Particle.REDSTONE, originalLoc, 50, 0.5, 0, 0.5, 0, dust);
                                    counter[0]++;
                                },0,1);
                            }
                        });
                        // Every raytrace
                        w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
                        return !targets.isEmpty();
                    });
                }
            }
        }
    }
    private void handleAdminUtility(PlayerInteractEvent e) {
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
    private void handleNetskyBlade(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();

        if (ItemUtils.matchDisplay(stack, ItemPresets.NETSKY_BLADE) && !netskySwordCooldown.isOnCooldown(p.getUniqueId())) {
            netskySwordCooldown.setCooldown(p.getUniqueId(), 333);
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    Location start = p.getEyeLocation();
                    Vector rotation = p.getEyeLocation().getDirection().normalize();
                    SoundPlayer shootSound = new SoundPlayer(start, Sound.ENTITY_BLAZE_SHOOT, 1, 1);

                    shootSound.playWithin(20);

                    RaycastUtils.raycast(start, rotation, 30, 0.5, 1, (point, distance) -> {
                        if (point == null || point.getWorld() == null) return false;

                        World w = point.getWorld();
                        SoundPlayer popSound = new SoundPlayer(start, Sound.BLOCK_LAVA_POP, 1, 1);
                        SoundPlayer hissSound = new SoundPlayer(start, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);

                        w.spawnParticle(Particle.FLAME, point, 1, 0, 0, 0, 0);
                        w.spawnParticle(Particle.LAVA, point, 1, 0, 0, 0, 0);
                        popSound.playWithin(3);

                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 2, 2, 2, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));

                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                living.damage(5.0, p);
                                living.setFireTicks(60);
                                hissSound.playWithin(20);
                            }
                        });

                        return !targets.isEmpty() || !point.getBlock().isPassable();
                    }, result -> {
                        if (result == null || result.getWorld() == null) return;

                        World w = result.getWorld();
                        SoundPlayer explodeSound = new SoundPlayer(result, Sound.ENTITY_GENERIC_EXPLODE, 3, 1.5F);

                        explodeSound.playWithin(500);
                        w.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, result, 20, 0, 0, 0, 0.1);
                        w.spawnParticle(Particle.LAVA, result, 120, 0, 0, 0, 1);
                    });
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rotation = p.getEyeLocation().getDirection().normalize();

                    p.getWorld().spawn(start, Fireball.class, fireball -> {
                        fireball.setDirection(rotation);
                        fireball.setVelocity(rotation);
                        fireball.setShooter(p);
                    });
                }
            }
        }
    }
}
