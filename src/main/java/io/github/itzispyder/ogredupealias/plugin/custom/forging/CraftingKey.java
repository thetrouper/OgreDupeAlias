package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CraftingKey {

    public final String key;

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
