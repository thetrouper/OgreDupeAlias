package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.commands.TabComplBuilder;
import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MessageCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            final Player messenger = (Player) sender;
            final Player recipient = Bukkit.getPlayer(args[0]);

            args[0] = null;
            final String msg = String.join(" ", Arrays.stream(args).filter(Objects::nonNull).toList());

            messenger.sendMessage(Text.builder("&7[&bPM&7] &3me &7to &3" + recipient.getName() + " &8>> &b&o" + msg).color().prefix().build());
            recipient.sendMessage(Text.builder("&7[&bPM&7] &3" + messenger.getName() + " &7to &3me &8>> &b&o" + msg).color().prefix().build());
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex,command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new TabComplBuilder(sender,command,label,args)
                .add(1, ArrayUtils.toNewList(Bukkit.getOnlinePlayers(), Player::getName))
                .add(2, new String[]{
                        "[<message>]"
                },args.length >= 2 && args[1].isBlank())
                .build();
    }
}
