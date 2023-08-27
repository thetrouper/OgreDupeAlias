package fun.ogre.ogredupealias.events;
import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.data.Config;
import fun.ogre.ogredupealias.data.Emojis;
import  fun.ogre.ogredupealias.utils.Webhook;
import litebans.api.Entry;
import litebans.api.Events;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

public class LiteBansEvents extends Events.Listener {
    public static void registerEvents() {
        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                if (entry.getType().equals("ban")) {
                    if (entry.getExecutorUUID() == null) return;
                    if (entry.getUuid() == null) return;
                    UUID target = UUID.fromString(entry.getUuid());
                    UUID executor = UUID.fromString(entry.getExecutorUUID());
                    sendBanLog(target,executor,entry.getReason(),entry.getDurationString());
                }
                if (entry.getType().equals("mute")) {
                    if (entry.getExecutorUUID() == null) return;
                    if (entry.getUuid() == null) return;
                    UUID target = UUID.fromString(entry.getUuid());
                    UUID executor = UUID.fromString(entry.getExecutorUUID());
                    sendMuteLog(target,executor,entry.getReason(),entry.getDurationString());
                }
            }
            @Override
            public void entryRemoved(Entry entry) {
                if (entry.getType().equals("ban")) {
                    if (entry.getExecutorUUID() == null) return;
                    if (entry.getUuid() == null) return;
                    UUID target = UUID.fromString(entry.getUuid());
                    UUID executor = UUID.fromString(entry.getExecutorUUID());
                    sendUnbanLog(target,executor,entry.getRemovalReason());
                }
                if (entry.getType().equals("mute")) {
                    if (entry.getExecutorUUID() == null) return;
                    if (entry.getUuid() == null) return;
                    UUID target = UUID.fromString(entry.getUuid());
                    UUID executor = UUID.fromString(entry.getExecutorUUID());
                    sendUnmuteLog(target,executor,entry.getRemovalReason());
                }
            }
        });
    }
    public static void sendBanLog(UUID target, UUID executerUUID, String reason, String duration) {
        String name = Bukkit.getOfflinePlayer(target).getName();
        String executor = Bukkit.getOfflinePlayer(executerUUID).getName();
        Webhook webhook = new Webhook(Config.Plugin.Webhooks.banWebhook);
        webhook.setAvatarUrl("https://r2.e-z.host/d440b58a-ba90-4839-8df6-8bba298cf817/3lwit5nt.png");
        webhook.setUsername("Staff Logs");
        Webhook.EmbedObject embed = new Webhook.EmbedObject()
                .setAuthor("Staff Punishment","","")
                .setTitle("Staff have banned a member")
                .setDescription(
                        Emojis.rightSort + "Username: `" + name + "` " + Emojis.target + "\\n" +
                                Emojis.space + Emojis.arrowRight + "UUID: `" + target + "`\\n"
                )
                .addField("Banned By: ", "`" + executor + "` " + Emojis.trustedAdmin, false)
                .addField("Reason: ", reason + " " + Emojis.activity, false)
                .addField("Duration: ", duration, false)
                .setColor(Color.red)
                .setThumbnail("https://crafatar.com/avatars/" + target + "?size=64&&overlay");
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException e) {
            OgreDupeAlias.log.info(e.toString());
        }
    }

    public static void sendMuteLog(UUID target, UUID executerUUID, String reason, String duration) {
        String name = Bukkit.getOfflinePlayer(target).getName();
        String executor = Bukkit.getOfflinePlayer(executerUUID).getName();
        Webhook webhook = new Webhook(Config.Plugin.Webhooks.muteWebhook);
        webhook.setAvatarUrl("https://r2.e-z.host/d440b58a-ba90-4839-8df6-8bba298cf817/3lwit5nt.png");
        webhook.setUsername("Staff Logs");
        Webhook.EmbedObject embed = new Webhook.EmbedObject()
                .setAuthor("Staff Punishment","","")
                .setTitle("Staff have Muted a member")
                .setDescription(
                        Emojis.rightSort + "Username: `" + name + "` " + Emojis.target + "\\n" +
                                Emojis.space + Emojis.arrowRight + "UUID: `" + target + "`\\n"
                )
                .addField("Muted By: ", "`" + executor + "` " + Emojis.trustedAdmin, false)
                .addField("Reason: ", reason + " " + Emojis.activity, false)
                .addField("Duration: ", duration, false)
                .setColor(Color.yellow)
                .setThumbnail("https://crafatar.com/avatars/" + target + "?size=64&&overlay");
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException e) {
            OgreDupeAlias.log.info(e.toString());
        }
    }
    public static void sendUnbanLog(UUID target, UUID executerUUID, String reason) {
        String name = Bukkit.getOfflinePlayer(target).getName();
        String executor = Bukkit.getOfflinePlayer(executerUUID).getName();
        Webhook webhook = new Webhook(Config.Plugin.Webhooks.unbanWebhook);
        webhook.setAvatarUrl("https://r2.e-z.host/d440b58a-ba90-4839-8df6-8bba298cf817/3lwit5nt.png");
        webhook.setUsername("Staff Logs");
        Webhook.EmbedObject embed = new Webhook.EmbedObject()
                .setAuthor("Staff Pardon","","")
                .setTitle("Staff have Unbanned a member")
                .setDescription(
                        Emojis.rightSort + "Username: `" + name + "` " + Emojis.target + "\\n" +
                                Emojis.space + Emojis.arrowRight + "UUID: `" + target + "`\\n"
                )
                .addField("Unbanned By: ", "`" + executor + "` " + Emojis.trustedAdmin, false)
                .addField("Reason: ", reason + " " + Emojis.activity, false)
                .setColor(Color.green)
                .setThumbnail("https://crafatar.com/avatars/" + target + "?size=64&&overlay");
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException e) {
            OgreDupeAlias.log.info(e.toString());
        }
    }
    public static void sendUnmuteLog(UUID target, UUID executerUUID, String reason) {
        String name = Bukkit.getOfflinePlayer(target).getName();
        String executor = Bukkit.getOfflinePlayer(executerUUID).getName();
        Webhook webhook = new Webhook(Config.Plugin.Webhooks.unMuteWebhook);
        webhook.setAvatarUrl("https://r2.e-z.host/d440b58a-ba90-4839-8df6-8bba298cf817/3lwit5nt.png");
        webhook.setUsername("Staff Logs");
        Webhook.EmbedObject embed = new Webhook.EmbedObject()
                .setAuthor("Staff Pardon","","")
                .setTitle("Staff have Unmuted a member")
                .setDescription(
                        Emojis.rightSort + "Username: `" + name + "` " + Emojis.target + "\\n" +
                                Emojis.space + Emojis.arrowRight + "UUID: `" + target + "`\\n"
                )
                .addField("Unmuted By: ", "`" + executor + "` " + Emojis.trustedAdmin, false)
                .addField("Reason: ", reason + " " + Emojis.activity, false)
                .setColor(Color.green)
                .setThumbnail("https://crafatar.com/avatars/" + target + "?size=64&&overlay");
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException e) {
            OgreDupeAlias.log.info(e.toString());
        }
    }
}
