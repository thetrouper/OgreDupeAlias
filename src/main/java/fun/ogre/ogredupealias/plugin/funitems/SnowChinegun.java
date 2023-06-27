package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.events.EntityDamageListener;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

import static org.bukkit.event.block.Action.*;


public class SnowChinegun {
    public static void handleSnowChinegun(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        Inventory inv = p.getInventory();
        ItemStack snowballItem = new ItemStack(Material.SNOWBALL, 1);
        if (ItemUtils.matchDisplay(stack, ItemPresets.SNOWCHINEGUN)) {
            e.setCancelled(true);
            SoundPlayer shootSound = new SoundPlayer(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1,0.8F);
            SoundPlayer launchSound = new SoundPlayer(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1,1.5F);
            switch (a) {
                case RIGHT_CLICK_BLOCK,  RIGHT_CLICK_AIR -> {
                    int[] counter = {0};
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                        if (counter[0] > 7) return;
                        counter[0]++;
                        if (!inv.contains(Material.SNOWBALL) && p.getGameMode().equals(GameMode.SURVIVAL)) return;
                        Vector look = p.getLocation().getDirection();
                        Snowball snowball = p.launchProjectile(Snowball.class);
                        snowball.setShooter(p);
                        snowball.setVelocity(look.multiply(3));
                        shootSound.playWithin(20);
                        inv.removeItem(snowballItem);

                    },0,1);
                }
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                    if (p.getGameMode().equals(GameMode.CREATIVE) && p.isSneaking()) {
                        for (int i = 0; i < 512; i++) {
                            Vector vec = RaycastUtils.randomVector(p.getLocation().getDirection(), 60);
                            Snowball snowball = p.launchProjectile(Snowball.class);
                            snowball.setVelocity(vec);
                        }
                    }
                    if (!inv.contains(Material.SNOWBALL) && p.getGameMode().equals(GameMode.SURVIVAL)) return;
                    launchSound.playWithin(20);
                    shootSound.playWithin(10);
                    for (int i = 0; i < 16; i++) {
                        if (!inv.contains(Material.SNOWBALL) && p.getGameMode().equals(GameMode.SURVIVAL)) return;
                        Vector vec = RaycastUtils.randomVector(p.getLocation().getDirection(), 20);
                        Snowball snowball = p.launchProjectile(Snowball.class);
                        snowball.setShooter(p);
                        snowball.setVelocity(vec.multiply(1.6));
                        inv.removeItem(snowballItem);
                    }
                }
            }
        }
    }

}
