package io.github.itzispyder.ogredupealias;

import io.github.itzispyder.ogredupealias.commands.commands.ConfigCommand;
import io.github.itzispyder.ogredupealias.commands.commands.MuteChatCommand;
import io.github.itzispyder.ogredupealias.commands.commands.StaffChatCommand;
import io.github.itzispyder.ogredupealias.data.Config;
import io.github.itzispyder.ogredupealias.events.ChatEventListener;
import io.github.itzispyder.ogredupealias.events.PlayerEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init() {
        // Events
        pm.registerEvents(new ChatEventListener(),this);
        pm.registerEvents(new PlayerEventListener(),this);

        // Commands
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("config").setTabCompleter(new ConfigCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("mutechat").setTabCompleter(new MuteChatCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("staffchat").setTabCompleter(new StaffChatCommand());
    }

    public void initConfig() {
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
    }

    public static String prefix() {
        return Config.Plugin.prefix();
    }
}
