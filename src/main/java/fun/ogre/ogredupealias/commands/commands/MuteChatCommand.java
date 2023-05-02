package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.commands.TabComplBuilder;
import fun.ogre.ogredupealias.plugin.ChatConstraints;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class MuteChatCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (args.length == 0) ChatConstraints.setChatMuted(!ChatConstraints.isChatMuted());
            else ChatConstraints.setChatMuted(Boolean.parseBoolean(args[0]));

            boolean muted = ChatConstraints.isChatMuted();
            sender.sendMessage(Text.builder("&3Chat is now " + (muted ? "&cmuted" : "&aunmuted") + "&3!")
                    .prefix()
                    .color()
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
                .add(1, new String[] {
                        "true",
                        "false"
                })
                .build();
    }
}
