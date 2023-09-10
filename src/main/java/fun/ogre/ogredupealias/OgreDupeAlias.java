package fun.ogre.ogredupealias;

import fun.ogre.ogredupealias.commands.commands.*;
import fun.ogre.ogredupealias.data.Config;
import fun.ogre.ogredupealias.events.*;
import fun.ogre.ogredupealias.plugin.custom.forging.CraftingKeys;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItems;
import fun.ogre.ogredupealias.plugin.funitems.AdminUtility;
import fun.ogre.ogredupealias.plugin.funitems.Pickler;
import fun.ogre.ogredupealias.plugin.funitems.PotatoCannon;
import fun.ogre.ogredupealias.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.logging.Logger;

public final class OgreDupeAlias extends JavaPlugin {

    public static final PluginManager pm = Bukkit.getPluginManager();
    public static final BukkitScheduler sch = Bukkit.getScheduler();
    public static final Logger log = Bukkit.getLogger();
    public static OgreDupeAlias instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.init();
        this.initConfig();
        CustomItems.init();
        CraftingKeys.initRecipes();
        this.initTimer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init() {
        // Events
        pm.registerEvents(new ChatEventListener(), this);
        pm.registerEvents(new PlayerEventListener(), this);
        pm.registerEvents(new CommandEventListener(), this);
        pm.registerEvents(new BlockActionListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new InteractionListener(), this);
        pm.registerEvents(new InventoryActionListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new SnowBallListener(), this);
        pm.registerEvents(new TurfWarsEventListener(), this);
        pm.registerEvents(new PotatoCannon(), this);
        pm.registerEvents(new AdminUtility(), this);
        pm.registerEvents(new Pickler(), this);
        pm.registerEvents(new SPBEventListener(), this);
        // event for custom items
        pm.registerEvents(new CustomItems(), this);
        LiteBansEvents.registerEvents();

        // Commands
        getCommand("forcefield").setExecutor(new ForceFieldCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("config").setTabCompleter(new ConfigCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("mutechat").setTabCompleter(new MuteChatCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("staffchat").setTabCompleter(new StaffChatCommand());
        getCommand("commandspy").setExecutor(new CommandSpyCommand());
        getCommand("commandspy").setTabCompleter(new CommandSpyCommand());
        getCommand("recipespy").setExecutor(new RecipeSpyCommand());
        getCommand("recipespy").setTabCompleter(new RecipeSpyCommand());
        getCommand("irepair").setExecutor(new IRepairCommand());
        getCommand("irepair").setTabCompleter(new IRepairCommand());
        getCommand("attackcooldown").setExecutor(new AttackCooldownCommand());
        getCommand("attackcooldown").setTabCompleter(new AttackCooldownCommand());
        getCommand("givecustom").setExecutor(new GiveCustomCommand());
        getCommand("givecustom").setTabCompleter(new GiveCustomCommand());
        getCommand("customitem").setExecutor(new CustomItemCommand());
        getCommand("customitem").setTabCompleter(new CustomItemCommand());
        getCommand("changerank").setExecutor(new ChangeRankCommand());
        getCommand("changerank").setTabCompleter(new ChangeRankCommand());
        getCommand("showdonation").setExecutor(new ShowDonationCommand());
        getCommand("showdonation").setTabCompleter(new ShowDonationCommand());
        getCommand("ranks").setExecutor(new RanksCommand());
        getCommand("ranks").setTabCompleter(new RanksCommand());
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("timer").setTabCompleter(new TimerCommand());
    }

    public void initConfig() {
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
    }

    public void initTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            try {
                if (TimerCommand.timers.isEmpty()) {
                    return;
                }
                for (Map.Entry<String, TimerCommand.TimerEntry> entry : TimerCommand.timers.entrySet()) {
                    TimerCommand.TimerEntry timer = entry.getValue();
                    String msg = "%s: %s".formatted(timer.name(), timer.getTimeLeft());
                    ServerUtils.forEachPlayerRun(player -> player.getScoreboardTags().contains(timer.tag()), player -> ServerUtils.sendActionBar(player, msg));
                }
            }
            catch (ConcurrentModificationException ignore) {}
        }, 0, 20);
    }

    public static String prefix() {
        return Config.Plugin.prefix();
    }
}