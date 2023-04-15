package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.commands.TabComplBuilder;
import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import io.github.itzispyder.ogredupealias.utils.ServerUtils;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffChatCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            String msg = String.join(" ", args);
            ServerUtils.dmEachPlayer(p -> p.hasPermission("oda.commands.staffchat"), Text.builder()
                    .message("&7[&bStaffChat&7] &8>> &e" + msg)
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
                .add(1, ArrayUtils.toNewList(Bukkit.getOnlinePlayers(), Player::getName))
                .build();
    }
}
