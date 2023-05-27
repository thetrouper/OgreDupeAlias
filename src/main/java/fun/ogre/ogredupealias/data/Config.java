package fun.ogre.ogredupealias.data;

import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

import static fun.ogre.ogredupealias.OgreDupeAlias.instance;
import static fun.ogre.ogredupealias.OgreDupeAlias.log;

public abstract class Config {

    public static final File DATA_FOLDER = instance.getDataFolder();
    public static final File CONFIG_FILE = new File(DATA_FOLDER,"config.yml");
    public static final String path = "";

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
        private static final String path = "plugin.";
        public static String prefix() {
            return Text.color(get().getString(path + "prefix", "&7[&5Ogre&3Dupe&7] &r"));
        }
    }

    public static class Chat {
        private static final String path = "chat.";

        public static class AntiSwear {
            private static final String path = Chat.path + "anti-swear.";
            public static boolean enabled() {
                return get().getBoolean(path + "enabled", false);
            }
            public static List<String> whitelist() {
                return get().getStringList(path + "whitelist");
            }
            public static List<String> blacklist() {
                return get().getStringList(path + "blacklist");
            }
            public static Map<String, String> leetPatterns() {
                Map<String, String> dictionary = new HashMap<>();
                ConfigurationSection section = get().getConfigurationSection(path + "leet-patterns");

                if (section == null) return dictionary;
                for (String key : section.getKeys(false)) {
                    dictionary.put(key, section.getString(key));
                }
                return dictionary;
            }
        }

        public static class AntiSpam {
            private static final String path = Chat.path + "anti-spam.";
            public static boolean enabled() {
                return get().getBoolean(path + "enabled", false);
            }
            public static double cooldown() {
                return get().getDouble(path + "cooldown", 0.0);
            }
        }

        public static class AntiRepeat {
            private static final String path = Chat.path + "anti-repeat.";
            public static boolean enabled() {
                return get().getBoolean(path + "enabled", false);
            }
        }

        public static class AntiUnicode {
            private static final String path = Chat.path + "anti-unicode.";
            public static boolean enabled() {
                return get().getBoolean(path + "enabled", false);
            }
        }
    }

    public static class Player {
        private static final String path = "player.";
        public static String firstJoinMessage() {
            return Text.color(get().getString(path + "first-join-message","&e%player% has joined the game for their first time!"));
        }
        public static String joinMessage() {
            return Text.color(get().getString(path + "join-message","&e%player% has joined the game"));
        }
        public static String quitMessage() {
            return Text.color(get().getString(path + "quit-message","&e%player% has left the game"));
        }
        public static List<String> onJoin() {
            return get().getStringList(path + "on-join");
        }
    }

    public static class Server {
        private static final String path = "server.";
        public static List<String> commandSpyBlacklist() {
            return get().getStringList(path + "command-spy-blacklist");
        }
    }
}
