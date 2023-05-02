package fun.ogre.ogredupealias.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class SoundUtils {

    private static final Map<String,Long> cooldown = new HashMap<>();

    /**
     * For the parameters, use the format: "sound-name/sound-volume/sound-pitch"
     * @param listener the player listening for the sounds
     * @param p simplified parameters
     */
    public static void play(Player listener, String p) {
        if (cooldown.containsKey(listener.getName()) && cooldown.get(listener.getName()) > System.currentTimeMillis()) return;
        cooldown.put(listener.getName(),System.currentTimeMillis() + 50);
        try {
            String[] params = p.split("/");
            Sound sound = Sound.valueOf(params[0].toUpperCase());
            float volume = Float.parseFloat(params[1]);
            float pitch = Float.parseFloat(params[2]);
            listener.playSound(listener.getLocation(),sound,volume,pitch);
        }
        catch (Exception ignore) {}
    }
}
