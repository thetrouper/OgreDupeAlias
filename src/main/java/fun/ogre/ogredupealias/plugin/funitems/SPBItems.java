package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import fun.ogre.ogredupealias.utils.PlayerUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class SPBItems {
    public static ItemStack bluePaint = new ItemStack(Material.BLUE_DYE, 1);
    public static ItemStack redPaint = new ItemStack(Material.RED_DYE, 1);
    public static void handleRifle(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        if (ItemUtils.matchDisplay(stack, ItemPresets.SPBRifle) && PlayerUtils.hasTag(p,"SPBplayer")) {
            e.setCancelled(true);
            switch (a) {
                case RIGHT_CLICK_BLOCK,  RIGHT_CLICK_AIR -> {
                    Vector vec = p.getLocation().getDirection();
                    Snowball snowball = p.launchProjectile(Snowball.class);
                        List<String> tags = PlayerUtils.getTags(p);
                        for (String tag : tags) {
                            switch (tag) {
                                case "SPBfire" -> {
                                    snowball.setItem(redPaint);
                                }
                                case "SPBfrost" -> {
                                    snowball.setItem(bluePaint);
                                }
                            }
                        }
                    snowball.setVelocity(vec.multiply(2));
                    SoundPlayer shootSound = new SoundPlayer(p.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM,10,1);
                    shootSound.playWithin(30);
                }
            }
        }
    }
}
