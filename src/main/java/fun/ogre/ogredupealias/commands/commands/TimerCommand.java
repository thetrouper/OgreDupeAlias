package fun.ogre.ogredupealias.commands.commands;

import fun.ogre.ogredupealias.OgreDupeAlias;
import fun.ogre.ogredupealias.commands.CmdExHandler;
import fun.ogre.ogredupealias.commands.LocationParser;
import fun.ogre.ogredupealias.utils.ArrayUtils;
import fun.ogre.ogredupealias.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimerCommand implements TabExecutor {

    public static final Map<String, TimerEntry> timers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            switch (args[0]) {
                case "set" -> {
                    String timer = args[1];

                    if (timers.containsKey(timer)) {
                        sender.sendMessage(Text.ofAll("&cTimer is already running!"));
                        return true;
                    }

                    String tag = args[2];
                    long ticks = Long.parseLong(args[3]) * 20L;
                    LocationParser parser;
                    World world;

                    if (sender instanceof BlockCommandSender source) {
                        parser = new LocationParser(args[4], args[5], args[6], source.getBlock().getLocation());
                        world = source.getBlock().getWorld();
                    }
                    else if (sender instanceof Player source) {
                        parser = new LocationParser(args[4], args[5], args[6], source.getLocation());
                        world = source.getWorld();
                    }
                    else {
                        throw new IllegalArgumentException("Sender should be a command block or player!");
                    }

                    Material type = Material.valueOf(args[7].toUpperCase());
                    timers.put(timer, new TimerEntry(timer, tag, System.currentTimeMillis() + ticks * 50L, () -> {
                        parser.getBlock(world).setType(type);
                        timers.remove(timer);
                    }));
                    Bukkit.getScheduler().runTaskLater(OgreDupeAlias.instance, () -> {
                        if (timers.containsKey(timer)) {
                            timers.get(timer).endAction().run();
                        }
                    }, ticks);

                    sender.sendMessage(Text.ofAll("&bRegistered timer &7" + timers.get(timer)));
                }
                case "stop" -> {
                    String timer = args[1];
                    if (timers.containsKey(timer)) {
                        sender.sendMessage(Text.ofAll("&cRemoved timer &7" + timers.get(timer)));
                        timers.remove(timer);
                    }
                    else {
                        sender.sendMessage(Text.ofAll("&cTimer not found."));
                    }
                }
            }
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex, command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> l = new ArrayList<>();

        switch (args.length) {
            case 1 -> l.addAll(List.of("stop", "set"));
            case 2 -> {
                l.addAll(timers.keySet());
                switch (args[0]) {
                    case "set" -> {
                        if (timers.isEmpty()) {
                            l.add("timer1");
                        }
                    }
                }
            }
            case 3 -> {
                switch (args[0]) {
                    case "set" -> l.add(args[1]);
                }
            }
            case 4 -> {
                switch (args[0]) {
                    case "set" -> l.addAll(List.of("5", "10", "20"));
                }
            }
            case 5 -> {
                switch (args[0]) {
                    case "set" -> l.addAll(List.of("~", "~ ~", "~ ~ ~"));
                }
            }
            case 6 -> {
                switch (args[0]) {
                    case "set" -> l.addAll(List.of("~", "~ ~"));
                }
            }
            case 7 -> {
                switch (args[0]) {
                    case "set" -> l.addAll(List.of("~"));
                }
            }
            case 8 -> {
                switch (args[0]) {
                    case "set" -> l.addAll(ArrayUtils.Constants.MATERIAL_NAMES);
                }
            }
        }

        l.removeIf(s -> !s.toLowerCase().contains(args[args.length - 1].toLowerCase()));
        return l;
    }

    public record TimerEntry(String name, String tag, long end, Runnable endAction) {
        public String getTimeLeft() {
            long diff = end - System.currentTimeMillis();
            return (int)(diff / 1000.0) + " secs";
        }

        @Override
        public String toString() {
            return "{name=%s, tag=%s, end=%s}".formatted(name, tag, end);
        }
    }
}
