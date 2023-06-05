package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.events.EntityDamageListener;
import fun.ogre.ogredupealias.events.InteractionListener;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ForceFieldCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            boolean isRecipient = InteractionListener.forceFieldProtected.isRecipient(p);
            if (isRecipient) InteractionListener.forceFieldProtected.removeRecipient(p);
            else InteractionListener.forceFieldProtected.addRecipient(p);
            isRecipient = InteractionListener.forceFieldProtected.isRecipient(p);

            sender.sendMessage(Text.builder()
                    .message("&7[&bForceField&7] &8>> &3You are " + (isRecipient ? "&anow" : "&cno longer") + " &3a protected.")
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
