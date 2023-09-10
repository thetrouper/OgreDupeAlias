package fun.ogre.ogredupealias.plugin.custom.items;

import fun.ogre.ogredupealias.utils.ItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class CustomItems implements Listener {

    private static final Map<String, CustomItemInteractionCallback> callbackList = new HashMap<>();
    private static final Map<Class<? extends CustomItem>, String> namesList = new HashMap<>();

    public static void init() {
        //register(new TazerItem());
        //register(new LazerItem());
        //register(new RailgunItem());
        //register(new LazerGunItem());
    }

    public static ItemStack register(ItemStack item, CustomItemInteractionCallback interactionCallback) {
        callbackList.put(ItemUtils.nbtOf(item), interactionCallback);
        return item;
    }

    public static ItemStack register(CustomItem item) {
        item.registerThis();
        namesList.put(item.getClass(), item.getName());
        return item.getItem();
    }

    public static Map<Class<? extends CustomItem>, String> getRegistries() {
        return new HashMap<>(namesList);
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
