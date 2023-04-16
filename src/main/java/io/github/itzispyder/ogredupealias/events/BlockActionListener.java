package io.github.itzispyder.ogredupealias.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockActionListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getBlockPlaced();
        final Block f = e.getBlockAgainst();

    }

    public void onBreak(BlockBreakEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getBlock();

    }
}
