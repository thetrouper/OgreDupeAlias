package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.commands.TabComplBuilder;
import io.github.itzispyder.ogredupealias.data.Config;
import io.github.itzispyder.ogredupealias.data.ConfigDataType;
import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.List;

public class ConfigCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            final String path = args[1];
            final ConfigDataType<?> type = ConfigDataType.valueOf(args[2]);

            switch (args[0]) {
                case "set" -> {
                    String value = args[3];
                    Config.get().set(path,ConfigDataType.parse(value,type));
                    Config.save();
                    sender.sendMessage("'" + path + "' has updated: \n" + ConfigDataType.parse(value,type));
                }
                case "get" -> {
                    sender.sendMessage("'" + path + "' has the following data: \n" + ConfigDataType.parseConfig(path,type));
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
                        "set",
                        "get"
                })
                .add(2, Config.getSections())
                .add(3, ArrayUtils.toNewList(ConfigDataType.values(), type -> type.name().toLowerCase()))
                .add(4, new String[] {
                        "<value>"
                },args[0].equals("set"))
                .build();
    }
}
