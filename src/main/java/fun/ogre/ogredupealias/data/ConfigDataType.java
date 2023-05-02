package fun.ogre.ogredupealias.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigDataType<T> {

    private static final Set<ConfigDataType<?>> values = new HashSet<>();

    public static final ConfigDataType<Boolean> BOOLEAN = register(Boolean.class);
    public static final ConfigDataType<String> STRING = register(String.class);
    public static final ConfigDataType<Integer> INTEGER = register(Integer.class);
    public static final ConfigDataType<Double> DOUBLE = register(Double.class);
    public static final ConfigDataType<Float> FLOAT = register(Float.class);
    public static final ConfigDataType<Long> LONG = register(Long.class);
    public static final ConfigDataType<Short> SHORT = register(Short.class);
    public static final ConfigDataType<Byte> BYTE = register(Byte.class);
    public static final ConfigDataType<Location> LOCATION = register(Location.class);
    public static final ConfigDataType<String[]> STRING_LIST = register(String[].class);
    public static final ConfigDataType<Byte[]> BYTE_LIST = register(Byte[].class);
    public static final ConfigDataType<Integer[]> INTEGER_LIST = register(Integer[].class);
    public static final ConfigDataType<Float[]> FLOAT_LIST = register(Float[].class);
    public static final ConfigDataType<Double[]> DOUBLE_LIST = register(Double[].class);
    public static final ConfigDataType<Null> NULL = register(Null.class);

    private static <T> ConfigDataType<T> register(Class<T> type) {
        ConfigDataType<T> dataType = new ConfigDataType<>(type);
        values.add(dataType);
        return dataType;
    }

    private final Class<T> klass;
    private final String name;

    public ConfigDataType(Class<T> klass) {
        this.klass = klass;
        this.name = klass.getSimpleName().toUpperCase();
    }

    public static Set<ConfigDataType<?>> values() {
        return values;
    }

    public static ConfigDataType<?> valueOf(String name) {
        for (ConfigDataType<?> type : values()) {
            if (name.equalsIgnoreCase(type.name())) return type;
        }
        return null;
    }

    public Class<T> getClassType() {
        return klass;
    }

    public String name() {
        return name;
    }

    public boolean isList() {
        return name.contains("[]");
    }

    public static <T> T parseConfig(String path, ConfigDataType<T> type) {
        if (type.isList()) return (T) Config.get().getList(path).toArray();
        return Config.get().getObject(path,type.getClassType());
    }

    public static <T> T parse(String value, ConfigDataType<T> type) {
        Object returnable;

        switch (type.name()) {
            case "BOOLEAN" -> returnable = Boolean.parseBoolean(value);
            case "STRING" -> returnable = value;
            case "INTEGER" -> returnable = Integer.parseInt(value);
            case "FLOAT" -> returnable = Float.parseFloat(value);
            case "DOUBLE" -> returnable = Double.parseDouble(value);
            case "SHORT" -> returnable = Short.parseShort(value);
            case "LONG" -> returnable = Long.parseLong(value);
            case "BYTE" -> returnable = Byte.parseByte(value);
            case "STRING[]" -> {
                List<String> list = new ArrayList<>();
                for (String s : value.split(",")) list.add(s);
                returnable = list;
            }
            case "BYTE[]" -> {
                List<Byte> list = new ArrayList<>();
                for (String s : value.split(",")) list.add(Byte.parseByte(s));
                returnable = list;
            }
            case "INTEGER[]" -> {
                List<Integer> list = new ArrayList<>();
                for (String s : value.split(",")) list.add(Integer.parseInt(s));
                returnable = list;
            }
            case "FLOAT[]" -> {
                List<Double> list = new ArrayList<>();
                for (String s : value.split(",")) list.add(Double.parseDouble(s));
                returnable = list;
            }
            case "DOUBLE[]" -> {
                List<Float> list = new ArrayList<>();
                for (String s : value.split(",")) list.add(Float.parseFloat(s));
                returnable = list;
            }
            case "LOCATION" -> {
                String[] parts = value.split(",");
                World world = Bukkit.getWorld(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                returnable = new Location(world,x,y,z);
            }
            case "NULL" -> {
                returnable = null;
            }
            default -> returnable = new Object();
        }

        try {
            return (T) returnable;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
