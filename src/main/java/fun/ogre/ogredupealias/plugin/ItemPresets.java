package fun.ogre.ogredupealias.plugin;

import fun.ogre.ogredupealias.data.builder.ItemBuilder;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public abstract class ItemPresets {

    private static final ItemFactory factory = Bukkit.getItemFactory();

    public static ItemStack LEGENDARY_HELMET = factory.createItemStack("netherite_helmet{Enchantments:[{id:\"minecraft:aqua_affinity\",lvl:1},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:respiration\",lvl:5},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Chill Shades \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_CHESTPLATE = factory.createItemStack("netherite_chestplate{Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Hawiian Shirt \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_LEGGINGS = factory.createItemStack("netherite_leggings{Damage:0,Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:swift_sneak\",lvl:3},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Jorts \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_BOOTS = factory.createItemStack("netherite_boots{Enchantments:[{id:\"minecraft:depth_strider\",lvl:3},{id:\"minecraft:feather_falling\",lvl:4},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:protection\",lvl:6},{id:\"minecraft:soul_speed\",lvl:3},{id:\"minecraft:thorns\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Flip Flops \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_SWORD = factory.createItemStack("netherite_sword{Damage:0,Enchantments:[{id:\"minecraft:fire_aspect\",lvl:2},{id:\"minecraft:knockback\",lvl:2},{id:\"minecraft:looting\",lvl:3},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:sharpness\",lvl:6},{id:\"minecraft:smite\",lvl:5},{id:\"minecraft:sweeping\",lvl:3},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Pool Noodle \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack LEGENDARY_AXE = factory.createItemStack("netherite_axe{Enchantments:[{id:\"minecraft:efficiency\",lvl:7},{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:sharpness\",lvl:5},{id:\"minecraft:unbreaking\",lvl:10}],display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"... \"},{\"bold\":true,\"italic\":false,\"obfuscated\":false,\"color\":\"dark_aqua\",\"text\":\"Golf Club \"},{\"bold\":false,\"italic\":false,\"obfuscated\":true,\"color\":\"yellow\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"dark_purple\",\"text\":\"...\"},{\"italic\":false,\"obfuscated\":true,\"color\":\"light_purple\",\"text\":\"...\"}],\"text\":\"\"}'}}");

    public static ItemStack SNAD = factory.createItemStack("sand{display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad\"}],\"text\":\"\"}'}}");

    public static ItemStack SNAD_COOKIE = factory.createItemStack("cookie{CustomModelData:1111,display:{Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"mmMmmM Extra Crunchy!\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Snad Cookie\"}],\"text\":\"\"}'}}");

    public static ItemStack SCULK_CATALYST = ItemBuilder.create()
            .material(Material.SCULK_CATALYST)
            .customModelData(1111)
            .build();

    public static ItemStack LEGENDARY_CORE = ItemBuilder.create()
            .material(Material.STRUCTURE_BLOCK)
            .name(Text.color("&cLegendary Core"))
            .lore(Text.color("&8- &7Used for forging"))
            .enchant(Enchantment.LURE,1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack LEGENDARY_INGOT = ItemBuilder.create()
            .material(Material.NETHERITE_INGOT)
            .name(Text.color("&cLegendary Ingot"))
            .lore(Text.color("&8- &7Used for forging"))
            .enchant(Enchantment.LURE,1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack NETSKY_BLADE = ItemBuilder.create()
            .material(Material.NETHERITE_SWORD)
            .name(Text.color("&7//&d&l&oNET&5&l&oSKY&7//"))
            .lore(Text.color("&7- &dLeft: &5Fire Spell"))
            .lore(Text.color("&7- &dRight: &5Fireball"))
            .enchant(Enchantment.FIRE_ASPECT, 1)
            .build();
    public static ItemStack ADMIN_UTILITY = ItemBuilder.create()
            .material(Material.BLAZE_ROD)
            .name(Text.color("&4-&c&l[&b&nAdministrative &3&nUtility&c&l]&4-"))
            .lore(Text.color("&b▪ &3Right-Click:&7 Drag Player"))
            .lore(Text.color("&b▪ &3Sneak Right-Click:&7 Zap Player"))
            .lore(Text.color("&b▪ &3Left-Click:&7 Fire Beam"))
            .lore(Text.color("&b▪ &3Sneak Left-Click:&7 Ring of Fire"))
            .lore(Text.color("&b▪ &3Swap-Hands:&7 Fireball"))
            .lore(Text.color("&b▪ &3Sneak Swap-Hands:&7 Orbital Strike"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack VOID_CHARM = ItemBuilder.create()
            .material(Material.BLACK_STAINED_GLASS)
            .name(Text.color("&0&l-&8&l[&7Void Charm&8&l]&0&l-"))
            .lore(Text.color("&7▪ &4Right-Click:&7 Take Entity"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack DEFENDER = ItemBuilder.create()
            .material(Material.PRISMARINE_SHARD)
            .name(Text.color("&1&l-&9&l[&bDefender&9&l]&1&l-"))
            .lore(Text.color("&1▪ &9Right-Click:&7 Stun"))
            .lore(Text.color("&1▪ &9Left-Click:&7 Shoot"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack LASER_POINTER = ItemBuilder.create()
            .material(Material.LIME_CANDLE)
            .name(Text.color("&2&l-&a&l[&bLaser Pointer&a&l]&2&l-"))
            .lore(Text.color("&2▪ &aRight-Click:&7 Full Beam"))
            .lore(Text.color("&2▪ &aLeft-Click:&7 Select Block"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack SHIELD_GEN = ItemBuilder.create()
            .material(Material.BEACON)
            .name(Text.color("&5&l-&d&l[&bShield Generator&d&l]&5&l-"))
            .lore(Text.color("&1▪ &9/hat:&7 Personal Shield"))
            .lore(Text.color("&1▪ &9Place:&7 Stationary Shield"))
            .build();
    public static ItemStack SNOWCHINEGUN = ItemBuilder.create()
            .material(Material.IRON_HOE)
            .name(Text.color("&f&l&nSNOWCHINEGUN"))
            .lore(Text.color("&b▪ &fRight-Click:&7 BRRRRTTTTT"))
            .lore(Text.color("&b▪ &fLeft-Click:&7 Burst"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack AK47 = ItemBuilder.create()
            .material(Material.ECHO_SHARD)
            .name(Text.color("&7&lAK&8-&647"))
            .lore(Text.color("&6▪ &fRight-Click:&7 Full Auto"))
            .lore(Text.color("&6▪ &fLeft-Click:&7 Single Shot"))
            .customModelData(1111)
            .enchant(Enchantment.VANISHING_CURSE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .build();
    public static ItemStack SPLEEFER = ItemBuilder.create()
            .material(Material.NETHERITE_SHOVEL)
            .name(Text.color("&2&lSpleefer"))
            .lore(Text.color("&7For use at /warp spleef"))
            .enchant(Enchantment.DIG_SPEED, 5)
            .unbreakable(true)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .flag(ItemFlag.HIDE_UNBREAKABLE)
            .build();
    public static ItemStack POTATOCANNON = ItemBuilder.create()
            .material(Material.GOLDEN_HOE)
            .name(Text.color("&6&lPotato Cannon"))
            .lore(Text.color("&e▪ &fLeft-Click:&7 Launch Potatoes"))
            .lore(Text.color("&e▪ &fRight-Click:&7 Load Potato (Max 6)"))
            .unbreakable(true)
            .enchant(Enchantment.DIG_SPEED, 5)
            .flag(ItemFlag.HIDE_ENCHANTS)
            .flag(ItemFlag.HIDE_UNBREAKABLE)
            .build();
    public static ItemStack SPBRifle = ItemBuilder.create()
            .material(Material.IRON_HORSE_ARMOR)
            .name(Text.color("&f[SPB] &eRifle"))
            .lore(Text.color("&e▪ &fRight-Click:&7 Shoot"))
            .customModelData(1111)
            .build();
    public static ItemStack PICKLER = ItemBuilder.create()
            .material(Material.SEA_PICKLE)
            .name(Text.color("&2&lPickler"))
            .lore(Text.color("&2▪ &aRight-Click:&7 Pickle-ify Someone"))
            .customModelData(1111)
            .build();

    public static ItemStack TAZER = ItemBuilder.create()
            .material(Material.NETHERITE_AXE)
            .name(Text.color("&7{&b&lTazer&7}"))
            .unbreakable(true)
            .enchant(Enchantment.LURE, 1)
            .flag(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
            .customModelData(1111)
            .build();

    public static ItemStack LAZER = ItemBuilder.create()
            .material(Material.REDSTONE_TORCH)
            .name("Lazer Pointer")
            .lore(Text.color("&7- A funny gadget!"))
            .customModelData(1111)
            .build();

    public static ItemStack BLANK = ItemBuilder.create()
            .material(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
            .name(" ")
            .build();

    public static ItemStack RANK_KING = ItemBuilder.create()
            .material(Material.MAGENTA_WOOL)
            .name(Text.color("&5&lKing"))
            .lore(Text.color("&7Click this to change"))
            .lore(Text.color("&7your rank to"))
            .lore(Text.color("&7➥ &5&lKING"))
            .build();

    public static ItemStack RANK_QUEEN = ItemBuilder.create()
            .material(Material.RED_WOOL)
            .name(Text.color("&c&lQueen"))
            .lore(Text.color("&7Click this to change"))
            .lore(Text.color("&7your rank to"))
            .lore(Text.color("&7➥ &c&lQUEEN"))
            .build();

    public static ItemStack RANK_ROOK = ItemBuilder.create()
            .material(Material.ORANGE_WOOL)
            .name(Text.color("&6&lRook"))
            .lore(Text.color("&7Click this to change"))
            .lore(Text.color("&7your rank to"))
            .lore(Text.color("&7➥ &6&lROOK"))
            .build();

    public static ItemStack RANK_BISHOP = ItemBuilder.create()
            .material(Material.LIME_WOOL)
            .name(Text.color("&a&lBishop"))
            .lore(Text.color("&7Click this to change"))
            .lore(Text.color("&7your rank to"))
            .lore(Text.color("&7➥ &a&lBISHOP"))
            .build();

    public static ItemStack RANK_KNIGHT = ItemBuilder.create()
            .material(Material.LIGHT_BLUE_WOOL)
            .name(Text.color("&b&lKnight"))
            .lore(Text.color("&7Click this to change"))
            .lore(Text.color("&7your rank to"))
            .lore(Text.color("&7➥ &b&lKNIGHT"))
            .build();

    public static ItemStack RANK_CONFIRM_BUTTON = ItemBuilder.create()
            .material(Material.BARRIER)
            .name(Text.color("&eConfirm Rank?"))
            .lore(Text.color("&7Left-Click to select rank"))
            .lore(Text.color("&7Right-Click to cancel"))
            .build();

    public static ItemStack CHANGE_TAG_BUTTON = ItemBuilder.create()
            .material(Material.NAME_TAG)
            .name(Text.color("&bChange Tag"))
            .lore(Text.color("&7Click to change your custom tag"))
            .build();
}
