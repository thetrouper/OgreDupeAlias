package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IRepairCommand implements TabExecutor {

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).forEach(item -> {
                item.setDurability(new ItemStack(item.getType()).getDurability());
                item.setAmount(item.getMaxStackSize());
            });
            sender.sendMessage(Text.builder("&3Repaired and restocked your inventory.").prefix().color().build());
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
