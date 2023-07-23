package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.utils.DisplayUtils;
import fun.ogre.ogredupealias.utils.PlayerUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.data.type.Snow;
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
        try {
            Projectile proj = e.getEntity();
            if (proj instanceof Snowball && proj.getShooter() instanceof Player shooter) {
                Snowball snowball = (Snowball) proj;
                if (snowball.getItem().getType() == Material.BLUE_DYE || snowball.getItem().getType() == Material.BLUE_DYE) {
                    shooter.sendMessage("Passed dye check");
                    if (!e.getHitBlock().getType().equals(Material.COMMAND_BLOCK) && !e.getHitBlock().getType().equals(Material.CHAIN_COMMAND_BLOCK) && !e.getHitBlock().getType().equals(Material.REPEATING_COMMAND_BLOCK)) {
                        shooter.sendMessage("Passed command block check");
                        SoundPlayer splatSound = new SoundPlayer(e.getHitBlock().getLocation(), Sound.ITEM_GLOW_INK_SAC_USE,1,1);
                        splatSound.playWithin(10);
                        List<String> tags = PlayerUtils.getTags(shooter);
                        for (String tag : tags) {
                            switch (tag) {
                                case "SPBfire" -> {
                                    shooter.sendMessage("You were fire");
                                    DisplayUtils.tempBlocks(e.getHitBlock(),Material.RED_TERRACOTTA,2,60);
                                }
                                case "SPBfrost" -> {
                                    shooter.sendMessage("You were frost");
                                    DisplayUtils.tempBlocks(e.getHitBlock(),Material.BLUE_TERRACOTTA,2,60);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
