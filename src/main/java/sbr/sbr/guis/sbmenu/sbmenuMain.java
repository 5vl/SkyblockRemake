package sbr.sbr.guis.sbmenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class sbmenuMain {
    @SuppressWarnings("deprecation")
    public static Inventory getGui() {
        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.WHITE + "Skyblock Menu");

        return gui;
    }
}
