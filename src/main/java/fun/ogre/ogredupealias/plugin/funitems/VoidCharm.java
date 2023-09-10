package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.data.builder.ItemBuilder;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.*;
import fun.ogre.ogredupealias.utils.raytracers.CustomDisplayRaytracer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class VoidCharm {
    public static void handleVoidCharm(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (ItemUtils.matchDisplay(stack, ItemPresets.VOID_CHARM)) {
            e.setCancelled(true);
            switch (a) {
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getLocation().getDirection().normalize();
                    SoundPlayer throwSound = new SoundPlayer(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1, 0.4F);
                    throwSound.playWithin(10);
                    RaycastUtils.raycast(start, rot, 60, 0.5, (point) -> {
                        World w = point.getWorld();
                        if (w == null) return false;
                        List <Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5, 0.5, 0.5, entity -> {return entity instanceof LivingEntity living && !living.isDead() && living != p;}));
                        Particle.DustOptions dust = new Particle.DustOptions(Color.BLACK, 1);
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                Location targetLoc = target.getLocation();
                                Location originalLoc = target.getLocation().add(0,0.1,0);
                                SoundPlayer bellSound = new SoundPlayer(target.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1,0.4F);
                                bellSound.playWithin(10);
                                int[] counter = {0};
                                Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                                    if (counter[0] > 60) return;
                                    target.teleport(targetLoc.subtract(0, 0.05, 0));
                                    w.spawnParticle(Particle.REDSTONE, originalLoc, 50, 0.5, 0, 0.5, 0, dust);
                                    w.spawnParticle(Particle.SQUID_INK, originalLoc, 50, 0.5, 0, 0.5, 0);
                                    counter[0]++;
                                },0,1);
                            }
                        });
                        w.spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, dust);
                        return !targets.isEmpty();
                    });
                }
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rot = p.getLocation().getDirection().normalize();
                    SoundPlayer throwSound = new SoundPlayer(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1, 0.4F);

                    throwSound.playWithin(10);
                    Particle.DustOptions dust = new Particle.DustOptions(Color.BLACK, 1);
                    var result = CustomDisplayRaytracer.trace(start,rot,30,0.5,CustomDisplayRaytracer.HIT_BLOCK);
                    World w = result.getWorld();
                    WitherSkeleton ws = w.spawn(result.getLoc().clone().add(0,-3,0),WitherSkeleton.class, skeleton -> {
                        skeleton.setInvulnerable(true);
                        SoundPlayer spawnSound = new SoundPlayer(result.getLoc(), Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 1, 0.4F);
                        spawnSound.playWithin(20);
                        Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance,() -> {
                            skeleton.setInvulnerable(false);
                            skeleton.getEquipment().setHelmet(new ItemBuilder().material(Material.NETHERITE_HELMET).enchant(Enchantment.VANISHING_CURSE,1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL,4).build());
                            skeleton.getEquipment().setChestplate(new ItemBuilder().material(Material.NETHERITE_CHESTPLATE).enchant(Enchantment.VANISHING_CURSE,1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL,4).build());
                            skeleton.getEquipment().setLeggings(new ItemBuilder().material(Material.NETHERITE_LEGGINGS).enchant(Enchantment.VANISHING_CURSE,1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL,4).build());
                            skeleton.getEquipment().setBoots(new ItemBuilder().material(Material.NETHERITE_BOOTS).enchant(Enchantment.VANISHING_CURSE,1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL,4).build());
                            skeleton.getEquipment().setItemInMainHand(new ItemBuilder().material(Material.NETHERITE_SWORD).enchant(Enchantment.DAMAGE_ALL,5).build());
                            skeleton.setMaxHealth(40);
                            skeleton.setHealth(40);
                            skeleton.setCustomName(Text.color("&0[&8Nasghoul&0]"));
                        },80);
                    });
                    AtomicInteger counter = new AtomicInteger(0);
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                        if (counter.getAndIncrement() > 80) return;
                        w.spawnParticle(Particle.REDSTONE, result.getLoc(), 50, 0.5, 0, 0.5, 0, dust);
                        w.spawnParticle(Particle.SQUID_INK, result.getLoc(), 50, 0.5, 0, 0.5, 0);
                        ws.teleport(ws.getLocation().add(0,0.04,0));
                    },0,1);
                }
            }
        }
    }
}
