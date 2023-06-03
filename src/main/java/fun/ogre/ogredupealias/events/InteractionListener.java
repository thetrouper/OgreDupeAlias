package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.Cooldown;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InteractionListener implements Listener {

    private static final Cooldown<UUID> netskySwordCooldown = new Cooldown<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
            this.handleNetskyBlade(e);
        }
        catch (Exception ignore) {}
    }

    private void processTable(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();

        if (PlacedStructures.isCustomTable(b)) {
            e.setCancelled(true);
            p.openInventory(InventoryPresets.createCustomTable());
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
                    Vector rotation = p.getLocation().getDirection().normalize();
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
                    Vector rotation = p.getLocation().getDirection().normalize();

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
