package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import io.github.itzispyder.ogredupealias.plugin.RecipientList;
import io.github.itzispyder.ogredupealias.utils.ItemUtils;
import io.github.itzispyder.ogredupealias.utils.SoundPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageListener implements Listener {

    public static final RecipientList attackCooldownBypassers = new RecipientList();

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        this.processPenisSword(e);
    }

    public void processPenisSword(EntityDamageByEntityEvent e) {
        final Entity victim = e.getEntity();
        final Entity damager = e.getDamager();

        if (damager instanceof Player pDamager && victim instanceof LivingEntity vLiving) {
            final ItemStack item = pDamager.getInventory().getItemInMainHand();
            final SoundPlayer sticky = new SoundPlayer(vLiving.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 10);

            if (ItemUtils.nbtMatches(item, ItemPresets.TROLL_SWORD) || attackCooldownBypassers.isRecipient(pDamager)) {
                vLiving.setNoDamageTicks(1);
                vLiving.setMaximumNoDamageTicks(2);
                sticky.playWithinAt(100);
            }
            else {
                vLiving.setNoDamageTicks(19);
                vLiving.setMaximumNoDamageTicks(20);
            }
        }
    }
}
