package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CraftingKey {

    private static final Map<String,ItemStack> REGISTERED_KEYS = new HashMap<>();
    public final String key;

    static {
        CraftingKey.register(ItemPresets.LEGENDARY_CORE,"netherite_scrap:{}-trident:{}-netherite_scrap:{}-enchanted_golden_apple:{}-player_head:{SkullOwner:{Id:[I;1216354028,1738164382,-1623044432,661086651],Name:\"DeepWarden\",Properties:{textures:[{Signature:\"pXTxO6vKSFkC2d9rWQc0cgSZoNTUsOb1lTiJKr2XwfL6k6XBZifYZramoitpB+NEav3GlVlIcgmC7+dcLIjVQTZAw1G7AyxAVdH2ggk3uArosj+vQlTZU2kGD0JFCrFN74avDlUgdp61E6AaXRgIOvhHs/Oeo/tzjllAvrgqauwxV/hlb/yTy2CRtrF1ooEXFmbti6iwntwfnwrSkzRb5eDvztneGbNQOZ2XYhyEU0lXCDzrrhYYExW8yHOjkqZPR2sd7eOsGRi19UpjQx2F4iX+MWFuiXJmUVrXEwCeggwywsuoQh0DxENwSyQxxGLk6Ck5pU3G19WXKXssN8+D9EdUQpYN58tMS4zO5B/htD0+n43O5ohOv8AgCrsorRiVGMc7wPIeQmgbsdegwiUlI535OHoIVy2Q9QPJhMm4C4kLbh+VbnoCRNzzDboodGeP146izCbd3S1tLv7H1z5vaCoA1tk8dSZeGipky45up6CHyGwrpPY9dOCtqIrAvlbdWNU3NUSDMdj2SqzHkM/7KtIQzOsuJ1/CmbZgFyvVWK4Yo55aatPoOnGI7D96A9VtjqpPC3rfX/Slo9iLVKEnhF6OYvjO+VgiJR5rHvplzGRyFd1cw3Q1n5ZTWUg1pMSd4GIAWeWz4DO6DuXGgdY4qnRSWLqAn5tL64MDLWG1h5Y=\",Value:\"ewogICJ0aW1lc3RhbXAiIDogMTY4MTYyNjMxNTgzOSwKICAicHJvZmlsZUlkIiA6ICI0ODgwMTZlYzY3OWE0ODllOWY0MjRlYjAyNzY3NjFiYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZWVwV2FyZGVuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MDRhYTg3YzlmNGQwNmNhODJkNzk3ZWUyYjk0YjdlMjhjZmQ2MDcxMDI3NmEyODhlMjNmNzkwNjI5NDA0NjAiCiAgICB9CiAgfQp9\"}]}}}-enchanted_golden_apple:{}-netherite_scrap:{}-dragon_head:{}-netherite_scrap:{}");
        CraftingKey.register(ItemPresets.LEGENDARY_INGOT,"totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-structure_block:{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Core\"}],\"text\":\"\"}'}}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}");
    }

    public static void register(ItemStack result, CraftingKey key) {
        REGISTERED_KEYS.put(key.getKey(),result);
    }

    public static void register(ItemStack result, String key) {
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
