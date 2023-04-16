package io.github.itzispyder.ogredupealias.commands.commands;

import io.github.itzispyder.ogredupealias.commands.CmdExHandler;
import io.github.itzispyder.ogredupealias.commands.TabComplBuilder;
import io.github.itzispyder.ogredupealias.utils.ArrayUtils;
import io.github.itzispyder.ogredupealias.utils.StringUtils;
import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IRepairCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;

            if (args.length == 0) {
                Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).forEach(this::repair);
                sender.sendMessage(Text.builder("&3Repaired and restocked your inventory.").prefix().color().build());
                return true;
            }

            switch (args[0]) {
                case "all" -> {
                    Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).forEach(this::repair);
                    sender.sendMessage(Text.builder("&3Repaired and restocked your inventory.").prefix().color().build());
                }
                case "hand" -> {
                    ItemStack hand = p.getInventory().getItemInMainHand();
                    if (hand.getType().isAir()) {
                        sender.sendMessage(Text.builder("&cCannot repair air.").prefix().color().build());
                        return true;
                    }
                    repair(hand);
                    sender.sendMessage(Text.builder("&3Repaired and restocked your hand item.").prefix().color().build());
                }
                case "fillrest" -> {
                    Material type = Material.valueOf(args[1].toUpperCase());
                    ItemStack item = new ItemStack(type, type.getMaxStackSize());
                    Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).forEach(this::repair);
                    while (p.getInventory().firstEmpty() != -1) p.getInventory().setItem(p.getInventory().firstEmpty(),item);
                    sender.sendMessage(Text.builder("&3Repaired and restocked your inventory, filled the rest with &7" + StringUtils.capitalizeWords(type.name()) + "&b.").prefix().color().build());
                }
                case "only" -> {
                    Material type = Material.valueOf(args[1].toUpperCase());
                    Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).filter(item -> item.getType() == type).forEach(this::repair);
                    sender.sendMessage(Text.builder("&3Repaired and restocked your &7" + StringUtils.capitalizeWords(type.name()) + "&b.").prefix().color().build());
                }
                case "exclude" -> {
                    Material type = Material.valueOf(args[1].toUpperCase());
                    Arrays.stream(p.getInventory().getContents()).filter(Objects::nonNull).filter(item -> item.getType() != type).forEach(this::repair);
                    sender.sendMessage(Text.builder("&3Repaired and restocked everything but your &7" + StringUtils.capitalizeWords(type.name()) + "&b.").prefix().color().build());
                }
            }
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex,command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void repair(ItemStack item) {
        item.setDurability(new ItemStack(item.getType()).getDurability());
        item.setAmount(item.getMaxStackSize());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new TabComplBuilder(sender, command, alias, args)
                .add(1, new String[] {
                        "fillrest",
                        "exclude",
                        "only",
                        "hand",
                        "all"
                })
                .add(2, ArrayUtils.Constants.MATERIAL_NAMES, !args[0].equals("all") && !args[0].equals("hand"))
                .build();
    }
}
