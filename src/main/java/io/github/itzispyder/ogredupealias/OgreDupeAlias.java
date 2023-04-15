package io.github.itzispyder.ogredupealias;

import io.github.itzispyder.ogredupealias.commands.commands.ConfigCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public final class OgreDupeAlias extends JavaPlugin {

    public static final PluginManager pm = Bukkit.getPluginManager();
    public static final BukkitScheduler sch = Bukkit.getScheduler();
    public static final Logger log = Bukkit.getLogger();
    public static final String prefix = "";
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

        // Commands
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("config").setTabCompleter(new ConfigCommand());
    }

    public void initConfig() {
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
    }
}
