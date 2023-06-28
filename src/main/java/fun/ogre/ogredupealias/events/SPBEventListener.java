package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.utils.DisplayUtils;
import fun.ogre.ogredupealias.utils.PlayerUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.List;

public class SPBEventListener implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile proj = e.getEntity();
        if (proj.getType() == EntityType.SNOWBALL && proj.getShooter() instanceof Player shooter) {
            Snowball snowball = (Snowball) proj;
            if (snowball.getItem().getType() == Material.BLUE_DYE || snowball.getItem().getType() == Material.BLUE_DYE) {
                if (!e.getHitBlock().getType().equals(Material.COMMAND_BLOCK) && !e.getHitBlock().getType().equals(Material.CHAIN_COMMAND_BLOCK) && !e.getHitBlock().getType().equals(Material.REPEATING_COMMAND_BLOCK)) {
                    SoundPlayer splatSound = new SoundPlayer(e.getHitBlock().getLocation(), Sound.ITEM_GLOW_INK_SAC_USE,1,1);
                    splatSound.playWithin(10);
                    List<String> tags = PlayerUtils.getTags(shooter);
                    for (String tag : tags) {
                        switch (tag) {
                            case "SPBfire" -> {
                                DisplayUtils.tempBlocks(e.getHitBlock(),Material.RED_TERRACOTTA,60);
                            }
                            case "SPBfrost" -> {
                                DisplayUtils.tempBlocks(e.getHitBlock(),Material.BLUE_TERRACOTTA,60);
                            }
                        }
                    }
                }
            }
        }
    }
}
