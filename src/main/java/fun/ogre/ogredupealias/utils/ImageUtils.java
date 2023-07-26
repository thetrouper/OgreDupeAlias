package fun.ogre.ogredupealias.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    public static List<String> imageToList(String URL) {
        try {
            URL url = new URL(URL);
            BufferedImage img = ImageIO.read(url);
            List<String> lines = new ArrayList<>();
            String message = "";
            int width = 0;

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int rgb = img.getRGB(x, y);
                    Color color = Color.fromARGB(rgb);
                    String hex = color.toString().replaceAll("Color:\\[argb0xFF", "").replaceAll("\\]", "");
                    ChatColor chat = ChatColor.of("#" + hex);
                    message += chat + "█";

                    if ((width++) >= 7) {
                        lines.add(message);
                        message = "";
                        width = 0;
                    }
                }
            }
            return lines;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
