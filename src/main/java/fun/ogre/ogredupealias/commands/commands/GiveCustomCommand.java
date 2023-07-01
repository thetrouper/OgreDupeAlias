package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.commands.TabComplBuilder;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveCustomCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage(Text.builder("&cYou must specify an item to give.").prefix().color().build());
                return true;
            }

            switch (args[0]) {
                case "AdminUtility" -> {
                    p.getInventory().addItem(ItemPresets.ADMIN_UTILITY);
                    p.sendMessage(Text.ofAll("&bGiven you an Admin Utility"));
                }
                case "AK47" -> {
                    p.getInventory().addItem(ItemPresets.AK47);
                    p.sendMessage(Text.ofAll("&bGiven you an AK-47"));
                }
                case "Defender" -> {
                    p.getInventory().addItem(ItemPresets.DEFENDER);
                    p.sendMessage(Text.ofAll("&bGiven you a Defender"));
                }
                case "NetSkyBlade" -> {
                    p.getInventory().addItem(ItemPresets.NETSKY_BLADE);
                    p.sendMessage(Text.ofAll("&bGiven you a Netsky Blade"));
                }
                case "SnowChinegun" -> {
                    p.getInventory().addItem(ItemPresets.SNOWCHINEGUN);
                    p.sendMessage(Text.ofAll("&bGiven you an SnowChinegun"));
                }
                case "VoidCharm" -> {
                    p.getInventory().addItem(ItemPresets.VOID_CHARM);
                    p.sendMessage(Text.ofAll("&bGiven you a Void Charm"));
                }
                case "Spleefer" -> {
                    p.getInventory().addItem(ItemPresets.SPLEEFER);
                    p.sendMessage(Text.ofAll("&bGiven you a Spleefer"));
                }
                case "PotatoCannon" -> {
                    p.getInventory().addItem(ItemPresets.POTATOCANNON);
                    p.sendMessage(Text.ofAll("&bGiven you a potato cannon"));
                }
                case "LaserPointer" -> {
                    p.getInventory().addItem(ItemPresets.LASER_POINTER);
                    p.sendMessage(Text.ofAll("&bGiven you a Laser Pointer"));
                }
                case "Pickler" -> {
                    p.getInventory().addItem(ItemPresets.PICKLER);
                    p.sendMessage(Text.ofAll("&bGiven you a Pickler"));
                }
            }
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex,command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new TabComplBuilder(sender, command, alias, args)
                .add(1, new String[] {
                        "AdminUtility",
                        "AK47",
                        "Defender",
                        "NetSkyBlade",
                        "SnowChinegun",
                        "VoidCharm",
                        "Spleefer",
                        "PotatoCannon",
                        "LaserPointer"
                })
                .build();
    }
}
