package fun.ogre.ogredupealias.plugin.funitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ItemUtils;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import fun.ogre.ogredupealias.utils.SoundPlayer;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PotatoCannon implements Listener {
    private static final int MAX_POTATOES = 6;
    private static Map<Player, List<Snowball>> loadedPotatoesMap = new HashMap<>();

    public static void handlePotatoCannon(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack stack = e.getItem();
        Action a = e.getAction();
        Inventory inv = p.getInventory();
        if (ItemUtils.matchDisplay(stack, ItemPresets.POTATOCANNON)) {
            e.setCancelled(true);
            switch (a) {
                case RIGHT_CLICK_BLOCK,  RIGHT_CLICK_AIR -> {
                    //Load potatoes
                    loadPotatoes(p);
                }
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                    // Launch Potatoes
                    launchPotatoes(p);
                    }
                }
            }
        }

    public static void loadPotatoes(Player p) {
        List<Snowball> loadedPotatoes = loadedPotatoesMap.getOrDefault(p, new ArrayList<>());
        int potatoesInLauncher = loadedPotatoes.size();

        if (potatoesInLauncher >= MAX_POTATOES) {
            p.sendMessage(Text.ofAll("&7Potato launcher is already full."));
            return;
        }

        int potatoesToLoad = Math.min(1, ItemUtils.itemCount(p, Material.POTATO));

        if (potatoesToLoad > 0) {
            p.getInventory().removeItem(new ItemStack(Material.POTATO, 1));
            loadedPotatoes.add(null); // Add a null placeholder for each potato loaded
            loadedPotatoesMap.put(p, loadedPotatoes);
            p.sendMessage(Text.ofAll("&7Loaded &a" + (potatoesInLauncher + 1) + "/" + MAX_POTATOES + "&7 potatoes into the launcher."));

            SoundPlayer loadSound = new SoundPlayer(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 0.1F);
            loadSound.play(p);
        } else {
            p.sendMessage(Text.ofAll("&7You are out of potatoes!"));
            SoundPlayer failSound = new SoundPlayer(p.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1F);
            failSound.play(p);
        }
    }


    public static void launchPotatoes(Player shooter) {
        List<Snowball> loadedPotatoes = loadedPotatoesMap.getOrDefault(shooter, new ArrayList<>());

        if (loadedPotatoes.isEmpty()) {
            // Play sound when launcher is empty
            SoundPlayer failSound = new SoundPlayer(shooter.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1F);
            failSound.play(shooter);
            return;
        }

        int potatoCount = loadedPotatoes.size();
        for (int i = 0; i < potatoCount; i++) {
            ItemStack potatoStack = new ItemStack(Material.POTATO, 1);
            Snowball launchedPotato = shooter.launchProjectile(Snowball.class);
            launchedPotato.setShooter(shooter);
            launchedPotato.setItem(potatoStack);
            launchedPotato.setVelocity(RaycastUtils.randomVector(shooter.getEyeLocation().getDirection(), 5));
            SoundPlayer launchSound = new SoundPlayer(shooter.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1F);
            launchSound.playWithin(10);
        }

        loadedPotatoes.clear();
        loadedPotatoesMap.remove(shooter);
    }



}

