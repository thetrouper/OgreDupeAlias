package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.events.EntityDamageListener;
import fun.ogre.ogredupealias.events.InteractionListener;
import fun.ogre.ogredupealias.plugin.RecipientList;
import fun.ogre.ogredupealias.utils.DisplayUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ForceFieldCommand implements TabExecutor {
    public static final RecipientList forceFieldProtected = new RecipientList();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            boolean isRecipient = forceFieldProtected.isRecipient(p);
            if (isRecipient) forceFieldProtected.removeRecipient(p);
            else forceFieldProtected.addRecipient(p);
            isRecipient = forceFieldProtected.isRecipient(p);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(OgreDupeAlias.instance, () -> {
                if (forceFieldProtected.isRecipient(p)) {
                    World w = p.getWorld();
                    Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 0.5F);
                    DisplayUtils.ring(p.getEyeLocation(), 4, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                        List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5, 10, 0.5, entity -> {
                            return entity instanceof LivingEntity living && !living.isDead() && living != p;
                        }));
                        targets.forEach(target -> {
                            if (target instanceof LivingEntity living) {
                                SoundPlayer blockSound = new SoundPlayer(target.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
                                Vector direction = target.getLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize();
                                double strength = 2.0;
                                double verticalMultiplier = 0.5;
                                living.setVelocity(direction.multiply(strength).setY(verticalMultiplier));
                                blockSound.playWithin(10);
                            }
                        });
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 0.5, 0), 3.968, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 1, 0), 3.872, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 1.5, 0), 3.708, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 2, 0), 3.464, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 2.5, 0), 3.122, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 3, 0), 2.645, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 3.5, 0), 1.936, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, 4, 0), 0.9, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -0.5, 0), 3.968, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -1, 0), 3.872, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -1.5, 0), 3.708, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -2, 0), 3.464, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -2.5, 0), 3.122, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -3, 0), 2.645, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -3.5, 0), 1.936, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                    DisplayUtils.ring(p.getEyeLocation().add(0, -4, 0), 0.9, (point) -> {
                        w.spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
                    }, (point, angle) -> {
                        return angle % 9 == 0;
                    });
                }
            }, 0,2);
            sender.sendMessage(Text.builder()
                    .message("&7[&bForceField&7] &8>> &3You are " + (isRecipient ? "&anow" : "&cno longer") + " &3a protected.")
                    .prefix()
                    .color()
                    .build());
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex,command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
