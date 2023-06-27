package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static fun.ogre.ogredupealias.utils.ServerUtils.hasBlockBelow;

public class TurfWarsEventListener implements Listener {
    ItemStack arrowItem = new ItemStack(Material.ARROW, 1);
    @EventHandler
    private static void antiTurfWalk(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (ServerUtils.hasTag(p,"TWplayer") && ServerUtils.hasTag(p,"TWat")) {
            if (ServerUtils.hasTag(p,"TWred")) {
                if (hasBlockBelow(p, Material.LIGHT_BLUE_TERRACOTTA)) {
                    p.setVelocity(new Vector(0, 0.75, -0.5)); // Adjust the velocity values as needed
                }
            } else if (ServerUtils.hasTag(p,"TWblue")) {
                if (hasBlockBelow(p, Material.RED_TERRACOTTA)) {
                    p.setVelocity(new Vector(0, 0.25, 0.5)); // Adjust the velocity values as needed
                }
            }
        }
    }

    @EventHandler
    private void onAOELand(ProjectileHitEvent e) {
        Entity ent = e.getEntity();
        if (e.getEntity().getShooter() instanceof Player shooter) {
            if (ServerUtils.hasTag(shooter, "TWbomber")) {
                if (!(ent instanceof Snowball)) return;
                if (shooter.getUniqueId().toString().equals("049460f7-21cb-42f5-8059-d42752bf406f") && shooter.isSneaking()) {
                    if (e.getHitEntity() != null && e.getHitEntity() instanceof Player victim) {
                        explodeOnLand(shooter,e.getHitEntity().getLocation(),3D,4);
                    }
                    if (e.getHitBlock() != null) {
                        explodeOnLand(shooter,e.getHitBlock().getLocation(),3D,2);
                    }
                }
                if (e.getHitEntity() != null && e.getHitEntity() instanceof Player) {
                    explodeOnLand(shooter,e.getHitEntity().getLocation(),1.5,3);
                }
                if (e.getHitBlock() != null) {
                    explodeOnLand(shooter,e.getHitBlock().getLocation(),1.5,1);
                }
            }
        }
    }

    @EventHandler
    private void onBlockHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player shooter) {
            if (ServerUtils.hasTag(shooter, "TWplayer")) {
            if (e.getHitBlock() == null) return;;
            Block block = e.getHitBlock();
            if (e.getHitBlock().getType() == Material.BLUE_WOOL || block.getType() == Material.RED_WOOL) {
                    e.getEntity().remove();
                    twBreak(block);
               } else if (e.getHitBlock().getType() == Material.BLUE_CONCRETE || block.getType() == Material.RED_CONCRETE) {
                    if (ServerUtils.hasTag(shooter,"TWbomber") || ServerUtils.hasTag(shooter,"TWshredder")) return;
                    e.getEntity().remove();
                    twBreak(block);
                }
            }
        }
    }

    @EventHandler
    private void antiSpawnKill(PlayerMoveEvent e) {
        if (ServerUtils.hasTag(e.getPlayer(),"TWsk")) {
            e.getPlayer().sendMessage(Text.color("&9Game> &c&lYou are not allowed to enter the other team's spawn! &7You have been killed!"));
            ServerUtils.safeKill(e.getPlayer());
            ServerUtils.removeTag(e.getPlayer(),"TWsk");
            }
        }
    @EventHandler
    private void onShotgunShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (!ServerUtils.hasTag(p,"TWshredder")) return;
            if (p.getUniqueId().toString().equals("049460f7-21cb-42f5-8059-d42752bf406f")) {
                shredderShot(p,4,7D);
            }
            shredderShot(p,10,5D);
        }
    }
    @EventHandler
    private void onSnipePlayer(ProjectileHitEvent e) {
        e.getEntity().remove();
        if (e.getHitEntity() instanceof Player victim && e.getEntity().getShooter() instanceof Player shooter) {
            if (ServerUtils.hasSameTag(shooter,victim,"TWblue")) return;
            if (ServerUtils.hasSameTag(shooter,victim,"TWred")) return;
            if (victim == shooter) return;
            SoundPlayer hitSound = new SoundPlayer(shooter.getLocation(),Sound.BLOCK_NOTE_BLOCK_BELL,10,2);
            if (e.getEntity() instanceof Arrow ent) {
                if (!ServerUtils.hasTag(victim,"TWplayer")) return;
                if (ServerUtils.hasTag(shooter,"TWplayer") && !ServerUtils.hasTag(shooter,"TWshredder")) {
                    ServerUtils.safeKill(victim);
                    ent.remove();
                    victim.sendMessage(Text.color("&9Game> &7You have been sniped by &e" + shooter.getName() + "&7."));
                    shooter.sendMessage(Text.color("&9Game> &7You sniped &e" + victim.getName() + "&7."));
                    hitSound.play(shooter);
                } else if (ServerUtils.hasTag(shooter,"TWshredder")) {
                    ServerUtils.safeKill(victim);
                    victim.sendMessage(Text.color("&9Game> &7You have been blundered by &e" + shooter.getName() + "&7."));
                    shooter.sendMessage(Text.color("&9Game> &7You blundered &e" + victim.getName() + "&7."));
                    hitSound.play(shooter);
                }
            }
        }
    }
    @EventHandler
    private void onInfiltratorHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player attacker && e.getEntity() instanceof Player victim) {
            if (ServerUtils.hasSameTag(attacker,victim,"TWblue")) return;
            if (ServerUtils.hasSameTag(attacker,victim,"TWred")) return;
            if (ServerUtils.hasTag(attacker, "TWinfiltrator") && ServerUtils.hasTag(victim, "TWplayer")) {
                ServerUtils.safeKill(victim);
                victim.sendMessage(Text.color("&9Game> &7You have been stabbed by &e" + attacker.getName() + "&7."));
                attacker.sendMessage(Text.color("&9Game> &7You stabbed &e" + victim.getName() + "&7."));
                }
            }
        }
    @EventHandler
    private void onInfiltratorKill(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player attacker && e.getEntity() instanceof Player victim) {
            if (ServerUtils.hasSameTag(attacker,victim,"TWblue")) return;
            if (ServerUtils.hasSameTag(attacker,victim,"TWred")) return;
            if (ServerUtils.hasTag(victim, "TWinfiltrator") && ServerUtils.hasTag(attacker, "TWplayer")) {
                ServerUtils.safeKill(victim);
                victim.sendMessage(Text.color("&9Game> &7You have been stabbed by &e" + attacker.getName() + "&7."));
                attacker.sendMessage(Text.color("&9Game> &7You stabbed &e" + victim.getName() + "&7."));
            }
        }
    }
    @EventHandler
    private void onRespawn(PlayerRespawnEvent e) {
        if (ServerUtils.hasTag(e.getPlayer(),"TWplayer")) {
            ServerUtils.removeTag(e.getPlayer(),"oneshot");
            e.getPlayer().sendMessage(Text.color("&9Game> &7Respawned!"));
        }
    }

    public void explodeWool(Location hitLoc, int radius) {
        World world = hitLoc.getWorld();
        int x = hitLoc.getBlockX();
        int y = hitLoc.getBlockY();
        int z = hitLoc.getBlockZ();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Block block = world.getBlockAt(x + dx, y + dy, z + dz);
                    if (block.getType() == Material.RED_WOOL || block.getType() == Material.BLUE_WOOL) {
                        SoundPlayer breakSound = new SoundPlayer(block.getLocation(), Sound.BLOCK_WOOL_BREAK,0.5F,1);
                        block.setType(Material.AIR);
                        breakSound.playWithin(10);
                    }
                }
            }
        }
    }
    public void explodePlayers(Player attacker, Location hitLoc, Double radius) {
        List<LivingEntity> Hit = new ArrayList<>();
        List<Entity> targets = new ArrayList<>(hitLoc.getWorld().getNearbyEntities(hitLoc, radius,radius,radius, entity -> {
            return entity instanceof Player victim1 && !victim1.isDead() && victim1 != attacker;
        }));
        targets.forEach(target -> {
            if (target instanceof Player victim2) {
                if (ServerUtils.hasSameTag(attacker,victim2,"TWblue")) return;
                if (ServerUtils.hasSameTag(attacker,victim2,"TWred")) return;
                ServerUtils.safeKill(victim2);
                victim2.sendMessage(Text.color("&9Game>&7 You got 'sploded by &e" + attacker.getName() + "&7!"));
                attacker.sendMessage(Text.color("&9Game>&7 You 'sploded &e" + victim2.getName() + "&7!"));
            }
        });
    }
    public void explodeOnLand(Player shooter, Location hitLoc, Double killRadius, Integer destroyRadius) {
        explodeWool(hitLoc, destroyRadius);
        explodePlayers(shooter, hitLoc, killRadius);
        hitLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, hitLoc,3);
        SoundPlayer explodeSound = new SoundPlayer(hitLoc, Sound.ENTITY_GENERIC_EXPLODE,10,1);
        explodeSound.playWithin(30);
    }
    public void shredderShot(Player shooter, Integer amount, Double angle) {
        for (int i = 0; i < amount; i++) {
            Vector vec = RaycastUtils.randomVector(shooter.getLocation().getDirection(), angle);
            Arrow arrow = shooter.launchProjectile(Arrow.class);
            arrow.setShooter(shooter);
            arrow.setVelocity(vec.multiply(2.5));
        }
    }
    public void twBreak(Block block) {
        Location loc = block.getLocation();
        BlockData fallingDustData = block.getType().createBlockData();
        block.setType(Material.AIR);
        block.getWorld().spawnParticle(Particle.BLOCK_DUST,loc.add(0.5D,0.5D,0.5D), 30, 0.5,0.5,0.5, fallingDustData);
        SoundPlayer breakSound = new SoundPlayer(loc,Sound.BLOCK_WOOL_BREAK,10,1);
        breakSound.playWithin(20);
    }
}
