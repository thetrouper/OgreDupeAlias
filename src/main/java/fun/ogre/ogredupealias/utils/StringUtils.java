package fun.ogre.ogredupealias.utils;

public final class StringUtils {

    public static String capitalize(String s) {
        if (s.length() == 1) return s.toUpperCase();
        s = s.toLowerCase();
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
    }

    public static String capitalizeWords(String s) {
        s = s.replaceAll("[_-]"," ");
        String[] sArray = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : sArray) sb.append(capitalize(str)).append(" ");
        return sb.toString().trim();
    }

    public static String fromLeetString(String s) {
        return s.replaceAll("0", "o")
                .replaceAll("1", "i")
                .replaceAll("3", "e")
                .replaceAll("4", "a")
                .replaceAll("5", "s")
                .replaceAll("7", "l")
                .replaceAll("\\$", "s")
                .replaceAll("!", "i")
                .replaceAll("\\+", "t")
                .replaceAll("#", "h")
                .replaceAll("@", "a")
                .replaceAll("<", "c")
                .replaceAll("v", "u");
    }
}
