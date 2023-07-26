package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.utils.ArrayUtils;
import fun.ogre.ogredupealias.utils.ImageUtils;
import fun.ogre.ogredupealias.utils.Text;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowDonationCommand implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            String name = args[0];
            String amount = args[1];
            String price = args[2];
            String item = ArrayUtils.joinFrom(3,args,s -> s);
            OfflinePlayer p = Bukkit.getOfflinePlayer(name);
            List<String> imageLines = ImageUtils.imageToList("https://crafatar.com/avatars/" + p.getUniqueId() + "?size=8&overlay");
            imageLines.set(2,imageLines.get(2) + " §b§k... §7DONATION ALERT §b§k...");
            imageLines.set(3,imageLines.get(3) + " §7Thank you, §b§n" + name + "§7!");
            imageLines.set(4,imageLines.get(4) + " §7" + amount + "x " + Text.color(item) + "§7 for §2$" + price);
            imageLines.set(5,imageLines.get(5) + " §7Every donation helps!");
            imageLines.set(7,imageLines.get(7) + " §8§m==========================");
            imageLines.set(0,imageLines.get(0) + " §8§m==========================");
            Bukkit.broadcastMessage("\n");
            Bukkit.broadcastMessage(String.join("\n", imageLines));
            Bukkit.broadcastMessage("\n");
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
