package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.DisplayUtils;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.concurrent.atomic.AtomicReference;


public class LaserPointer {
    public static void handleLaserPointer(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        Location start = p.getEyeLocation();
        Vector rot = p.getLocation().getDirection();
        if (!ItemUtils.matchDisplay(stack, ItemPresets.LASER_POINTER)) return;
        e.setCancelled(true);
        switch (a) {
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                // Full beam
                RaycastUtils.raycast(start,rot,30,0.1,(point) -> {
                    Particle.DustOptions dust = new Particle.DustOptions(Color.LIME, 0.5F);
                    point.getWorld().spawnParticle(Particle.REDSTONE,point,1,0,0,0,0,dust);
                   return !point.getBlock().getType().equals(Material.AIR);
                });
            }
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                // Select Block
                Location block = RaycastUtils.raycast(start,rot,60,0.5,(point) -> {
                    return !point.getBlock().isPassable();
                });
                AtomicReference<Integer> counter = new AtomicReference<>(0);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance, () -> {
                    if (counter.get() > 60) return;
                    DisplayUtils.outLineBlock(block.getBlock(), (point) -> {
                        Particle.DustOptions dust = new Particle.DustOptions(Color.LIME, 0.5F);
                        block.getWorld().spawnParticle(Particle.REDSTONE,point,1,0,0,0,0,dust);
                        return false;
                    });
                    counter.set(counter.get() + 1);
                },0,1);
            }
        }
    }
}
