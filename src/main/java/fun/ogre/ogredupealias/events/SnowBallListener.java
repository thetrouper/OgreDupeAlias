package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SnowBallListener implements Listener {
    private ItemStack snowStack = new ItemStack(Material.SNOWBALL, 4);
    @EventHandler
    public void onProjectileHitPLayer(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball) {
            if (e.getHitEntity() instanceof Player) {
                if (e.getEntity().getShooter() instanceof Player) {
                Player shooter = (Player) e.getEntity().getShooter();
                Player victim = (Player) e.getHitEntity();
                double health = victim.getHealth();
                victim.damage(1, shooter);
                Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance,() -> {
                    victim.setHealth(health);
                    },1);
                }
            }
        }
    }
    @EventHandler
    public void onBreakSnow(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand != null) {
            if (e.getBlock().getType() == Material.SNOW_BLOCK) {
                final ItemStack stack = p.getItemInHand();
                if (ItemUtils.matchDisplay(stack, ItemPresets.SPLEEFER)) {
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    for (int i = 0; i < 4; i++) {
                        e.getPlayer().getInventory().addItem(snowStack);
                    }
                }
            }
        }
    }


    @EventHandler
    public void onProjectileHitBlock(ProjectileHitEvent e) {
        if (e.getHitBlock() == null) return;
        if (e.getEntity() == null) return;
        Location hit = Objects.requireNonNull(e.getHitBlock()).getLocation();
        World world = hit.getWorld();
        if (e.getEntity().getShooter() instanceof Player) {
            Player shooter = (Player) e.getEntity().getShooter();
            if (e.getEntity() instanceof Snowball) {
                if (e.getHitBlock().getType() == Material.SNOW_BLOCK) {
                    hit.getBlock().setType(Material.AIR);
                    shooter.getInventory().addItem(snowStack);
                }
            }
        }
    }
}
