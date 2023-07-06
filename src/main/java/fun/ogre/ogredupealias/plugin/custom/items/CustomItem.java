package fun.ogre.ogredupealias.plugin.custom.items;

import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {

    private final ItemStack item;

    public CustomItem(ItemStack item) {
        this.item = item;
    }

    public abstract CustomItemInteractionCallback getCallback();

    public void registerThis() {
        ItemStack item = getItem();
        CustomItemInteractionCallback callback = getCallback();

        if (item != null && callback != null) {
            CustomItems.register(item, callback);
        }
    }

    public ItemStack getItem() {
        return item;
    }
}
