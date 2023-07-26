package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AK47 {
    public static void handleAK47(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        Location start = p.getEyeLocation();
        Vector rot = p.getLocation().getDirection().normalize();
        World w = p.getWorld();
        if (!ItemUtils.matchDisplay(stack, ItemPresets.AK47)) return;
        e.setCancelled(true);
        switch (a) {
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                // Full auto
                int[] counter = {0};
                Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance,() -> {
                    if (counter[0] > 3) return;
                    counter[0]++;
                    SoundPlayer shootSound = new SoundPlayer(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT,10,1);
                    shootSound.playWithin(30);
                    Location realStart = p.getEyeLocation();
                    Vector realRot = p.getLocation().getDirection().normalize();
                    RaycastUtils.raycast(realStart,realRot,30,(point) -> {
                        if (w == null) return false;
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity) {
                                BlockData blockData = Material.RED_WOOL.createBlockData();
                                SoundPlayer hitSound = new SoundPlayer(target.getLocation(), Sound.ITEM_DYE_USE,3,2);
                                ((LivingEntity) target).damage(10,p);
                                hitSound.playWithin(20);
                                w.spawnParticle(Particle.BLOCK_DUST,target.getLocation().add(0,1,0),30,0.25F,1,0.25F,blockData);
                            }
                        });
                        w.spawnParticle(Particle.CRIT, point, 1, 0,0,0, 0);
                        w.spawnParticle(Particle.SMOKE_NORMAL, point, 1, 0,0,0, 0.1F);
                        return !targets.isEmpty() || !point.getBlock().isPassable();
                    });
                },0,2);
            }
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                // Single shot
                SoundPlayer shootSound = new SoundPlayer(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT,10,1);
                shootSound.playWithin(60);
                RaycastUtils.raycast(start,rot,30,(point) -> {
                        if (w == null) return false;
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity) {
                                BlockData blockData = Material.RED_WOOL.createBlockData();
                                SoundPlayer hitSound = new SoundPlayer(target.getLocation(), Sound.ITEM_DYE_USE,3,2);
                                ((LivingEntity) target).damage(10,p);
                                hitSound.playWithin(20);
                                w.spawnParticle(Particle.BLOCK_DUST,target.getLocation().add(0,1,0),30,0.25F,1,0.25F,blockData);
                            }
                        });
                        w.spawnParticle(Particle.CRIT, point, 1, 0,0,0, 0);
                        w.spawnParticle(Particle.SMOKE_NORMAL, point, 1, 0,0,0, 0.1);
                        return !targets.isEmpty() || !point.getBlock().isPassable();
                });
            }
        }
    }
}
/*
DO NOT GET WORLD FOR A RESULT UNLESS YOU WANT YOUR CONSOLE TO SPIT THIS OUT EVERY PICOSECOND

[16:34:12 WARN]: [ODA] Task #994 for OgreDupeAlias v2.0.0 generated an exception
java.lang.IllegalArgumentException: data should be interface org.bukkit.block.data.BlockData got class org.bukkit.Material
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2077) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2072) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2061) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2041) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2021) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2016) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at fun.ogre.ogredupealias.plugin.funitems.AK47.lambda$handleAK47$3(AK47.java:56) ~[OgreDupeAlias-2.0.0.jar:?]
        at fun.ogre.ogredupealias.utils.RaycastUtils.lambda$raycast$0(RaycastUtils.java:55) ~[OgreDupeAlias-2.0.0.jar:?]
        at org.bukkit.craftbukkit.v1_19_R3.scheduler.CraftTask.run(CraftTask.java:101) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.scheduler.CraftScheduler.mainThreadHeartbeat(CraftScheduler.java:483) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1485) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:450) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1399) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1176) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:322) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at java.lang.Thread.run(Thread.java:833) ~[?:?]
[15:56:53 WARN]: [ODA] Task #488173 for OgreDupeAlias v2.0.0 generated an exception
java.lang.IllegalArgumentException: data should be interface org.bukkit.block.data.BlockData got class org.bukkit.Material
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2077) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2072) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2061) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2041) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2021) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.CraftWorld.spawnParticle(CraftWorld.java:2016) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at fun.ogre.ogredupealias.plugin.funitems.AK47.lambda$handleAK47$3(AK47.java:56) ~[OgreDupeAlias-2.0.0.jar:?]
        at fun.ogre.ogredupealias.utils.RaycastUtils.lambda$raycast$0(RaycastUtils.java:55) ~[OgreDupeAlias-2.0.0.jar:?]
        at org.bukkit.craftbukkit.v1_19_R3.scheduler.CraftTask.run(CraftTask.java:101) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at org.bukkit.craftbukkit.v1_19_R3.scheduler.CraftScheduler.mainThreadHeartbeat(CraftScheduler.java:483) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1485) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:450) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1399) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1176) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:322) ~[pufferfish-1.19.4.jar:git-Pufferfish-65]
        at java.lang.Thread.run(Thread.java:833) ~[?:?]
 */