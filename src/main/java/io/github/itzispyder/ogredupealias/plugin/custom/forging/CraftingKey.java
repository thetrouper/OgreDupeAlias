package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CraftingKey {

    private static final Map<String,ItemStack> REGISTERED_KEYS = new HashMap<>();
    public final String key;

    public static void register(CraftingKey key, ItemStack result) {
        REGISTERED_KEYS.put(key.getKey(),result);
    }

    public static void register(String key, ItemStack result) {
        REGISTERED_KEYS.put(key,result);
    }

    public static ItemStack getResult(CraftingKey key) {
        ItemStack result = REGISTERED_KEYS.get(key.getKey());
        return result != null ? result : new ItemStack(Material.AIR);
    }

    public CraftingKey(Iterable<ItemStack> input) {
        this.key = String.join("-", ArrayUtils.toNewList(input,this::keyOfStack));
    }

    private String keyOfStack(ItemStack item) {
        return item != null && item.getItemMeta() != null ? item.getType().name().toLowerCase() + ":" + item.getItemMeta().getAsString() : "air:{}";
    }

    public CraftingKey(ItemStack[] input) {
        this(Arrays.stream(input).toList());
    }

    public CraftingKey(Material[] input) {
        this(ArrayUtils.toNewList(Arrays.stream(input).toList(),ItemStack::new));
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CraftingKey comparing) {
            return comparing.getKey().equals(key);
        }
        return false;
    }
}
