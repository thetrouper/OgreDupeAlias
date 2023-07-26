package fun.ogre.ogredupealias.plugin.custom.gui.CustomGUIs;

import fun.ogre.ogredupealias.data.builder.ItemBuilder;
import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.gui.CustomGui;
import fun.ogre.ogredupealias.utils.Text;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public final class StoreGUI {
    public static final ItemStack knightItem = new ItemStack(ItemBuilder.create()
            .material(Material.LIGHT_BLUE_WOOL)
            .name(Text.color("&b&lKnight &7(Lifetime)"))
            .lore(" ")
            .lore(Text.color("&1&l➥ &9/fly"))
            .lore(Text.color("&1&l➥ &9/chatcolor"))
            .lore(Text.color("&1&l➥ &9/tpahere"))
            .lore(Text.color("&1&l➥ &9Higher /dupe limits"))
            .lore(Text.color("&1&l➥ &92 Vaults (/pv)"))
            .lore(Text.color("&1&l➥ &92 Homes (/sethome)"))
            .lore(Text.color("&1&l➥ &92 Auction Listings (/ah)"))
            .lore(Text.color("&8Price: &2$2.00"))
            .build());
    public static final ItemStack bishopItem = new ItemStack(ItemBuilder.create()
            .material(Material.LIME_WOOL)
            .name(Text.color("&a&lBishop &7(Lifetime)"))
            .lore(" ")
            .lore(Text.color("&2&l➥ &aEverything from &b&lKnight"))
            .lore(Text.color("&2&l➥ &a/smithingtable"))
            .lore(Text.color("&2&l➥ &aDupe Shulkers"))
            .lore(Text.color("&2&l➥ &a5 Auction Listings (/ah)"))
            .lore(Text.color("&2&l➥ &a4 Homes (/sethome)"))
            .lore(Text.color("&2&l➥ &a5 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$4.00"))
            .build());
    public static final ItemStack rookItem = new ItemStack(ItemBuilder.create()
            .material(Material.ORANGE_WOOL)
            .name(Text.color("&6&lRook &7(Lifetime)"))
            .lore(" ")
            .lore(Text.color("&6&l➥ &eEverything from &a&lBishop"))
            .lore(Text.color("&6&l➥ &e/anvil"))
            .lore(Text.color("&6&l➥ &e/hat"))
            .lore(Text.color("&6&l➥ &e/cartographytable"))
            .lore(Text.color("&6&l➥ &e/smithingtable"))
            .lore(Text.color("&6&l➥ &e/craft"))
            .lore(Text.color("&6&l➥ &e/kittycannon"))
            .lore(Text.color("&6&l➥ &e/lounge"))
            .lore(Text.color("&6&l➥ &eDupe stacks (/dupe 1 stack)"))
            .lore(Text.color("&6&l➥ &e10 Auction Listings (/ah)"))
            .lore(Text.color("&6&l➥ &e8 Homes (/sethome)"))
            .lore(Text.color("&6&l➥ &e10 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$8.00 &4* MOST VALUE *"))
            .build());
    public static final ItemStack queenItem = new ItemStack(ItemBuilder.create()
            .material(Material.RED_WOOL)
            .name(Text.color("&c&lQueen &7(Lifetime)"))
            .lore(" ")
            .lore(Text.color("&4&l➥ &6Everything from &6&lRook"))
            .lore(Text.color("&4&l➥ &6/eglow"))
            .lore(Text.color("&4&l➥ &6/itemname"))
            .lore(Text.color("&4&l➥ &6/rtp biome"))
            .lore(Text.color("&4&l➥ &6/chatcolor gui"))
            .lore(Text.color("&4&l➥ &6/nick"))
            .lore(Text.color("&4&l➥ &6/beezooka"))
            .lore(Text.color("&4&l➥ &6/loom"))
            .lore(Text.color("&4&l➥ &6/emotes"))
            .lore(Text.color("&4&l➥ &6Dupe stacks (/dupe 1 stack)"))
            .lore(Text.color("&4&l➥ &615 Auction Listings (/ah)"))
            .lore(Text.color("&4&l➥ &610 Homes (/sethome)"))
            .lore(Text.color("&4&l➥ &625 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$16.00"))
            .build());
    public static final ItemStack lifetimeKingItem = new ItemStack(ItemBuilder.create()
            .material(Material.NETHERITE_BLOCK)
            .name(Text.color("&b&k. &bLIFETIME &b&k. &5&lKING"))
            .lore(Text.color("&4&l➥ &dEverything from &c&lQueen"))
            .lore(Text.color("&4&l➥ &d/back"))
            .lore(Text.color("&4&l➥ &d/changerank"))
            .lore(Text.color("&4&l➥ &d/rankhearts"))
            .lore(Text.color("&4&l➥ &d15 Auction Listings (/ah)"))
            .lore(Text.color("&4&l➥ &d15 Homes (/sethome)"))
            .lore(Text.color("&4&l➥ &d54 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$40.00"))
            .build());
    public static final ItemStack quarterlyKingItem = new ItemStack(ItemBuilder.create()
            .material(Material.ANCIENT_DEBRIS)
            .name(Text.color("&bQuarterly &5&lKING"))
            .lore(Text.color("&4&l➥ &dEverything from &c&lQueen"))
            .lore(Text.color("&4&l➥ &d/back"))
            .lore(Text.color("&4&l➥ &d/changerank"))
            .lore(Text.color("&4&l➥ &d/rankhearts"))
            .lore(Text.color("&4&l➥ &d15 Auction Listings (/ah)"))
            .lore(Text.color("&4&l➥ &d15 Homes (/sethome)"))
            .lore(Text.color("&4&l➥ &d54 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$15.00&8/&7quarter"))
            .build());
    public static final ItemStack monthlyKingItem = new ItemStack(ItemBuilder.create()
            .material(Material.NETHERITE_SCRAP)
            .name(Text.color("&cMonthly &5&lKING"))
            .lore(Text.color("&4&l➥ &dEverything from &c&lQueen"))
            .lore(Text.color("&4&l➥ &d/back"))
            .lore(Text.color("&4&l➥ &d/changerank"))
            .lore(Text.color("&4&l➥ &d/rankhearts"))
            .lore(Text.color("&4&l➥ &d15 Auction Listings (/ah)"))
            .lore(Text.color("&4&l➥ &d15 Homes (/sethome)"))
            .lore(Text.color("&4&l➥ &d54 Vaults (/pv)"))
            .lore(Text.color("&8Price: &2$8.00&8/&7month"))
            .build());
    public static final ItemStack donatorTag = new ItemStack(ItemBuilder.create()
            .material(Material.NAME_TAG)
            .name(Text.color("&5&l[&dDONATOR&5&l] &7Tag"))
            .lore(Text.color("&&Price: &2$1.00"))
            .build());
    public static final ItemStack customTag = new ItemStack(ItemBuilder.create()
            .material(Material.NAME_TAG)
            .name(Text.color("&9&l[&bCUSTOM&9&l] &7Tag"))
            .lore(Text.color("&&Price: &2$5.00"))
            .build());
    public static final ItemStack nextToKing = new ItemStack(ItemBuilder.create()
            .material(Material.ARROW)
            .name(Text.color("&fNext Page >"))
            .lore(Text.color("&7➥ &fKing Upgrade"))
            .build());
    public static final ItemStack nextToTags = new ItemStack(ItemBuilder.create()
            .material(Material.ARROW)
            .name(Text.color("&fNext Page >"))
            .lore(Text.color("&7➥ &fTags"))
            .build());
    public static final ItemStack prevToKing = new ItemStack(ItemBuilder.create()
            .material(Material.ARROW)
            .name(Text.color("&f< Previous Page"))
            .lore(Text.color("&fKing Upgrade &7&l⮨"))
            .build());
    public static final ItemStack prevToRanks = new ItemStack(ItemBuilder.create()
            .material(Material.ARROW)
            .name(Text.color("&f< Previous Page"))
            .lore(Text.color("&fRanks &7&l⮨"))
            .build());

    public static final CustomGui STORE_TAGS = CustomGui.create()
            .title(Text.color("&8| &b&lTAGS &8|"))
            .size(27)
            .onDefine(inv -> {
                ItemStack fill = ItemPresets.BLANK;
                while (inv.firstEmpty() != -1) {
                    inv.setItem(inv.firstEmpty(), fill);
                }
            })
            .defineMain(event -> {
                event.setCancelled(true);
            })
            .define(12,donatorTag, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &b&lDonator &7tag at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(14,customTag, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &b&lCustom &7tag at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(18,prevToKing,event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1F);
                p.openInventory(StoreGUI.STORE_KING.getInventory());
            })
            .build();
    public static final CustomGui STORE_KING = CustomGui.create()
            .title(Text.color("&8| &b&lKING UPGRADE &8|"))
            .size(27)
            .onDefine(inv -> {
                ItemStack fill = ItemPresets.BLANK;
                while (inv.firstEmpty() != -1) {
                    inv.setItem(inv.firstEmpty(), fill);
                }
            })
            .defineMain(event -> {
                event.setCancelled(true);
            })
            .define(11,lifetimeKingItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &b&lKING &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(13,quarterlyKingItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &b&lKING &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(15,monthlyKingItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &b&lKING &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(18,prevToRanks,event -> {
                Player p = (Player) event.getWhoClicked();
                p.openInventory(StoreGUI.STORE_RANKS.getInventory());
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1F);
            })
            .define(26,nextToTags,event -> {
                Player p = (Player) event.getWhoClicked();
                p.openInventory(StoreGUI.STORE_TAGS.getInventory());
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1F);
            })
            .build();
    public static final CustomGui STORE_RANKS = CustomGui.create()
            .title(Text.color("&8| &b&lRANKS &8|"))
            .size(27)
            .onDefine(inv -> {
                ItemStack fill = ItemPresets.BLANK;
                while (inv.firstEmpty() != -1) {
                    inv.setItem(inv.firstEmpty(), fill);
                }
            })
            .defineMain(event -> {
                event.setCancelled(true);
            })
            .define(10, knightItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &9&lKnight &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(12, bishopItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &a&lBishop &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(14, rookItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &6&lRook &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(16, queenItem, event -> {
                Player p = (Player) event.getWhoClicked();
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1F);
                p.closeInventory();
                TextComponent message = new TextComponent();
                message.setText(Text.color("\n &7You can buy &c&lQueen &7Rank at&b https://store.ogre.fun \n"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.ogre.fun"));
                p.spigot().sendMessage(message);
            })
            .define(26, nextToKing, event -> {
                Player p = (Player) event.getWhoClicked();
                p.openInventory(StoreGUI.STORE_KING.getInventory());
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1F);
            })
            .build();


}
