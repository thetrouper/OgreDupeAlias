package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.plugin.ItemPresets;
import io.github.itzispyder.ogredupealias.plugin.custom.forging.CraftingKey;
import io.github.itzispyder.ogredupealias.plugin.custom.forging.CustomTable;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryActionListener implements Listener {

    static {
        CraftingKey.register("netherite_scrap:{}-trident:{}-netherite_scrap:{}-enchanted_golden_apple:{}-player_head:{SkullOwner:{Id:[I;1216354028,1738164382,-1623044432,661086651],Name:\"DeepWarden\",Properties:{textures:[{Signature:\"pXTxO6vKSFkC2d9rWQc0cgSZoNTUsOb1lTiJKr2XwfL6k6XBZifYZramoitpB+NEav3GlVlIcgmC7+dcLIjVQTZAw1G7AyxAVdH2ggk3uArosj+vQlTZU2kGD0JFCrFN74avDlUgdp61E6AaXRgIOvhHs/Oeo/tzjllAvrgqauwxV/hlb/yTy2CRtrF1ooEXFmbti6iwntwfnwrSkzRb5eDvztneGbNQOZ2XYhyEU0lXCDzrrhYYExW8yHOjkqZPR2sd7eOsGRi19UpjQx2F4iX+MWFuiXJmUVrXEwCeggwywsuoQh0DxENwSyQxxGLk6Ck5pU3G19WXKXssN8+D9EdUQpYN58tMS4zO5B/htD0+n43O5ohOv8AgCrsorRiVGMc7wPIeQmgbsdegwiUlI535OHoIVy2Q9QPJhMm4C4kLbh+VbnoCRNzzDboodGeP146izCbd3S1tLv7H1z5vaCoA1tk8dSZeGipky45up6CHyGwrpPY9dOCtqIrAvlbdWNU3NUSDMdj2SqzHkM/7KtIQzOsuJ1/CmbZgFyvVWK4Yo55aatPoOnGI7D96A9VtjqpPC3rfX/Slo9iLVKEnhF6OYvjO+VgiJR5rHvplzGRyFd1cw3Q1n5ZTWUg1pMSd4GIAWeWz4DO6DuXGgdY4qnRSWLqAn5tL64MDLWG1h5Y=\",Value:\"ewogICJ0aW1lc3RhbXAiIDogMTY4MTYyNjMxNTgzOSwKICAicHJvZmlsZUlkIiA6ICI0ODgwMTZlYzY3OWE0ODllOWY0MjRlYjAyNzY3NjFiYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZWVwV2FyZGVuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MDRhYTg3YzlmNGQwNmNhODJkNzk3ZWUyYjk0YjdlMjhjZmQ2MDcxMDI3NmEyODhlMjNmNzkwNjI5NDA0NjAiCiAgICB9CiAgfQp9\"}]}}}-enchanted_golden_apple:{}-netherite_scrap:{}-dragon_head:{}-netherite_scrap:{}", ItemPresets.LEGENDARY_CORE);
        CraftingKey.register("totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-structure_block:{Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"dark_gray\",\"text\":\"- \"},{\"italic\":false,\"color\":\"gray\",\"text\":\"Used for forging\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Legendary Core\"}],\"text\":\"\"}'}}-netherite_ingot:{}-totem_of_undying:{}-netherite_ingot:{}-totem_of_undying:{}", ItemPresets.LEGENDARY_INGOT);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        final String title = e.getView().getTitle();

        try {
            if (inv == null) return;
            if (inv.getType() == InventoryType.PLAYER) return;

            if (title.equals(Text.color("&eForging Table"))) CustomTable.onInventoryAction(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        final String title = e.getView().getTitle();

        try {
            if (title.equals(Text.color("&eForging Table"))) CustomTable.onInventoryClose(e);
        }
        catch (Exception ignore) {}
    }
}
