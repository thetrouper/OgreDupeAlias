package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        final LivingEntity ent = e.getEntity();
        final EntityType type = ent.getType();
        final Location loc = ent.getLocation();

        if (type == EntityType.WARDEN) {
            e.getDrops().stream().filter(Objects::nonNull).filter(i -> i.getType() == Material.SCULK_CATALYST).forEach(i -> i.setItemMeta(ItemPresets.SCULK_CATALYST.getItemMeta()));
        }
    }
}
