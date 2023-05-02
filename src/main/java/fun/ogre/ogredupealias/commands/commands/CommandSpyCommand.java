package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.events.CommandEventListener;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandSpyCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            boolean isRecipient = CommandEventListener.commandSpies.isRecipient(p);
            if (isRecipient) CommandEventListener.commandSpies.removeRecipient(p);
            else CommandEventListener.commandSpies.addRecipient(p);
            isRecipient = CommandEventListener.commandSpies.isRecipient(p);

            sender.sendMessage(Text.builder()
                    .message("&7[&bCommandSpy&7] &8>> &3You are " + (isRecipient ? "&anow" : "&cno longer") + " &3a recipient!")
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
        return new ArrayList<>();
    }
}
