package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
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

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
            this.handleNetskyBlade(e);
        }
        catch (Exception ignore) {
            ignore.printStackTrace();
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

    private void handleNetskyBlade(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();

        if (ItemUtils.matchDisplay(stack, ItemPresets.NETSKY_BLADE)) {
            switch (a) {
                case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> {
                    Location start = p.getEyeLocation();
                    Vector rotation = p.getLocation().getDirection().normalize();

                    RaycastUtils.raycast(start, rotation, 20, 0.5, 1, point -> {
                        if (point == null || point.getWorld() == null) return false;

                        World w = point.getWorld();
                        w.spawnParticle(Particle.FLAME, point, 1, 0, 0, 0, 0);

                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 2, 2, 2, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));

                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                living.damage(2.0, p);
                                living.setFireTicks(60);
                            }
                        });

                        return !targets.isEmpty();
                    });
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    Location start = p.getEyeLocation();
                    Vector rotation = p.getLocation().getDirection().normalize();

                    p.getWorld().spawn(start, Fireball.class, fireball -> {
                        fireball.setDirection(rotation);
                        fireball.setVelocity(rotation.multiply(3));
                        fireball.setShooter(p);
                    });
                }
            }
        }
    }
}
