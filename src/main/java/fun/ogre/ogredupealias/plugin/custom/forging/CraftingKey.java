package fun.ogre.ogredupealias.plugin.custom.forging;

import fun.ogre.ogredupealias.utils.ArrayUtils;
import fun.ogre.ogredupealias.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CraftingKey {

    public final String key;

    public CraftingKey(Iterable<ItemStack> input) {
        this.key = "[" + String.join(", ", ArrayUtils.toNewList(input, ItemUtils::nbtOf)) + "]";
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
