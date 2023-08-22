package fun.ogre.ogredupealias.events;
import fun.ogre.ogredupealias.OgreDupeAlias;
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
                    UUID target = UUID.fromString(entry.getUuid());
                    UUID executor = UUID.fromString(entry.getExecutorUUID());
                    sendBanLog(target,executor,entry.getReason(),entry.getDurationString());
                }
            }
            @Override
            public void entryRemoved(Entry entry) {
                if (entry.getType().equals("ban")) {
                    /* This will be done soon */
                }
            }
        });
    }
    public static void sendBanLog(UUID target, UUID executerUUID, String reason, String time) {
        String name = Bukkit.getPlayer(target).getName();
        String executor = Bukkit.getPlayer(executerUUID).getName();
        Webhook webhook = new Webhook("https://discord.com/api/webhooks/1110731451982422136/U33AFoT3nVpVo2iTO2kVRuHV4F4PdOtJDp8xsTavkmctU0fDKmW0ckxfGtpKKjobH-Cb");
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
                .setColor(Color.red)
                .setThumbnail("https://crafatar.com/avatars/" + target + "?size=64&&overlay");
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException e) {
            OgreDupeAlias.log.info(e.toString());
        }
    }

}
