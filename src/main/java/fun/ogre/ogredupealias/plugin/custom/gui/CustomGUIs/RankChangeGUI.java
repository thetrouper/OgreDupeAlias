package fun.ogre.ogredupealias.plugin.custom.gui.CustomGUIs;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.gui.CustomGui;
import fun.ogre.ogredupealias.utils.ServerUtils;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class RankChangeGUI {
    public static Map<UUID,Boolean> choosingTagMap = new HashMap<>();
    public static final CustomGui RANKS = CustomGui.create()
            .title(Text.color("&8| &b&lRank Customization &8|"))
            .size(27)
            .onDefine(inv -> {
                ItemStack fill = ItemPresets.BLANK;
                while (inv.firstEmpty() != -1) {
                    inv.setItem(inv.firstEmpty(), fill);
                }
            })
            .defineMain(event -> event.setCancelled(true))
            .define(16, ItemPresets.CHANGE_TAG_BUTTON, event -> {
                Player p = (Player) event.getWhoClicked();
                if (event.getCurrentItem().getType() == Material.NAME_TAG && p.hasPermission("oda.customtag.change")) {
                    choosingTagMap.put(p.getUniqueId(), true);
                    p.closeInventory();
                    p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                    p.sendMessage(Text.ofAll("&7Please type your desired tag in chat, or type &b#exit &7to cancel"));
                } else if (!p.hasPermission("oda.customtag.change")) {
                    event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                    p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1, 1F);
                } else if (event.getClick() == ClickType.LEFT) {
                    String name = p.getName();
                    switch (event.getCurrentItem().getType()) {
                        case MAGENTA_WOOL -> {
                            ServerUtils.dispatch("lp user " + name + " meta removeprefix 1000");
                            ServerUtils.dispatch("lp user " + name + " meta addprefix 1000 \"&5&lKING &d&o\"");
                            p.sendMessage(Text.ofAll("&7Set your rank to &5&lKING&7."));
                        }
                        case RED_WOOL -> {
                            ServerUtils.dispatch("lp user " + name + " meta removeprefix 1000");
                            ServerUtils.dispatch("lp user " + name + " meta addprefix 1000 \"&4&lQUEEN &c&l\"");
                            p.sendMessage(Text.ofAll("&7Set your rank to &4&lQUEEN&7."));
                        }
                        case ORANGE_WOOL -> {
                            ServerUtils.dispatch("lp user " + name + " meta removeprefix 1000");
                            ServerUtils.dispatch("lp user " + name + " meta addprefix 1000 \"&6Rook &e\"");
                            p.sendMessage(Text.ofAll("&7Set your rank to &6Rook&7."));
                        }
                        case LIME_WOOL -> {
                            ServerUtils.dispatch("lp user " + name + " meta removeprefix 1000");
                            ServerUtils.dispatch("lp user " + name + " meta addprefix 1000 \"&2Bishop &a\"");
                            p.sendMessage(Text.ofAll("&7Set your rank to &2Bishop&7."));
                        }
                        case LIGHT_BLUE_WOOL -> {
                            ServerUtils.dispatch("lp user " + name + " meta removeprefix 1000");
                            ServerUtils.dispatch("lp user " + name + " meta addprefix 1000 \"&9Knight &b\"");
                            p.sendMessage(Text.ofAll("&7Set your rank to &9Knight&7."));
                        }
                    }
                    event.getWhoClicked().closeInventory();
                } else {
                    event.getClickedInventory().setItem(16, ItemPresets.CHANGE_TAG_BUTTON);
                    p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1, 1F);
                }
            })
            .define(10, ItemPresets.RANK_KING, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_BEACON_POWER_SELECT, 1, 2F);
                event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(11, ItemPresets.RANK_QUEEN, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_BEACON_POWER_SELECT, 1, 1.9F);
                event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(12, ItemPresets.RANK_ROOK, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_BEACON_POWER_SELECT, 1, 1.8F);
                event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(13, ItemPresets.RANK_BISHOP, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_BEACON_POWER_SELECT, 1, 1.7F);
                event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .define(14, ItemPresets.RANK_KNIGHT, event -> {
                Player p = (Player)event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_BEACON_POWER_SELECT, 1, 1.6F);
                event.getClickedInventory().setItem(16, ItemPresets.RANK_CONFIRM_BUTTON);
                event.getClickedInventory().getItem(16).setType(event.getCurrentItem().getType());
            })
            .build();

    public static void chooseTag(Player p, String tag) {
        choosingTagMap.put(p.getUniqueId(),false);
        ServerUtils.dispatch("lp user " + p.getName() + " meta clear");
        ServerUtils.dispatch("lp user " + p.getName() + " meta addsuffix 1000 \" " + tag + "\"");
        p.sendMessage(Text.ofAll("&7You have set your tag to " + tag));
    }

}
