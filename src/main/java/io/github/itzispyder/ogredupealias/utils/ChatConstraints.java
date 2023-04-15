package io.github.itzispyder.ogredupealias.utils;

import io.github.itzispyder.ogredupealias.data.Config;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.*;

public class ChatConstraints {

    private static final Map<String,Long> chatCooldown = new HashMap<>();
    private static final Map<String,String> lastMessage = new HashMap<>();
    private static boolean isChatMuted = false;
    private final Player player;
    private final String message;

    public ChatConstraints(Player player, String message) {
        this.player = player;
        this.message = this.removeColors(message);
    }

    public boolean passAllChecks() {
        return passChatMuted() && passRepeat() && passSwear() && passUnicode() && passSpam();
    }

    public boolean passChatMuted() {
        if (!isChatMuted) return true;
        if (player.hasPermission("oda.chat.bypass")) return true;
        player.sendMessage(Text.builder()
                .message("&cChat is currently muted! Please contact an administrator if you believe this is a mistake!")
                .prefix()
                .color()
                .build());
        return false;
    }

    public boolean passRepeat() {
        if (!Config.Chat.AntiRepeat.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.repeat")) return true;

        if (lastMessage.containsKey(player.getName()) && isSimilar(message, lastMessage.get(player.getName()))) {
            player.sendMessage(Text.builder("&cPlease do not repeat similar messages!")
                    .prefix()
                    .color()
                    .build());
            return false;
        }
        lastMessage.put(player.getName(), message);
        return true;
    }

    public boolean passSpam() {
        if (!Config.Chat.AntiSpam.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.spam")) return true;

        if (chatCooldown.containsKey(player.getName()) && chatCooldown.get(player.getName()) > System.currentTimeMillis()) {
            player.sendMessage(Text.builder("&cYou can chat again in &e" + getChatCooldown(player) + "&c seconds!")
                    .prefix()
                    .color()
                    .build());
            return false;
        }
        int cooldownMillis = (int) (Config.Chat.AntiSpam.cooldown() * 1000);
        chatCooldown.put(player.getName(), System.currentTimeMillis() + cooldownMillis);
        return true;
    }

    public boolean passUnicode() {
        if (!Config.Chat.AntiUnicode.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.unicode")) return true;

        String nonAllowed = message.replaceAll("[A-Za-z0-9\\[,./?><|\\]()*&^%$#@!~`{}:;'\"-_]","").trim();
        if (nonAllowed.length() == 0) return true;

        TextComponent text = new TextComponent();
        text.setText(Text.builder(
                        "&cPlease do not send unsupported characters in chat!"
                                + "\n&cMessage: &f" + message
                                + "\n&cCaught: &e" + nonAllowed
                ).prefix()
                .color()
                .build());
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(Text.color(
                "&cMessage: &f" + message
                        + "\n&cCaught: &e" + nonAllowed
        ))));
        player.spigot().sendMessage(text);
        ServerUtils.forEachStaff(p -> {
            p.spigot().sendMessage(text);
        });
        return false;
    }

    public boolean passSwear() {
        if (!Config.Chat.AntiSwear.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.swear")) return true;

        String s = message.toLowerCase().replaceAll("[^a-z0-9]","");
        String d = s;
        List<String> caught = new ArrayList<>();

        for (String whitelisted : Config.Chat.AntiSwear.whitelist()) {
            s = s.replaceAll(whitelisted.toLowerCase(),"");
        }

        for (String blacklisted : Config.Chat.AntiSwear.blacklist()) {
            if (s.contains(blacklisted.toLowerCase())) {
                caught.add(blacklisted);
                d = d.replaceAll(blacklisted, Text.color("&e" + blacklisted + "&f"));
            }
        }

        if (caught.isEmpty()) return true;

        String warn = ArrayUtils.list2string(caught);
        TextComponent text = new TextComponent();
        text.setText(Text.builder(
                        "&cPlease do not swear in chat!"
                                + "\n&cMessage: &f" + d
                                + "\n&cCaught: &e" + warn
                ).prefix()
                .color()
                .build());
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(Text.color(
                "&cMessage: &f" + d
                        + "\n&cCaught: &e" + warn
        ))));
        player.spigot().sendMessage(text);
        ServerUtils.forEachStaff(p -> {
            p.spigot().sendMessage(text);
        });
        return false;
    }

    private String removeColors(String msg) {
        return String.join(" ", Arrays.stream(msg.split(" "))
                .filter(s -> !(s.length() >= 1 && s.charAt(0) == '§'))
                .toList());
    }

    public double getChatCooldown() {
        return getChatCooldown(player);
    }

    public static double getChatCooldown(Player p) {
        if (!chatCooldown.containsKey(p.getName())) return 0.0;
        if (chatCooldown.get(p.getName()) <= System.currentTimeMillis()) return 0.0;

        return Math.floor((chatCooldown.get(p.getName()) - System.currentTimeMillis()) / 10.0) / 100.0;
    }

    private boolean isSimilar(String s1, String s2) {
        float max = Math.max(s1.length(), s2.length());
        float min = Math.min(s1.length(), s2.length());
        double lengthRatio = min / max;
        if (lengthRatio < 0.6) return false;
        return s1.toLowerCase().contains(s2.toLowerCase()) || s2.toLowerCase().contains(s1.toLowerCase());
    }

    public static void setChatMuted(boolean muted) {
        isChatMuted = muted;
    }

    public static boolean isChatMuted() {
        return isChatMuted;
    }
}
