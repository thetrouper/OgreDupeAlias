package fun.ogre.ogredupealias.plugin;

import fun.ogre.ogredupealias.data.Config;
import fun.ogre.ogredupealias.utils.ArrayUtils;
import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.utils.Text;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

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
        return passChatMuted() && passUnicode() ;
    }

    public boolean passChatMuted() {
        if (!isChatMuted) return true;
        if (player.hasPermission("oda.chat.bypass")) return true;
        player.sendMessage(Text.builder().message("&cChat is currently muted! Please contact an administrator if you believe this is a mistake!").prefix().color().build());
        return false;
    }

    public boolean passRepeat() {
        if (!Config.Chat.AntiRepeat.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.repeat")) return true;

        if (lastMessage.containsKey(player.getName()) && isSimilar(message, lastMessage.get(player.getName()))) {
            player.sendMessage(Text.builder("&cPlease do not repeat similar messages!").prefix().color().build());
            return false;
        }
        lastMessage.put(player.getName(), message);
        return true;
    }

    public boolean passSpam() {
        if (!Config.Chat.AntiSpam.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.spam")) return true;

        if (chatCooldown.containsKey(player.getName()) && chatCooldown.get(player.getName()) > System.currentTimeMillis()) {
            player.sendMessage(Text.builder("&cYou can chat again in &e" + getChatCooldown(player) + "&c seconds!").prefix().color().build());
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
        text.setText(Text.builder("&cPlease do not send unsupported characters in chat!" + "\n&cMessage: &f" + message + "\n&cCaught: &e" + nonAllowed).prefix().color().build());
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(Text.color("&cMessage: &f" + message + "\n&cCaught: &e" + nonAllowed))));
        player.spigot().sendMessage(text);
        return false;
    }

    /**
     * 1: Strip special characters
     * 2: lowercase the text
     * 3: remove the known false positives
     * 4: remove periods and spaces
     * 5: scan for swears
     * 6: notify the player (do not tell them what they flagged, only that they flagged it and that attempts to bypass will result in a mute)
     * 7: notify staff (send the original message and the trimmed string) highlighting the flagged word (listing the keywords violated as a hover text)
     */
    public boolean passSwear() {
        if (!Config.Chat.AntiSwear.enabled()) return true;
        if (player.hasPermission("oda.chat.bypass.swear")) return true;

        // 1
        String msg = fromLeetString(message);
        msg = msg.replaceAll("[^A-Za-z0-9]", "").trim();
        // 2
        msg = msg.toLowerCase();
        // 3
        for (String whitelisted : Config.Chat.AntiSwear.whitelist()) {
            String key = whitelisted.toLowerCase().replaceAll(" ", "");
            if (msg.contains(key)) {
                msg = msg.replaceAll(key, "").trim();
            }
        }
        // 4
        msg = msg.replaceAll("[. _-]", "");
        // 5
        List<String> flags = new ArrayList<>();
        for (String blacklisted : Config.Chat.AntiSwear.blacklist()) {
            String key = blacklisted.toLowerCase().replaceAll(" ", "");
            if (msg.contains(key)) {
                flags.add(blacklisted);
                msg = msg.replaceAll(key, Text.color("&e" + key + "&f"));
            }
        }
        // 6
        if (!flags.isEmpty()) {
            player.sendMessage(Text.ofAll("&cPlease do not swear in chat! Attempting to bypass this filter would result in a mute!"));
            // 7
            String hover = Text.color("&bOriginal: &f" + this.message + "\n&bMessage: &f" + msg + "\n&bFlags: &f" + ArrayUtils.list2string(flags) + "\n&7&o(click to copy)");
            TextComponent text = new TextComponent();
            text.setText(Text.ofAll("&f&n" + player.getName() + "&e has triggered the anti-swear!"));
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hover)));
            text.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, this.message));

            ServerUtils.forEachStaff(staff -> {
                staff.spigot().sendMessage(text);
            });
            return false;
        }

        return true;
    }

    private String removeColors(String msg) {
        String s = msg;
        while (s.length() >= 2 && s.contains("§")) {
            int index = s.indexOf("§");
            s = s.replaceAll(s.substring(index, index + 2), "");
        }
        return s;
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

    public static String fromLeetString(String s) {
        Map<String, String> dictionary = Config.Chat.AntiSwear.leetPatterns();
        String msg = s;

        for (String key : dictionary.keySet()) {
            if (!s.contains(key)) continue;
            try {
                if (key.equals("$")) {
                    msg = msg.replaceAll("\\$", "s");
                }
                else {
                    msg = msg.replaceAll(key, dictionary.get(key));
                }
            }
            catch (PatternSyntaxException ex) {
                String regex = "[" + key + "]";
                msg = msg.replaceAll(regex, dictionary.get(key));
            }
        }
        return msg;
    }
}
