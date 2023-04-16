package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        final LivingEntity ent = e.getEntity();
        final EntityType type = ent.getType();
        final Location loc = ent.getLocation();

        if (type == EntityType.WARDEN) {
            loc.getWorld().dropItemNaturally(loc, ItemUtils.skullOf("DeepWarden"));
        }
    }
}
