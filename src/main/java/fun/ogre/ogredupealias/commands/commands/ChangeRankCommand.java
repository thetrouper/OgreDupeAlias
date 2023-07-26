package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.plugin.custom.gui.CustomGUIs.RankChangeGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChangeRankCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            if (p.hasPermission("oda.changerank.use")) {
                p.openInventory(RankChangeGUI.RANKS.getInventory());
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
        return new ArrayList<>();
    }
}
