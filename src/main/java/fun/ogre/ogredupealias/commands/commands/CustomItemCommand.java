package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.commands.TabComplBuilder;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItems;
import fun.ogre.ogredupealias.utils.StringUtils;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CustomItemCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            String name = args[0];
            CustomItem result = null;

            for (Class<? extends CustomItem> item : CustomItems.getRegistries().keySet()) {
                if (item.getSimpleName().equalsIgnoreCase(name)) {
                    result = item.newInstance();
                    break;
                }
            }

            if (result == null) {
                sender.sendMessage(Text.ofAll("&cItem not found!"));
            }
            else {
                Player player = (Player)sender;
                player.getInventory().addItem(result.getItem());
                sender.sendMessage(Text.ofAll("&dGave one &7" + StringUtils.capitalizeWords(result.getName())));
            }
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex, command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new TabComplBuilder(sender, command, label, args)
                .add(1, CustomItems.getRegistries().keySet().stream().map(item -> item.getSimpleName().toLowerCase()).toList())
                .build();
    }
}
