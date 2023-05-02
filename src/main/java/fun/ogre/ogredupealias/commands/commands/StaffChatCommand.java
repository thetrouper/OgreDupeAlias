package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.commands.TabComplBuilder;
import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class StaffChatCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            String msg = String.join(" ", args);
            ServerUtils.dmEachPlayer(p -> p.hasPermission("oda.commands.staffchat"), Text.builder()
                    .message("&7[&bStaffChat&7] &3" + sender.getName() + " &8>> &e" + msg)
                    .color()
                    .prefix()
                    .build());
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
                .add(1, new String[]{
                        "[<message>]"
                },args[0].length() == 0)
                .build();
    }
}
