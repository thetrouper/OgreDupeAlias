package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Pickler implements Listener {
    public static void handlePickler(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        Location start = p.getEyeLocation();
        Vector rot = p.getLocation().getDirection();
        if (!ItemUtils.matchDisplay(stack, ItemPresets.PICKLER)) return;
        e.setCancelled(true);
        switch (a) {
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                // Pickle
                SoundPlayer throwSound = new SoundPlayer(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1,0.4F);
                throwSound.playWithin(10);
                RaycastUtils.raycast(start, rot, 60, 0.5, (point) -> {
                    World w = point.getWorld();
                    if (w == null) return false;
                    List<Entity> targets = new ArrayList<>(w.getNearbyEntities(point, 0.5,0.5,0.5, entity -> {
                        return entity instanceof Player player && !player.isDead() && player != p;
                    }));
                    targets.forEach(target -> {
                        if (target instanceof Player targetedPlayer) {
                            // Player hit here
                            SoundPlayer pickleSound = new SoundPlayer(target.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1,1F);
                            pickleSound.playWithin(10);
                            Block pickleBlock = targetedPlayer.getLocation().getBlock();
                            pickleBlock.setType(Material.SEA_PICKLE);
                            BlockState state = pickleBlock.getState();
                            Waterlogged waterlogged = (Waterlogged) state.getBlockData();
                            waterlogged.setWaterlogged(false);
                            pickleBlock.setBlockData(waterlogged, false);
                            PlayerUtils.addTag(targetedPlayer,"ODApickled");
                            p.sendMessage(Text.color("&9Event> &7You have been turned &e" + targetedPlayer.getName() + "&7 into a pickle!"));
                            target.sendMessage(Text.color("&9Event> &7You have been turned into a pickle by &e" + p.getName() + "&7. Its the funniest thing they've ever seen!"));
                            targetedPlayer.setInvisible(true);
                            Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance,() -> {
                                PlayerUtils.removeTag(targetedPlayer,"ODApickled");
                                target.getLocation().getBlock().setType(Material.AIR);
                                target.sendMessage(Text.color("&9Event> &7You are no longer a pickle."));
                                targetedPlayer.setInvisible(false);
                            },120);
                        }
                    });
                    return !targets.isEmpty();
                });
            }
        }
    }
    @EventHandler
    private void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (PlayerUtils.hasTag(p,"ODApickled")){
            e.setCancelled(true);
            p.sendTitle(Text.color("&2&lI am a pickle"),Text.color("&aIts the funniest thing I've ever seen"), 0,1,1);

        }
    }
}
