package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class PotatoHitListener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile proj = e.getEntity();
        if (proj.getType() == EntityType.SNOWBALL && proj.getShooter() instanceof Player) {
            Snowball snowball = (Snowball) proj;
            if (snowball.getItem().getType() == Material.POTATO) {
                if (e.getHitEntity() instanceof Player) {
                    Player victim = (Player) e.getHitEntity();
                    Player shooter = (Player) snowball.getShooter();
                    victim.damage(1, shooter);
                    SoundPlayer splatSound = new SoundPlayer(victim.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE,1,1);
                    splatSound.playWithin(10);
                }
            }
        }
    }
}
