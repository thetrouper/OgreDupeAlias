package io.github.itzispyder.ogredupealias.events;

import io.github.itzispyder.ogredupealias.data.Config;
import io.github.itzispyder.ogredupealias.plugin.RecipientList;
import io.github.itzispyder.ogredupealias.utils.Text;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandEventListener implements Listener {

    public static final RecipientList commandSpies = new RecipientList();
    public static final RecipientList socialSpies = new RecipientList();
    private static final List<String> socialCommands = Arrays.asList(
            "/tell",
            "/whisper",
            "/w",
            "/message",
            "/msg"
    );

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        final String msg = e.getMessage();
        final Player p = e.getPlayer();
        final String[] words = msg.split(" ");
        final String cmd = msg.split(" ")[0];
        final String body = words.length >= 2 ? String.join(" ", new ArrayList<>(Arrays.stream(words).toList()).subList(1, words.length)) : Text.color("&c<no-args>");

        if (socialCommands.contains(cmd.toLowerCase())) {
            if (words.length <= 2) return;
            final Player to = Bukkit.getPlayer(words[1]);
            final String context = String.join(" ", new ArrayList<>(Arrays.stream(words).toList()).subList(2, words.length));
            if (to == null || !to.isOnline()) return;

            TextComponent text = new TextComponent();
            text.setText(Text.builder("&7[&bSocialSpy&7] &8>> &3" + p.getName() + " &7messaged &3" + to.getName()).color().prefix().build());
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(Text.color("&b" + context + "\n&8(click to copy)"))));
            text.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, context));

            socialSpies.forEachRecipient(player -> {
                player.spigot().sendMessage(text);
            });
        }
        else if (!Config.Server.commandSpyBlacklist().contains(cmd.toLowerCase())) {
            TextComponent text = new TextComponent();
            text.setText(Text.builder("&7[&bCommandSpy&7] &8>> &3" + p.getName() + " &7used &6" + cmd).color().prefix().build());
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(Text.color("&6" + cmd + " &e" + body + "\n&8(click to copy)"))));
            text.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, msg));

            commandSpies.forEachRecipient(player -> {
                player.spigot().sendMessage(text);
            });
        }
    }
}
