package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import io.github.itzispyder.ogredupealias.plugin.RecipientList;
import io.github.itzispyder.ogredupealias.utils.ItemUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageListener implements Listener {

    public static final RecipientList attackCooldownBypassers = new RecipientList();
    public static final int DEFAULT_NO_DAMAGE_TICKS = 9;
    public static final int DEFAULT_MAX_NO_DAMAGE_TICKS = 10;
    public static final int NO_DAMAGE_TICKS = 1;
    public static final int MAX_NO_DAMAGE_TICKS = 2;

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            this.handleBurstMelee(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        try {
            this.handleBurstMelee(e);
        }
        catch (Exception ignore) {}
    }

    public void handleBurstMelee(EntityDamageEvent e) {
        final Entity ent = e.getEntity();
        final EntityDamageEvent.DamageCause cause = e.getCause();

        if (cause.name().contains("ENTITY")) return;
        if (ent instanceof LivingEntity eLiving) {
            eLiving.setNoDamageTicks(14);
            eLiving.setMaximumNoDamageTicks(15);
        }
    }

    public void handleBurstMelee(EntityDamageByEntityEvent e) {
        final Entity victim = e.getEntity();
        final Entity damager = e.getDamager();
        final double dist = victim.getLocation().distance(damager.getLocation());

        if (damager instanceof Player pDamager && victim instanceof LivingEntity vLiving) {
            if (dist > 3.5) return;
            if (pDamager.isDead() || victim.isDead()) return;

            final ItemStack item = pDamager.getInventory().getItemInMainHand();

            if (ItemUtils.nbtMatches(item, ItemPresets.TROLL_SWORD) || attackCooldownBypassers.isRecipient(pDamager)) {
                vLiving.setNoDamageTicks(NO_DAMAGE_TICKS);
                vLiving.setMaximumNoDamageTicks(MAX_NO_DAMAGE_TICKS);
            }
            else {
                vLiving.setNoDamageTicks(DEFAULT_NO_DAMAGE_TICKS);
                vLiving.setMaximumNoDamageTicks(DEFAULT_MAX_NO_DAMAGE_TICKS);
            }
        }
    }
}
