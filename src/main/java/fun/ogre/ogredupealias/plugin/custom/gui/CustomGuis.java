package fun.ogre.ogredupealias.plugin.custom.gui;

import org.bukkit.Material;
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


}
