package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.events.CommandEventListener;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SocialSpyCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            boolean isRecipient = CommandEventListener.socialSpies.isRecipient(p);
            if (isRecipient) CommandEventListener.socialSpies.removeRecipient(p);
            else CommandEventListener.socialSpies.addRecipient(p);
            isRecipient = CommandEventListener.socialSpies.isRecipient(p);

            sender.sendMessage(Text.builder()
                    .message("&7[&bSocialSpy&7] &8>> &3You are " + (isRecipient ? "&anow" : "&cno longer") + " &3a recipient!")
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
