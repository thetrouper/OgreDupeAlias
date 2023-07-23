package fun.ogre.ogredupealias.plugin.custom.gui;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class CustomGuis {

    public static final CustomGui EXAMPLE = CustomGui.create()
            .title("Example GUI") // Gui title
            .size(27) // Gui size
            .onDefine(inv -> {
                // Task to run upon the creation of a new inventory from this gui
            })
            .defineMain(event -> {
                // Define the task to run when the inventory is clicked
                event.setCancelled(true);
            })
            .define(/* slot number */ 12, /* item display */ new ItemStack(Material.DIRT)) // Clicking this will have no reaction
            .define(/* slot number */ 14, /* item display */ new ItemStack(Material.DIAMOND), /* on click */ event -> {
                event.getWhoClicked().getInventory().addItem(event.getCurrentItem());
                event.getWhoClicked().sendMessage("Gave You a Diamond!");
            }) // <- Clicking this will have reaction
            .onClose(event -> {
                // Task to run when the inventory is closed.
                event.getPlayer().sendMessage("You've closed " + event.getView().getTitle());
            })
            .build(); // Completes the build, returns a CustomGUI!!

    public static final CustomGui RANKS = CustomGui.create()
            .title(Text.color("&8| &b&lRank Customization &8|"))
            .size(27)
            .onDefine(inv -> {
                ItemStack fill = ItemPresets.BLANK;
                while (inv.firstEmpty() != -1) {
                    inv.setItem(inv.firstEmpty(), fill);
                }
            })
            .defineMain(event -> event.setCancelled(true))
            .define(16, ItemPresets.RANK_CONFIRM_BUTTON, event -> {
                switch (event.getCurrentItem().getType()) {
                    case MAGENTA_WOOL -> ServerUtils.dispatch("say king");
                    case RED_WOOL -> ServerUtils.dispatch("say queen");
                    case ORANGE_WOOL -> ServerUtils.dispatch("say rook");
                    case LIME_WOOL -> ServerUtils.dispatch("say bishop");
                    case LIGHT_BLUE_WOOL -> ServerUtils.dispatch("say knight");
                }
                event.getWhoClicked().closeInventory();
            })
            .define(10, ItemPresets.RANK_KING, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0.2F);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(11, ItemPresets.RANK_QUEEN, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0.4F);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(12, ItemPresets.RANK_ROOK, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0.6F);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(13, ItemPresets.RANK_BISHOP, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0.8F);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(14, ItemPresets.RANK_KNIGHT, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1.0F);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .build();

}
