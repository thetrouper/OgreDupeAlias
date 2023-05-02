package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.events.EntityDamageListener;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AttackCooldownCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            boolean isRecipient = EntityDamageListener.attackCooldownBypassers.isRecipient(p);
            if (isRecipient) EntityDamageListener.attackCooldownBypassers.removeRecipient(p);
            else EntityDamageListener.attackCooldownBypassers.addRecipient(p);
            isRecipient = EntityDamageListener.attackCooldownBypassers.isRecipient(p);

            sender.sendMessage(Text.builder()
                    .message("&7[&bAttackCooldown&7] &8>> &3You are " + (isRecipient ? "&anow" : "&cno longer") + " &3a an attack cooldown bypasser!")
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
