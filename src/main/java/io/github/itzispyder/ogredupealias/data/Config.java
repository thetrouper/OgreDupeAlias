package io.github.itzispyder.ogredupealias.data;

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

    public static FileConfiguration get() {
        return YamlConfiguration.loadConfiguration(CONFIG_FILE);
    }

    public static void save() {
        try {
            get().save(CONFIG_FILE);
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
}
