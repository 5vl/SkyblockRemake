package sbr.sbr.utils;

import org.bukkit.ChatColor;

public class chatcolors {
    public static String color(String s) {
        s = s.replaceAll("<black>", "&0");
        s = s.replaceAll("<dark blue>", "&1");
        s = s.replaceAll("<dark green>", "&2");
        s = s.replaceAll("<dark acqua>", "&3");
        s = s.replaceAll("<dark red>", "&4");
        s = s.replaceAll("<dark purple>", "&5");
        s = s.replaceAll("<gold>", "&6");
        s = s.replaceAll("<gray>", "&7");
        s = s.replaceAll("<dark gray>", "&8");
        s = s.replaceAll("<blue>", "&9");
        s = s.replaceAll("<green>", "&a");
        s = s.replaceAll("<aqua>", "&b");
        s = s.replaceAll("<red>", "&c");
        s = s.replaceAll("<light purple>", "&d");
        s = s.replaceAll("<yellow>", "&e");
        s = s.replaceAll("<white>", "&f");
        s = s.replaceAll("<reset>", "&r");
        s = s.replaceAll("<bold>", "&l");
        s = s.replaceAll("<random>", "&k");
        s = s.replaceAll("<italic>", "&o");
        s = s.replaceAll("<strike>", "&m");
        s = s.replaceAll("<underline>", "&n");
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}