package io.github.itzispyder.ogredupealias.data;

import io.github.itzispyder.ogredupealias.utils.Text;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.itzispyder.ogredupealias.OgreDupeAlias.instance;
import static io.github.itzispyder.ogredupealias.OgreDupeAlias.log;

public abstract class Config {

    public static final File DATA_FOLDER = instance.getDataFolder();
    public static final File CONFIG_FILE = new File(DATA_FOLDER,"config.yml");
    public static final String path = "config.";

    public static FileConfiguration get() {
        return YamlConfiguration.loadConfiguration(CONFIG_FILE);
    }

    public static void save(FileConfiguration config) {
        try {
            config.save(CONFIG_FILE);
        }
        catch (Exception ex) {
            log.warning("unable to save plugin config!");
        }
    }

    public static List<String> getSections() {
        ConfigurationSection mainSection = get().getConfigurationSection("");
        Set<String> keys = mainSection != null ? mainSection.getKeys(true) : new HashSet<>();
        List<String> sections = new ArrayList<>(keys);
        return sections;
    }

    public static class Plugin {
        private static final String path = "config.plugin.";
        public static String prefix() {
            return get().getString(path + "prefix") != null ? get().getString(path + "prefix") : Text.color("&7[&5Ogre&3Dupe&7] &r");
        }
    }
}
