package io.github.itzispyder.ogredupealias.plugin.custom.forging;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class CraftingKeys {

    private static final Map<String,ItemStack> REGISTERED_KEYS = new HashMap<>();

    public static void initRecipes() {
        register(ItemPresets.LEGENDARY_CORE,"netherite_scrap:{}-trident:{}-netherite_scrap:{}-enchanted_golden_apple:{}-player_head:{SkullOwner:{Id:[I;1216354028,1738164382,-1623044432,661086651],Name:\"DeepWarden\",Properties:{textures:[{Signature:\"pXTxO6vKSFkC2d9rWQc0cgSZoNTUsOb1lTiJKr2XwfL6k6XBZifYZramoitpB+NEav3GlVlIcgmC7+dcLIjVQTZAw1G7AyxAVdH2ggk3uArosj+vQlTZU2kGD0JFCrFN74avDlUgdp61E6AaXRgIOvhHs/Oeo/tzjllAvrgqauwxV/hlb/yTy2CRtrF1ooEXFmbti6iwntwfnwrSkzRb5eDvztneGbNQOZ2XYhyEU0lXCDzrrhYYExW8yHOjkqZPR2sd7eOsGRi19UpjQx2F4iX+MWFuiXJmUVrXEwCeggwywsuoQh0DxENwSyQxxGLk6Ck5pU3G19WXKXssN8+D9EdUQpYN58tMS4zO5B/htD0+n43O5ohOv8AgCrsorRiVGMc7wPIeQmgbsdegwiUlI535OHoIVy2Q9QPJhMm4C4kLbh+VbnoCRNzzDboodGeP146izCbd3S1tLv7H1z5vaCoA1tk8dSZeGipky45up6CHyGwrpPY9dOCtqIrAvlbdWNU3NUSDMdj2SqzHkM/7KtIQzOsuJ1/CmbZgFyvVWK4Yo55aatPoOnGI7D96A9VtjqpPC3rfX/Slo9iLVKEnhF6OYvjO+VgiJR5rHvplzGRyFd1cw3Q1n5ZTWUg1pMSd4GIAWeWz4DO6DuXGgdY4qnRSWLqAn5tL64MDLWG1h5Y=\",Value:\"ewogICJ0aW1lc3RhbXAiIDogMTY4MTYyNjMxNTgzOSwKICAicHJvZmlsZUlkIiA6ICI0ODgwMTZlYzY3OWE0ODllOWY0MjRlYjAyNzY3NjFiYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZWVwV2FyZGVuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MDRhYTg3YzlmNGQwNmNhODJkNzk3ZWUyYjk0YjdlMjhjZmQ2MDcxMDI3NmEyODhlMjNmNzkwNjI5NDA0NjAiCiAgICB9CiAgfQp9\"}]}}}-enchanted_golden_apple:{}-netherite_scrap:{}-dragon_head:{}-netherite_scrap:{}");
        register(ItemPresets.LEGENDARY_CORE,"netherite_scrap:{}-trident:{}-netherite_scrap:{}-enchanted_golden_apple:{}-player_head:{SkullOwner:{Id:[I;1216354028,1738164382,-1623044432,661086651],Name:\"DeepWarden\",Properties:{textures:[{Signature:\"DjztLlK1EwqCe+I14DWq9GLO2wfykaWl0RR//OT/Bt0mZoTgooVS61hrqM5Xkd6aXDxlDe9th9dhU1qD4PnFKKTQ/NrV8Ma09XcV56KcfaonVwtJ1D/jN02rTqPDF5Hk08yFEw/aBeU7QHtQJl86YyUdW7Yq+yJEAsa+CLr5HgKniGZGehENpx/jwEBWVxJOjIBTr5GXaEeiPpJ7STz3Qnswt+NNmppNhBoYChp+64jgnn/EtIxSuyNtJ/qghrvNMjrcTb1hMjgVcWB4XuEOkjPSSdAnKzwRRdTTpzx7qkb80SdKi1j9UYctyzbS/mqDQ5VQjbbRUhZXmUxq9c/2vBbAX9ftbyV37nzqoG+2x1TMs33ZRRPbuK9TJixLK2FpOk/XB9pQyH4VBcLn+UD8AnB/u5f9G3VvPZXNs4YC3Oi9bvGwbyaLzNeoi9F0nNbM+k3TL4c2TVj8pz+oAx7kogIAfRfSravST7GTAFNRhsF+xD8hGZYg4h6xsibVK7I2smoNdeatTbIZH0Q+QqIIjua6fdbFIEvAU/pltDoxDeP+fcJ+JA7+e9VH/o7xIhKHYtiojUHJiSICW8WM7sGKd4/Pc/OC5werAlHtNGc70Nip2wdzKWgRr8ozCNPanp4yqVwzUigT47wiH5xvC8m1X8Vm3b8OkwXT5ec+VIcIrR4=\",Value:\"ewogICJ0aW1lc3RhbXAiIDogMTY4MTY1OTU2MjQxNiwKICAicHJvZmlsZUlkIiA6ICI0ODgwMTZlYzY3OWE0ODllOWY0MjRlYjAyNzY3NjFiYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZWVwV2FyZGVuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MDRhYTg3YzlmNGQwNmNhODJkNzk3ZWUyYjk0YjdlMjhjZmQ2MDcxMDI3NmEyODhlMjNmNzkwNjI5NDA0NjAiCiAgICB9CiAgfQp9\"}]}}}-enchanted_golden_apple:{}-netherite_scrap:{}-dragon_head:{}-netherite_scrap:{}");
        register(ItemPresets.LEGENDARY_INGOT,"totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-structure_block:{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Core\"}],\"text\":\"\"}'}}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}");
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
}
