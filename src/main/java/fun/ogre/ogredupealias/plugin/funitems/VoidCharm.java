package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public
class
VoidCharm
{
    public
    static
    void
    handleVoidCharm
            (
                    PlayerInteractEvent e
            ) {
        Player
                p =
                e.getPlayer();
        ItemStack
                stack =
                e.getItem();
        Action
                a =
                e.getAction();
        if
        (
                ItemUtils.matchDisplay
                        (
                                stack,
                                ItemPresets.VOID_CHARM
                        )
        )
        {
            e.setCancelled
                    (
                    true
                    );
            switch
            (
                    a
            )
            {
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK
                        -> {
                    Location
                            start =
                            p.getEyeLocation();
                    Vector
                            rot =
                            p.getLocation()
                                    .getDirection()
                                    .normalize();
                    SoundPlayer
                            throwSound =
                            new
                                    SoundPlayer
                                    (
                                            p.getLocation(),
                                            Sound.ITEM_TRIDENT_RIPTIDE_3,
                                            1,
                                            0.4F
                                    );
                    throwSound.
                            playWithin
                                    (
                                            10
                                    );
                    RaycastUtils.
                            raycast
                                    (
                                            start,
                                            rot,
                                            60,
                                            0.5,
                                            (
                                                    point
                                            )
                                                    ->
                                            {
                        World
                                w =
                                point.getWorld();
                        if
                        (
                                w == null
                        )
                            return false;
                        List
                                <Entity>
                                targets =
                                new ArrayList
                                        <>
                                        (
                                                w.getNearbyEntities
                                                        (
                                                                point,
                                                                0.5,
                                                                0.5,
                                                                0.5,
                                                                entity
                                                                        -> {
                            return
                                    entity
                                            instanceof
                                            LivingEntity
                                                    living &&
                                            !living.isDead() &&
                                            living !=
                                                    p;
                        }
                        )
                                        );
                        Particle.
                                DustOptions
                                dust = new Particle.
                                DustOptions
                                (
                                        Color.BLACK, 1
                                );
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
            }
        }
    }
}
