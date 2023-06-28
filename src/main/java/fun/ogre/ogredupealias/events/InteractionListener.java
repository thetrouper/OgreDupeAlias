package fun.ogre.ogredupealias.events;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.data.PlacedStructures;
import fun.ogre.ogredupealias.plugin.InventoryPresets;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.RecipientList;
import fun.ogre.ogredupealias.plugin.funitems.*;
import fun.ogre.ogredupealias.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.beans.MethodDescriptor;
import java.beans.beancontext.BeanContext;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            this.processTable(e);
            NetSkyBlade.handleNetskyBlade(e);
            Defender.handleDefender(e);
            AdminUtility.handleAdminUtility(e);
            VoidCharm.handleVoidCharm(e);
            SnowChinegun.handleSnowChinegun(e);
            AK47.handleAK47(e);
            PotatoCannon.handlePotatoCannon(e);
            PlayerEventListener.handleEnderPearls(e);
            SPBItems.handleRifle(e);
        }
        catch (Exception ignore) {}
    }


    private void processTable(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();

        if (PlacedStructures.isCustomTable(b)) {
            e.setCancelled(true);
            p.openInventory(InventoryPresets.createCustomTable());
        }
    }
}
