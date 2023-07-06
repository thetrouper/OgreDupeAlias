package fun.ogre.ogredupealias.plugin.custom.items;

import fun.ogre.ogredupealias.plugin.custom.items.customitems.TazerItem;
import fun.ogre.ogredupealias.utils.ItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class CustomItems implements Listener {

    private static final Map<String, CustomItemInteractionCallback> callbackList = new HashMap<>();

    public static void init() {
        register(new TazerItem());
    }

    public static ItemStack register(ItemStack item, CustomItemInteractionCallback interactionCallback) {
        callbackList.put(ItemUtils.nbtOf(item), interactionCallback);
        return item;
    }

    public static ItemStack register(CustomItem item) {
        item.registerThis();
        return register(item.getItem(), item.getCallback());
    }

    public static void onInteract(ItemStack item, PlayerInteractEvent event) {
        String nbt = ItemUtils.nbtOf(item);
        if (callbackList.containsKey(nbt)) {
            callbackList.get(nbt).handleInteraction(event.getPlayer(), item, event);
        }
    }

    @EventHandler
    private void onPlayerInteraction(PlayerInteractEvent e) {
        onInteract(e.getItem(), e);
    }
}
